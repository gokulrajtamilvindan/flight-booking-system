package com.fbs.notification_api.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirLine {
    UUID id;
    String website;
    String airLineName;
    String companyName;
    int employees;
    int totalFlights;
    AppUser admin;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
