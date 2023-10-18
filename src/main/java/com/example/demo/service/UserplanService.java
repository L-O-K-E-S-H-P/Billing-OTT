package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repo.PaymentRepo;
import com.example.demo.Repo.UserplanRepo;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Userplan;

import jakarta.persistence.EntityNotFoundException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserplanService {
	@Autowired
    private UserplanRepo userplanRepository;

	@Autowired
	private PaymentRepo paymentRepo;
	
    public Userplan createUserplan(Userplan userplan) {

        LocalDate activationDate = LocalDate.now();

        // Check if the user already has an active plan with the same plan ID
        Userplan existingPlan = userplanRepository.findByUserIdAndPlanIdAndExpiryDateAfter(
                userplan.getUserId(),
                userplan.getPlanId(),
                Date.from(activationDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            );

            if (existingPlan != null) {
                throw new RuntimeException("You already have an active plan with the same ID.");
            }

            // Generate expiry date (14 days from activation date)
            LocalDate expiryDate = activationDate.plusDays(14);

            // Convert LocalDate to Date for database storage
            Date activationDateDate = Date.from(activationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date expiryDateDate = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            userplan.setActivationDate(activationDateDate);
            userplan.setExpiryDate(expiryDateDate);

            return userplanRepository.save(userplan);
    }

    public List<Userplan> getUserPlansByUserId(Long userId) {
        return userplanRepository.findByUserId(userId);
    }

    public boolean deleteUserPlan(Long userPlanId) {
        Optional<Userplan> userPlanOptional = userplanRepository.findById(userPlanId);
        if (userPlanOptional.isPresent()) {
            userplanRepository.deleteById(userPlanId);
            return true; 
        }
        return false; 
    }
    
    public void payInvoice(Long userPlanId) {
        Userplan userplan = userplanRepository.findById(userPlanId)
            .orElseThrow(() -> new RuntimeException("Invoice not found"));
        if (!userplan.isPaid()) {
            userplan.setPaid(true);
            userplanRepository.save(userplan);
         // Add payment record to the database
            Payment payment = new Payment();
            payment.setInvoiceId(userPlanId);
            payment.setAmount(userplan.getPrice());
            payment.setPaymentDate(LocalDateTime.now());
            paymentRepo.save(payment);
        } else {
            throw new RuntimeException("Invoice is already paid");
        }
        
        
    }

    
    //overdue invoices
//    public List<Userplan> getOverdueInvoices() {
//    	 Date currentDate = new Date();
//         Date dueDateThreshold = calculateDueDateThreshold(currentDate, 7); 
//         
//         return userplanRepository.findByExpiryDateBeforeAndPaidFalse(dueDateThreshold);
//    }
//
//    private Date calculateDueDateThreshold(Date currentDate, int daysToSubtract) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
//        calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
//        return calendar.getTime();
//    }
//
//
//	// Retrieve long overdue invoices
//    public List<Userplan> getLongOverdueInvoices() {
//    	Date currentDate = new Date();
//        Date dueDateThreshold = calculateDueDateThreshold(currentDate, 30); 
//        
//        return userplanRepository.findByExpiryDateBeforeAndPaidFalse(dueDateThreshold);
//    }
    
//    public byte[] getPdfContent(Long userPlanId) {
//        String samplePdfContent = "This is a sample PDF content.";
//        return samplePdfContent.getBytes(StandardCharsets.UTF_8);
//    }
    
}
