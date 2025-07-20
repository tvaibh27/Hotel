package com.example.hotelbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // redirect to login or homepage based on your logic
        return "redirect:/user/login";  // ✅ Make sure this HTML exists
    }
}
