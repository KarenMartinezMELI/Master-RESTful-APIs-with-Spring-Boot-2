package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;

import java.util.List;

public interface IUserService {

    UserDetails returnNewUserDetails();
    List<UserDetails> getAllUsers();
}
