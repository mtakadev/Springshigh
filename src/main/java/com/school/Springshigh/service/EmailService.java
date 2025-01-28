package com.school.Springshigh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username")
    private String fromEmail;
    public void sendCode(String toEmail, String code) {
      try {
          SimpleMailMessage message = new SimpleMailMessage();
          message.setFrom(fromEmail);
          message.setTo(toEmail);
          message.setSubject("Your verification Code");
          message.setText("Your verification code is: " + code);
          mailSender.send(message);
      } catch (MailException e) {
          throw new RuntimeException("Failed to send verification code",e);
      }
    }
}