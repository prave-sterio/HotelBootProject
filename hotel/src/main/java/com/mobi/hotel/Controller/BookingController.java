package com.mobi.hotel.Controller;


import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Service.ServiceClass.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/BookPage")
    public String bookRoom(@RequestParam("roomType") String roomType, HttpSession session, Model model) {
        // Retrieve session attributes for check-in, check-out, and username
        LocalDate checkinDate = (LocalDate) session.getAttribute("cin");
        LocalDate checkoutDate = (LocalDate) session.getAttribute("cout");
        String username = (String) session.getAttribute("username");

        // Call service to book room
        boolean success = bookingService.bookRoom(checkinDate, checkoutDate, roomType, username);

        // Add booking success status to the model
        model.addAttribute("bookingSuccess", success);
        return "BookPage";
    }

    @PostMapping("/myBookings")
    public String showUserBookings(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        List<Booking> bookings = bookingService.getBookingsByUsername(username);

        model.addAttribute("bookings", bookings);
        session.setAttribute("list", bookings);
        return "userBookings";
    }

    @PostMapping("/cancelBooking")
    public String cancelBooking(@RequestParam("bookingId") int bookingId) {
        // Cancel booking via the service
        bookingService.cancelBooking(bookingId);

        return "redirect:/userBookings";
    }

    @GetMapping("/showHistory")
    public String showUserBookingHistory(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            List<Booking> bookings = bookingService.getBookingsByUsername(username);
            model.addAttribute("bookings", bookings);
            session.setAttribute("list", bookings);
            return "userBookings";
        }

        return "redirect:/login";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
}
