package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DbApiConnector;
import com.fbs.central_api.enums.UserTypeEnum;
import com.fbs.central_api.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    This class is going to contain all the user related logics
 */

@Service
public class UserService {
    DbApiConnector dbApiConnector;

    @Autowired
    public UserService(DbApiConnector dbApiConnector) {
        this.dbApiConnector = dbApiConnector;
    }

    /*
        Work of this function is get all system admins from our users table
     */

    public List<AppUser> getAllSystemAdmins() {
        // To get all the system admins from the user table we need to call the DbApiConnector
        return dbApiConnector.callGetAllUsersByUserTypeEndpoint(UserTypeEnum.SYSTEM_ADMIN.toString());
    }
}
