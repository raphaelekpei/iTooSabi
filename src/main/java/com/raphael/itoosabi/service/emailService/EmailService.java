package com.raphael.itoosabi.service.emailService;

import com.raphael.itoosabi.dto.request.Attachment;

import java.util.List;

public interface EmailService {
    void sendEmail(String to, String subject, String content);

    void sendEmailWithAttachment(String to, String subject, String content, List<Attachment> attachments);
}
