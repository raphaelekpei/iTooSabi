package com.raphael.itoosabi.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdatePostRequest {
    private String title;
    private String content;
}
