package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.service.HotelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // For users
    @GetMapping("/user/hotels")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "user/hotels";
    }

    @GetMapping("/admin/add-hotel")
    public String showAddHotelForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        model.addAttribute("hotel", new Hotel());
        return "admin/add-hotel";
    }

    @PostMapping("/admin/add-hotel")
    public String addHotel(@ModelAttribute Hotel hotel, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        hotelService.saveHotel(hotel);
        return "redirect:/admin/hotels";
    }

    @GetMapping("/admin/delete-hotel/{id}")
    public String deleteHotel(@PathVariable String id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || !user.getRole().equalsIgnoreCase("ADMIN")) {
            return "redirect:/login";
        }

        hotelService.deleteHotel(id);
        return "redirect:/admin/hotels";
    }
}
