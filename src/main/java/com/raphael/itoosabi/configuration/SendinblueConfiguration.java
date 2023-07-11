package com.raphael.itoosabi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sendinblue.ApiClient;
import sibApi.TransactionalEmailsApi;

@Configuration
public class SendinblueConfiguration {

    @Value("${sendinblue.api-key}")
    private String apikey;


    @Bean
    public TransactionalEmailsApi transactionalEmailsApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setApiKey(apikey);
        return new TransactionalEmailsApi(apiClient);

    }

// In this code, we define a bean named transactionalEmailsApi that returns a new instance of TransactionalEmailsApi initialized with the ApiClient configured with the Sendinblue API key.




}
