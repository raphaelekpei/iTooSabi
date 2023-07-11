package com.raphael.itoosabi.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Attachment {
    private String name;
    private byte[] content;
    private String contentType;

}
