package com.easc01.disastermediaapi.scheduler;


import com.easc01.disastermediaapi.service.DisasterSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DisasterScheduler {

    private final DisasterSchedulerService disasterSchedulerService;

    @Scheduled(initialDelay = 5000, fixedDelay = 1200000)
    public void fetchAndSaveDiastersFromYouTube() {
        log.info("Starting new diaster scraping process...");
        disasterSchedulerService.collectAndSaveDisastersFromYouTube();
    }

}