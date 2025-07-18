package com.fbs.central_api.utilities;

import com.fbs.central_api.dtos.AirLineRegistrationDto;
import com.fbs.central_api.enums.AirLineStatusEnum;
import com.fbs.central_api.enums.UserStatusEnum;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.models.AirLine;
import com.fbs.central_api.models.AppUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MappingUtility {
    public AppUser mapAirLineRegistrationDtoToAppUser(AirLineRegistrationDto airLineRegistrationDto) {
        AppUser airLineAdmin = new AppUser();
        airLineAdmin.setEmail(airLineRegistrationDto.getEmail());
        airLineAdmin.setName(airLineRegistrationDto.getAirLineName() + " Admin");
        airLineAdmin.setUserType(UserTypeEnum.AIRLINE_ADMIN.toString());
        airLineAdmin.setContactNumber(airLineRegistrationDto.getContactNumber());
        airLineAdmin.setVerified(false);
        airLineAdmin.setStatus(UserStatusEnum.INACTIVE.toString());
        airLineAdmin.setPassword(airLineRegistrationDto.getPassword());
        airLineAdmin.setCreatedAt(LocalDateTime.now());
        airLineAdmin.setUpdatedAt(LocalDateTime.now());
        return airLineAdmin;
    }

    public AirLine mapAirLineRegistrationDtoToAirLine(AirLineRegistrationDto airLineRegistrationDto, AppUser airLineAdmin) {
        AirLine airLine = new AirLine();
        airLine.setAirLineName(airLineRegistrationDto.getAirLineName());
        airLine.setStatus(AirLineStatusEnum.INACTIVE.toString());
        airLine.setAdmin(airLineAdmin);
        airLine.setEmployees(airLineRegistrationDto.getEmployees());
        airLine.setWebsite(airLineRegistrationDto.getWebsite());
        airLine.setCompanyName(airLineRegistrationDto.getCompanyName());
        airLine.setTotalFlights(airLineRegistrationDto.getTotalFlights());
        airLine.setCreatedAt(LocalDateTime.now());
        airLine.setUpdatedAt(LocalDateTime.now());
        return airLine;
    }
}
