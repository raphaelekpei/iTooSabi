package com.raphael.itoosabi.service.cloudService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static com.raphael.itoosabi.utility.AppUtils.TEST_PHOTO;
import static com.raphael.itoosabi.utility.AppUtils.TEST_VIDEO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CloudinaryCloudServiceImplTest {


    @Autowired
    private CloudService cloudService;


    @Test
    public void uploadPhoto() throws IOException {
        // TODO: given that I have a photo file
        MultipartFile image = new MockMultipartFile("photo1", new FileInputStream(TEST_PHOTO));
        // TODO: when I call the cloudinary service to upload the file on cloudinary
        String cloudinaryImageUrl = cloudService.uploadPhoto(image);
        // TODO: assert that the file was uploaded
        assertThat((cloudinaryImageUrl)).isNotNull();
    }

    @Test
    public void uploadVideo() throws IOException {
        // TODO: given that I have a video file
        MultipartFile video = new MockMultipartFile("video1", new FileInputStream(TEST_VIDEO));
        // TODO: when I call the cloudinary service to upload the file on cloudinary
        String cloudinaryVideoUrl = cloudService.uploadVideo(video);
        // TODO: assert that the file was uploaded
        assertThat((cloudinaryVideoUrl)).isNotNull();
    }

}