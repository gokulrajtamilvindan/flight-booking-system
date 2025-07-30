package com.fbs.central_api.utilities;

import com.fbs.central_api.dtos.*;
import com.fbs.central_api.enums.AirlineStatusEnum;
import com.fbs.central_api.enums.UserStatusEnum;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MappingUtility {
    public AppUser mapAirlineRegistrationDtoToAppUser(AirlineRegistrationDto airlineRegistrationDto) {
        AppUser airlineAdmin = new AppUser();
        airlineAdmin.setEmail(airlineRegistrationDto.getEmail());
        airlineAdmin.setName(airlineRegistrationDto.getAirlineName() + " Admin");
        airlineAdmin.setUserType(UserTypeEnum.AIRLINE_ADMIN.toString());
        airlineAdmin.setContactNumber(airlineRegistrationDto.getContactNumber());
        airlineAdmin.setVerified(false);
        airlineAdmin.setStatus(UserStatusEnum.INACTIVE.toString());
        airlineAdmin.setPassword(airlineRegistrationDto.getPassword());
        airlineAdmin.setCreatedAt(LocalDateTime.now());
        airlineAdmin.setUpdatedAt(LocalDateTime.now());
        return airlineAdmin;
    }

    public Airline mapAirlineRegistrationDtoToAirline(AirlineRegistrationDto airlineRegistrationDto,
                                                      AppUser airlineAdmin) {
        Airline airline = new Airline();
        airline.setAirlineName(airlineRegistrationDto.getAirlineName());
        airline.setStatus(AirlineStatusEnum.INACTIVE.toString());
        airline.setAdmin(airlineAdmin);
        airline.setEmployees(airlineRegistrationDto.getEmployees());
        airline.setWebsite(airlineRegistrationDto.getWebsite());
        airline.setCompanyName(airlineRegistrationDto.getCompanyName());
        airline.setTotalFlights(airlineRegistrationDto.getTotalFlights());
        airline.setCreatedAt(LocalDateTime.now());
        airline.setUpdatedAt(LocalDateTime.now());
        return airline;
    }

    public Aircraft mapAircraftRegistrationDtoToAircraft(AircraftRegistrationDto aircraftRegistrationDto,
                                                         Airline airline) {
        Aircraft aircraft = new Aircraft();
        aircraft.setAirline(airline);
        aircraft.setCapacity(aircraftRegistrationDto.getCapacity());
        aircraft.setManufacturer(aircraftRegistrationDto.getManufacturer());
        aircraft.setModelName(aircraftRegistrationDto.getModelName());
        aircraft.setTotalFlights(aircraftRegistrationDto.getTotalFlights());
        aircraft.setModelNumber(aircraftRegistrationDto.getModelNumber());
        aircraft.setBuildDate(aircraftRegistrationDto.getBuildDate());
        aircraft.setCreatedAt(LocalDateTime.now());
        aircraft.setUpdatedAt(LocalDateTime.now());
        return aircraft;
    }

    public Flight mapFlightDetailsDtoToFlight(FlightDetailsDto flightDetailsDto,
                                              Airline airline,
                                              Aircraft aircraft) {
        Flight flight = new Flight();
        flight.setAirline(airline);
        flight.setAircraft(aircraft);
        flight.setConnecting(flightDetailsDto.isConnecting());
        flight.setBoardingTime(flightDetailsDto.getBoardingTime());
        flight.setArrivalTime(flightDetailsDto.getArrivalTime());
        flight.setDepartureTime(flightDetailsDto.getDepartureTime());
        flight.setBoardingMinutes(flightDetailsDto.getBoardingMinutes());
        flight.setTotalTime(flightDetailsDto.getTotalTime());
        flight.setFlightType(flightDetailsDto.getFlightType());
        flight.setSourceAirport(flightDetailsDto.getSourceAirport());
        flight.setDestinationAirport(flightDetailsDto.getDestinationAirport());
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());
        return flight;
    }

    public FlightSeatMapping mapFlightSeatMappingDtoToFlightSeatMapping(SeatMappingDto seatMappingDto,
                                                                        Flight flight) {
        FlightSeatMapping flightSeatMapping = new FlightSeatMapping();
        flightSeatMapping.setFlight(flight);
        flightSeatMapping.setRange(seatMappingDto.getRange());
        flightSeatMapping.setClassName(seatMappingDto.getClassName());
        flightSeatMapping.setBasePrice(seatMappingDto.getBasePrice());
        flightSeatMapping.setWindowPrice(seatMappingDto.getWindowPrice());
        flightSeatMapping.setTotalWindow(seatMappingDto.getTotalWindow());
        flightSeatMapping.setCreatedAt(LocalDateTime.now());
        flightSeatMapping.setUpdatedAt(LocalDateTime.now());
        return flightSeatMapping;
    }

    public SubFlight mapSubFlightDtoToSubFlight(SubFlightDto subFlightDto,
                                                Flight flight) {
        SubFlight subFlight = new SubFlight();
        subFlight.setFlight(flight);

        subFlight.setArrivalTime(subFlightDto.getArrivalTime());
        subFlight.setBoardingTime(subFlightDto.getBoardingTime());
        subFlight.setBoardingMinutes(subFlightDto.getBoardingMinutes());
        subFlight.setDepartureTime(subFlightDto.getDepartureTime());
        subFlight.setPriority(subFlightDto.getPriority());
        subFlight.setDestinationAirport(subFlightDto.getDestinationAirport());
        subFlight.setSourceAirport(subFlightDto.getSourceAirport());
        subFlight.setCreatedAt(LocalDateTime.now());
        subFlight.setUpdatedAt(LocalDateTime.now());
        return subFlight;
    }
}
