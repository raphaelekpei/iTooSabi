package com.raphael.itoosabi.service.smsService;

import com.raphael.itoosabi.dto.request.SmsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TwilioSmsServiceImplTest {


    @Autowired
    private TwilioSmsServiceImpl twilioSmsService;
    @Test
    void sendSms() {
        // TODO: Given that I have a phone number and a message
        // TODO: When I use the twilio service to send an SMS message to the phone number
        // TODO: Then I assert that the message is sent to the phone number

        // Given
        SmsRequest smsRequest = SmsRequest.
                builder()
                .to("+2349093837491")
                .message("Test Message")
                .build();

        // When
        String status = twilioSmsService.sendSms(smsRequest);

        // Then
        assertThat(status).isEqualTo("queued");


    }

}