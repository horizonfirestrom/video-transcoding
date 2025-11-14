package com.imran.videoplatform.video.controller;

import com.imran.videoplatform.video.model.Video;
import com.imran.videoplatform.video.service.TranscodingService;
import com.imran.videoplatform.video.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;
    private final TranscodingService transcodingService;

    public VideoController(VideoService videoService, TranscodingService transcodingService ) {
        this.videoService = videoService;
        this.transcodingService= transcodingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        try {
            Video saved = videoService.uploadVideo(file);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Failed to save video: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getById(@PathVariable Long id) {
        Video video = videoService.getVideo(id);
        return ResponseEntity.ok(video);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAll() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }
    
 // ---- NEW: trigger transcoding for a video ----
    @PostMapping("/{id}/transcode")
    public ResponseEntity<String> startTranscoding(@PathVariable Long id) {
        transcodingService.transcodeAsync(id);  // fire-and-forget
        return ResponseEntity.accepted().body("Transcoding started for video id " + id);
    }

    // ---- NEW: list videos that are not READY yet ----
    @GetMapping("/processing")
    public ResponseEntity<List<Video>> getProcessingVideos() {
        return ResponseEntity.ok(videoService.getProcessingVideos());
    }
}
