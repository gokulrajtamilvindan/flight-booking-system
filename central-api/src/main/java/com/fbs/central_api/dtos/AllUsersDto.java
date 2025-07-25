package com.fbs.central_api.dtos;

import com.fbs.central_api.models.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllUsersDto {
    List<AppUser> appUsers;
}
