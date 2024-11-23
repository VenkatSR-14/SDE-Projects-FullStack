package com.leetcodeClone.user_service.service;

import com.leetcodeClone.user_service.model.User;
import com.leetcodeClone.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }


    public User updateUser(UUID id, User userDetails){
        return userRepository.findById(id).map(user -> {
            user.setFullname(userDetails.getFullname());
            user.setRating(userDetails.getRating());
            user.setEmail(userDetails.getEmail());
            user.setRegion(userDetails.getRegion());
            user.setPassword(userDetails.getPassword());
            user.setProblemsSolved(userDetails.getProblemsSolved());
            user.setNumberOfSubmissions(userDetails.getNumberOfSubmissions());
            user.setBio(userDetails.getBio());
            user.setUsername(userDetails.getUsername());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User with id" + id + "Not found"));
    }
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
