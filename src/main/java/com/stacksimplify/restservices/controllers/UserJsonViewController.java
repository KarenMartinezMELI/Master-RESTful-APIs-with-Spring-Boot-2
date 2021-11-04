package com.stacksimplify.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.entities.View;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

    private final IUserService userService;

    @Autowired
    public UserJsonViewController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/external/{id}")
    @JsonView(View.External.class)
    public ResponseEntity<?> getUserByIdExternal(@Min(1) @PathVariable Long id) {
        try {
            UserDetailsWithId user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/internal/{id}")
    @JsonView(View.Internal.class)
    public ResponseEntity<?> getUserByIdInternal(@Min(1) @PathVariable Long id) {
        try {
            UserDetailsWithId user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
