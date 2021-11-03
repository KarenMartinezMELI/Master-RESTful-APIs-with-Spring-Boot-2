package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.IOrderService;
import com.stacksimplify.restservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateosController {

    private final IOrderService orderService;
    private final IUserService userService;

    @Autowired
    public OrderHateosController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getAllOrdersByUserId(@Min(1) @PathVariable("id") Long userId) {
        try {
            List<OrderDetailsWithId> orders = userService.getAllOrders(userId);
            return new ResponseEntity<>(CollectionModel.of(orders), HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
