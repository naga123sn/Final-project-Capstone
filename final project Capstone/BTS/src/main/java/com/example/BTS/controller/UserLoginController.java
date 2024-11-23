/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BTS.controller;

import com.example.BTS.model.User;
import com.example.BTS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserRepository userRepository;

    // User Login Page
    @GetMapping("/login")
    public String showUserLoginPage() {
        return "user_login"; // Renders user_login.html
    }

    // User Login Processing
    @PostMapping("/login")
    public String processUserLogin(@RequestParam String username,
                                   @RequestParam String password,
                                   Model model) {
        User user = userRepository.findByUsername(username)
                                   .filter(u -> u.getPassword().equals(password))
                                   .orElse(null);

        if (user != null) {
            model.addAttribute("username", user.getUsername());
            return "redirect:/user/dashboard?username=" + user.getUsername(); // Redirect to User Dashboard
        } else {
            model.addAttribute("error", "Invalid User Credentials");
            return "user_login";
        }
    }

    // User Registration Page
    @GetMapping("/register")
    public String showUserRegisterPage() {
        return "user_register"; // Renders user_register.html
    }

    // User Registration Processing
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "user_register";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);

        return "redirect:/user/login"; // Redirect to User Login
    }

    // User Dashboard Page
    @GetMapping("/dashboard")
    public String userDashboard(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "user_dashboard"; // Renders user_dashboard.html
    }
}

