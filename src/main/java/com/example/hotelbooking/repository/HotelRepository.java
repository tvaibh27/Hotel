package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {
}
