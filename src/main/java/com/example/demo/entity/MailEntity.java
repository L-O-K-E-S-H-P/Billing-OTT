package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailEntity {
	
	private String email;
	private String username;
	private String activation_date;
	private String expiry_date;
	private String plan_name;
	private String price;
	private String phone;
	

}
