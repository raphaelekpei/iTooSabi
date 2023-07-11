package com.raphael.itoosabi.service;


public interface VerificationService {
    void verifyCode(Long userId, String code);
}
