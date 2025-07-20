package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.User;
import com.example.hotelbooking.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setRole("USER");
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password,
                            HttpSession session, Model model) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getId());
            return user.getRole().equalsIgnoreCase("ADMIN") ? "redirect:/admin/hotels" : "redirect:/user/hotels";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
