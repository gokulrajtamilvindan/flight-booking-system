package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "airlines")
public class AirLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(unique = true, nullable = false)
    String website;
    @Column(unique = true, nullable = false)
    String airLineName;
    @Column(unique = true, nullable = false)
    String companyName;
    int employees;
    int totalFlights;
    @OneToOne
    AppUser admin;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
