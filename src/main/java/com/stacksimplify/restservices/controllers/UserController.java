package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.user.UserMmDTO;
import com.stacksimplify.restservices.dtos.user.UserMmWithIdDTO;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

//You can use @JacksonXmlRootElement(localName = "user") and others annotations for the xml representation

//Controller
@Api(tags = "User Management RESTful Services", value = "UserController", description = "Controller for User Management Service")
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retrieve list of users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserMmWithIdDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user")
    @PostMapping
    public ResponseEntity<?> createUser(@ApiParam("User information for a new user to be created.") @Valid @RequestBody UserMmDTO user, UriComponentsBuilder builder) {
        try {
            UserMmWithIdDTO userDetails=userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(userDetails.getId()).toUri());
            return new ResponseEntity<>(userDetails, headers, HttpStatus.CREATED);
        } catch (UserExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMmWithIdDTO> getUserById(@Min(1) @PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserMmWithIdDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserMmDTO user) {
        try {
            return new ResponseEntity<>(userService.updateUserById(id, user), HttpStatus.OK);
        }catch (UserNotFoundException ex){
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
        UserMmWithIdDTO user = userService.getUserByUsername(username);
        if (user == null){
            throw new UserNameNotFoundException("Username: '"+username+"' not found in User repository");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
