package service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendOTPService {

    public static void sendOTP(String email, String genOTP) {
        // Recipient's email ID
        String to = email;

        // Sender's email ID
        String from = "abhishekmankude@gmail.com";

        // Gmail SMTP server
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "bjxv pyfc bwpu bbfd"); // Use App Password here
            }
        });

        session.setDebug(true); // Enable debugging

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header
            message.setFrom(new InternetAddress(from));

            // Set To: header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header
            message.setSubject("Your OTP Code");

            // Set actual OTP message
            message.setText("Your OTP is: " + genOTP);

            // Send message
            Transport.send(message);
            System.out.println("OTP Sent successfully to " + email);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}


