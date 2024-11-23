package com.example.BTS.controller;

import com.example.BTS.model.Bus;
import com.example.BTS.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    // Show all buses in the admin dashboard
    @GetMapping("/buses")
    public String listBuses(Model model) {
        List<Bus> buses = busRepository.findAll();
        model.addAttribute("buses", buses);
        return "admin_manage_bus"; // Template for managing buses
    }

    // Show the form to edit bus details
    @GetMapping("/editBus/{id}")
    public String showEditBusForm(@PathVariable("id") String id, Model model) {
        Optional<Bus> busOptional = busRepository.findById(id);
        if (busOptional.isPresent()) {
            Bus bus = busOptional.get();
            model.addAttribute("bus", bus);  // Add bus object to model for the form
            return "admin_edit_bus";  // Template for editing the bus
        } else {
            model.addAttribute("error", "Bus not found with ID: " + id);
            return "error_page";  // Show error page if bus is not found
        }
    }

    // Update the bus details in the database
    @PostMapping("/updateBus/{id}")
    public String updateBus(@PathVariable("id") String id, @ModelAttribute Bus bus, Model model) {
        Optional<Bus> existingBusOptional = busRepository.findById(id);
        if (existingBusOptional.isPresent()) {
            Bus existingBus = existingBusOptional.get();
            // Update bus details
            existingBus.setBusName(bus.getBusName());
            existingBus.setBusNumber(bus.getBusNumber());
            existingBus.setFromLocation(bus.getFromLocation());
            existingBus.setToLocation(bus.getToLocation());
            existingBus.setSeatsAvailable(bus.getSeatsAvailable());
            existingBus.setTiming(bus.getTiming());

            busRepository.save(existingBus); // Save the updated bus details
            return "redirect:/admin/buses"; // Redirect back to the bus list page (dashboard)
        } else {
            model.addAttribute("error", "Bus not found with ID: " + id);
            return "error_page"; // Show error page if bus not found
        }
    }

    // Delete a bus from the database
    @GetMapping("/deleteBus/{id}")
    public String deleteBus(@PathVariable("id") String id, Model model) {
        Optional<Bus> busOptional = busRepository.findById(id);
        if (busOptional.isPresent()) {
            busRepository.deleteById(id); // Delete the bus from the repository
            return "redirect:/admin/buses"; // Redirect back to the bus list page (dashboard)
        } else {
            model.addAttribute("error", "Bus not found with ID: " + id);
            return "error_page"; // Show error if bus not found
        }
    }

    // Show the form to add a new bus
   // Show the form to add a new bus
    @GetMapping("/addBus")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus()); // Provide an empty Bus object for the form
        return "admin_add_bus"; // Template for adding a new bus
    }

    // Add a new bus to the database and redirect to the bus list page
    @PostMapping("/addBus")
    public String addBus(@ModelAttribute Bus bus, RedirectAttributes redirectAttributes) {
        busRepository.save(bus); // Save the new bus in the repository
        // Add a success message
        redirectAttributes.addFlashAttribute("successMessage", "Bus added successfully!");
        return "redirect:/admin/buses"; // Redirect to bus list page (dashboard)
    }

}
