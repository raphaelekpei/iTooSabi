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
}
