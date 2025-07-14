package com.fbs.db_api.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

/*
    This class is going to represent booking details

    Direct Flight -> Delhi - mumbai (subFlight list will be empty)
    Connecting Flight -> Sub Flight list have all the subFlight passenger is going to cover
    --Delhi to mumbai to chandigarh to sikkim
    --subFlight -> [(Delhi - mumbai), (mumbai - chandigarh)]
 */
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    Flight flight;
    @ManyToMany
    List<SubFlight> subFlights;
    @ManyToOne
    AppUser bookedBy;
    int totalAmount;
    String passengerName;
}
