package com.fbs.notification_api.controllers;

import com.fbs.notification_api.dtos.AirLineRegistrationRequestDto;
import com.fbs.notification_api.services.AppAdminNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    This particular controller is created to send notifications to App Admin
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/notify/appadmin")
public class AppAdminNotificationController {
    AppAdminNotificationService appAdminNotificationService;

    @Autowired
    public AppAdminNotificationController(AppAdminNotificationService appAdminNotificationService) {
        this.appAdminNotificationService = appAdminNotificationService;
    }
    /*
        To send registration request email of a airline to application admin.
     */
    @PutMapping("/airline-registration")
    public void airLineRegistrationRequestNotification(@RequestBody AirLineRegistrationRequestDto airLineRegistrationRequestDto) {
        log.info("Inside airLineRegistrationRequestNotification with payload : " + airLineRegistrationRequestDto.toString());
        // From here we need to call appAdminNotificationService
        // We will be calling service layer which will be sending mail to application admin
        appAdminNotificationService.sendAirLineRegistrationRequestNotification(airLineRegistrationRequestDto);
    }
}
