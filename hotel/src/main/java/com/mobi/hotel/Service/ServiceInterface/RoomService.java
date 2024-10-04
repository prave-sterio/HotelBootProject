package com.mobi.hotel.Service.ServiceInterface;

import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public interface RoomService {
    void saveRoom(Booking room);
    List<Object> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);

    void bookRoomOnGivenDate(LocalDate checkIn, LocalDate checkOut, String roomType);
    void saveData();
}
