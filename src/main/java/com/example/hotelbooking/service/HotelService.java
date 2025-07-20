package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    public void deleteHotel(String id) {
        hotelRepository.deleteById(id);
    }
}
