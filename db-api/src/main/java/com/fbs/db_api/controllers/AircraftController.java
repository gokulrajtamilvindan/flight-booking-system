package com.fbs.db_api.controllers;

import com.fbs.db_api.models.Aircraft;
import com.fbs.db_api.repositories.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/db/aircraft")
public class AircraftController {
    AircraftRepository aircraftRepository;

    @Autowired
    public AircraftController(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @PostMapping("/create")
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        aircraftRepository.save(aircraft);
        return aircraft;
    }
}
