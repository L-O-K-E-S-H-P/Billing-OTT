package com.example.demo.service;

import java.util.Date;

import java.util.HashMap;

import java.util.Map;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.Config.OtpConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.*;


@Service
@Slf4j
public class OtpService {
	
	@Autowired
	private OtpConfig otpConfig;

	
	public static String otpset;
	private Map<String, String> otpStorage = new HashMap<>();


	public String generateRandomOTP(String phno) {

        Random random = new Random();

        Integer otpValue = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
        String otp=otpValue.toString();
        sendOtp(phno,otp);
        otpset=otp;
        return String.valueOf(otpValue);

    }

 

 

 

public boolean sendOtp(String phoneNumber, String otp) {

	try {

		System.out.println("sending SMS");
		Twilio.init(otpConfig.getAccountSid(),otpConfig.getAuthToken());

		 Message message = Message.creator(

			      new com.twilio.type.PhoneNumber("+91"+phoneNumber),

			      new com.twilio.type.PhoneNumber("+12564884313"),

			      "Your OTP is:"+otp)

			    .create();

		 otpStorage.put(phoneNumber, otp);

		 return true;

	}catch(Exception e)
	{
	e.printStackTrace();
		return false;
	}
}

public String validateOtp(String otpget)

{
	String msg;
	if (otpset.equals(otpget))
	{
		msg="validated";
	}
	else
	{
		msg="wrongotp";
	}
	return msg;
}
}
