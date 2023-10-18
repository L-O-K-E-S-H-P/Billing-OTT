package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptionplans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long planId;
	private String planName;
	private String planDesc;
	private String price;
	private String sms="100 SMS Per Day";
	private String planDuration;
	private String roamingCalls="UNLIMITED";
	private String dataLimit;
	private String voiceCall;
	private String streamQuality;
	private String noOfDevices;
	private String image;
	private String ott1;
	private String ott2;
	private String ott3;
	 
//	 @OneToMany(mappedBy = "subscriptionPlans")
//	 private List<User_plan> userPlan;
//	
	

}
