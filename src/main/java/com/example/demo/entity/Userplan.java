package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userplan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userPlanId;
	private Long userId;
	private Long planId;
	private String username;
	private String email;
	private String phone;
	private String planName;
	private Date activationDate;
	private Date expiryDate;
	private String price;
	private boolean paid;
	
	
	

}
