package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/hateoas/users")
public class UserHateosController {

    private final IUserService userService;

    @Autowired
    public UserHateosController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDetailsWithId> userDetailsWithIds = userService.getAllUsers();
        userDetailsWithIds.forEach(
                u->{
                    //Self link
                    u.add(WebMvcLinkBuilder.linkTo(this.getClass()).slash(u.getId()).withSelfRel());
                    ResponseEntity ordersEntity = WebMvcLinkBuilder.methodOn(OrderHateosController.class).getAllOrdersByUserId(u.getId());
                    //Relationship link with getAllOrders
                    Link ordersLink = WebMvcLinkBuilder.linkTo(ordersEntity).withRel("all-orders");
                    u.add(ordersLink);
                }
        );
        //Self link for getAllUsers
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(userDetailsWithIds, selfLink), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@Min(1) @PathVariable Long id) {
        try {
            UserDetailsWithId userDetailsWithId = userService.getUserById(id);
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userDetailsWithId.getId()).withSelfRel();
            userDetailsWithId.add(selfLink);
            return new ResponseEntity<>(EntityModel.of(userDetailsWithId), HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
