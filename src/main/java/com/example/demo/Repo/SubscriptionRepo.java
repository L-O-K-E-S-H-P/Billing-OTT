package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Subscriptionplans;

public interface SubscriptionRepo extends JpaRepository<Subscriptionplans, Long> {
	public List<Subscriptionplans> findByPlanName(String planname);

}
