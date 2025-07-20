package com.example.hotelbooking.service;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Register a new user
    public void register(User user) {
        userRepository.save(user);
    }

    // ✅ Login method (returns matching user or null)
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    // ✅ Get user by ID
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // ✅ Get all users (admin view)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
