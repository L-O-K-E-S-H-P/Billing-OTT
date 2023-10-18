package com.example.demo.controller;

import com.example.demo.entity.Userplan;
import com.example.demo.service.UserplanService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/userplans")
public class UserplanController {

    private final UserplanService userplanService;

    @Autowired
    public UserplanController(UserplanService userplanService) {
        this.userplanService = userplanService;
    }

    @PostMapping
    public Userplan createUserPlan(@RequestBody Userplan userplan) {
        return userplanService.createUserplan(userplan);
    }

    @GetMapping("/by-user/{userId}")
    public List<Userplan> getUserPlansByUserId(@PathVariable Long userId) {
        return userplanService.getUserPlansByUserId(userId);
    }
    
    @DeleteMapping("/{userPlanId}")
    public ResponseEntity<String> deleteUserPlan(@PathVariable Long userPlanId) {
        if (userplanService.deleteUserPlan(userPlanId)) {
            return new ResponseEntity<>("User plan with ID " + userPlanId + " has been deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User plan with ID " + userPlanId + " not found", HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PostMapping("/pay-invoice/{userPlanId}")
    public void payInvoice(@PathVariable Long userPlanId) {
        userplanService.payInvoice(userPlanId);
    }
    
    

    
    
//    //overdue invoices
//    @GetMapping("/invoices/overdue")
//    public ResponseEntity<List<Userplan>> getOverdueInvoices() {
//        List<Userplan> overdueInvoices = userplanService.getOverdueInvoices();
//        return ResponseEntity.ok(overdueInvoices);
//    }
//
//    // Get long overdue invoices
//    @GetMapping("/invoices/long-overdue")
//    public ResponseEntity<List<Userplan>> getLongOverdueInvoices() {
//        List<Userplan> longOverdueInvoices = userplanService.getLongOverdueInvoices();
//        return ResponseEntity.ok(longOverdueInvoices);
//    }
//
//

   
}
