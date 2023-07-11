package com.raphael.itoosabi.controller;

import com.raphael.itoosabi.dto.request.PasswordResetRequest;
import com.raphael.itoosabi.dto.response.PasswordResetResponse;
import com.raphael.itoosabi.service.passwordResetService.PasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password-reset")
@AllArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;


    @PostMapping("/")
    public ResponseEntity<PasswordResetResponse> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        PasswordResetResponse response = passwordResetService.resetPassword(passwordResetRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<PasswordResetResponse> confirmResetPassword(@RequestBody PasswordResetConfirmRequest confirmRequest) {
        PasswordResetResponse response = passwordResetService.confirmResetPassword(confirmRequest.getResetToken(), confirmRequest.getNewPassword());
        return ResponseEntity.ok(response);
    }

}
