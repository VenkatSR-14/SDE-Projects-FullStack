package com.leetcodeClone.user_service.controller;

import com.leetcodeClone.user_service.model.User;
import com.leetcodeClone.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(UUID id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(UUID id, User userDetails){
        return userService.updateUser(id, userDetails);
    }

    @PostMapping
    public User createUser(User userDetails){
        return userService.createUser(userDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(UUID id){
        userService.deleteUser(id);
        return "User with id "+ id + "successfully deleted";
    }

}
