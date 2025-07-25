package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.connectors.GeminiApiConnector;
import com.fbs.central_api.dtos.AirlineRegistrationDto;
import com.fbs.central_api.enums.AirlineStatusEnum;
import com.fbs.central_api.enums.UserStatusEnum;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.models.GeminiApiResponse;
import com.fbs.central_api.utilities.MappingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class AirlineService {
    MappingUtility mappingUtility;
    DbApiConnector dbApiConnector;
    UserService userService;
    MailService mailService;
    GeminiApiConnector geminiApiConnector;

    @Autowired
    public AirlineService(MappingUtility mappingUtility,
                          DbApiConnector dbApiConnector,
                          UserService userService,
                          MailService mailService,
                          GeminiApiConnector geminiApiConnector) {
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
        this.geminiApiConnector = geminiApiConnector;
    }

    /*
        This function work is to call db api and save airline details in airline table and airline admins details in user table.
     */

    public Airline registerAirline(AirlineRegistrationDto airlineRegistrationDto) {
        log.info("airlineService registerAirline method called : " + airlineRegistrationDto.toString());
        // before calling db api lets map the details which we are getting in dto to respective models
        // Ideally we should not write mapping logic here we should keep it in different class
        AppUser airlineAdmin = mappingUtility.mapAirlineRegistrationDtoToAppUser(airlineRegistrationDto);
        // After creating airline admin object we should call db-api to save this object to the table.
        // That means we need to connect with db-api AppUser registration endpoint
        // So, to connect with the db-api app user registration endpoint we should create connector class
        log.info("Calling dbApiConnector callCreateUserEndpoint with payload : " + airlineAdmin.toString());
        airlineAdmin = dbApiConnector.callCreateUserEndpoint(airlineAdmin);
        // Mapping airlineRegistrationDto to Airline object -> we need to write another mapping utility
        Airline airline = mappingUtility.mapAirlineRegistrationDtoToAirline(airlineRegistrationDto, airlineAdmin);
        // Now we got the airline object we need to save this airline into the airline table
        // So, to this airline into airline table we need to call db-api connector
        // Internally db-api connector will be calling your create api endpoint
        airline = dbApiConnector.callCreateAirlineEndpoint(airline);
        // When we habe created both the inactive records for airline as well as airline admin
        // we need to think something how can we email?
        // we will be creating another microservice whose work is to send notifications to the use via email
        // Now we need to mail application admin regarding airline registration request
        // So, to mail we require application admin object
        // We need to mail all the system admins so, we need to get all the system admins from the table
        List<AppUser> systemAdminsList = userService.getAllSystemAdmins();
        // Mail All System Admins
        mailService.mailSystemAdminForAirlineRegistration(systemAdminsList, airline);
        return airline;
    }

    public Airline getAirlineById(UUID airlineId) {
        // So, to get the airline by id we need to call database api
        // So, to call database api we require database api connector
        return dbApiConnector.callAirlineByIdEndpoint(airlineId);
    }

    public Airline updateAirlineDetails(Airline airline) {
        // We should call database api to update the airline object in the airline table
        return dbApiConnector.callUpdateAirlineEndpoint(airline);
    }

    public Airline acceptAirlineRequest(UUID airlineId) {
        // 1. to get the airline object on the basis of Id.
        // 2. Update the status of airline as well status of airline Admin.
        // 3. Save those changes into their respective tables in the database.
        // 4. We need to mail airline admin that congratulations your request got approved.
        log.info("airlineId : " + airlineId.toString());
        Airline airline = getAirlineById(airlineId);
        airline.setStatus(AirlineStatusEnum.ACTIVE.toString());
        airline.setUpdatedAt(LocalDateTime.now());
        // So, now we want to save the changes of airline to airline table and airline admin to user table
        // Airline Admin -> update the status of airline admin to ACTIVE
        airline = updateAirlineDetails(airline);
        AppUser airlineAdmin = airline.getAdmin();
        airlineAdmin.setStatus(UserStatusEnum.ACTIVE.toString());
        airline.setUpdatedAt(LocalDateTime.now());
        userService.updateUserDetails(airlineAdmin);
        // Mail Airline Admin that your request got accepted now you are part of our application.
        mailService.mailAcceptRequestToAirlineAdmin(airline);
        return airline;
    }

    public void rejectAirlineRequest(UUID airlineId) {
        // Verify the ID of airline is correct or not?
        // If it is correct -> fine or else we will throw exception
        Airline airline = this.getAirlineById(airlineId);
        airline.setStatus(AirlineStatusEnum.REJECTED.toString());
        this.updateAirlineDetails(airline);
        // We need to generate rejection reason
        String prompt = "Generate Professional Failure Reason for the airline details : " + airline.toString();
        GeminiApiResponse geminiApiResponse = geminiApiConnector.callGeminiGenAiEndpoint(prompt);
        String rejectReason = geminiApiResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
        // We need to mail this rejectReason to airline admin that his request got cancelled because of this reasons.
        // I need to call notification api such that Airline admin will receive mail that these are the resons for rejection.
        mailService.mailRejectRequestToAirlineAdmin(airline.getAdmin().getEmail(), airline.getAdmin().getName(), rejectReason);
    }

    public Airline getAirlineByAdminId(UUID adminId) {
        // DbApiConnector to get the airline details
        return dbApiConnector.callGetAirlineByAdminIdEndpoint(adminId);
    }
}
