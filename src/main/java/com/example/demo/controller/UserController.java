package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Demo;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserMemberRequestDTO;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	 @Autowired
	   private UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        try {
	            User registeredUser = userService.registerUser(user);
	            if (registeredUser != null) {
	                return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	            } else {
	                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	            MailService s=new MailService();
//		    	s.sendMail("jerish304@gmail.com", "Reshma", "123456789");
	        } 
	        }catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
	        }
	        
	    }
	    
	    @GetMapping("/{phone}")
	    public Optional<User> getuser(@PathVariable String phone) {
	    	return userService.getuser(phone);
	    }
	    
	    @GetMapping("/userDetails")
	    public List<User> getAllUsersDetails()
	    {
	    	return userService.getDetails();
	    }
	    
	    
	    @GetMapping("/checknumber")
	    public ResponseEntity<Demo> checkPhoneNumber(@RequestParam String number) {
	        boolean exists = userService.existsInDatabase(number);
	        if (exists) {
	            return new ResponseEntity<Demo>(new Demo("success"),HttpStatus.OK);
	        } else {
	            return new ResponseEntity<Demo>(new Demo("Fail"),HttpStatus.OK);
	        }   
	    }
	    
	    @GetMapping("/userid/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable Long id) {
	        User user = userService.getUserById(id);
	        System.out.println(user);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	        
	    
	    @PostMapping("/addmember")
	    public User addUserwithFamilyMembers(@RequestBody UserMemberRequestDTO userMemberRequestDTO)
	    {
	    	
	    	
	    	return userService.addUserWithFamilyMembers(userMemberRequestDTO);
	    }
	    
	
	    @GetMapping("/with-family-member-count")
	    public List<Object[]> getUsersWithFamilyMemberCount() {
	        return userService.getUsersWithFamilyMemberCount();
	    }
	    
	    
	    @DeleteMapping("/{id}/family-member/{familyMemberId}")
	    public ResponseEntity<User> deleteFamilyMember(@PathVariable Long id,@PathVariable Long familyMemberId) {
	        User user = userService.deleteFamilyMember(id, familyMemberId);
	        return ResponseEntity.ok(user);
	    }
	    
	    @PostMapping("/{userId}/pay/{invoiceId}")
	    public String makePayment(@PathVariable Long userId, @PathVariable Long invoiceId) {
	        return userService.makePayment(userId, invoiceId);
	    }

	    @PutMapping("/{userId}/resume")
	    public String resumeServices(@PathVariable Long userId) {
	        return userService.resumeServices(userId);
	    }

	    @PutMapping("/{userId}/terminate")
	    public String terminateServices(@PathVariable Long userId) {
	        return userService.terminateServices(userId);
	    }
	    
}


