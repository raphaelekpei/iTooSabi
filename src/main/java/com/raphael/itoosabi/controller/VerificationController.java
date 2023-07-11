package com.raphael.itoosabi.controller;

import com.raphael.itoosabi.service.VerificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/verify")
@AllArgsConstructor
public class VerificationController {


    private final VerificationService verificationService;
    @PostMapping("/code")
    public void verifyCode(@RequestParam("userId") Long userId, @RequestParam("code") String code) {
        verificationService.verifyCode(userId, code);
    }

//    @PostMapping("/jwt-auth-token")
//    public void verifyToken(@RequestParam("userId") Long userId, @RequestParam("code") String code) {
//        jwtService.verifyToken(userId, code);
//
//        if (!user.getVerificat            throw new InvalidVerificationTokenException("Invalid verification token");
//        ionToken().equals(loginUserRequest.getVerificationToken()))
//        existingUser.get().setVerified(true);
//        userRepository.save(existingUser.get());
//        return LoginUserResponse.builder()
//                .message("User logged in successfully")
//                .build();
//    }



}
