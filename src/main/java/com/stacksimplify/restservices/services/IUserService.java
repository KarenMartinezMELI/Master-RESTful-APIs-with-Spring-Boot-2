package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.exceptions.UserCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface IUserService {

    List<UserDetailsWithId> getAllUsers();
    UserDetailsWithId createUser(UserDetails user) throws UserCouldntBeSavedException, UserExistsException;
    UserDetailsWithId getUserById(Long id) throws UserNotFoundException;
    UserDetailsWithId updateUserById(Long id, UserDetails user) throws UserNotFoundException, UserCouldntBeSavedException;
    void deleteUserById(Long id) throws ResponseStatusException;
    UserDetailsWithId getUserByUsername(String username);
}
