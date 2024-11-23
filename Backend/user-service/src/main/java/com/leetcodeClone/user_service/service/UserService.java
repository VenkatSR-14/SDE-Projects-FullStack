package com.leetcodeClone.user_service.service;
import com.leetcodeClone.user_service.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User createUser(User userDetails);

    User updateUser(UUID id, User userDetails);
    void deleteUser(UUID id);
}
