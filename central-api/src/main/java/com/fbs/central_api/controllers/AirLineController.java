package com.fbs.central_api.controllers;

import com.fbs.central_api.dtos.AirLineRegistrationDto;
import com.fbs.central_api.models.AirLine;
import com.fbs.central_api.services.AirLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/central/airline")
public class AirLineController {
    AirLineService airLineService;

    @Autowired
    public AirLineController(AirLineService airLineService) {
        this.airLineService = airLineService;
    }

    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirLineRegistrationDto airLineRegistrationDto) {
        // Airline Details -> We need to catch that airline details json in an airlineDetailsDto
        // From here we should call airLineService for further processing
        // From this method we are going to call
        log.info("airLineRegistration method got called with the request body : " + airLineRegistrationDto.toString());
        log.info("calling airLineService registerAirLine method");
        // From here we will call airline service register airline method
        AirLine airLine = airLineService.registerAirLine(airLineRegistrationDto);
        return new ResponseEntity<>(airLine, HttpStatus.CREATED);
    }
}
