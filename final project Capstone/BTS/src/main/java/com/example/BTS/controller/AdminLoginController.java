/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BTS.controller;

/**
 *
 * @author hp
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController {

    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    // Admin Login Page
    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin_login"; // Renders admin_login.html
    }

    // Admin Login Processing
    @PostMapping("/admin/login")
    public String processAdminLogin(@RequestParam String username,
                                    @RequestParam String password,
                                    Model model) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return "redirect:/admin/dashboard"; // Redirect to Admin Dashboard
        } else {
            model.addAttribute("error", "Invalid Admin Credentials");
            return "admin_login";
        }
    }

    // Admin Dashboard Page
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard"; // Renders admin_dashboard.html
    }
}


