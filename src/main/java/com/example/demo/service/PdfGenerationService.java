package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerationService {

    public byte[] generateSamplePdfContent(Long userPlanId,
            String price,
            String planName) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA, 12);

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700); // Adjust the coordinates as needed
            contentStream.showText("This is a sample PDF content.");
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Plan Name: " + planName);
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Price: $" + price);
            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
