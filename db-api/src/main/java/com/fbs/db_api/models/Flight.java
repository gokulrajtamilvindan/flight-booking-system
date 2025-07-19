package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Airline airline;
    @ManyToOne
    AirCraft airCraft;
    String sourceAirport; // mumbai
    String destinationAirport; // new york
    String flightType; //International, Domestic, Emergency
    int totalTime; // Total time in minutes
    LocalDateTime boardingTime; // when passengers need to sit in the aircraft
    int boardingMinutes;
    LocalDateTime departureTime; // when aircraft is going to take-off //IST Time zone
    LocalDateTime arrivalTime; //when aircraft is going to land // EST Time zone
    boolean isConnecting; // is this flight a connecting flight ? or not
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
