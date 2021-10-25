package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;
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
