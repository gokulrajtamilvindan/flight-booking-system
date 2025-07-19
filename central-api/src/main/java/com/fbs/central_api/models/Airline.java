package com.fbs.central_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Airline {
    UUID id;
    String website;
    String airlineName;
    String companyName;
    int employees;
    int totalFlights;
    AppUser admin;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
