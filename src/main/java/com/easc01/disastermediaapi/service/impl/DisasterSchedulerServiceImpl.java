package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.ProcessedDisasterData;
import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;
import com.easc01.disastermediaapi.model.Post;
import com.easc01.disastermediaapi.model.Video;
import com.easc01.disastermediaapi.service.DisasterSchedulerService;
import com.easc01.disastermediaapi.service.GenerativeAIService;
import com.easc01.disastermediaapi.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterSchedulerServiceImpl implements DisasterSchedulerService {

    private final YouTubeService youTubeService;
    private final GenerativeAIService generativeAIService;

    public void collectDisastersFromYouTube() {
        try {
            // Step 1: collection
            YouTubeSearchListResponseDTO youtubeDisasterData = youTubeService.fetchRecentNaturalDisastersPosts();
            // sub step 1: serialize result
            List<RawDisasterData> rawDisasterData = serializeYouTubeResults(youtubeDisasterData.getItems());

            // Step 2: AI procession
            List<AIProcessedDisaster> aiProcessedData = generativeAIService.processRawDisasterData(rawDisasterData);
            // sub step 2: map AI processed data with original data
            List<ProcessedDisasterData> mappedDisasterData = mapAIProcessedDataWithOriginal(youtubeDisasterData.getItems(), aiProcessedData);

            // Step 3: Save mapped and processed disasters
            boolean success = saveProcessAndMappedDisasters(mappedDisasterData);
            log.info("Status of saving last 15 minutes of disasters from youtube" + success);

        } catch (Exception e) {
            log.info("Something went wrong collecting disasters from youtube, {}", e.getMessage());
            throw e;

        }

    }

    private List<RawDisasterData> serializeYouTubeResults(List<YouTubeSearchListResponseDTO.SearchResult> disasterData) {
        return disasterData.parallelStream()
                .map((disaster) -> RawDisasterData.builder()
                        .id(disaster.getId().getVideoId())
                        .title(disaster.getSnippet().getTitle())
                        .description(disaster.getSnippet().getDescription())
                        .build())
                .toList();
    }

    private List<ProcessedDisasterData> mapAIProcessedDataWithOriginal(List<YouTubeSearchListResponseDTO.SearchResult> youtubeDisasterData, List<AIProcessedDisaster> aiProcessedData) {
        return youtubeDisasterData.parallelStream()
                .map((disasterItem) -> {
                    Optional<AIProcessedDisaster> currentAIProcessedData = aiProcessedData.stream()
                            .filter(data -> Objects.equals(data.getId(), disasterItem.getId().getVideoId()))
                            .findFirst();

                    if (currentAIProcessedData.isEmpty()) {
                        return null;

                    } else {
                        AIProcessedDisaster aiProcessedDisaster = currentAIProcessedData.get();
                        Set<Video> videos = Set.of(
                                Video.builder()
                                        .userId(disasterItem.getSnippet().getChannelId())
                                        .url("https://www.youtube.com/watch?v=" + disasterItem.getId().getVideoId())
                                        .publishedDate(Instant.parse(disasterItem.getSnippet().getPublishedAt()))
                                        .build()
                        );

                        Set<Post> posts = Set.of(
                                Post.builder()
                                        .userId(disasterItem.getSnippet().getChannelId())
                                        .content(disasterItem.getSnippet().getDescription())
                                        .publishedDate(Instant.parse(disasterItem.getSnippet().getPublishedAt()))
                                        .build()
                        );

                        return ProcessedDisasterData.builder()
                                .id(disasterItem.getId().getVideoId())
                                .recordId(aiProcessedDisaster.getDisasterId())
                                .title(aiProcessedDisaster.getTitle())
                                .summary(aiProcessedDisaster.getSummary())
                                .incidentLocation(aiProcessedDisaster.getLocation())
                                .incidentType(aiProcessedDisaster.getIncidentType())
                                .pictures(Set.of())
                                .videos(videos)
                                .posts(posts)
                                .build();
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }


    private boolean saveProcessAndMappedDisasters(List<ProcessedDisasterData> mappedDisasterData) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
