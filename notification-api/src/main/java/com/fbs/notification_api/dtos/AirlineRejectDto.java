package com.fbs.notification_api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirlineRejectDto {
    String airlineAdminName;
    String airlineAdminEmail;
    String rejectReason;
}
