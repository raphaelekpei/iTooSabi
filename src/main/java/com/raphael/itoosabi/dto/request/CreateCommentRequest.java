package com.raphael.itoosabi.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class CreateCommentRequest {
    private String content;

}
