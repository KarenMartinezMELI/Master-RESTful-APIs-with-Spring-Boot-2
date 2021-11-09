package com.stacksimplify.restservices.mappers;

import com.stacksimplify.restservices.dtos.order.OrderMmDTO;
import com.stacksimplify.restservices.dtos.user.UserMsDTO;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //User to UserMsDTO
    @Mappings({
    @Mapping(source = "email", target="emailadress"),
    @Mapping(source = "role", target="rolename")
    })
    UserMsDTO userToUserMsDTO(User user);

    //List<User> to List<UserMsDTO>
    List<UserMsDTO> usersToUserMsDTOs(List<User> users);

    //Order to OrderMmDTO, this is needed for the property orders
    OrderMmDTO map(Order order);

    // you can add custom logic like this
    //default OrderMmDTO personToPersonDto(Order order) {
        //hand-written mapping logic
    //}

}
