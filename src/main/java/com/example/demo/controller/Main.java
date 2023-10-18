package com.example.demo.controller;
import java.io.UnsupportedEncodingException;

import java.util.Date;

import java.util.Properties;

 

import javax.mail.Authenticator;

import javax.mail.Message;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

public class Main {

    public static void main(String[] args) {

        System.out.println("Outlook Email Start");

 

        String smtpHostServer = "smtp.office365.com";

        final String emailID = "lokeshpulluru1972@outlook.com"; // inga Outlook email address

        final String password = "Loki@2002"; // inga un outlook account password

        String toEmail = "dajerish@gmail.com";

        String subject = "Outlook Email Testing Subject";

        String messageBody = "Hi hello how are you"; // inga type mail body

        Properties props = new Properties();

        props.put("mail.smtp.host", smtpHostServer);

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.starttls.enable", "true");

 

        Session session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(emailID, password);

            }

        });

 

        sendEmail(session, emailID, subject, messageBody,toEmail);
    }

    @CrossOrigin("http://localhost:4200")
    public static void sendEmail(Session session, String fromEmail, String subject, String body,String toEmail){

        try

        {

            MimeMessage msg = new MimeMessage(session);

            //set message headers

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            msg.addHeader("format", "flowed");

            msg.addHeader("Content-Transfer-Encoding", "8bit");

 

            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));

 

            msg.setReplyTo(InternetAddress.parse(toEmail, false));

 

            msg.setSubject(subject, "UTF-8");

 

            msg.setText(body, "UTF-8");

 

            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            System.out.println("Message is ready");

            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}