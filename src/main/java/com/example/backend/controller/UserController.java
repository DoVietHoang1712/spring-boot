package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable("userId") String userId) {
        return userRepository.findById(new ObjectId(userId));
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User body) {
        User user = userRepository.insert(body);
        return "Success";
    }
}
