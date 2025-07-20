package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/login")
    public String showLoginForm() {
        return "user/login"; // Make sure this HTML file exists in templates/user/
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin/hotels";
            } else {
                return "redirect:/user/hotels";
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "user/login";
        }
    }

    @GetMapping("/user/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register"; // Make sure this file exists
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            return "user/register";
        }

        user.setRole("USER");
        userRepository.save(user);
        model.addAttribute("success", "Registration successful. Please login.");
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
