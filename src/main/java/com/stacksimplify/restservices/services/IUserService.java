package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.exceptions.EntityCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface IUserService {

    List<UserDetailsWithId> getAllUsers();
    List<OrderDetailsWithId> getAllOrders(Long id) throws UserNotFoundException;
    UserDetailsWithId createUser(UserDetails user) throws EntityCouldntBeSavedException, UserExistsException;
    UserDetailsWithId getUserById(Long id) throws UserNotFoundException;
    UserDetailsWithId updateUserById(Long id, UserDetails user) throws UserNotFoundException, EntityCouldntBeSavedException;
    void deleteUserById(Long id) throws ResponseStatusException;
    UserDetailsWithId getUserByUsername(String username);
}
