package com.fbs.db_api.controllers;

import com.fbs.db_api.models.AirLine;
import com.fbs.db_api.repositories.AirLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    As this is simple crud endpoint controller and in db api we don't write any logics,
    So, we don't require any service class we will be directly calling repository layer.
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/db/airline")
public class AirLineController {
    AirLineRepository airlineRepository;

    @Autowired
    public AirLineController(AirLineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createAirline(@RequestBody AirLine airLine) {
        // To save airline we need airline repository
        log.info("Request Body : " + airLine.toString());
        AirLine airlineResponse = airlineRepository.save(airLine);
        return new ResponseEntity<>(airlineResponse, HttpStatus.CREATED);
    }
}
