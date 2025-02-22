package com.main.servie;

import com.main.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class InvoiceService {
    private final JavaMailSender mailSender;
    private final CsvUserService csvUserService;
    private final TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledTask;
    private final AtomicReference<String> cronExpression = new AtomicReference<>("0 */2 * * * *");

    public InvoiceService(JavaMailSender mailSender, CsvUserService csvUserService, TaskScheduler taskScheduler) {
        this.mailSender = mailSender;
        this.csvUserService = csvUserService;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void startScheduler() {
        log.info("Starting scheduler with default cron: {}", cronExpression.get());
        scheduleInvoiceGeneration();
    }

    public void updateCronExpression(String newCron) {
        log.info("Updating cron expression to: {}", newCron);
        cronExpression.set(newCron);

        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }

        scheduleInvoiceGeneration();
    }

    private void scheduleInvoiceGeneration() {
        scheduledTask = taskScheduler.schedule(this::generateMonthlyInvoices, new CronTrigger(cronExpression.get()));
        log.info("Scheduled task updated with new cron: {}", cronExpression.get());
    }

    public void generateMonthlyInvoices() {
        log.info("Invoice Generation Started");

        List<User> users = csvUserService.getUsersFromCsv();
        for (User user : users) {
            if (user.isActiveSubscription()) {
                log.info("Generating invoice for user: {}", user.getName());
                sendInvoiceEmail(user);
            }
        }
    }

    private void sendInvoiceEmail(User user) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nevisadhiambo71@gmail.com");
            message.setTo(user.getEmail());
            message.setSubject("Your Monthly Invoice");
            message.setText("Hello " + user.getName() + ",\n\nYour invoice for this month is ready. Amount: $100.00\n\nThank you for your business!");

            mailSender.send(message);
            log.info("Invoice email sent to: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to user: {} :: {}", user.getEmail(), e.getMessage());
        }
    }
}
