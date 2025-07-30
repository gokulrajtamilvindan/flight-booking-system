package com.fbs.central_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetailsDto {
    String sourceAirport;
    String destinationAirport;
    String flightType;
    int totalTime;
    LocalDateTime boardingTime;
    int boardingMinutes;
    LocalDateTime departureTime;
    LocalDateTime arrivalTime;
    boolean isConnecting;
    UUID aircraftId;
    List<SubFlightDto> subFlightDtos;
    List<SeatMappingDto> seatMappingDtos;
}
