package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
    private String phone;
    private String email;
    private String address;
    private String profileImageUrl;
 
    private boolean isPaymentMade;
    private boolean isServicesActive;
    
    @OneToMany
    private List<FamilyMember> familyMember;
    
    
    public void resumeServices() {
        if (isPaymentMade) {
            isServicesActive = true;
            
        }
    }

    public void terminateServices() {
        isServicesActive = false;
    }
    
}
