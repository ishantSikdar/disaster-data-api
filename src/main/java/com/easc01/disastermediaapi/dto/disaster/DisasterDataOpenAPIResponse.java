package com.easc01.disastermediaapi.dto.disaster;

import com.easc01.disastermediaapi.model.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
public class DisasterDataOpenAPIResponse {
    private String disasterId;
    private String title;
    private String summary;
    private Set<Video> videos;
    private String incidentLocation;
    private String incidentType;
    private Date infoPublishedDate;
}