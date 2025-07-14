package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.util.UUID;

/*
    This booking table we are strictly going to use for non connecting flights
*/
@Entity
@Table(name = "flight_booked_seats")
public class FlightSeatBooked extends SeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToOne
    AppUser bookedBy;
}
