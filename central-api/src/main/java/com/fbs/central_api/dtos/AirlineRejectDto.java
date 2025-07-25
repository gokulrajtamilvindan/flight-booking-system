package com.fbs.central_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineRejectDto {
    String airlineAdminName;
    String airlineAdminEmail;
    String rejectReason;
}
