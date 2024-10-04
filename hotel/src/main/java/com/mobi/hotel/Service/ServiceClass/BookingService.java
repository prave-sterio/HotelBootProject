package com.mobi.hotel.Service.ServiceClass;


import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public boolean bookRoom(LocalDate checkInDate, LocalDate checkOutDate, String roomType, String username) {
        Booking booking = new Booking();
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setRoomType(roomType);
        booking.setUsername(username);
        booking.setActive(true);
        // Save the booking and return success flag
        bookingRepository.save(booking);
        return true;
    }

    public List<Booking> getBookingsByUsername(String username) {
        // Fetch bookings from repository
        return bookingRepository.findByUsername(username);
    }

    public void cancelBooking(int bookingId) {
        // Fetch booking by ID and set its active flag to false
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setActive(false);
            bookingRepository.save(booking);  // Update the booking
        }
    }
}
