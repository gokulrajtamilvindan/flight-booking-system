package com.fbs.db_api.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    int priority;
    String sourceAirport;
    String destinationAirport;
    LocalDateTime boardingTime;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    int boardingMinutes;
}
