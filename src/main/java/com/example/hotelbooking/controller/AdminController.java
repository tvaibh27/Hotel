package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/bookings")
    public String viewAllBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("bookings", bookingRepository.findAll());
        return "admin/bookings";
    }

    @GetMapping("/admin/hotels")
    public String viewAllHotels(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("hotels", hotelRepository.findAll());
        return "admin/hotels";
    }

    @GetMapping("/admin/users")
    public String viewAllUsers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
}
