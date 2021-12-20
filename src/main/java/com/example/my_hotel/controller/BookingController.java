package com.example.my_hotel.controller;

import com.example.my_hotel.model.Booking;
import com.example.my_hotel.repository.RoomRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class BookingController {
    @Autowired
    private RoomRepository roomRepository;

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
        if (date1.isAfter(date2)) {
            model.addAttribute("error","Error");
            return "booking";

        }
        else {
            model.addAttribute("booking", booking);
            return "booking/free_room";
        }
    }
}
