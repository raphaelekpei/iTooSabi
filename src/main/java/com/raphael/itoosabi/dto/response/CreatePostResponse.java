package com.raphael.itoosabi.dto.response;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class CreatePostResponse {

    private String title;
    private String content;
}
