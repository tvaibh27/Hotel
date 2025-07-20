package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByEmailAndPassword(String email, String password);
}
