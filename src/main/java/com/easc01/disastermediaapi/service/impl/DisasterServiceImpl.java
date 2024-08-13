package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.DisasterDataOpenAPIResponse;
import com.easc01.disastermediaapi.service.DisasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterServiceImpl implements DisasterService {
    @Override
    public List<DisasterDataOpenAPIResponse> getProcessedDisasterDataByParams(
            String incidentType,
            String incidentLocation,
            String tags,
            String publishedBefore,
            String publishedAfter
    ) {
        return List.of();
    }
}
