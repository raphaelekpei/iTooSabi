package com.raphael.itoosabi.service;

import com.raphael.itoosabi.data.models.User;
import com.raphael.itoosabi.data.repository.UserRepository;
import com.raphael.itoosabi.dto.request.Attachment;
import com.raphael.itoosabi.dto.request.LoginUserRequest;
import com.raphael.itoosabi.dto.request.RegisterUserRequest;
import com.raphael.itoosabi.dto.response.LoginUserResponse;
import com.raphael.itoosabi.dto.response.RegisterUserResponse;
import com.raphael.itoosabi.exceptions.EmailAlreadyTakenException;
import com.raphael.itoosabi.exceptions.EmailOrPasswordConflictException;
import com.raphael.itoosabi.service.cloudService.CloudService;
import com.raphael.itoosabi.service.UserService;
import com.raphael.itoosabi.service.emailService.SendinblueEmailServiceImpl;
import com.raphael.itoosabi.service.smsService.TwilioSmsServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CloudService cloudService;

    private final TwilioSmsServiceImpl twilioSmsService;
    private final SendinblueEmailServiceImpl sendinblueEmailService;

    private final ResourceLoader resourceLoader;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws IOException {

        Optional<User> existingUser = userRepository.findByEmail(registerUserRequest.getEmail());
        if (existingUser.isPresent()) throw new EmailAlreadyTakenException("Email already taken");
        User newUser = User.builder().build();


        // TODO: Upload photo & set the user's photo url
        String photoUrl = cloudService.uploadPhoto(registerUserRequest.getPhoto());
        newUser.setUploadedPhotoUrl(photoUrl);

        // TODO: Generate verification code
        String verificationCode = RandomStringUtils.randomNumeric(6); // generates 6-digit code
        newUser.setVerificationCode(verificationCode);

        // TODO: send verification code
        // TODO: Save the user to the database
        userRepository.save(newUser);

        // TODO: Send verification code via SMS
        twilioSmsService.sendSms(newUser.getPhoneNumber(), "Your verification code is: " + verificationCode);

        // TODO: Send activation email to the user
        String subject = "Welcome to i2sabi.com";
        // Load the emailTemplates as a Resource using ResourceLoader & read the content of the resource into a string using StreamUtils.copyToString
        Resource resource = resourceLoader.getResource("classpath:emailTemplates/activation.html");
        String emailContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        // The classpath: prefix is used to indicate that the resource should be loaded from the classpath.
        //By using classpath:, the resource loader will look for the emailTemplates file within the classpath, allowing you to load it as a resource directly.

        sendinblueEmailService.sendEmail(registerUserRequest.getEmail(), subject, emailContent);

        // TODO: Send confirmation or welcome email to the user upon successful registration or creation of account
        // compose the email
        String subject2 = "Welcome to i2sabi.com";
        Resource resource2 = resourceLoader.getResource("classpath:emailTemplates/welcome.html");
        String emailContent2 = StreamUtils.copyToString(resource2.getInputStream(), StandardCharsets.UTF_8);
        // create attachments
        List<Attachment> attachments = List.of(
                new Attachment("file1.jpg", "attachments/file1 .jpg".getBytes(), "attachments/file"),
                new Attachment("file2.png", "attachments/file2.png".getBytes(), ""),
                new Attachment("file2.pdf", "attachments/file3.pdf".getBytes(), "")
        );

        // send the email with the attachments
        sendinblueEmailService.sendEmailWithAttachment(newUser.getEmail(), subject2, emailContent2, attachments);

        // TODO: Return the response
        return RegisterUserResponse.builder()
                .message("success")
                .build();
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) {
        Optional<User> existingUser = userRepository.findByEmail(loginUserRequest.getEmail());
        if(existingUser.isEmpty()) throw new EmailOrPasswordConflictException("Incorrect email or password");
        User user = existingUser.get();
        if (!user.getPassword().equals(loginUserRequest.getPassword())) throw new EmailOrPasswordConflictException("Incorrect email or password");


        // TODO: Use generateTokenService to generate login token

        // TODO: Save the token in the database

        return LoginUserResponse.builder()
                .token("")
                .build();
    }
}
