package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.OrderDetailsWithId;
import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.EntityCouldntBeSavedException;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.IUserRepository;
import com.stacksimplify.restservices.utils.OrderParse;
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

    @Autowired
    UserService(IUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<UserDetailsWithId> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDetailsWithId> usersDetails = new ArrayList<>();

        users.forEach(u->usersDetails.add(mapUserToUserDetailsWithId(u)));
        return usersDetails;
    }

    @Override
    public List<OrderDetailsWithId> getAllOrders(Long id) throws UserNotFoundException {
        List<OrderDetailsWithId> orders = getUserById(id).getOrders();
        return orders;
    }

    @Override
    public UserDetailsWithId createUser(UserDetails user) throws UserExistsException, EntityCouldntBeSavedException {
        User userCreation = mapUserDetailsToUser(user);
        if(userRepository.findByUsername(userCreation.getUsername()).isPresent()){
            throw new UserExistsException("User already exists in repository");
        }
        User userReturn = userRepository.save(userCreation);

        if(userReturn==null){
            throw new EntityCouldntBeSavedException("User","There was a problem saving the entity");
        }

        return mapUserToUserDetailsWithId(userReturn);
    }

    @Override
    public UserDetailsWithId getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not found in User Repository");
        }
        return mapUserToUserDetailsWithId(user.get());
    }

    @Override
    public UserDetailsWithId updateUserById(Long id, UserDetails user) throws UserNotFoundException, EntityCouldntBeSavedException {

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User Not found in User Repository, provide the correct user id");
        }

        User updateUser = mapUserDetailsToUser(user);
        updateUser.setId(id);

        updateUser = userRepository.save(updateUser);

        if(updateUser==null){
            throw new EntityCouldntBeSavedException("User","There was a problem saving the entity");
        }

        return mapUserToUserDetailsWithId(updateUser);
    }

    @Override
    public void deleteUserById(Long id) throws ResponseStatusException{
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not found in User Repository, provide the correct user id");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetailsWithId getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            return null;
        }
        return mapUserToUserDetailsWithId(user.get());
    }

    private UserDetailsWithId mapUserToUserDetailsWithId(User user){
        UserDetailsWithId userCreation = new UserDetailsWithId();
        userCreation.setUsername(user.getUsername());
        userCreation.setEmail(user.getEmail());
        userCreation.setFirstname(user.getFirstname());
        userCreation.setLastname(user.getLastname());
        userCreation.setId(user.getId());
        userCreation.setSsn(user.getSsn());
        if(user.getOrders()!=null) {
            userCreation.setOrders(user.getOrders().stream().map(order -> OrderParse.mapOrderToOrderDetailsWithId(order)).collect(Collectors.toList()));
        }else{
            userCreation.setOrders(new ArrayList<>());
        }
        return userCreation;
    }

    private User mapUserDetailsToUser(UserDetails user){
        User userCreation = new User();
        userCreation.setUsername(user.getUsername());
        userCreation.setEmail(user.getEmail());
        userCreation.setFirstname(user.getFirstname());
        userCreation.setLastname(user.getLastname());
        return userCreation;
    }




}
