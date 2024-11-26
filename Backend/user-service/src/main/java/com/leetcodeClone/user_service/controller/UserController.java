package com.leetcodeClone.user_service.controller;

import com.leetcodeClone.user_service.config.JwtTokenUtil;
import com.leetcodeClone.user_service.dto.SignInRequest;
import com.leetcodeClone.user_service.dto.SignUpRequest;
import com.leetcodeClone.user_service.model.User;
import com.leetcodeClone.user_service.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public User signup(@Valid @RequestBody SignUpRequest signUpRequest){
        return userService.createUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        String token = userService.authenticateUser(signInRequest.getEmail(), signInRequest.getPassword());
        return ResponseEntity.ok(token);
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable  UUID id, @RequestBody User userDetails){
        return userService.updateUser(id, userDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable UUID id){
        userService.deleteUser(id);
        return "User with id "+ id + "successfully deleted";
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String token){
        if (jwtTokenUtil.validateToken(token.substring(7))){
            return ResponseEntity.ok("Token is valid");
        }else{
            return ResponseEntity.status(401).body("Token is invalid or expired");
        }
    }

}
