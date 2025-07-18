package com.fbs.db_api.dtos;

import com.fbs.db_api.models.AppUser;
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
