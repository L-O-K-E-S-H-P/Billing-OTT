package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.SubscriptionRepo;
import com.example.demo.entity.Subscriptionplans;

@Service
public class Subscriptionservice {
	
	@Autowired
	public SubscriptionRepo repo;

	public Subscriptionplans newPlan(Subscriptionplans subscriptionPlans) {
		// TODO Auto-generated method stub
		return repo.save(subscriptionPlans);
	}

	public List<Subscriptionplans> getAllPlans() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public List<Subscriptionplans> getMobilePlans(String planname)
	{
		return repo.findByPlanName(planname);
	}

}
