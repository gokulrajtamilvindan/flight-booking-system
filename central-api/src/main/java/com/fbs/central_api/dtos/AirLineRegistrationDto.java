package com.fbs.central_api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirLineRegistrationDto {
    String website;
    String airLineName;
    String companyName;
    int employees;
    int totalFlights;
    String email;
    String password;
    Long contactNumber;
}
