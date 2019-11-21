package com.flashex.usermicroservice.controller;

import com.flashex.usermicroservice.model.User;
import com.flashex.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;
    //    To get all users the method below is used
    @RequestMapping("/users")
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }
    //    To get a user by its name
    @RequestMapping("/users/{userName}")
    public User getUser (@PathVariable("userName") String userName){
        return userService.getUserByUserName(userName);
    }
    @RequestMapping(method= RequestMethod.POST, value="/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
    @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
    public void editUser(@RequestBody User user, @PathVariable String id){
        userService.editUser(id, user);
    }
    @RequestMapping(method= RequestMethod.DELETE, value="/users/{userName}")
    public void deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
    }
}
