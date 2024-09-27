package com.mylearning.scheduler.springbootscheduler.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class NotificationService {

    @Autowired
    private ReportService service;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${report.send.email.toList}")
    private String toEMails;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendDailyReports() throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(sender);
        helper.setTo(toEMails.split(","));
        helper.setSubject("List of orders_" + new Date().getTime());
        helper.setText("Hi User \n Please find the attachment for today's order received !!");
        helper.setCc(toEMails.split(","));

        byte[] report = service.generateReport();

        ByteArrayResource content = new ByteArrayResource(report);
        helper.addAttachment(new Date().getTime()+"_orders.xlsx", content);

        javaMailSender.send(mimeMessage);
        return "Mail sent successfully with attachment ";
    }

}
