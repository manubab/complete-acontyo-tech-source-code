package com.email.ownprocess;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) {
        // Sender's email and password
        final String username = "talarimanu5@gmail.com";
        final String password = "6301514150";

        // Recipient's email address
        String to = "manoharprofessional000@gmail.com";

        // Email subject and content
        String subject = "Test Email";
        String body = "This is a test email sent from Java.";

        sendEmail(username, password, to, subject, body);
    }

    public static void sendEmail(String username, String password, String to, String subject, String body) {
        // Gmail SMTP server settings
        Properties props = new Properties();
        props.put("spring.mail.smtp.auth", "true");
        props.put("spring.mail.smtp.starttls.enable", "true");
        props.put("spring.mail.smtp.host", "smtp.gmail.com");
        props.put("spring.mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set sender and recipient email addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set email subject and content
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            if (e instanceof AuthenticationFailedException) {
                System.out.println("Authentication failed. Please check your Gmail username and password.");
            } else {
                e.printStackTrace();
            }
        }
    }
}
