package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.exceptions.UserCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.util.List;

//Controller
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsWithId>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDetails user, UriComponentsBuilder builder) {
        try {
            UserDetailsWithId userDetails=userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(userDetails.getId()).toUri());
            return new ResponseEntity<>(userDetails, headers, HttpStatus.CREATED);
        } catch (UserCouldntBeSavedException | UserExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsWithId> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsWithId> updateUserById(@PathVariable Long id, @Valid @RequestBody UserDetails user) {
        try {
            return new ResponseEntity<>(userService.updateUserById(id, user), HttpStatus.OK);
        }catch (UserNotFoundException | UserCouldntBeSavedException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws UserNameNotFoundException {
        UserDetailsWithId user = userService.getUserByUsername(username);
        if (user == null){
            throw new UserNameNotFoundException("Username: '"+username+"' not found in User repository");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
