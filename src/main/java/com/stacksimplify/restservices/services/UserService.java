package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.dtos.UserDetails;
import com.stacksimplify.restservices.dtos.UserDetailsWithId;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public UserDetailsWithId createUser(UserDetails user) {
        User userCreation = mapUserDetailsToUser(user);
        User userReturn = userRepository.save(userCreation);

        if(userReturn==null){
            return new UserDetailsWithId();
        }

        return mapUserToUserDetailsWithId(userReturn);
    }

    @Override
    public UserDetailsWithId getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return mapUserToUserDetailsWithId(user.get());
        }else{
            return new UserDetailsWithId();
        }
    }

    @Override
    public UserDetailsWithId updateUserById(Long id, UserDetails user) {

        if(userRepository.existsById(id)){
            User updateUser = mapUserDetailsToUser(user);
            updateUser.setId(id);

            updateUser = userRepository.save(updateUser);

            if(updateUser==null){
                return new UserDetailsWithId();
            }

            return mapUserToUserDetailsWithId(updateUser);
        }

        return new UserDetailsWithId();
    }

    @Override
    public void deleteUserById(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDetailsWithId getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return mapUserToUserDetailsWithId(user.get());
        }else{
            return new UserDetailsWithId();
        }
    }

    private UserDetailsWithId mapUserToUserDetailsWithId(User user){
        UserDetailsWithId userCreation = new UserDetailsWithId();
        userCreation.setUsername(user.getUsername());
        userCreation.setEmail(user.getEmail());
        userCreation.setFirstname(user.getFirstname());
        userCreation.setLastname(user.getLastname());
        userCreation.setId(user.getId());
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
