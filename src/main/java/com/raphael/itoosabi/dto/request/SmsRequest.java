package com.raphael.itoosabi.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SmsRequest {

    private String to;
    private String message;

}
