package com.fbs.central_api.dtos;

import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirlineRegistrationRequestDto {
    AppUser appAdmin;
    Airline airline;
}
