package com.fbs.notification_api.services;

import com.fbs.notification_api.dtos.AirlineRejectDto;
import com.fbs.notification_api.models.Airline;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
public class AirlineNotificationService {
    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AirlineNotificationService(JavaMailSender javaMailSender,
                                      TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void airlineAcceptRequestNotification(Airline airline) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("airlineAdminName", airline.getAdmin().getName());
        context.setVariable("airlineName", airline.getAirlineName());
        context.setVariable("companyName", airline.getCompanyName());
        context.setVariable("website", airline.getWebsite());
        context.setVariable("totalFlights", airline.getTotalFlights());
        context.setVariable("employees", airline.getEmployees());
        context.setVariable("activatedAt", airline.getUpdatedAt().toString());
        String htmlContent = templateEngine.process("accept-airline-registration-request", context);
        try {
            mimeMessageHelper.setTo(airline.getAdmin().getEmail());
            mimeMessageHelper.setSubject("Airline Registration Request Accepted");
            mimeMessageHelper.setText(htmlContent, true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        javaMailSender.send(mimeMessage);
    }

    public void airlineRejectRequestNotification(AirlineRejectDto airlineRejectDto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("adminName", airlineRejectDto.getAirlineAdminName());
        context.setVariable("reason", airlineRejectDto.getRejectReason());
        String htmlContent = templateEngine.process("reject-airline-registration-request", context);
        try {
            mimeMessageHelper.setTo(airlineRejectDto.getAirlineAdminEmail());
            mimeMessageHelper.setSubject("Airline Registration Request Rejected");
            mimeMessageHelper.setText(htmlContent, true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        javaMailSender.send(mimeMessage);
    }
}
