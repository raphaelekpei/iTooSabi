package com.raphael.itoosabi.service.emailService;

import com.raphael.itoosabi.dto.request.Attachment;
import com.raphael.itoosabi.exceptions.EmailSendingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sendinblue.ApiException;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailAttachment;
import sibModel.SendSmtpEmailTo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SendinblueEmailServiceImpl implements EmailService {

    private final TransactionalEmailsApi transactionalEmailsApi;

    @Override
    public void sendEmail(String to, String subject, String content) {
        // Prepare the email request
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                .to(Collections.singletonList(new SendSmtpEmailTo().email(to)))
                .subject(subject)
                .htmlContent(content);

        // Send the email
        try {
            transactionalEmailsApi.sendTransacEmail(sendSmtpEmail);
        } catch (ApiException ex) {
            throw new EmailSendingException("Failed to send email: " + ex.getMessage());
            // catches ApiException and re-throws it as an EmailSendingException.
        }
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String content, List<Attachment> attachments) {
            // Prepare the email request
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail()
                    .to(Collections.singletonList(new SendSmtpEmailTo().email(to))) // Set the recipient email address
                    .subject(subject)       // Set the email subject
                    .htmlContent(content);  // Set the email content

            // Add attachments
            List<SendSmtpEmailAttachment> attachmentList = attachments.stream()
                    .map(attachment -> new SendSmtpEmailAttachment()
                            .name(attachment.getName())
                            .content(attachment.getContent()))
                    .collect(Collectors.toList());
            sendSmtpEmail.setAttachment(attachmentList);
            // Send the email
            try {
                transactionalEmailsApi.sendTransacEmail(sendSmtpEmail);
            }
            catch (ApiException e) {
                throw new EmailSendingException("Failed to send email.");
            }

    }
}
