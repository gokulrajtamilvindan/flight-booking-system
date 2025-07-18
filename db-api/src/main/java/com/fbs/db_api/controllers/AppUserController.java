package com.fbs.db_api.controllers;

import com.fbs.db_api.dtos.AllUsersDto;
import com.fbs.db_api.models.AppUser;
import com.fbs.db_api.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    As this is simple crud endpoint controller and in db api we don't write any logics,
    So, we don't require any service class we will be directly calling repository layer.
 */

@RestController
@RequestMapping("/api/v1/db/user")
public class AppUserController {
    AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody AppUser user) {
        // To save user we need appUser repository
        AppUser userResponse = appUserRepository.save(user);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userType}")
    public ResponseEntity getAllUsersByUserType(@PathVariable String userType) {
        List<AppUser> users = appUserRepository.findByUserType(userType);
        AllUsersDto allUsersDto = new AllUsersDto();
        allUsersDto.setAppUsers(users);
        return new ResponseEntity<>(allUsersDto, HttpStatus.OK);
    }
}
