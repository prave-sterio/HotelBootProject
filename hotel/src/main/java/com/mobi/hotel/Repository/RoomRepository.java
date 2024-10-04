package com.mobi.hotel.Repository;

import com.mobi.hotel.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Modifying
    @Query("UPDATE Room r SET r.mini = r.mini - 1 WHERE r.mini > 0 AND r.checkInDate BETWEEN :startDate AND :endDate")
    void decrementMiniRoom(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Query("UPDATE Room r SET r.standard = r.standard - 1 WHERE r.standard > 0 AND r.checkInDate BETWEEN :startDate AND :endDate")
    void decrementStandardRoom(LocalDate startDate, LocalDate endDate);

    @Modifying
    @Query("UPDATE Room r SET r.premium = r.premium - 1 WHERE r.premium > 0 AND r.checkInDate BETWEEN :startDate AND :endDate")
    void decrementPremiumRoom(LocalDate startDate, LocalDate endDate);

    List<Room> findByCheckInDateBetween(LocalDate checkIn, LocalDate localDate);
}
