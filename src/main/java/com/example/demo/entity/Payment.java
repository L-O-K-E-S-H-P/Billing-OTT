package com.example.demo.entity;

import java.time.LocalDateTime;

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
public class Payment {

	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long paymentId;
	    private Long invoiceId;
	    private String amount;
	    private LocalDateTime paymentDate;
}
