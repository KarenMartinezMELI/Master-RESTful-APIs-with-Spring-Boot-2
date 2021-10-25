package com.stacksimplify.restservices.service;

import com.stacksimplify.restservices.dto.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    UserService(){
        //Add Repository here
    }

    @Override
    public UserDetails returnNewUserDetails() {
        return new UserDetails("Kalyan", "Reddy", "Hyderabad");
    }
}
