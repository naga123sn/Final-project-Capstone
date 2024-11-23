package com.example.BTS.controller;

import com.example.BTS.model.Bus;
import com.example.BTS.repository.BusRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BusRepository busRepository;

    // Display the search results on the user dashboard
    @GetMapping("/user/searchBus")
    public String searchBus(@RequestParam String fromLocation, 
                            @RequestParam String toLocation,
                            Model model) {
        // Search for buses matching the given fromLocation and toLocation
        List<Bus> buses = busRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
        
        model.addAttribute("buses", buses);
        return "user_dashboard"; // Redirect back to the user dashboard with results
    }

    // Show bus details when the user clicks "Book Now"
    @GetMapping("/user/confirmBooking/{id}")
    public String showBookingConfirmation(@PathVariable String id, Model model) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
        model.addAttribute("bus", bus);
        return "booking_confirmation"; // Show booking confirmation page
    }

    // Handle booking and confirmation when the user submits the booking request
    @PostMapping("/user/bookBus/{id}")
    public String confirmBooking(@PathVariable String id, 
                                 @RequestParam int seatsBooked, 
                                 Model model) {
        // Fetch the bus details by id
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));

        // Check if the requested seats are available
        if (seatsBooked <= bus.getSeatsAvailable()) {
            // Proceed with booking logic, reduce available seats and save the changes
            bus.setSeatsAvailable(bus.getSeatsAvailable() - seatsBooked);
            busRepository.save(bus);

            // Add a success message to the model
            model.addAttribute("message", "Booking Confirmed! Seats booked: " + seatsBooked);
            model.addAttribute("bus", bus); // Add the bus details to the model
            return "booking_success";  // Render a page showing booking success
        } else {
            // If not enough seats are available, show an error message
            model.addAttribute("message", "Not enough seats available.");
            model.addAttribute("bus", bus); // Add the bus details to the model
            return "booking_failed";  // Show an error page if seats are not available
        }
    }

    // Handle the confirmation page after booking success
    @GetMapping("/user/bookingSuccess")
    public String bookingSuccess() {
        // This page can show the success message after a successful booking
        return "booking_success"; // Redirect to booking success page
    }

    // Handle the failed booking page
    @GetMapping("/user/bookingFailed")
    public String bookingFailed() {
        // This page can show the failure message after an unsuccessful booking
        return "booking_failed"; // Redirect to booking failure page
    }
    
    @GetMapping("/user/confirmBus/{id}")
    public String confirmBus(@PathVariable String id, Model model) {
    Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
    model.addAttribute("bus", bus);
    return "bus_details"; // Should return the bus details page
    }
}

