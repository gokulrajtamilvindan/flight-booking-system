package com.fbs.central_api.services;

import com.fbs.central_api.connectors.NotificationApiConnector;
import com.fbs.central_api.dtos.AirLineRegistrationRequestDto;
import com.fbs.central_api.models.AirLine;
import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MailService {
    NotificationApiConnector notificationApiConnector;

    @Autowired
    public MailService(NotificationApiConnector notificationApiConnector) {
        this.notificationApiConnector = notificationApiConnector;
    }

    /*
        This function is responsible for sending mail to all system admins regarding airline registration
     */

    public void mailSystemAdminForAirLineRegistration(List<AppUser> systemAdmins, AirLine airLine) {
        // We will apply one loop over all the system admins and one by one we will mail all the system admins
        for(AppUser systemAdmin: systemAdmins) {
            // We need to call notification api one by one for all the system admins
            // So, to call notification api from the central api we require -> NotificationApiConnector class
            AirLineRegistrationRequestDto airLineRegistrationRequestDto = new AirLineRegistrationRequestDto();
            airLineRegistrationRequestDto.setAirLine(airLine);
            airLineRegistrationRequestDto.setAppAdmin(systemAdmin);
            notificationApiConnector.notifySystemAdminForAirLineRegistration(airLineRegistrationRequestDto);
        }
    }
}
