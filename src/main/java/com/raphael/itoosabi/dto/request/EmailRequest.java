package com.raphael.itoosabi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {

    private String to;
    private String subject;
    private String content;
    private List<Attachment> attachments;

}
