package com.fbs.db_api.controllers;

import com.fbs.db_api.repositories.FlightSeatMappingRepository;
import com.fbs.db_api.models.FlightSeatMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/seatmapping")
public class FlightSeatMappingController {
    FlightSeatMappingRepository flightSeatMappingRepository;

    @Autowired
    public FlightSeatMappingController(FlightSeatMappingRepository flightSeatMappingRepository) {
        this.flightSeatMappingRepository = flightSeatMappingRepository;
    }

    @PostMapping("/create")
    public FlightSeatMapping createFlightSeatMapping(@RequestBody FlightSeatMapping flightSeatMapping) {
        flightSeatMappingRepository.save(flightSeatMapping);
        return flightSeatMapping;
    }
}
