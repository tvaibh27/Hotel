package com.hotelbooking;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelServiceTest {

    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        hotelRepository = mock(HotelRepository.class);
        hotelService = new HotelService(hotelRepository);  // custom constructor you already have
    }

    @Test
    public void testFindHotelById() {
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("Sample Hotel");

        when(hotelRepository.findById("1")).thenReturn(Optional.of(hotel));

        Optional<Hotel> result = Optional.ofNullable(hotelService.getHotelById("1"));

        assertTrue(result.isPresent());
        assertEquals("Sample Hotel", result.get().getName());
    }

    @Disabled("Demo disabled test")
    @Test
    public void disabledTest() {
        fail("This test is disabled.");
    }
}
