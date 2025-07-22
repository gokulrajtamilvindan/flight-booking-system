package com.fbs.central_api.services;

import com.fbs.central_api.connectors.NotificationApiConnector;
import com.fbs.central_api.dtos.AirlineRegistrationRequestDto;
import com.fbs.central_api.dtos.AirlineRejectDto;
import com.fbs.central_api.models.Airline;
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

    public void mailSystemAdminForAirlineRegistration(List<AppUser> systemAdmins, Airline airline) {
        // We will apply one loop over all the system admins and one by one we will mail all the system admins
        for(AppUser systemAdmin: systemAdmins) {
            // We need to call notification api one by one for all the system admins
            // So, to call notification api from the central api we require -> NotificationApiConnector class
            AirlineRegistrationRequestDto airlineRegistrationRequestDto = new AirlineRegistrationRequestDto();
            airlineRegistrationRequestDto.setAirline(airline);
            airlineRegistrationRequestDto.setAppAdmin(systemAdmin);
            notificationApiConnector.notifySystemAdminForAirlineRegistration(airlineRegistrationRequestDto);
        }
    }

    public void mailAcceptRequestToAirlineAdmin(Airline airline) {
        notificationApiConnector.notifyAcceptRequestToAirlineAdmin(airline);
    }

    public void mailRejectRequestToAirlineAdmin(String airlineAdminEmail, String airlineAdminName, String rejectReason) {
        AirlineRejectDto airlineRejectDto = new AirlineRejectDto();
        airlineRejectDto.setAirlineAdminEmail(airlineAdminEmail);
        airlineRejectDto.setAirlineAdminName(airlineAdminName);
        airlineRejectDto.setRejectReason(rejectReason);
        notificationApiConnector.notifyRejectRequestToAirlineAdmin(airlineRejectDto);
    }
}
