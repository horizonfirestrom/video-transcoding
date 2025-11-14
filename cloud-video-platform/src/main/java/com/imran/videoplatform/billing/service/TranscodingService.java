package com.imran.videoplatform.video.service;

import com.imran.videoplatform.billing.service.BillingService;
import com.imran.videoplatform.video.model.Video;
import com.imran.videoplatform.video.service.VideoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TranscodingService {

    private static final Logger log = LoggerFactory.getLogger(TranscodingService.class);

    private final VideoService videoService;
    private final BillingService billingService;
    private final Random random = new Random();

    public TranscodingService(VideoService videoService, BillingService billingService) {
        this.videoService = videoService;
        this.billingService = billingService;
    }

    @Async
    public void transcodeAsync(Long videoId) {
        try {
            Video v = videoService.markAsTranscoding(videoId);
            log.info("Started transcoding for video {} ({})", v.getId(), v.getOriginalFileName());

            Thread.sleep(5000);

            boolean success = random.nextBoolean();
            if (success) {
                Video ready = videoService.markAsReady(videoId);
                billingService.createBillingForVideo(ready);
                log.info("Transcoding completed & billing generated for video {}", ready.getId());
            } else {
                Video failed = videoService.markAsFailed(videoId);
                log.warn("Transcoding FAILED for video {}", failed.getId());
            }

        } catch (Exception e) {
            log.error("Error while transcoding video {}", videoId, e);
            videoService.markAsFailed(videoId);
        }
    }
}
