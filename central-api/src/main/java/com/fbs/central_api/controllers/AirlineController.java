package com.fbs.central_api.controllers;

import com.fbs.central_api.dtos.AirlineRegistrationDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.services.AirlineService;
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
public class AirlineController {
    AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirlineRegistrationDto airlineRegistrationDto) {
        // Airline Details -> We need to catch that airline details json in an airlineDetailsDto
        // From here we should call airlineService for further processing
        // From this method we are going to call
        log.info("airlineRegistration method got called with the request body : " + airlineRegistrationDto.toString());
        log.info("calling airlineService registerAirline method");
        // From here we will call airline service register airline method
        Airline airline = airlineService.registerAirline(airlineRegistrationDto);
        return new ResponseEntity<>(airline, HttpStatus.CREATED);
    }
}
