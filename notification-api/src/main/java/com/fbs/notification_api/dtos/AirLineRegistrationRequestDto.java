package com.fbs.notification_api.dtos;

import com.fbs.notification_api.models.AirLine;
import com.fbs.notification_api.models.AppUser;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirLineRegistrationRequestDto {
    AppUser appAdmin;
    AirLine airLine;
}
