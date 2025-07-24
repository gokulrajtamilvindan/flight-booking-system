package com.fbs.central_api.controllers;

import com.fbs.central_api.dtos.LoginDto;
import com.fbs.central_api.exceptions.InvalidCredentialsException;
import com.fbs.central_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Purpose of this general user controller to have all the common endpoints of the all the type of users
 */

@RestController
@RequestMapping("/api/v1/central/user")
public class GeneralUserController {
    UserService userService;

    @Autowired
    public GeneralUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        try {
            String token = userService.isValidCredentials(loginDto.getEmail(), loginDto.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
