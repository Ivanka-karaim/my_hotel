package com.example.my_hotel.controller;

import com.example.my_hotel.dto.AdditionalServicesDTO;
import com.example.my_hotel.dto.CustomDTO;
import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.CustomRepository;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CustomController {
    @Autowired
    private CustomService customService;

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    AdditionalServicesService additionalServicesService;

    @Autowired
    ClientService clientService;
    @Autowired
    BookingService bookingService;


    @Autowired
    private  Order_ServicesService order_servicesService;


    @GetMapping("/view")
    public String view(Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        Map<Integer, Client> clientHashMap = new HashMap<>();
        for (CustomDTO customDTO: customs){
            clientHashMap.put(customDTO.getId_order(), customDTO.getIPN());
        }
        model.addAttribute("type", "Preview");
        model.addAttribute("title", "View");
        model.addAttribute("customs", clientHashMap);
        return "custom";
    }

    @GetMapping("/view/{id_order}")
    public String view(@PathVariable(value = "id_order") int id_order, Model model) {
        Custom custom = customRepository.findById(id_order).orElseThrow();
        float total_price = customService.getCostServices(id_order);
        model.addAttribute("title", "View");
        model.addAttribute("custom", custom);
        model.addAttribute("type", "More");
        model.addAttribute("total_price", total_price);
        return "custom";
    }


    @GetMapping("/add")
    public String add(Model model) {

        List<CustomDTO> customs = customService.getAllCustoms();
        //List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();
        model.addAttribute("title", "Add");
        //model.addAttribute("services", additionalServices);
        return "custom";
    }

    @PostMapping("/add/service")
    public String addService(@RequestParam(value = "id_order") Integer id_order, @RequestParam List<Integer> service, Model model) {
        System.out.println(id_order);

        order_servicesService.addOrderService(customService.getById(id_order), service.stream()
             .map(additionalServicesService::getById)
             .collect(Collectors.toList()));

        model.addAttribute("title", "Add");
        return "custom";
    }

    @PostMapping("/add")
    public String add(@RequestParam String IPN, @RequestParam int id_booking, Model model) {

        //customService.addCustom(clientService.getById(IPN), bookingService.getById(id_booking), service);



            Client client = clientService.getById(IPN);
            Booking booking =bookingService.getById(id_booking);
        model.addAttribute("title", "Add");
            if(client == null) {
                model.addAttribute("exception", "non-client");
                return "custom";
            }
            if(booking == null){
                model.addAttribute("exception", "non-booking");
                return "custom";
            }


            Integer id = customService.addCustom(clientService.getById(IPN), bookingService.getById(id_booking));


        List<CustomDTO> customs = customService.getAllCustoms();
        List<AdditionalServicesDTO> additionalServices = additionalServicesService.getAllAdditionalServices();

        model.addAttribute("typeForm","additional");
        model.addAttribute("id_order",id);
       model.addAttribute("services", additionalServices);
        return "custom";
    }

    @GetMapping("/remove")
    public String remove(Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        Map<Integer, Client> clientHashMap = new HashMap<>();
        for (CustomDTO customDTO: customs){
            clientHashMap.put(customDTO.getId_order(), customDTO.getIPN());
        }
        model.addAttribute("title", "Remove");
        model.addAttribute("customs", clientHashMap);
        return "custom";
    }

    @PostMapping("/remove/{id_order}")
    public String remove(@PathVariable(value = "id_order") int id_order, Model model) {
        Custom custom = customRepository.findById(id_order).orElseThrow();
        customRepository.delete(custom);
        return "redirect:/view";
    }

    @GetMapping("/edit")
    public String edit (Model model) {
        List<CustomDTO> customs = customService.getAllCustoms();
        model.addAttribute("title", "edit");
        model.addAttribute("customs", customs);
        return "custom";
    }

    @GetMapping("/edit/{id_order}")
    public String edit (@PathVariable(value = "id_order") int id_order, @RequestParam List<Integer> services,Model model) {
        Custom custom = customRepository.findById(id_order).orElseThrow();
        float total_price = customService.getCostServices(id_order);
        model.addAttribute("title", "View");
        model.addAttribute("custom", custom);
        model.addAttribute("type", "More");
        model.addAttribute("total_price", total_price);
//        Custom custom = customRepository.findById(id_order).orElseThrow();
//        customService.addService(id_order, services);
//        customRepository.save(custom);
        return "custom";
    }
}
