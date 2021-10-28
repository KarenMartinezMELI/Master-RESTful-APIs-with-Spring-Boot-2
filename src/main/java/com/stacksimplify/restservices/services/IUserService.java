package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;

import java.util.List;

public interface IUserService {

    List<UserDetailsWithId> getAllUsers();
    UserDetailsWithId createUser(UserDetails user);
    UserDetailsWithId getUserById(Long id);
    UserDetailsWithId updateUserById(Long id, UserDetails user);
    void deleteUserById(Long id);
    UserDetailsWithId getUserByUsername(String username);
}
