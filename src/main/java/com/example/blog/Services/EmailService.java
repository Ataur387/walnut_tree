package com.example.blog.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            javaMailSender.send(message);
            String logMessage = "Email sent to: " + to;
            logger.info(logMessage); // Log the event
        } catch (Exception e) {
            String logMessage = "Error sending email to: " + to + "  " + e.getMessage();
            logger.info(logMessage); // Log the error
        }
    }
}
