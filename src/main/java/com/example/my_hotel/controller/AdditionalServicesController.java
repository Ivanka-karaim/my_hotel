package com.example.my_hotel.controller;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Custom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.AdditionalServicesRepository;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.AdditionalServicesService;
import com.example.my_hotel.service.CustomService;
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

@Controller
public class AdditionalServicesController {
    @Autowired
    private AdditionalServicesService additionalServicesService;

    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    @GetMapping("/viewServ")
    public String view(Model model) {
        List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();
        model.addAttribute("title", "View");
        model.addAttribute("additionalservices", additionalServices);
        return "additionalservices";
    }

    @GetMapping("/addServ")
    public String add(Model model) {
        List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();
        model.addAttribute("title", "Add");
        return "additionalservices";
    }

    @GetMapping("/removeServ")
    public String remove(Model model) {
        List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();
        model.addAttribute("title", "Remove");
        model.addAttribute("additionalservices", additionalServices);
        return "additionalservices";
    }

    @GetMapping("/editServ")
    public String edit (Model model) {
        List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();
        model.addAttribute("title", "Edit");
        model.addAttribute("additionalservices", additionalServices);
        return "additionalservices";
    }
    @PostMapping("/addServ")
    public String add(@RequestParam String type_service, @RequestParam float price) {
        AdditionalServices additionalServices = new AdditionalServices(type_service, price);
        additionalServicesRepository.save(additionalServices);
        return "redirect:/viewServ";
    }

    @PostMapping("/removeServ/{id_service}")
    public String remove(@PathVariable(value = "id_service") int id_service, Model model) {
        AdditionalServices additionalServices = additionalServicesRepository.findById(id_service).orElseThrow();
        additionalServicesRepository.delete(additionalServices);
        return "redirect:/viewServ";
    }

    @GetMapping("/editServ/{id_service}")
    public String edit (@PathVariable(value = "id_service") int id_service, String type_service, float price, Model model) {
        AdditionalServices additionalServices = additionalServicesRepository.findById(id_service).orElseThrow();
        additionalServices.setType_service(type_service);
        additionalServices.setPrice(price);
        return "redirect:/viewServ";
    }
}

