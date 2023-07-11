package com.raphael.itoosabi.controller;


import com.raphael.itoosabi.dto.request.Attachment;
import com.raphael.itoosabi.dto.request.EmailRequest;
import com.raphael.itoosabi.service.emailService.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/email/send")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String content = emailRequest.getContent();
        emailService.sendEmail(to, subject, content);
    }

    @PostMapping("/attachments")
    public void sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String content = emailRequest.getContent();
        List<Attachment> attachments = emailRequest.getAttachments();
        emailService.sendEmailWithAttachment(to, subject, content, attachments);

    }
    }
