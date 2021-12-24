package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Client;
import com.example.my_hotel.model.Response;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.ClientRepository;
import com.example.my_hotel.repository.ResponseRepository;
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
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/response")
    public String response(Model model) {
        Iterable<Response> responses=responseRepository.findAll();
        Iterable<Client> clients=clientRepository.findAll();
        model.addAttribute("clients",clients);
        model.addAttribute("responses", responses);
        return "response";
    }
    @GetMapping("/addResponse")
    public String add(Model model) {
        model.addAttribute("title", "Add");
        return "response";
    }
    @PostMapping("/addResponse")
    public String add(@RequestParam int mark, @RequestParam String comment) {
        Response response = new Response(mark, comment);
        responseRepository.save(response);
        System.out.println(242432);
        return "redirect:/response";
    }
}
