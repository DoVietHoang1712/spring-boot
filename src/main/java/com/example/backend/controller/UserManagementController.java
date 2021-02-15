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
@RequestMapping("management/v1")
public class UserManagementController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers() {
        System.out.println("Test");
        return userRepository.findAll();
    }

    @PostMapping()
    public String createNewUser(@RequestBody User user) {
        userRepository.insert(user);
        return "Success";
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable("userId") String userId, @RequestBody() User user) {
        Optional<User> userOptional = userRepository.findById(new ObjectId(userId));
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            userRepository.save(user1);
            return ResponseEntity.ok(user1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
