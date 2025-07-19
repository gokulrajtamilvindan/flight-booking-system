package com.fbs.notification_api.services;

import com.fbs.notification_api.dtos.AirlineRegistrationRequestDto;
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
public class AppAdminNotificationService {
    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;

    @Autowired
    public AppAdminNotificationService(JavaMailSender javaMailSender,
                                       TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendAirlineRegistrationRequestNotification(AirlineRegistrationRequestDto airlineRegistrationRequestDto) {
        // To send the mail we require javaMailSender library object
        // To send the mail we need to set mail content
        // To set the mail content there is a class call MimeMessage so, we will set all the mail content into this MimeMessage class object
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // To send the values inside the MimeMessage we need MimeMessage helper class object
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // Now by using MimeMessageHelper class object we will be setting all the mail content
        Context context = new Context(); // This class object help us to set values of the variables present inside the html template
        context.setVariable("airlineName", airlineRegistrationRequestDto.getAirline().getAirlineName());
        context.setVariable("companyName", airlineRegistrationRequestDto.getAirline().getCompanyName());
        context.setVariable("website", airlineRegistrationRequestDto.getAirline().getWebsite());
        context.setVariable("employees", airlineRegistrationRequestDto.getAirline().getEmployees());
        context.setVariable("totalFlights", airlineRegistrationRequestDto.getAirline().getTotalFlights());
        context.setVariable("airlineAdminName", airlineRegistrationRequestDto.getAirline().getAdmin());
        context.setVariable("adminEmail", airlineRegistrationRequestDto.getAirline().getAdmin().getEmail());
        context.setVariable("requestedTime", airlineRegistrationRequestDto.getAirline().getCreatedAt());
        // We need to load the Html Template inside this function and populate value of all the variables
        // To load html template inside this function we will use library called Thymeleaf.
        // To load html template we require object of TemplateEngine class (Present Inside your Thymeleaf).
        // I want to get that TemplateEngine object from Spring boot. So, we need to create a bean of thymeleaf class and store it inside the IOC container
        String htmlContent = templateEngine.process("airline-registration-request", context); // We use templateEngine.process method to load the template inside our java function
        try {
            mimeMessageHelper.setTo(airlineRegistrationRequestDto.getAppAdmin().getEmail()); // By using this method we will the setting the mailId whom we want to send email
            mimeMessageHelper.setSubject(airlineRegistrationRequestDto.getAirline().getAirlineName() + " Registration Request");
            // mimeMessageHelper.setText("Hey, There is the New Registration Request"); -> This line is sending normal email with normal body
            mimeMessageHelper.setText(htmlContent, true); // When we need to send html content in this setText method we will pass another boolean parameter
            // If  we are passing boolean parameter as "true" that means I am passing html content.netsh
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //By calling below method we will be sending email to the users.
        javaMailSender.send(mimeMessage);
    }
}
