package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "airlines")
public class AirLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(unique = true, nullable = false)
    String website;
    @Column(unique = true, nullable = false)
    String airlineName;
    @Column(unique = true, nullable = false)
    String companyName;
    int employees;
    int totalFlights;
    @OneToOne
    AppUser admin;
}
