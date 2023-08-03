package com.raphael.itoosabi.service;

import com.raphael.itoosabi.data.models.User;
import com.raphael.itoosabi.data.repository.UserRepository;
import com.raphael.itoosabi.exceptions.InvalidVerificationException;
import com.raphael.itoosabi.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final UserRepository userRepository;


    @Override
    public void verifyCode(Long userId, String code) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Invalid user ID"));
        if (!code.equals(user.getVerificationCode())) throw new InvalidVerificationException("Invalid verification code");
        user.setIsVerified(true);
        userRepository.save(user);

    }
}
