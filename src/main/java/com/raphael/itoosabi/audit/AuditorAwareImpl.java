package com.raphael.itoosabi.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Retrieve the currently logged-in user from the security context &
        // Return the username or ID as a String

        return Optional.of("current user");

        // ---> This is how to go about it------>
//        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication.getName();
//        return Optional.of(loggedInUsername);
    }
}
