package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.dto.ApiResponse;
import com.easc01.disastermediaapi.dto.disaster.DisasterDataOpenAPIResponse;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.model.Video;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.DisasterService;
import com.easc01.disastermediaapi.util.IDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = AppConstant.DISASTER)
public class DisasterController {

    private final DisasterService disasterService;

    @GetMapping(value = AppConstant.ALL)
    public ResponseEntity<ApiResponse<List<DisasterDataOpenAPIResponse>>> getAllDisasterData(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "publishedBefore") String publishedBefore,
            @RequestParam(name = "publishedAfter") String publishedAfter
    ) {
        ApiResponse<List<DisasterDataOpenAPIResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(
                    disasterService.getProcessedDisasterDataByCriteria(
                            type,
                            location,
                            publishedBefore.isBlank() ? String.valueOf(Instant.now()) : publishedBefore,
                            publishedAfter.isBlank() ? String.valueOf(Instant.EPOCH) : publishedAfter
                    )
            );
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setMessage("Fetched Disaster Data");

        } catch (Exception e) {
            log.error("Failed to fetch disaster data");
            apiResponse.setMessage("Failed to fetch disaster data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping(value = AppConstant.RECENT)
    public ResponseEntity<ApiResponse<List<DisasterDataOpenAPIResponse>>> getRecentDisasterData(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "location") String location
    ) {
        ApiResponse<List<DisasterDataOpenAPIResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(
                    disasterService.getProcessedDisasterDataByCriteria(
                            type,
                            location,
                            String.valueOf(Instant.now()),
                            String.valueOf(Instant.now().minus(15, ChronoUnit.MINUTES))
                    )
            );
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setMessage("Fetched Recent Disaster Data");

        } catch (Exception e) {
            log.error("Failed to fetch recent disaster data");
            apiResponse.setMessage("Failed to fetch recent disaster data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


}