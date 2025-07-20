package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Show booking form for selected hotel
    @GetMapping("/book/{hotelId}")
    public String showBookingFormWithHotel(@PathVariable String hotelId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("hotelId", hotelId);
        return "user/booking-form";
    }

    // Handle form submission and save booking
    @PostMapping("/book")
    public String bookHotel(@RequestParam String hotelId,
                            @RequestParam String checkInDate,
                            @RequestParam String checkOutDate,
                            @RequestParam int numberOfGuests,
                            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        Booking booking = new Booking();
        booking.setHotelId(hotelId);
        booking.setUserId(user.getId());
        booking.setCheckInDate(LocalDate.parse(checkInDate));
        booking.setCheckOutDate(LocalDate.parse(checkOutDate));
        booking.setNumberOfGuests(numberOfGuests);

        bookingService.saveBooking(booking);
        session.setAttribute("bookingId", booking.getId());

        return "redirect:/user/payment-form";
    }

}
