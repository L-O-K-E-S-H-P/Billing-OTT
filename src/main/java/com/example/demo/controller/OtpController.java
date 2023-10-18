package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Demo;
import com.example.demo.service.OtpService;



@RestController
@RequestMapping
public class OtpController {
	@Autowired
	private OtpService otpService;

	@CrossOrigin(origins="http://localhost:4200/")
	@PostMapping("/requestotp")
	public ResponseEntity<Map<String, String>> requestOTP(@RequestBody Map<String, String> requestBody) {

	    String phoneNumber = requestBody.get("phoneNumber");
	    System.out.println(phoneNumber);
	    String otp = otpService.generateRandomOTP(phoneNumber);
	    System.out.println(otp);

//	    if (otpService.sendOtp(phoneNumber, otp)) {
//
//	    	   Map<String, String> response = new HashMap<>();
//
//	           response.put("message", "OTP sent successfully");
//
//	           return ResponseEntity.ok(response);
//
//	    }
	    

	    return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);

	}
	

		@CrossOrigin(origins="http://localhost:4200")
		@PostMapping("/validateotp")
		public Demo validateOtp(@RequestBody String otpno)

		{

			String otpvalue=otpService.otpset;

			System.out.println("this is printint from the boot "+otpno);

			String msg=otpService.validateOtp(otpno);

			return new Demo(msg);

		}

}
