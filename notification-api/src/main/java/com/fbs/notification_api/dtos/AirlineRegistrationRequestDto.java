package com.fbs.notification_api.dtos;

import com.fbs.notification_api.models.Airline;
import com.fbs.notification_api.models.AppUser;
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
