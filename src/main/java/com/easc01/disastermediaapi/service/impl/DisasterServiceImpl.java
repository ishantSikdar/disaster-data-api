package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.DisasterDataOpenAPIResponse;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.DisasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterServiceImpl implements DisasterService {

    private final DisasterRepository disasterRepository;

    @Override
    public List<DisasterDataOpenAPIResponse> getProcessedDisasterDataByParams(
            String incidentType,
            String incidentLocation,
            String publishedBefore,
            String publishedAfter
    ) {
        if (incidentType != null && incidentType.trim().isEmpty()) {
            incidentType = null;
        }

        if (incidentLocation != null && incidentLocation.trim().isEmpty()) {
            incidentLocation = null;
        }

        List<Disaster> disasters = disasterRepository.findDisastersByCriteria(
                incidentType,
                incidentLocation,
                Instant.parse(publishedAfter),
                Instant.parse(publishedBefore)
        );

        return disasters.parallelStream()
                .map((disaster -> DisasterDataOpenAPIResponse.builder()
                        .disasterId(disaster.getRecordId())
                        .title(disaster.getTitle())
                        .summary(disaster.getSummary())
                        .videos(disaster.getVideos())
                        .incidentLocation(disaster.getIncidentLocation())
                        .incidentType(disaster.getIncidentType())
                        .infoPublishedDate(Date.from(disaster.getCreatedAt()))
                        .build()))
                .toList();
    }
}
