package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private BookingService bookingService;

    // Show all bookings of logged-in user
    @GetMapping("/bookings")
    public String viewUserBookings(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        String userId = loggedInUser.getId();
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        model.addAttribute("bookings", bookings);
        return "user/bookings"; // Thymeleaf template path: templates/user/bookings.html
    }
}
