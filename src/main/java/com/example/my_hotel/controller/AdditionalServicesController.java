package com.example.my_hotel.controller;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.model.AdditionalServices;
import com.example.my_hotel.repository.AdditionalServicesRepository;
import com.example.my_hotel.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/edit_service/{id_service}")
    public String edit (@PathVariable(value = "id_service") int id_service, String type_service, float price) {
        AdditionalServices additionalServices = additionalServicesRepository.findById(id_service).orElseThrow();
        additionalServices.setType_service(type_service);
        additionalServices.setPrice(price);
        additionalServicesRepository.save(additionalServices);
        return "redirect:/viewServ";
    }

    @GetMapping("/edit_service/{id_service}")
    public String edit_service (@PathVariable(value = "id_service") int id_service, Model model) {
        if (!additionalServicesRepository.existsById(id_service)){
            return "/editServ";
        }
        Optional<AdditionalServices> additionalService = additionalServicesRepository.findById(id_service);
        model.addAttribute("service", additionalService.get());
        return "edit_service";
    }

}

