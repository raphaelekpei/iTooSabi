package com.raphael.itoosabi.service.smsService;


import com.raphael.itoosabi.dto.request.SmsRequest;

public interface SmsService {


    String sendSms(SmsRequest smsRequest);

}
