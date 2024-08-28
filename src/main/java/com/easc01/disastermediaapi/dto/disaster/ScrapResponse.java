package com.easc01.disastermediaapi.dto.disaster;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScrapResponse {
    private String message;
    private String timeTaken;
}
