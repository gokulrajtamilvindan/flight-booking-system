package com.fbs.notification_api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.Properties;

@Configuration
public class AppConfiguration {
    /*
        @Bean annotation will ask spring boot to save the hashmap object inside the IOC container
        and whenever someone will try to @Autowire hashmap object spring boot will provide that object from the IOC Container
     */

    @Bean
    public HashMap<Integer, Integer> generateHashMap() {
        return new HashMap<>();
    }

    /*
        Java mail sender is the class or library that will help our spring boot code to send mails to emailIds
     */

    @Bean
    public JavaMailSender generateJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        // Our backend api needs to send mail to the user for sending mail
        // we need to provide credentials of the emailId by using which our backend will send mail to the user of application
        javaMailSender.setHost("smtp.gmail.com"); // For now email which I am using belongs to gmail so, the host will be smtp.gmail.com
        javaMailSender.setPort(587); // generally to send mail from our computer we require some port number so, the port number which we will use is 587
        javaMailSender.setUsername("accioshoppingwebsite@gmail.com"); // We will be sending email so, by what email our spring application will send mail to the users
        javaMailSender.setPassword("relcfdwhahhcvokv"); // Password of the email.... It is app password, not actual password
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true"); // Our spring boot api will connect gmail to send email via password so, mail.smtp.auth is true
        properties.put("mail.smtp.starttls.enable", "true"); // This property we are setting for secure connection
        return javaMailSender;
    }

    /*
        Below method will create a @Bean of TemplateEngine class(Present Inside ThymeLeaf Library), and it will store it inside the IOC container
     */

    public TemplateEngine getThymeLeafBean() {
        return new TemplateEngine();
    }
}
