package com.stacksimplify.restservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.dtos.user.UserMmWithIdDTO;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/jacksonfiltrer/users")
@Validated
public class UserMappingJacksonController {

    private final IUserService userService;

    @Autowired
    public UserMappingJacksonController(IUserService userService) {
        this.userService = userService;
    }

    //Fields with hashset
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@Min(1) @PathVariable Long id) {
        try {
            UserMmWithIdDTO user = userService.getUserById(id);
            Set<String> fields = new HashSet<>();
            fields.add("id");
            fields.add("username");
            fields.add("ssn");
            fields.add("orders");

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return new ResponseEntity<>(mapper, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //Fields with @RequestParam
    @GetMapping("/params/{id}")
    public ResponseEntity<?> getUserById2(@Min(1) @PathVariable Long id,
                                          @RequestParam Set<String> fields) {
        try {
            UserMmWithIdDTO user = userService.getUserById(id);

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return new ResponseEntity<>(mapper, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
