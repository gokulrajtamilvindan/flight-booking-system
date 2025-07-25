package com.fbs.central_api.controllers;

import com.fbs.central_api.dtos.AircraftRegistrationDto;
import com.fbs.central_api.dtos.AirlineRegistrationDto;
import com.fbs.central_api.models.Aircraft;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.services.AircraftService;
import com.fbs.central_api.services.AirlineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/central/airline")
public class AirlineController {
    AirlineService airlineService;
    AircraftService aircraftService;

    @Autowired
    public AirlineController(AirlineService airlineService,
                             AircraftService aircraftService) {
        this.airlineService = airlineService;
        this.aircraftService = aircraftService;
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

    /*
        This endpoint will get triggered from the mail which we have sent to system admin.
        When System Admin will check over accept button this endpoint will trigger.
        Work of this endpoint to change the status of airline to ACTIVE also it will change the status of airline admin also to active
     */

    @GetMapping("/request/accept/{airlineId}")
    public void acceptAirlineRequest(@PathVariable UUID airlineId) {
        log.info("Accept Airline function - airlineId : " + airlineId.toString());
        // we will be calling our airlineService to change the status of airline and airline admin
        airlineService.acceptAirlineRequest(airlineId);
    }

    @GetMapping("/request/reject/{airlineId}")
    public void rejectAirlineRequest(@PathVariable UUID airlineId) {
        log.info("Reject Airline function - airlineId : " + airlineId.toString());
        airlineService.rejectAirlineRequest(airlineId);
    }

    @PostMapping("/aircraft/register")
    public Aircraft registerAircraft(@RequestBody AircraftRegistrationDto aircraftRegistrationDto,
                                     @RequestHeader String authorization) {
        return aircraftService.registerAircraft(aircraftRegistrationDto, authorization);
    }
}
