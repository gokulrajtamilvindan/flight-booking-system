package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.dtos.AirLineRegistrationDto;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utilities.MappingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirLineService {
    MappingUtility mappingUtility;
    DbApiConnector dbApiConnector;

    @Autowired
    public AirLineService(MappingUtility mappingUtility,
                          DbApiConnector dbApiConnector) {
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
    }

    /*
        This function work is to call db api and save airline details in airline table and airline admins details in user table.
     */
    public void registerAirLine(AirLineRegistrationDto airLineRegistrationDto) {
        log.info("airLineService registerAirLine method called : " + airLineRegistrationDto.toString());
        // before calling db api lets map the details which we are getting in dto to respective models
        // Ideally we should not write mapping logic here we should keep it in different class
        AppUser airLineAdmin = mappingUtility.mapAirLineRegistrationDtoToAppUser(airLineRegistrationDto);
        // After creating airline admin object we should call db-api to save this object to the table.
        // That means we need to connect with db-api AppUser registration endpoint
        // So, to connect with the db-api app user registration endpoint we should create connector class
        log.info("Calling dbApiConnector callCreateUserEndpoint with payload : " + airLineAdmin.toString());
        airLineAdmin = dbApiConnector.callCreateUserEndpoint(airLineAdmin);
        // Mapping airLineRegistrationDto to AirLine object -> we need to write another mapping utility
    }
}
