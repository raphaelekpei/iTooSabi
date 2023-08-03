package com.raphael.itoosabi.service.smsService;

import com.raphael.itoosabi.dto.request.SmsRequest;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsServiceImpl implements SmsService{

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String senderPhoneNumber;

    @PostConstruct
    private void init(){
        Twilio.init(accountSid, authToken);
    }


    @Override
    public String sendSms(SmsRequest smsRequest) {
        try {
            return Message.creator(
                    new PhoneNumber(smsRequest.getTo()),
                    new PhoneNumber(senderPhoneNumber),
                    smsRequest.getMessage()
            ).create().getStatus().toString();
        } catch (TwilioException e) {
            return "Failed to send SMS: " + e.getMessage();
        }
    }

    }
