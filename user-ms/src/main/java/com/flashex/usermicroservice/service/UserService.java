package com.flashex.usermicroservice.service;

import com.flashex.usermicroservice.model.User;
import com.flashex.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users =new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users;
    }
//    public List<User> getUserByUserName(String userName) {
//        return userRepository.findByUserNameContaining(userName);
//    }
    public User getUserByUserName(String userName) {
        return userRepository.findByUserNameContaining(userName).get();
    }
    public void addUser (User user){
        userRepository.save(user);
    }
    public void editUser(String id, User user){
        userRepository.save(user);
    }
    public void deleteUser(String userName){
        userRepository.deleteByUserNameContaining(userName);
    }

}
