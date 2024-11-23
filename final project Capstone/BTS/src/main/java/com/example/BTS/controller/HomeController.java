package com.example.BTS.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    // Mapping for the home page (index.html)
    @GetMapping("/")
    public String home() {
        return "index"; // Maps to src/main/resources/templates/index.html
    }

    // Post method to handle logout
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalidate the session
        }
        
        // Optionally, invalidate any cookies or clear other session data if needed
        // Redirect to the index page (home page)
        return "redirect:/";  // Redirects to the home page (index.html)
    }
}
