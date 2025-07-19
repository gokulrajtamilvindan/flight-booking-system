package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.dtos.AirlineRegistrationDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utilities.MappingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AirlineService {
    MappingUtility mappingUtility;
    DbApiConnector dbApiConnector;
    UserService userService;
    MailService mailService;

    @Autowired
    public AirlineService(MappingUtility mappingUtility,
                          DbApiConnector dbApiConnector,
                          UserService userService,
                          MailService mailService) {
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
        this.userService = userService;
        this.mailService = mailService;
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
}
