package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.OrderDetails;
import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.EntityCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.IOrderRepository;
import com.stacksimplify.restservices.repositories.IUserRepository;
import com.stacksimplify.restservices.utils.OrderParse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDetailsWithId> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        List<OrderDetailsWithId> orderDetails = new ArrayList<>();

        orders.forEach(o->orderDetails.add(OrderParse.mapOrderToOrderDetailsWithId(o)));
        return orderDetails;
    }

    public OrderDetailsWithId createOrder(Long userId, OrderDetails order) throws UserNotFoundException, EntityCouldntBeSavedException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found in User Repository");
        }
        Order orderReturn = mapOrderDetailsToOrder(order);
        orderReturn.setUser(user.get());

        orderReturn = orderRepository.save(orderReturn);

        if(orderReturn==null){
            throw new EntityCouldntBeSavedException("Order","There was a problem saving the entity");
        }

        return OrderParse.mapOrderToOrderDetailsWithId(orderReturn);
    }

    private Order mapOrderDetailsToOrder(OrderDetails orderDetails){
        Order order = new Order();
        order.setDescription(orderDetails.getDescription());
        return order;
    }
}
