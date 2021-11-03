package com.stacksimplify.restservices.utils;

import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.entities.Order;

public class OrderParse {

    public static OrderDetailsWithId mapOrderToOrderDetailsWithId(Order order){
        OrderDetailsWithId orderDetails = new OrderDetailsWithId();
        orderDetails.setId(order.getId());
        orderDetails.setDescription(order.getDescription());
        return orderDetails;
    }
}
