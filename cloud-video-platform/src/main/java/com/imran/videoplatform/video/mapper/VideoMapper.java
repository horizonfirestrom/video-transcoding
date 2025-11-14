package com.imran.videoplatform.video.mapper;

import com.imran.videoplatform.video.dto.VideoDto;
import com.imran.videoplatform.video.model.Video;

public class VideoMapper {

    public static VideoDto toDto(Video video) {
        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setOriginalFileName(video.getOriginalFileName());
        dto.setStoragePath(video.getStoragePath());
        dto.setStatus(video.getStatus());
        dto.setCreatedAt(video.getCreatedAt());
        dto.setUpdatedAt(video.getUpdatedAt());
        return dto;
    }
}
