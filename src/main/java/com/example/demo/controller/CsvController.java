package com.example.demo.controller;
import com.example.demo.entity.UserInvoiceResponce;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/csv")
public class CsvController {
	
	
    @SuppressWarnings("null")
	@GetMapping("/data")
    public List<UserInvoiceResponce> getCsvData() throws IOException, CsvException {
    	List<UserInvoiceResponce> list = new ArrayList<>();
    	
//    	File file = new File("C:\\Users\\lokesh.gp\\OneDrive - Prodapt Solutions Private Limited\\InvoiceData.csv");
    	File file = new File("C:\\Users\\lokesh.gp\\OneDrive - Prodapt Solutions Private Limited\\SampleBill.csv");
    	try(    			FileReader reader = new FileReader(file);
    			BufferedReader bf = new BufferedReader(reader);)
    	{
    		String data ="";
    		String[] invoiceData;
    		String[] tempArr = null;
    		while((data = bf.readLine())!=null)
    		{
    			UserInvoiceResponce userData = UserInvoiceResponce.builder().build();
    			invoiceData = data.split(",");
    			int i=1;
    			for(String tempData:invoiceData)
    			{
    				switch(i)
    				{
    				case 1: userData.setUser_plan_id(tempData);break;
    				case 2: userData.setUser_id(tempData);break;
    				case 3: userData.setPlan_id(tempData);break;
    				case 4: userData.setUsername(tempData);break;
    				case 5: userData.setEmail(tempData);break;
    				case 6: userData.setPhone(tempData);break;
    				case 7: userData.setPlan_name(tempData);break;
    				case 8: userData.setActivation_date(tempData);break;
    				case 9: userData.setExpiry_date(tempData);break;
    				case 10:userData.setPrice(tempData);break;
//    				case 1: userData.setInvoiceId(tempData);break;
//    				case 2: userData.setCustomerfName(tempData);break;
//    				case 3: userData.setCustomerlName(tempData);break;
//    				case 4: userData.setCustomerEmail(tempData);break;
//    				case 5: userData.setCustomerPhone(tempData);break;
//    				case 6: userData.setInvoiceDate(tempData);break;
//    				case 7: userData.setPaymentdueDate(tempData);break;
//    				case 8: userData.setPayment(tempData);break;
    				}
    				i++;
    			}
    			list.add(userData);
    			
    			
    			System.out.println();
    		}
    		list.stream().forEach(temp->{System.out.println(temp);});
    	}
        return list;
    }


        
    }


