//package com.example.my_hotel.controller;
//
//import com.example.my_hotel.dto.RoomDTO;
//import com.example.my_hotel.model.Room;
//import com.example.my_hotel.repository.ClassificationroomRepository;
//import com.example.my_hotel.repository.RoomRepository;
//import com.example.my_hotel.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//public class BronController {
//    @Autowired
//    private RoomService roomService;
//    @Autowired
//    private RoomRepository roomRepository;
//    @Autowired
//    private ClassificationroomRepository classificationroomRepository;
//
//    @GetMapping("/viewRooms")
//    public String view(Model model) {
//        List<RoomDTO> rooms = roomService.getAllRooms();
//        model.addAttribute("title", "View");
//        System.out.println("62665");
//        model.addAttribute("rooms", rooms);
//        return "rooms";
//    }
//
//    @GetMapping("/addRoom")
//    public String adds(Model model) {
//        model.addAttribute("title", "Add");
//        return "rooms";
//    }
//
//    @GetMapping("/removeRoom")
//    public String remove(Model model) {
//        List<RoomDTO> rooms = roomService.getAllRooms();
//        model.addAttribute("title", "Remove");
//        model.addAttribute("rooms", rooms);
//        return "rooms";
//    }
//    @PostMapping("/removeRoom")
//    public String remove_room(@RequestParam int id_room, Model model) {
//        RoomDTO room = roomService.getId(id_room);
//        model.addAttribute("rooms", room);
//        model.addAttribute("title", "Remove");
//        return "rooms";
//    }
//    @GetMapping("/editRoom")
//    public String edit (Model model) {
//        List<RoomDTO> rooms = roomService.getAllRooms();
//        model.addAttribute("title", "Edit");
//        model.addAttribute("rooms", rooms);
//        return "rooms";
//    }
//    @PostMapping("/addRoom")
//    public String add(@RequestParam int floor, @RequestParam String image, @RequestParam String depiction, @RequestParam int id_classification) {
//        Room room = new Room(floor, "img/"+image+".jpg", depiction, classificationroomRepository.findById(id_classification).get());
//        roomRepository.save(room);
//        return "redirect:/viewRooms";
//    }
//
//    @PostMapping("/removeRoom/{id_room}")
//    public String remove(@PathVariable(value = "id_room") int id_room, Model model) {
//        Room room = roomRepository.findById(id_room).orElseThrow();
//        roomRepository.delete(room);
//        return "redirect:/viewRooms";
//    }
//
//    @PostMapping("/edit_room/{id_room}")
//    public String edit (@PathVariable(value = "id_room") int id_room, int floor,String depiction, int classificationroom) {
//        Room room = roomRepository.findById(id_room).orElseThrow();
//        room.setFloor(floor);
//        room.setDepiction(depiction);
//        room.setClassificationroom(classificationroomRepository.findById(classificationroom).get());
//        roomRepository.save(room);
//        return "redirect:/viewRooms";
//    }
//    @PostMapping("/editRoom")
//    public String edit_room(@RequestParam int id_room, Model model) {
//        RoomDTO room = roomService.getId(id_room);
//        model.addAttribute("rooms", room);
//        model.addAttribute("title", "Edit");
//        return "rooms";
//    }
//    @GetMapping("/edit_room/{id_room}")
//    public String edit_service (@PathVariable(value = "id_room") int id_room, Model model) {
//        if (!roomRepository.existsById(id_room)){
//            return "/editRoom";
//        }
//        RoomDTO room = roomService.getId(id_room);
//        model.addAttribute("room", room);
//        return "edit_room";
//    }
//
//}
//
