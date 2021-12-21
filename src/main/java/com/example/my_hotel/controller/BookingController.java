package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.RoomService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private RoomService roomService;


    @GetMapping("/booking")
    public String main(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("error","");
        return "booking";
    }
    @PostMapping("/booking")
    public String search(Booking booking, Model model) {
        LocalDate date1 = LocalDate.ofInstant(booking.getDate_arrival().toInstant(), ZoneId.systemDefault());
        LocalDate date2 = LocalDate.ofInstant(booking.getDate_departure().toInstant(), ZoneId.systemDefault());
        System.out.println(date1);
        System.out.println(date2);
        if (date1.isAfter(date2)) {
            model.addAttribute("error","Error");
            return "booking";

        }
        else {
            model.addAttribute("booking", booking);

            List<RoomDTO> rooms = roomService.getAllRooms();

            model.addAttribute("rooms", rooms);
            return "booking/free_room";
        }
    }
}