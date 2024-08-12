package com.easc01.disastermediaapi.dto.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class YouTubeSearchListResponseDTO {
    private String kind;
    private String etag;
    private String regionCode;
    private PageInfo pageInfo;
    private List<SearchResult> items;

    @Getter
    @Setter
    @Builder
    public static class PageInfo {
        private int totalResults;
        private int resultsPerPage;
    }

    @Getter
    @Setter
    @Builder
    public static class SearchResult {
        private String kind;
        private String etag;
        private VideoId id;
        private Snippet snippet;

        @Getter
        @Setter
        @Builder
        public static class VideoId {
            private String kind;
            private String videoId;
        }

        @Getter
        @Setter
        @Builder
        public static class Snippet {
            private String publishedAt;
            private String channelId;
            private String title;
            private String description;
            private Thumbnails thumbnails;
            private String channelTitle;
            private String liveBroadcastContent;
            private String publishTime;

            @Getter
            @Setter
            @Builder
            public static class Thumbnails {
                @JsonProperty("default")
                private VideoThumbnailSize defaultThumbnail;
                private VideoThumbnailSize medium;
                private VideoThumbnailSize high;

                @Getter
                @Setter
                @Builder
                public static class VideoThumbnailSize {
                    private String url;
                    private int width;
                    private int height;
                }
            }
        }
    }
}
