package com.leetcodeClone.user_service.service;

import com.leetcodeClone.user_service.config.Constants;
import com.leetcodeClone.user_service.config.JwtTokenUtil;
import com.leetcodeClone.user_service.dto.SignUpRequest;
import com.leetcodeClone.user_service.exception.InvalidCredentialsException;
import com.leetcodeClone.user_service.exception.UserNotFoundException;
import com.leetcodeClone.user_service.model.User;
import com.leetcodeClone.user_service.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    @Override
    public User createUser(SignUpRequest signUpRequest){
        logger.info("Creating new user..");
        User user = new User();
        user.setRating(Constants.DEFAULT_RATING);
        user.setNumberOfSubmissions(Constants.DEFAULT_SUBMISSIONS);
        user.setProblemsSolved(Constants.DEFAULT_PROBLEMS_SOLVED);
        user.setBio(signUpRequest.getBio());
        user.setFullname(signUpRequest.getFullname());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRegion(signUpRequest.getRegion());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        return userRepository.save(user);
    }


    @Override
    public User updateUser(UUID id, User userDetails) {
        logger.info("Updating user details with id: {}", id);
        return userRepository.findById(id).map(user -> {
            user.setFullname(userDetails.getFullname());
            user.setEmail(userDetails.getEmail());
            user.setRegion(userDetails.getRegion());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setBio(userDetails.getBio());
            user.setUsername(userDetails.getUsername());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public String authenticateUser(String email, String password) {
        logger.info("Attempting to authenticate user with email: {}", email);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            logger.info("Authentication successful for user: {}", email);
            String token = jwtTokenUtil.generateToken(email);
            logger.info("JWT token generated for user: {}", email);
            return token;
        } else {
            logger.warn("Authentication failed for user: {}", email);
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user by email (username in this case)
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }

        User user = userOptional.get();

        // Return a Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Set username
                .password(user.getPassword()) // Set hashed password
                .roles("USER") // Set roles (you can fetch roles dynamically if needed)
                .build();
    }
}
