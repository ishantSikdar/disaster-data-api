package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.disaster.DisasterDataOpenAPIResponse;

import java.util.List;

public interface DisasterService {

    List<DisasterDataOpenAPIResponse> getProcessedDisasterDataByParams(
            String incidentType,
            String incidentLocation,
            String publishedBefore,
            String publishedAfter
    );

}
