package com.raphael.itoosabi.service.passwordResetService;

import com.raphael.itoosabi.data.models.User;
import com.raphael.itoosabi.data.repository.UserRepository;
import com.raphael.itoosabi.dto.request.PasswordResetRequest;
import com.raphael.itoosabi.dto.response.PasswordResetResponse;
import com.raphael.itoosabi.service.emailService.SendinblueEmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService{

    private final UserRepository userRepository;
    private final SendinblueEmailServiceImpl sendinblueService;


    public PasswordResetResponse requestPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            String resetToken = generateResetToken();
            optionalUser.setResetToken(resetToken);
            userRepository.save(user);
            sendResetEmail(user.getEmail(), resetToken);
        }
    }

    public void confirmResetPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user != null) {
            user.setPassword(newPassword);
            user.setResetToken(null);
            userRepository.save(user);
        }
    }

    private void sendResetEmail(String email, String resetToken) {
        String resetLink = "http://your-app-url/reset?token=" + resetToken;
        sendinblueService.sendResetPasswordEmail(email, resetLink);
    }

    private String generateResetToken() {
        // Generate a unique reset token
        // You can use UUID or any other method to generate a secure token
    }

    @Override
    public PasswordResetResponse resetPassword(PasswordResetRequest passwordResetRequest) {
        return null;
    }
}
