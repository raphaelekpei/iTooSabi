package com.raphael.itoosabi.controller;

import com.raphael.itoosabi.dto.request.SmsRequest;
import com.raphael.itoosabi.service.smsService.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/sms")
public class SmsController {

    private final SmsService smsService;


//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void sendSms(@RequestBody SmsRequest smsRequest) {
//        twilioService.sendSms(smsRequest.getToPhoneNumber(), smsRequest.getMessageBody());
//    }


    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsRequest smsRequest){
        String to = smsRequest.getTo();
        String message = smsRequest.getMessage();
        String sms = smsService.sendSms(to, message);
        return ResponseEntity.ok(sms);
    }

}
