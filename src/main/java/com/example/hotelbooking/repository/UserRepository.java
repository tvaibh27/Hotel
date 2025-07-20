package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);  // ✅ Enables login to work
}
