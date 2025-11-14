package com.imran.videoplatform.video.controller;

import com.imran.videoplatform.video.dto.VideoDto;
import com.imran.videoplatform.video.mapper.VideoMapper;
import com.imran.videoplatform.video.model.Video;
import com.imran.videoplatform.video.service.TranscodingService;
import com.imran.videoplatform.video.service.VideoService;
import org.springframework.http.MediaType;
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

    public VideoController(VideoService videoService, TranscodingService transcodingService) {
        this.videoService = videoService;
        this.transcodingService = transcodingService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile file) {
        try {
            Video saved = videoService.uploadVideo(file);
            return ResponseEntity.ok(VideoMapper.toDto(saved));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.internalServerError().body("Failed to save video: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getById(@PathVariable Long id) {
        Video video = videoService.getVideo(id);
        return ResponseEntity.ok(VideoMapper.toDto(video));
    }

    @GetMapping
    public ResponseEntity<List<VideoDto>> getAll() {
        List<VideoDto> dtos = videoService.getAllVideos()
                .stream()
                .map(VideoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{id}/transcode")
    public ResponseEntity<String> startTranscoding(@PathVariable Long id) {
        transcodingService.transcodeAsync(id);
        return ResponseEntity.accepted().body("Transcoding started for video id " + id);
    }

    @GetMapping("/processing")
    public ResponseEntity<List<VideoDto>> getProcessingVideos() {
        List<VideoDto> dtos = videoService.getProcessingVideos()
                .stream()
                .map(VideoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
