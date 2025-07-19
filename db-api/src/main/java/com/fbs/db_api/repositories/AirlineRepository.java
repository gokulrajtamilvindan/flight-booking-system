package com.fbs.db_api.repositories;

import com.fbs.db_api.models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, UUID> {
}
