package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "aircraft")
public class AirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int modelNumber;
    String manufacturer;
    String modelName;
    int totalFlights;
    LocalDate buildDate;
    int capacity;
    @ManyToOne
    AirLine airLine;
}
