package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Classificationroom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.ClassificationroomRepository;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.ClassificationService;
import com.example.my_hotel.service.RoomService;
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
public class ClassificationController {
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private ClassificationroomRepository classificationroomRepository;

    @GetMapping("/viewClass")
    public String view(Model model) {
        List<Classificationroom> classs = classificationService.getAllClass();
        model.addAttribute("title", "View");
        model.addAttribute("class", classs);
        return "classifications";
    }

    @GetMapping("/addClass")
    public String adds(Model model) {
        model.addAttribute("title", "Add");
        return "classifications";
    }

    @GetMapping("/removeClass")
    public String remove(Model model) {
        List<Classificationroom> classs = classificationService.getAllClass();
        model.addAttribute("title", "Remove");
        model.addAttribute("class", classs);
        return "classifications";
    }
    @PostMapping("/removeClass")
    public String remove_room(@RequestParam int id_classification, Model model) {
        Classificationroom classs = classificationService.getById(id_classification);
        model.addAttribute("class", classs);
        model.addAttribute("title", "Remove");
        return "classifications";
    }
    @GetMapping("/editClass")
    public String edit (Model model) {
        List<Classificationroom> classs = classificationService.getAllClass();
        model.addAttribute("title", "Edit");
        model.addAttribute("class", classs);
        return "classifications";
    }
    @PostMapping("/addClass")
    public String add(@RequestParam String classification_room, @RequestParam float cost,  @RequestParam int number_beds) {
        Classificationroom classificationroom= new Classificationroom(classification_room, cost, number_beds);
        classificationroomRepository.save(classificationroom);
        return "redirect:/viewClass";
    }

    @PostMapping("/removeClass/{id_classification}")
    public String remove(@PathVariable(value = "id_classification") int id_classification, Model model) {
        Classificationroom classs= classificationroomRepository.findById(id_classification).orElseThrow();
        classificationroomRepository.delete(classs);
        return "redirect:/viewClass";
    }

    @PostMapping("/edit_class/{id_classification}")
    public String edit (@PathVariable(value = "id_classification") int id_classification, String classification_room, float cost, int number_beds) {
        Classificationroom classificationroom = classificationroomRepository.findById(id_classification).orElseThrow();
        classificationroom.setClassification_room(classification_room);
        classificationroom.setCost(cost);
        classificationroom.setNumber_beds(number_beds);
        classificationroomRepository.save(classificationroom);
        return "redirect:/viewClass";
    }
    @PostMapping("/editClass")
    public String edit_room(@RequestParam int id_classification, Model model) {
        Classificationroom classificationroom = classificationService.getById(id_classification);
        model.addAttribute("class", classificationroom);
        model.addAttribute("title", "Edit");
        return "classifications";
    }
    @GetMapping("/edit_class/{id_classification}")
    public String edit_service (@PathVariable(value = "id_classification") int id_classification, Model model) {
        if (!classificationroomRepository.existsById(id_classification)){
            return "/editClass";
        }
        Classificationroom classs = classificationService.getById(id_classification);
        model.addAttribute("class", classs);
        return "edit_class";
    }

}

