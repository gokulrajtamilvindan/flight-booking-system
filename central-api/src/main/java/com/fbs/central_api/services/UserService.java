package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.exceptions.InvalidCredentialsException;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utilities.AuthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    This class is going to contain all the user related logics
 */

@Service
public class UserService {
    DbApiConnector dbApiConnector;
    AuthUtility authUtility;

    @Autowired
    public UserService(DbApiConnector dbApiConnector,
                       AuthUtility authUtility) {
        this.dbApiConnector = dbApiConnector;
        this.authUtility = authUtility;
    }

    /*
        Work of this function is get all system admins from our users table
     */

    public List<AppUser> getAllSystemAdmins() {
        // To get all the system admins from the user table we need to call the DbApiConnector
        return dbApiConnector.callGetAllUsersByUserTypeEndpoint(UserTypeEnum.SYSTEM_ADMIN.toString());
    }

    public AppUser updateUserDetails(AppUser user) {
        return dbApiConnector.callUpdateUserEndpoint(user);
    }

    /*
        This Function should be able to validate the credentials of users
        1. This function will first get the user record from the userTable on the basis of email
        2. After getting the user this function will compare the password is correct or not
        3. If it is correct we will return true else we will return false.
     */

    public String isValidCredentials(String email, String password) {
        AppUser user = this.getUserByEmail(email);
        if(user.getPassword().equals(password)) {
            // If password is correct, going to return a token.
            // If it is incorrect, going to return null value.
            return authUtility.generateToken(user.getEmail(), user.getPassword(), user.getUserType());
        }
        throw new InvalidCredentialsException("Email or Password is Wrong");
    }

    /**
     * Work of this method is to bring user by email
     * @param email
     * @return
     */

    public AppUser getUserByEmail(String email) {
        return dbApiConnector.callGetUserByEmailEndpoint(email);
    }

    public boolean validateToken(String token) {
        String payload = authUtility.decryptJwtToken(token);
        String [] details = payload.split(":");
        String email = details[0];
        String password = details[1];
        try {
            isValidCredentials(email, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
