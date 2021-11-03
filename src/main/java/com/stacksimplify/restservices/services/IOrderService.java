package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.OrderDetails;
import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.exceptions.EntityCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;

import java.util.List;

public interface IOrderService {
    OrderDetailsWithId createOrder(Long userId, OrderDetails order) throws UserNotFoundException, EntityCouldntBeSavedException;
    OrderDetailsWithId getOrderByUserIdAndOrderId(Long userId, Long orderId) throws UserNotFoundException, OrderNotFoundException;
}
