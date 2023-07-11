package com.raphael.itoosabi.configuration;


import com.raphael.itoosabi.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfiguration {

    @Bean
    public AuditorAwareImpl auditorAware(){
        return new AuditorAwareImpl();
    }

    // TODO: The @EnableJpaAuditing annotation configures JPA auditing and specifies auditorAwareRef = "auditorAware" to use the auditorAware bean as the AuditorAware implementation for auditing.

}
