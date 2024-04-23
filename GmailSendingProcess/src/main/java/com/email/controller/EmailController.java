package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.service.EmailService;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        String to = "manoharprofessional000@gmail.com"; // recipient email address
        String subject = "Test Email";
        String body = "This is a test email sent from Spring Boot.";

        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }
}
