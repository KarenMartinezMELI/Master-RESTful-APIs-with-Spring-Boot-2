package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.user.UserMmWithIdDTO;
import com.stacksimplify.restservices.dtos.user.UserMsDTO;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.repositories.IUserRepository;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserMapStructController(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserMsDTO>> getAllUsers() {
        return new ResponseEntity<>(userMapper.usersToUserMsDTOs(userRepository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMsDTO> getUserById(@Min(1) @PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new ResponseEntity<>(userMapper.userToUserMsDTO(user.get()), HttpStatus.OK);
    }
}
