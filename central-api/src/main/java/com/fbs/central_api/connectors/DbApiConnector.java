package com.fbs.central_api.connectors;

import com.fbs.central_api.dtos.AllUsersDto;
import com.fbs.central_api.models.Airline;
import com.fbs.central_api.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
    Purpose of this class is to connect with db-api endpoints.
 */

@Slf4j
@Component
public class DbApiConnector {
    RestTemplate restTemplate;

    @Autowired
    public DbApiConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${db.api.url}")
    String dbApiBaseUrl; // For this variable pick the value from application.properties

    /*
            We will write one method and that method will be hitting request to db-api create user endpoint
    */

    public AppUser callCreateUserEndpoint(AppUser user) {
        log.info("Inside callCreateUserEndpoint method with user object : " + user.toString());
        // 1. Create URL that you want to call
        String url = dbApiBaseUrl + "/user/create";
        // 2. We want to tell what rest method we want to use and what request body we want to pass
        RequestEntity request = RequestEntity.post(url).body(user);
        log.info("Created request : " + request.toString());
        // 3. Hit or make the request on the postman to do this step we click send button
        //    but here we are going to use a class called RestTemplate
        // Send button(postman) -> RestTemplate class exchange method
        log.info("Calling dbApi create user endpoint");
        ResponseEntity<AppUser> response = restTemplate.exchange(url, HttpMethod.POST, request, AppUser.class);
        log.info("Response : " + response.toString());
        return response.getBody();
    }

    /*
        We will write one method and that method will be hitting request to db-api create airline endpoint
     */

    public Airline callCreateAirlineEndpoint(Airline airline) {
        log.info("Inside callCreateAirlineEndpoint method with airline object : " + airline.toString());
        // 1. Create URL that you want to call
        String url = dbApiBaseUrl + "/airline/create";
        // 2. We want to tell what rest method we want to use and what request body we want to pass
        RequestEntity request = RequestEntity.post(url).body(airline);
        log.info("Created request : " + request.toString());
        // 3. Hit or make the request on the postman to do this step we click send button
        //    but here we are going to use a class called RestTemplate
        // Send button(postman) -> RestTemplate class exchange method
        log.info("Calling dbApi create airline endpoint");
        ResponseEntity<Airline> response = restTemplate.exchange(url, HttpMethod.POST, request, Airline.class);
        log.info("Response : " + response.toString());
        return response.getBody();
    }

    /*
        This function will make request to db-api to getAllSystemAdmins endpoint such that we will get all the system admins from the user table
     */

    public List<AppUser> callGetAllUsersByUserTypeEndpoint(String userType) {
        String url = dbApiBaseUrl + "/user/get/" + userType;
        RequestEntity request = RequestEntity.get(url).build();
        ResponseEntity<AllUsersDto> response = restTemplate.exchange(url, HttpMethod.GET, request, AllUsersDto.class);
        return response.getBody().getAppUsers();
    }
}
