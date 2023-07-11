package com.raphael.itoosabi.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdatePostResponse {

    private String title;
    private String content;
}
