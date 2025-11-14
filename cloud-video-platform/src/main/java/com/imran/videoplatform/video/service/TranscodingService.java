package com.imran.videoplatform.video.service;

import com.imran.videoplatform.video.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TranscodingService {

    private static final Logger log = LoggerFactory.getLogger(TranscodingService.class);
    private final VideoService videoService;
    private final Random random = new Random();

    public TranscodingService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Async
    public void transcodeAsync(Long videoId) {
        try {
            // 1) mark as TRANSCODING
            Video v = videoService.markAsTranscoding(videoId);
            log.info("Started transcoding for video {} ({})", v.getId(), v.getOriginalFileName());

            // 2) simulate long-running work (e.g. FFmpeg)
            Thread.sleep(5000); // 5 seconds – pretend we’re encoding

            // 3) randomly decide success/failure to simulate real-world
            boolean success = random.nextBoolean();
            if (success) {
                Video ready = videoService.markAsReady(videoId);
                log.info("Transcoding completed for video {}. Status: {}", ready.getId(), ready.getStatus());
            } else {
                Video failed = videoService.markAsFailed(videoId);
                log.warn("Transcoding FAILED for video {}. Status: {}", failed.getId(), failed.getStatus());
            }

        } catch (Exception e) {
            log.error("Error while transcoding video {}", videoId, e);
            videoService.markAsFailed(videoId);
        }
    }
}
