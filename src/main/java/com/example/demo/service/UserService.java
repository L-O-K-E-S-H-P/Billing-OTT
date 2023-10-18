package com.example.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.MemberRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.Repo.UserplanRepo;
import com.example.demo.entity.FamilyMember;
import com.example.demo.entity.User;
import com.example.demo.entity.Userplan;
import com.example.demo.entity.dto.UserMemberRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	@Autowired
	private UserRepo repo;

	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private UserplanRepo planRepo;

	public User registerUser(User user) {
		// Implement registration logic, including validation
		// Save the user to the database
		return repo.save(user);
	}

	public Optional<User> getuser(String phone) {

		return repo.findByphone(phone);
	}

	public boolean existsInDatabase(String number) {
		Optional<User> phoneNumber = repo.findByphone(number);
		return phoneNumber.isPresent();
	}

	public List<User> getDetails() {
		return repo.findAll();
	}

	public User getUserById(Long userId) {
        Optional<User> user = repo.findById(userId);
        return user.orElse(null);
    }

	
	public User addUserWithFamilyMembers(UserMemberRequestDTO userMemberRequestDTO) {
	    User account = repo.findById(userMemberRequestDTO.getAccountId())
	            .orElseThrow(() -> new RuntimeException("Account not found"));

	    // Check if the user already has 4 family members
	    if (account.getFamilyMember().size() + userMemberRequestDTO.getFamilMemberList().size() > 4) {
	        throw new RuntimeException("Cannot add more than 4 family members to this account.");
	    }

	    // Save and add the new family members
	    userMemberRequestDTO.getFamilMemberList().forEach(member -> {
	        memberRepo.save(member);
	    });
	    account.getFamilyMember().addAll(userMemberRequestDTO.getFamilMemberList());
	    return repo.save(account);
	}


	public List<Object[]> getUsersWithFamilyMemberCount() {
		return repo.findUsersWithFamilyMemberCount();
	}
	
	public User deleteFamilyMember(Long userId, Long familyMemberId) {
        User user = repo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<FamilyMember> familyMembers = user.getFamilyMember();
        familyMembers.removeIf(member -> member.getId().equals(familyMemberId));

        return repo.save(user);
    }
	public String makePayment(Long userId, Long invoiceId) {
		Optional<User> userOptional = repo.findById(userId);
	    Optional<Userplan> invoiceOptional = planRepo.findById(invoiceId);

	    if (userOptional.isPresent() && invoiceOptional.isPresent()) {
	        User user = userOptional.get();
	        Userplan invoice = invoiceOptional.get();

	        if (user.isPaymentMade()) {
	            return "Payment already made";
	        }

	        if (invoice.isPaid()) {
	            user.setPaymentMade(true);
	            user.setServicesActive(true);
	            repo.save(user);
	            return "Payment successful, services activated";
	        } else {
	            return "Payment failed";
	        }
	    } else {
	        return "User or invoice not found";  // Handle the case where the entities are not found.
	    }
    }

	public String terminateServices(Long userId) {
	    Optional<User> userOptional = repo.findById(userId);

	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        user.terminateServices();
	        repo.save(user);
	        return "Services terminated";
	    } else {
	        return "User not found";  // Handle the case where the user is not found.
	    }
	}
	
	public String resumeServices(Long userId) {
	    Optional<User> userOptional = repo.findById(userId);

	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        user.resumeServices();
	        repo.save(user);
	        return "Services resumed";
	    } else {
	        return "User not found";  // Handle the case where the user is not found.
	    }
	}

	

	

}
