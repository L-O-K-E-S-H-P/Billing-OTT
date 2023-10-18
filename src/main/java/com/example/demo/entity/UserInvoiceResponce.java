package com.example.demo.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInvoiceResponce {
	
//	private String invoiceId;
//	private String customerfName;
//	private String customerlName;
//	private String customerEmail;
//	private String customerPhone;
//	private String invoiceDate;
//	private String paymentdueDate;
//	private String payment;
	private String user_plan_id;
	private String user_id;
	private String plan_id;
	private String username;
	private String email;
	private String phone;
	private String plan_name;
	private String price;
	private String activation_date;
	private String expiry_date;
	
	
	
	

}
