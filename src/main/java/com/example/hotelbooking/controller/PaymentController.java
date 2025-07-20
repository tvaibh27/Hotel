package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Payment;
import com.example.hotelbooking.model.User;
import com.example.hotelbooking.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Show payment form
    @GetMapping("/user/payment-form")
    public String showPaymentForm() {
        return "user/payment";
    }

    // Handle payment form submission
    @PostMapping("/user/payment")
    public String processPayment(@RequestParam String cardNumber,
                                 @RequestParam String cardHolder,
                                 @RequestParam String expiry,
                                 @RequestParam String cvv,
                                 HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        String bookingId = (String) session.getAttribute("bookingId");

        if (user == null || bookingId == null) {
            return "redirect:/user/login";
        }

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setUserId(user.getId());
        payment.setCardNumber(cardNumber);
        payment.setCardHolder(cardHolder);
        payment.setExpiry(expiry);
        payment.setCvv(cvv);

        paymentService.savePayment(payment);
        return "redirect:/user/thankyou";
    }

    // Show thank you page
    @GetMapping("/user/thankyou")
    public String showThankYouPage() {
        return "user/thankyou";
    }
}
