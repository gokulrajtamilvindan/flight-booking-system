package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.dtos.AircraftRegistrationDto;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.Aircraft;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utilities.MappingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AircraftService {
    UserService userService;
    AirlineService airlineService;
    MappingUtility mappingUtility;
    DbApiConnector dbApiConnector;

    @Autowired
    public AircraftService(UserService userService,
                           AirlineService airlineService,
                           MappingUtility mappingUtility,
                           DbApiConnector dbApiConnector) {
        this.userService = userService;
        this.airlineService = airlineService;
        this.mappingUtility = mappingUtility;
        this.dbApiConnector = dbApiConnector;
    }

    public Aircraft registerAircraft(AircraftRegistrationDto aircraftRegistrationDto, String authorization) {
        // We need to Decrypt authorization and bring airline details
        String token = authorization.substring(7);
        AppUser airlineAdmin = userService.getUserFromToken(token);
        if(!airlineAdmin.getUserType().equals(UserTypeEnum.AIRLINE_ADMIN.toString())) {
            throw new UnAuthorizedException("User is not allowed to register Aircraft");
        }
        // With the help of airlineAdmin we need to get airline object
        Airline airline = airlineService.getAirlineByAdminId(airlineAdmin.getId());
        // Mapping Logic to map aircraftDto to aircraft
        Aircraft aircraft = mappingUtility.mapAircraftRegistrationDtoToAircraft(aircraftRegistrationDto, airline);
        // Now we need to call save aircraft method
        return createAircraft(aircraft);
    }

    public Aircraft createAircraft(Aircraft aircraft) {
        return dbApiConnector.callCreateAircraftEndpoint(aircraft);
    }

    public Aircraft getAircraftById(UUID id) {
        return dbApiConnector.callGetAircraftByIdEndpoint(id);
    }
}
