package com.fbs.central_api.dtos;

import com.fbs.central_api.models.AirLine;
import com.fbs.central_api.models.AppUser;
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
