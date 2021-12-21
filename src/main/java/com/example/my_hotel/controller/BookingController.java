package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.RoomService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {
    @Autowired
    private RoomService roomService;
    private Date date_1;
    private Date date_2;
    private int count_1;


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
        date_1=booking.getDate_arrival();
        date_2=booking.getDate_departure();
        count_1=booking.getCount_people();
        if (date1.isAfter(date2)) {
            model.addAttribute("error","Error");
            return "booking";
        }
        else {
            model.addAttribute("booking", booking);
//            List<RoomDTO> rooms = roomService.getAllRooms();
            System.out.println("85");
            List<RoomDTO> rooms = roomService.getFreeRooms(booking.getDate_arrival(), booking.getDate_departure(), booking.getCount_people());
            model.addAttribute("rooms", rooms);
            return "booking/free_room";
        }
    }
    @GetMapping("/booking/{id}")
    public String booking_custom(@PathVariable(value="id") int id,   Model model) {
        Booking booking = new Booking();
        booking.setRoom(roomService.getById(id));
        booking.setDate_arrival(date_1);
        booking.setDate_departure(date_2);
        booking.setCount_people(count_1);
        System.out.println(booking.getDate_arrival());
        model.addAttribute("booking", booking);
        return "booking/form";
    }
    @PostMapping("/booking/{id}")
    public String booking_rest(Booking booking, Model model)  {
        System.out.println(booking.getName());
        return "general";
    }
}