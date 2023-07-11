package com.raphael.itoosabi.controller;

import com.raphael.itoosabi.service.cloudService.CloudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/uploads")
public class CloudController {
    private final CloudService cloudService;



    @PostMapping("/photos")
    public ResponseEntity<String> uploadPhoto(@RequestParam MultipartFile photo) {
        String imageUrl = cloudService.uploadPhoto(photo);
        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/videos")
    public ResponseEntity<String> uploadVideo(@RequestParam MultipartFile video) {
        String videoUrl = cloudService.uploadVideo(video);
        return ResponseEntity.ok(videoUrl);
    }

}
