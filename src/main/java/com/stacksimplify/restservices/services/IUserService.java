package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import com.stacksimplify.restservices.dtos.user.UserMmDTO;
import com.stacksimplify.restservices.dtos.user.UserMmWithIdDTO;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface IUserService {

    List<UserMmWithIdDTO> getAllUsers();
    List<OrderMmWithIdDTO> getAllOrders(Long id) throws UserNotFoundException;
    UserMmWithIdDTO createUser(UserMmDTO user) throws UserExistsException;
    UserMmWithIdDTO getUserById(Long id) throws UserNotFoundException;
    UserMmWithIdDTO updateUserById(Long id, UserMmDTO user) throws UserNotFoundException;
    void deleteUserById(Long id) throws ResponseStatusException;
    UserMmWithIdDTO getUserByUsername(String username);
}
