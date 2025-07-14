package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "sub_flight_booked_seat")
public class SubFlightSeatBooked extends SeatBooked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    SubFlight subFlight;
    @ManyToOne
    AppUser bookedBy;
}
