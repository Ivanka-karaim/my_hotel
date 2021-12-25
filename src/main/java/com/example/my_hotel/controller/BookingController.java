package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Classificationroom;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.BookingRepository;
import com.example.my_hotel.service.BookingService;
import com.example.my_hotel.service.RoomService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.joda.time.Days;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class BookingController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    private Date date_1;
    private Date date_2;
    private int count_1;
    private Room room_1;
    private boolean employee;
    private float price;
    List<RoomDTO> rooms;

    @GetMapping("/booking")
    public String main(Model model) {
        employee=false;
        model.addAttribute("booking", new Booking());
        model.addAttribute("error","");
        return "booking";
    }
    @GetMapping("/booking1")
    public String main1(Model model) {
        employee=true;
        model.addAttribute("booking", new Booking());
        model.addAttribute("error","");
        return "booking";
    }
    @PostMapping("/booking{id}")
    public String search(Booking booking, Model model) {
        LocalDate date1 = LocalDate.ofInstant(booking.getDate_arrival().toInstant(), ZoneId.systemDefault());
        LocalDate date2 = LocalDate.ofInstant(booking.getDate_departure().toInstant(), ZoneId.systemDefault());
        LocalDate now = LocalDate.now();
        date_1=booking.getDate_arrival();
        date_2=booking.getDate_departure();
        count_1=booking.getCount_people();
        if (date1.isAfter(date2)) {
            model.addAttribute("error","The date of eviction is specified earlier than the date of settlement...");
            return "booking";
        }
        else if(date1.equals(date2)) {
            model.addAttribute("error","You cannot book a room for less than 1 night...");
            return "booking";
        }
        else if (now.isAfter(date1)) {
            model.addAttribute("error","You book a room on a date that has already passed...");
            return "booking";
        }
        else {
            model.addAttribute("booking", booking);
            int days = Days.daysBetween(new DateTime(date_1), new DateTime(date_2)).getDays();
            rooms = roomService.getFreeRooms(booking.getDate_arrival(), booking.getDate_departure(), booking.getCount_people());
            if (rooms.isEmpty()) {
                model.addAttribute("error","Sorry, there are no available rooms for the given dates...");
                return "booking";
            }
            for(RoomDTO r:rooms) {
                r.setCost(r.getCost()*days);
                System.out.println(r.getId_room());
            }
            model.addAttribute("rooms", rooms);
            return "booking/free_room";
        }
    }
    @GetMapping("/booking/{id}_{error}")
    public String booking_custom(@PathVariable(value="id") int id,@PathVariable(value="error") int error,  Model model) {
        Booking booking = new Booking();
        booking.setRoom(roomService.getById(id));
        room_1=booking.getRoom();
        for (RoomDTO r: rooms) {
            if(r.getId_room()==room_1.getId_room()) {
                price=r.getCost();
                System.out.println(price);
            }
        }
        booking.setDate_arrival(date_1);
        booking.setDate_departure(date_2);
        booking.setCount_people(count_1);
        booking.setPrice(price);
        model.addAttribute("error", error);
        model.addAttribute("booking", booking);
        return "booking/form";
    }
    @PostMapping("/booking/{id}_{error}")
    public String booking_rest(Booking booking, Model model)  {
        String str=  "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
        if (!Pattern.compile(str).matcher(booking.getPhone_number()).matches()) {
            return "redirect:/booking/{id}_1";
        }
        booking.setDate_arrival(date_1);
        booking.setDate_departure(date_2);
        booking.setCount_people(count_1);
        booking.setRoom(room_1);
        System.out.println(price);
        booking.setPrice(price);
        System.out.println(booking.getPrice());
        System.out.println();
        bookingService.addBooking(booking);
        model.addAttribute("booking", booking);
        if (employee) {
            model.addAttribute("role", "employee");
        }
        else {
            model.addAttribute("role", "client");
        }
        return "booking/done";
    }
    @GetMapping("/viewBook")
    public String view_book(Model model) {
//        List<Booking> booking = bookingService.getAllBooks();
        List<Booking> booking = bookingRepository.findAll();
        model.addAttribute("title", "View");
        model.addAttribute("booking", booking);
        return "bookings";
    }
    @GetMapping("/removeBook")
    public String remove_book(Model model) {
        List<Booking> booking = bookingService.getAllBooks();
        model.addAttribute("title", "Remove");
        model.addAttribute("booking", booking);
        return "bookings";
    }
    @PostMapping("/removeBook")
    public String remove_book(@RequestParam int id_booking, Model model) {
        Booking booking = bookingRepository.getById(id_booking).get();
        model.addAttribute("booking", booking);
        model.addAttribute("title", "Remove");
        return "bookings";
    }
    @PostMapping("/removeBook/{id_booking}")
    public String removes_book(@PathVariable(value = "id_booking") int id_booking, Model model) {
        Booking booking= bookingRepository.findById(id_booking).orElseThrow();
        bookingRepository.delete(booking);
        return "redirect:/viewBook";
    }
}