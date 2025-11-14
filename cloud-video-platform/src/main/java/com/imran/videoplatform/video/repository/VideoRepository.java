package com.imran.videoplatform.video.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imran.videoplatform.video.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}