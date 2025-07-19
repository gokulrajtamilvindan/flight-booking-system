package com.fbs.central_api.utilities;

import com.fbs.central_api.dtos.AirlineRegistrationDto;
import com.fbs.central_api.enums.AirlineStatusEnum;
import com.fbs.central_api.enums.UserStatusEnum;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
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

    public Airline mapAirlineRegistrationDtoToAirline(AirlineRegistrationDto airlineRegistrationDto, AppUser airlineAdmin) {
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
}
