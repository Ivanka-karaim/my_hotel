//package com.example.my_hotel.controller;
//
//import com.example.my_hotel.dto.RoomDTO;
//import com.example.my_hotel.model.Booking;
//import com.example.my_hotel.model.Custom;
//import com.example.my_hotel.model.Room;
//import com.example.my_hotel.repository.RoomRepository;
//import com.example.my_hotel.service.RoomService;
//import org.apache.tomcat.jni.Local;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class CustomController {
//    @Autowired
//    private Custom custom;
//
//    @GetMapping("/custom")
//    public String employee(Model model) {
//        model.addAttribute("title", "Замовлення");
//        return "custom";
//    }
//
//    @GetMapping("/view")
//    public String view(Model model) {
//        //List<RoomDTO> rooms = custom.getAllRooms();
//        model.addAttribute("title", "Замовлення");
//        return "custom";
//    }
//
//
//}
