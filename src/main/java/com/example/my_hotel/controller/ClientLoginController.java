package com.example.my_hotel.controller;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.dto.ClientDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.AdditionalServicesRepository;
import com.example.my_hotel.repository.ClientRepository;
import com.example.my_hotel.repository.ResponseRepository;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.AdditionalServicesService;
import com.example.my_hotel.service.ClientService;
import com.example.my_hotel.service.RoomService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientLoginController {
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/clienlogin")
    public String client(Model model) {
        Iterable<Client> clientlogin=clientRepository.findAll();
        model.addAttribute("clientlogin", clientlogin);
        return "clientlogin";
    }
}
