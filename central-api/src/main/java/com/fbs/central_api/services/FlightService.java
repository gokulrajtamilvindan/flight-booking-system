package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.dtos.FlightDetailsDto;
import com.fbs.central_api.dtos.SeatMappingDto;
import com.fbs.central_api.dtos.SubFlightDto;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.exceptions.UnAuthorizedException;
import com.fbs.central_api.models.*;
import com.fbs.central_api.utilities.MappingUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FlightService {
    UserService userService;
    MappingUtility mappingUtility;
    AirlineService airlineService;
    AircraftService aircraftService;
    DbApiConnector dbApiConnector;

    @Autowired
    public FlightService(UserService userService,
                         MappingUtility mappingUtility,
                         AirlineService airlineService,
                         AircraftService aircraftService,
                         DbApiConnector dbApiConnector) {
        this.userService = userService;
        this.mappingUtility = mappingUtility;
        this.airlineService = airlineService;
        this.aircraftService = aircraftService;
        this.dbApiConnector = dbApiConnector;
    }

    public Flight createFlight(FlightDetailsDto flightDetailsDto,
                             String authorization) {
        String token = authorization.substring(7);
        AppUser user = userService.getUserFromToken(token);
        if(!user.getUserType().equals(UserTypeEnum.AIRLINE_ADMIN.toString())) {
            throw new UnAuthorizedException("User is Not Allowed to Create Flight");
        }
        Airline airline = airlineService.getAirlineByAdminId(user.getId());
        Aircraft aircraft = aircraftService.getAircraftById(flightDetailsDto.getAircraftId());
        Flight flight = mappingUtility.mapFlightDetailsDtoToFlight(flightDetailsDto, airline, aircraft);
        // We need to save the flight inside the database
        flight = dbApiConnector.callCreateFlightEndpoint(flight);
        // We need to create different classes for the flight
        if(flightDetailsDto.getSubFlightDtos().size() > 0) {
            createSubFlight(flightDetailsDto.getSubFlightDtos(), flight);
        }
        List<SeatMappingDto> seatMappingDtos = flightDetailsDto.getSeatMappingDtos();
        for(int i = 0; i < seatMappingDtos.size(); i++) {
            SeatMappingDto seatMappingDto = seatMappingDtos.get(i);
            FlightSeatMapping flightSeatMapping = mappingUtility.mapFlightSeatMappingDtoToFlightSeatMapping(seatMappingDto, flight);
            dbApiConnector.callCreateFlightSeatMappingEndpoint(flightSeatMapping);
        }
        // Mail -> We need to mail to the flight admin that your requested flight got created inside the system

        return flight;
    }

    public void createSubFlight(List<SubFlightDto> subFlightDtos,
                                Flight flight) {
        for(SubFlightDto subFlightDto: subFlightDtos) {
            // We need to map subFlightDto one by one to subFlightModel
            SubFlight subFlight = mappingUtility.mapSubFlightDtoToSubFlight(subFlightDto, flight);
            dbApiConnector.callCreateSubFlightEndpoint(subFlight);
        }
    }

    public Object searchFlight(String sourceAirport,
                                     String destinationAirport,
                                     String dateTime) {
        return dbApiConnector.callSearchFlightEndpoint(sourceAirport, destinationAirport, dateTime);
    }
}
