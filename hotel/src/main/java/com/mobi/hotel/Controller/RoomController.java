package com.mobi.hotel.Controller;
import com.mobi.hotel.Model.Booking;
import com.mobi.hotel.Model.RoomType;
import com.mobi.hotel.Service.ServiceInterface.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;


    @GetMapping("/checkAvailability")
    public String showCheckAvailabilityPage() {
        return "check";
    }

    @RequestMapping("/BookPage")
    public String BookPage(@RequestParam("roomType") String roomType , HttpSession session) {
        roomService.bookRoomOnGivenDate((LocalDate) session.getAttribute("cin") , (LocalDate) session.getAttribute("cout") , roomType);
        String username= (String) session.getAttribute("username");
        Booking booking = null;
        booking.setRoomType(roomType);
        booking.setCheckInDate((LocalDate) session.getAttribute("cin"));
        booking.setCheckOutDate((LocalDate) session.getAttribute("cout"));
        booking.setUsername(username);
        roomService.saveRoom(booking);
        return "BookPage";
    }
    @RequestMapping("/checkB")
    public String checkB(){
        return "login";
    }

    @RequestMapping("roomback")
    public String roomback(){
        return "Check";
    }

    @RequestMapping("/searchBooking")
    public String searchBooking(@RequestParam("checkin") LocalDate checkInDate,
                                @RequestParam("checkout") LocalDate checkOutDate,
                                HttpSession session  , Model model ) {
        System.out.println(checkOutDate);
        List<Object> availableRooms = roomService.getAvailableRooms( checkInDate, checkOutDate); // Replace "anyType" with actual room type if necessary
        session.setAttribute("cin" , checkInDate);
        session.setAttribute("cout" , checkOutDate);
        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        boolean mini = availableRooms.contains(RoomType.MINI.name());
        boolean standard = availableRooms.contains(RoomType.STANDARD.name());
        boolean premium = availableRooms.contains(RoomType.PREMIUM.name());

        System.out.println("mini = "+mini+" std = "+standard +" pre = "+premium);
        model.addAttribute("miniPrice" , days*RoomType.MINI.getPrice());
        model.addAttribute("mini" , mini);
        model.addAttribute("stdPrice" , days*RoomType.STANDARD.getPrice());
        model.addAttribute("standard", standard);
        model.addAttribute("prePrice" , days*RoomType.PREMIUM.getPrice());
        model.addAttribute("premium", premium);

        System.out.println(model.getAttribute("mini"));

        return "rooms";
    }
}
