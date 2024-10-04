package com.mobi.hotel.Service.ServiceClass;

import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Model.Room;
import com.mobi.hotel.Model.RoomType;
import com.mobi.hotel.Repository.RoomRepository;
import com.mobi.hotel.Service.ServiceInterface.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

@Autowired
    private RoomRepository roomRepo;

    @Override
    public void saveRoom(Booking room) {

    }

    @Override
    public List<Object> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {

        LocalDate checkIn = checkInDate;
        LocalDate checkOut =checkOutDate;
        List<Room> availableRooms = roomRepo.findByCheckInDateBetween(checkIn, checkOut.minusDays(1));
        System.out.println(availableRooms.isEmpty());
        List<Object> list = new ArrayList<>();

        for(Room r : availableRooms)
        {
            if(r.getMini() != 0)
                list.add((RoomType.MINI.name()));
            if(r.getStandard() != 0)
                list.add((RoomType.STANDARD.name()));
            if(r.getPremium() != 0)
                list.add((RoomType.PREMIUM.name()));
        }
        return list;
    }

    @Transactional
    public void bookRoomOnGivenDate(LocalDate checkIn, LocalDate checkOut, String roomType) {
        // Check if the room type is available for the given dates

            switch (roomType.toLowerCase()) {
                case "mini":
                    roomRepo.decrementMiniRoom(checkIn,checkOut.minusDays(1));
                    break;
                case "standard":
                    roomRepo.decrementStandardRoom(checkIn,checkOut.minusDays(1));
                    break;
                case "premium":
                    roomRepo.decrementPremiumRoom(checkIn,checkOut.minusDays(1));
                    break;
            }
    }

    @Override
    public void saveData() {

    }

}
