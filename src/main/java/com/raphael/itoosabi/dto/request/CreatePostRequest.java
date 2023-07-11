package com.raphael.itoosabi.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreatePostRequest {

    private String title;
    private String content;

}
