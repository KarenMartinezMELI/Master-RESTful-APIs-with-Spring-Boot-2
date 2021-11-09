package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.order.OrderMmDTO;
import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;

public interface IOrderService {
    OrderMmWithIdDTO createOrder(Long userId, OrderMmDTO order) throws UserNotFoundException;
    OrderMmWithIdDTO getOrderByUserIdAndOrderId(Long userId, Long orderId) throws UserNotFoundException, OrderNotFoundException;
}
