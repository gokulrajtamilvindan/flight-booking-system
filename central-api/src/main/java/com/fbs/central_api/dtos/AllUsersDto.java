package com.fbs.central_api.dtos;

import com.fbs.central_api.models.AppUser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllUsersDto {
    List<AppUser> appUsers;
}
