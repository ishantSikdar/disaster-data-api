package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;

public interface YouTubeService {

    /**
     * Fetches Recent Natural disasters tweets and posts from YouTube
     * @return
     */
    YouTubeSearchListResponseDTO fetchRecentNaturalDisastersPosts();
}
