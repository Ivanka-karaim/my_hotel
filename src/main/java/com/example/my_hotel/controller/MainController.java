package com.example.my_hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String greeting( Model model) {
        model.addAttribute("title", "Головна сторінка");
        return "general";
    }

}
