package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.order.OrderMmWithIdDTO;
import com.stacksimplify.restservices.dtos.user.UserMmDTO;
import com.stacksimplify.restservices.dtos.user.UserMmWithIdDTO;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    UserService(IUserRepository userRepository, ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<UserMmWithIdDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserMmWithIdDTO> usersDetails = new ArrayList<>();

        users.forEach(u->usersDetails.add(mapUserToUserMmWithIdDTO(u)));
        return usersDetails;
    }

    @Override
    public List<OrderMmWithIdDTO> getAllOrders(Long id) throws UserNotFoundException {
        return getUserById(id).getOrders();
    }

    @Override
    public UserMmWithIdDTO createUser(UserMmDTO user) throws UserExistsException{
        User userCreation = mapUserMmDTOToUser(user);
        if(userRepository.findByUsername(userCreation.getUsername()).isPresent()){
            throw new UserExistsException("User already exists in repository");
        }
        User userReturn = userRepository.save(userCreation);

        return mapUserToUserMmWithIdDTO(userReturn);
    }

    @Override
    public UserMmWithIdDTO getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found in User Repository");
        }
        return user.map(this::mapUserToUserMmWithIdDTO).orElseThrow(() ->new UserNotFoundException("User Not found in User Repository"));
    }

    @Override
    public UserMmWithIdDTO updateUserById(Long id, UserMmDTO user) throws UserNotFoundException{

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User Not found in User Repository, provide the correct user id");
        }

        User updateUser = mapUserMmDTOToUser(user);
        updateUser.setId(id);

        updateUser = userRepository.save(updateUser);

        return mapUserToUserMmWithIdDTO(updateUser);
    }

    @Override
    public void deleteUserById(Long id) throws ResponseStatusException{
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not found in User Repository, provide the correct user id");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserMmWithIdDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(this::mapUserToUserMmWithIdDTO).orElse(null);
    }

    private UserMmWithIdDTO mapUserToUserMmWithIdDTO(User user){
        UserMmWithIdDTO userMmWithIdDTO = modelMapper.map(user,UserMmWithIdDTO.class);
        if(user.getOrders()!=null) {
            userMmWithIdDTO.setOrders(user.getOrders().stream().map(order -> modelMapper.map(order, OrderMmWithIdDTO.class)).collect(Collectors.toList()));
        }else{
            userMmWithIdDTO.setOrders(new ArrayList<>());
        }
        return userMmWithIdDTO;
    }

    private User mapUserMmDTOToUser(UserMmDTO user){
        return modelMapper.map(user,User.class);
    }




}
