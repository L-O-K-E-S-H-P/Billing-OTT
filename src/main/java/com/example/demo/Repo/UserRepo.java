package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
//	User findByphone(String phone);
	Optional<User> findByphone(String number);
	
	@Query("SELECT u, COUNT(fm) FROM User u LEFT JOIN u.familyMember fm GROUP BY u")
	List<Object[]> findUsersWithFamilyMemberCount();
	
	Optional<User> findById(Long id);

}
