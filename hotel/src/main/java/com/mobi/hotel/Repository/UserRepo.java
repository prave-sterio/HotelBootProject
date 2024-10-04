package com.mobi.hotel.Repository;

import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    static List<Booking> getBookingsByUsername(String username) {

        return List.of();
    }

    static boolean insertBooking(LocalDate checkinDate, LocalDate checkoutDate, String roomType, String username) {

        return false;
    }

    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.username = :username")
    int updatePassword(String username, String newPassword);

    User findUserByUsername(String username);
}
