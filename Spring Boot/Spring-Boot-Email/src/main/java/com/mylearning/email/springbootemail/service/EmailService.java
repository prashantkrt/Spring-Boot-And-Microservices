package com.mylearning.email.springbootemail.service;


import com.mylearning.email.springbootemail.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public String sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(request.getToEMail());
        mailMessage.setTo(request.getToEmails());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getMessageBody());
        javaMailSender.send(mailMessage);
        return "email successfully send to : " + request.getToEMail();
    }

    public String sendEmailWithAttachment(EmailRequest request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(sender);
        helper.setTo(request.getToEmails());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMessageBody());

        FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
        //helper.addAttachment(file.getFilename(), file);
        helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with attachment " + file.getFilename();
    }

}
