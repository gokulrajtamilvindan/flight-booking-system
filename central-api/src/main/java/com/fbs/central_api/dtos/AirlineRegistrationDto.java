package com.fbs.central_api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirlineRegistrationDto {
    String website;
    String airlineName;
    String companyName;
    int employees;
    int totalFlights;
    String email;
    String password;
    Long contactNumber;
}
