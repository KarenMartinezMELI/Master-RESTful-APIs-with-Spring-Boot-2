package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    @Autowired
    UserService(IUserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public List<UserDetails> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDetails> usersDetails = new ArrayList<>();

        users.forEach(u->usersDetails.add(new UserDetails(u.getFirstname(), u.getLastname(), u.getEmail())));
        return usersDetails;
    }

    @Override
    public UserDetails returnNewUserDetails() {
        return null;
    }

}
