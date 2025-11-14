package com.imran.videoplatform.video.dto;

import com.imran.videoplatform.video.model.VideoStatus;

import java.time.LocalDateTime;

public class VideoDto {

    private Long id;
    private String originalFileName;
    private String storagePath;
    private VideoStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // later we can add playbackUrl, duration, etc.

    // getters + setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOriginalFileName() { return originalFileName; }
    public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }

    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }

    public VideoStatus getStatus() { return status; }
    public void setStatus(VideoStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
