package com.fbs.central_api.dtos;

import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineRegistrationRequestDto {
    AppUser appAdmin;
    Airline airline;
}
