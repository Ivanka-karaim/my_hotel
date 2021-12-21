package com.example.my_hotel.controller;

import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.CustomService;
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
public class CustomController {
    @Autowired
    private CustomService customService;

    @GetMapping("/view")
    public String view(Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        model.addAttribute("title", "View");
        model.addAttribute("page", customs);
        return "custom";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        model.addAttribute("title", "Add");
        return "custom";
    }

    @GetMapping("/remove")
    public String remove(Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        model.addAttribute("title", "Remove");
        return "custom";
    }

    @GetMapping("/edit")
    public String edit (Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        model.addAttribute("title", "Edit");
        return "custom";
    }
}

