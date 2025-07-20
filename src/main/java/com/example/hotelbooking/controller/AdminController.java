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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Admin - All Bookings
    @GetMapping("/admin/bookings")
    public String viewAllBookings(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "admin/bookings";
    }

    // ✅ Admin - All Users
    @GetMapping("/admin/users")
    public String viewAllUsers(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

    // ✅ Admin - View Hotels Dashboard
    @GetMapping("/admin/hotels")
    public String viewAllHotels(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "admin/hotels";
    }

    // ✅ Admin - Show Add Hotel Form
    @GetMapping("/admin/add-hotel")
    public String showAddHotelForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        model.addAttribute("hotel", new Hotel());
        return "admin/add-hotel";
    }

    // ✅ Admin - Handle Add Hotel Submission
    @PostMapping("/admin/add-hotel")
    public String addHotel(@ModelAttribute("hotel") Hotel hotel, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        hotelRepository.save(hotel);
        return "redirect:/admin/hotels";
    }
}
