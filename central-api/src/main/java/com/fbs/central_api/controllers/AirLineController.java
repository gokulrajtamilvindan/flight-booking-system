package com.fbs.central_api.controllers;

import com.fbs.central_api.dtos.AirLineRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/airline")
@Slf4j
public class AirLineController {
    @PostMapping("/register")
    public ResponseEntity registerAirline(@RequestBody AirLineRegistrationDto airLineRegistrationDto) {
        // Airline Details -> We need to catch that airline details json in an airlineDetailsDto
        // From here we should call airLineService for further processing
        // From this method we are
        log.info("airLineRegistration method got called with the request body : " + airLineRegistrationDto.toString());
        log.info("calling airLineService registerAirLine method");
    }
}
