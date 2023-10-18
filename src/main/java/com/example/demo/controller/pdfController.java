package com.example.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PdfGenerationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pdf")
public class pdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam Long userPlanId,
    	    @RequestParam String price,
    	    @RequestParam String planName) {
        byte[] fileContent = pdfGenerationService.generateSamplePdfContent(userPlanId,price,planName);

        if (fileContent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "invoicebill.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } else {
            // Handle the case where PDF generation failed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new byte[0]);
        }
    }
    
}

