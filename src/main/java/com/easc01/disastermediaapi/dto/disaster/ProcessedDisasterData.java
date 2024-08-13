package com.easc01.disastermediaapi.dto.disaster;

import com.easc01.disastermediaapi.model.Picture;
import com.easc01.disastermediaapi.model.Post;
import com.easc01.disastermediaapi.model.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
public class ProcessedDisasterData {
    private String disasterId;
    private String title;
    private String summary;
    private Set<Post> posts;
    private Set<Video> videos;
    private Set<Picture> pictures;
    private String incidentLocation;
    private Date infoPublishedDate;
}