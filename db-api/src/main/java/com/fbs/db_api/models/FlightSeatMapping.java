package com.fbs.db_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/*
    This flight seat mapping model will be used for non connecting flights
*/

@Data
@Entity
@Table(name = "flight_seat_mapping")
public class FlightSeatMapping extends SeatMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
