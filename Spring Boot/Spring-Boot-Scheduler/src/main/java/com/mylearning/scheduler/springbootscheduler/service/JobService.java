package com.mylearning.scheduler.springbootscheduler.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class JobService {

    @Autowired
    private NotificationService service;

    //0 0/2 * * * * => Task executed every 2 minutes at , continue to execute every 2 minutes
    @Scheduled(cron = "${cron_interval}",zone = "IST")
    public void process() {
        System.out.println("job started on " + new Date());
        try {
            service.sendDailyReports();
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
