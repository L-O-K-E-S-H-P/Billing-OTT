package com.example.demo.Repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Userplan;

public interface UserplanRepo extends JpaRepository<Userplan, Long> {
	List<Userplan> findByUserId(Long userId);

	Userplan findByUserIdAndPlanIdAndExpiryDateAfter(Long userId, Long planId, Date from);



//	// Find overdue Userplans (expired within the past 7 days)
//    List<Userplan> findByExpiryDateBeforeAndPaidFalse1(Date dueDateThreshold);
//    
//    // Find long overdue Userplans (expired more than 30 days ago)
//    List<Userplan> findByExpiryDateBeforeAndPaidFalse(Date dueDateThreshold);

}
