package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.disaster.ProcessedDisasterData;

import java.util.List;

public interface DisasterService {

    List<ProcessedDisasterData> getProcessedDisasterDataByParams(
            String incidentType,
            String incidentLocation,
            String tags,
            String publishedBefore,
            String publishedAfter
    );

}
