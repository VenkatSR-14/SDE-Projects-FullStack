package com.leetcodeClone.user_service.repository;
import java.util.Optional;
import java.util.UUID;

import com.leetcodeClone.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
