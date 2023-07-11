package com.raphael.itoosabi.service.passwordResetService;


import com.raphael.itoosabi.dto.request.PasswordResetRequest;
import com.raphael.itoosabi.dto.response.PasswordResetResponse;

public interface PasswordResetService {

    PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest);
}
