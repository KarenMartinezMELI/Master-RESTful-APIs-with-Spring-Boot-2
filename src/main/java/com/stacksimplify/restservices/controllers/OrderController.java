package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.OrderDetails;
import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.exceptions.EntityCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IOrderService;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class OrderController {

    private final IOrderService orderService;
    private final IUserService userService;

    @Autowired
    public OrderController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderDetailsWithId>> getAllOrdersByUserId(@Min(1) @PathVariable("id") Long userId) {
        try {
            return new ResponseEntity<>(userService.getAllOrders(userId), HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping("/{id}/orders")
    public ResponseEntity<OrderDetailsWithId> createOrder(@Min(1) @PathVariable("id") Long userId, @Valid @RequestBody OrderDetails order, UriComponentsBuilder builder) {
        try {
            OrderDetailsWithId userDetails=orderService.createOrder(userId,order);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/"+userId+"/orders/{id}").buildAndExpand(userDetails.getId()).toUri());
            return new ResponseEntity<>(userDetails, headers, HttpStatus.CREATED);
        } catch (EntityCouldntBeSavedException | UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
