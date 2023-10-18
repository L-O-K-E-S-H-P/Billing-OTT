package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Subscriptionplans;
import com.example.demo.service.Subscriptionservice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/plans")
public class SubscriptionController {
	
	@Autowired
	public Subscriptionservice planService;
	
	@PostMapping()
	public Subscriptionplans addnewPlan(@RequestBody Subscriptionplans subscriptionPlans)
	{
		return planService.newPlan(subscriptionPlans);
	}
	
	@GetMapping()
	public List<Subscriptionplans> getAllPlans()
	{
		return planService.getAllPlans();
	}
	
	@GetMapping("/{planname}")
	public List<Subscriptionplans> getMobilePlans(@PathVariable String planname )
	{
		return planService.getMobilePlans(planname);
	}

}
