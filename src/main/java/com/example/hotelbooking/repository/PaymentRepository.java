// PaymentRepository.java
package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
