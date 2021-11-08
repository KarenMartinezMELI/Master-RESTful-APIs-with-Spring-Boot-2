package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.order.OrderMmDTO;
import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.IOrderRepository;
import com.stacksimplify.restservices.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository,ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public OrderMmWithIdDTO createOrder(Long userId, OrderMmDTO order) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found in User Repository");
        }
        Order orderReturn = mapOrderDetailsToOrder(order);
        orderReturn.setUser(user.get());

        orderReturn = orderRepository.save(orderReturn);

        return modelMapper.map(orderReturn,OrderMmWithIdDTO.class);
    }

    @Override
    public OrderMmWithIdDTO getOrderByUserIdAndOrderId(Long userId, Long orderId) throws UserNotFoundException, OrderNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found in User Repository");
        }
        Optional<Order> order = orderRepository.findById(orderId);

        if(!order.isPresent()){
            throw new OrderNotFoundException("Pair Order/User Not found");
        }
        Order orderFinal =order.get();

        if(!orderFinal.getUser().getId().equals(user.get().getId())){
            throw new OrderNotFoundException("Pair Order/User Not found");
        }

        return modelMapper.map(orderFinal,OrderMmWithIdDTO.class);
    }

    private Order mapOrderDetailsToOrder(OrderMmDTO orderMmDTO){
        Order order = new Order();
        order.setDescription(orderMmDTO.getDescription());
        return order;
    }
}
