package com.raphael.itoosabi.service.cloudService;

import org.springframework.web.multipart.MultipartFile;


public interface CloudService {

    String uploadPhoto(MultipartFile photo);

    String uploadVideo(MultipartFile video);
}
