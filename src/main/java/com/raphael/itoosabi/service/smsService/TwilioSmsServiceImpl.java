package com.raphael.itoosabi.service.smsService;

import com.twilio.Twilio;
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
    public String sendSms(String to, String message) {
        return Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(senderPhoneNumber),
                message
        ).create().getStatus().toString();
    }

//    public void sendSms(String toPhoneNumber, String messageBody) {
//        try {
//            Message.creator(
//                    new PhoneNumber(toPhoneNumber),
//                    new PhoneNumber(fromPhoneNumber),
//                    messageBody
//            ).create();
//        } catch (TwilioException e) {
//            System.out.println("Failed to send SMS: " + e.getMessage());
//        }

    }
