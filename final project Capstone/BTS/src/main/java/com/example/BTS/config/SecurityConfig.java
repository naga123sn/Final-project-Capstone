/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BTS.config;

/**
 *
 * @author hp
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login**", "/user/**", "/admin/**", "/css/**", "/js/**").permitAll() // Public access
                .anyRequest().authenticated() // Protect other routes
            )
            .formLogin().disable() // Disable default login
            .csrf().disable(); // Disable CSRF for simplicity in development

        return http.build();
    }
}

