package com.imran.videoplatform.video.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imran.videoplatform.video.model.Video;
import com.imran.videoplatform.video.model.VideoStatus;
import com.imran.videoplatform.video.repository.VideoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    // base folder on your machine where videos will be stored
    private final Path storageRoot = Path.of("E:/java/videos"); // change if you want

    public VideoService(VideoRepository videoRepository) throws IOException {
        this.videoRepository = videoRepository;
        // ensure directory exists
        Files.createDirectories(storageRoot);
    }

    public Video uploadVideo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        // 1. decide where to save the file
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path destination = storageRoot.resolve(fileName);

        // 2. physically save the file
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        // 3. persist metadata in DB
        Video video = new Video();
        video.setOriginalFileName(file.getOriginalFilename());
        video.setStoragePath(destination.toString());
        video.setStatus(VideoStatus.UPLOADED);

        return videoRepository.save(video);
    }

    public Video getVideo(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found with id " + id));
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
    
    public Video markAsTranscoding(Long id) {
        Video video = getVideo(id);
        video.setStatus(VideoStatus.TRANSCODING);
        return videoRepository.save(video);
    }

    public Video markAsReady(Long id) {
        Video video = getVideo(id);
        video.setStatus(VideoStatus.READY);
        return videoRepository.save(video);
    }

    public Video markAsFailed(Long id) {
        Video video = getVideo(id);
        video.setStatus(VideoStatus.FAILED);
        return videoRepository.save(video);
    }

    public List<Video> getProcessingVideos() {
        return videoRepository.findAll()
                .stream()
                .filter(v -> v.getStatus() != VideoStatus.READY)
                .toList();
    }
}