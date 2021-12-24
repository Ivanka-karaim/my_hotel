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
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/viewClient")
    public String view(Model model) {
        List<ClientDTO> client = clientService.getAllClient();
        model.addAttribute("title", "View");
        model.addAttribute("clients", client);
        return "client";
    }

    @GetMapping("/addClient")
    public String add(Model model) {
        model.addAttribute("title", "Add");
        return "client";
    }

    @GetMapping("/removeClient")
    public String remove(Model model) {
        List<ClientDTO> client = clientService.getAllClient();
        model.addAttribute("title", "Remove");
        model.addAttribute("clients", client);
        return "client";
    }

    @GetMapping("/editClient")
    public String edit (Model model) {
        List<ClientDTO> client = clientService.getAllClient();
        model.addAttribute("title", "Edit");
        model.addAttribute("clients", client);
        return "client";
    }
    @PostMapping("/addClient")
    public String add(@RequestParam String IPN, @RequestParam String surname,@RequestParam String name,@RequestParam String patronymic,@RequestParam String phone_number,@RequestParam String email) {
        Client client = new Client(IPN,surname,name,patronymic,phone_number,email);
        clientRepository.save(client);
        System.out.println(242432);
        return "redirect:/viewClient";
    }

    @PostMapping("/removeClient/{IPN}")
    public String remove(@PathVariable(value = "IPN") String IPN, Model model) {
        Client client = clientRepository.findById(IPN).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/viewClient";
    }

    @PostMapping("/edit_client/{IPN}")
    public String edit (@PathVariable(value = "IPN") String IPN, String surname, String name,String patronymic, String phone_number, String email) {
        Client client = clientRepository.findById(IPN).orElseThrow();
        client.setSurname(surname);
        client.setName(name);
        client.setPatronymic(patronymic);
        client.setPhone_number(phone_number);
        client.setEmail(email);
        clientRepository.save(client);
        return "redirect:/viewClient";
    }

    @GetMapping("/edit_client/{IPN}")
    public String edit_client (@PathVariable(value = "IPN") String IPN, Model model) {
        if (!clientRepository.existsById(IPN)){
            return "/editClient";
        }
        Optional<Client> client = clientRepository.findById(IPN);
        model.addAttribute("clients", client.get());
        return "edit_client";
    }

//    @GetMapping("/client")
//    public String client(Model model) {
//        Iterable<Client> clients=clientRepository.findAll();
//        model.addAttribute("clients", clients);
//        return "clients";
//    }
//    @GetMapping("/clientregister")
//    public String clientregister(Model model) {
//        Iterable<Client> clientregister=clientRepository.findAll();
//        model.addAttribute("clientregister", clientregister);
//        return "clientregister";
//    }
//    @GetMapping("/clienlogin")
//    public String clients(Model model) {
//        Iterable<Client> clientlogin=clientRepository.findAll();
//        model.addAttribute("clientlogin", clientlogin);
//        return "clientlogin";
//    }
}
