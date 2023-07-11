package com.raphael.itoosabi.service.cloudService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.raphael.itoosabi.exceptions.ImageUploadException;
import com.raphael.itoosabi.exceptions.VideoUploadException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryCloudServiceImpl implements CloudService {

    private final Cloudinary cloudinary;

   //  the uploadPhoto method takes a MultipartFile as input,
   //  uploads it to Cloudinary using the Cloudinary SDK,
   //  and returns the secure URL of the uploaded photo as a string.
    @Override
    public String uploadPhoto(MultipartFile photo) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("secure_url").toString();
        }
        catch (IOException e) {throw new ImageUploadException("Error saving file");}

             /*
     The try block calls the upload method on the cloudinary's uploader object.
     The method upload takes in the byte array of the image data and an empty map of options.
     It then retrieves the resulting URL from the response and returns it as a string.

     The catch block catches an IOException that might be thrown by the upload() method, and
     re-throws it as a more specific ImageUploadException with a custom error message.
      */

    }

    public String uploadVideo(MultipartFile video) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(video.getBytes(), ObjectUtils.asMap("resource_type", "video"));
            return uploadResult.get("secure_url").toString();
        }
        catch (IOException e) {throw new VideoUploadException("Error uploading video: " + video);}}


    // the resource_type set to "video" using ObjectUtils.asMap(). This ensures that the uploaded file is recognized as a video by Cloudinary.
}
