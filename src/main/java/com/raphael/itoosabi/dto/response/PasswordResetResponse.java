package com.raphael.itoosabi.dto.response;

import com.raphael.itoosabi.enums.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetResponse {

    private String message;
    private OtpStatus status;

}
