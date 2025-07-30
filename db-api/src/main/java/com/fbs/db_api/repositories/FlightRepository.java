package com.fbs.db_api.repositories;

import com.fbs.db_api.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    @Query(value = "select * from flight where source_airport=:sourceAirport and destination_airport=:destinationAirport and departure_time>=:dateTime", nativeQuery = true)
    public List<Flight> getAllFlights(String sourceAirport,
                                      String destinationAirport,
                                      String dateTime);
}
