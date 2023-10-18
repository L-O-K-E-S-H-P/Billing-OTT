package com.example.demo.controller;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Demo;
import com.example.demo.entity.MailEntity;
import com.example.demo.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {
	private final MailService userservice;

	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping
	public Demo addUser(@RequestBody MailEntity mailData ) {
		System.out.println(mailData);
		String msg=userservice.sendMail(mailData);
		return  new Demo(msg);
	}




 
}



 






	