package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password); // ✅ ADD THIS LINE
}
