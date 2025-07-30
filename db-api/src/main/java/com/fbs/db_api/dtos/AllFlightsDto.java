package com.fbs.db_api.dtos;

import com.fbs.db_api.models.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllFlightsDto {
    List<Flight> flights;
}
