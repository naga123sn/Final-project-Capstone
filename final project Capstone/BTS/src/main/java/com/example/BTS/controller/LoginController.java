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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String role, Model model) {
        if ("admin".equalsIgnoreCase(role)) {
            return "admin_login";
        } else if ("user".equalsIgnoreCase(role)) {
            return "user_login";
        } else {
            model.addAttribute("error", "Invalid role specified!");
            return "error";
        }
    }
}

