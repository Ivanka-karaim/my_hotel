package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.Booking;
import com.example.my_hotel.model.Employees;
import com.example.my_hotel.model.Occupations;
import com.example.my_hotel.model.Room;
import com.example.my_hotel.repository.EmployeesRepository;
import com.example.my_hotel.repository.OccupationsRepository;
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
import java.util.Optional;

@Controller
public class EmployeeController {
    public static Integer sessionId = null;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private OccupationsRepository occupationsRepository;

    @GetMapping("/employee")
    public String employee(Model model) {
        model.addAttribute("title", "Профіль");
        if (sessionId == null){
            return "login";
        }
        return "employee";
        //return "employee-profile";
    }


    @GetMapping("/employee/login")
    public String login(Model model) {
        if (sessionId != null) {
            return "redirect:/employee";
        }
        model.addAttribute("title", "Вхід");
        //model.addAttribute("email","");
        model.addAttribute("errorEmail", "");
        model.addAttribute("errorPass", "");
        return "login";
    }

    @PostMapping("/login")
    public String authorize(@RequestParam String email, @RequestParam String password, Model model) {
        model.addAttribute("title", "Вхід");
        List<Employees> employees = employeesRepository.findAll();
        boolean email_confirm = false;
        boolean password_confirm;
        int idList = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (email.equals(employees.get(i).getEmail())) {
                email_confirm = true;
                idList = i;
                break;
            }
        }
        //boolean password_confirm = false;
        //if (password.equals(employees.get(id).getPassword_employee())){
        //    password_confirm = true;
        //}
        //if (email_confirm && password_confirm){
        try {
            password_confirm = password.equals(employees.get(idList).getPassword_employee());
            if (email_confirm && password_confirm) {
                sessionId = employees.get(idList).getId();
                return "redirect:/employee";
            } else if (email_confirm) {
                model.addAttribute("errorPass", "Wrong password. Try again or call Technical Support.");
                //model.addAttribute("email", email);
            } else if (password_confirm) {
                model.addAttribute("errorEmail", "Cannot find your email address");
            }
            ///return "redirect:Login?errorEmail";
            return "login";
        } catch (IndexOutOfBoundsException e) {
            model.addAttribute("errorEmail", "Cannot find your email address");
            return "login";
        }
    }

    @GetMapping("/employee/profile")
    public String profile(Model model){
        if (sessionId == null){
            return "login";
        }
        //List<Employees> employee = employeesRepository.findAllById();
        //String sql = "SELECT * FROM employees WHERE id = " + sessionId;
        //@Query(sql)
//        List<Employees> employees = employeesRepository.findAll();
//        Employees employee = null;
//        for (Employees i : employees){
//            if (sessionId.equals(i.getId())){
//                employee = i;
//            }
//      }
        Optional<Employees> opt;
        try {
            //System.out.println("\n\n\nId:" + sessionId + "\n\n\n");
            opt = employeesRepository.findById(sessionId);
            //System.out.println("\n\n\nId:" + opt.get().getId() + "\n\n\n");
        }catch (IllegalArgumentException e){
            return "general";
        }
        if(opt.isEmpty()){
            return "general";
        }

        Employees employee = opt.get();

        //model.addAttribute("employee", employee);
//        System.out.println(employee.getFullname());
//        System.out.println(employee.getSurname());
//        System.out.println(employee.getPatron());
//        System.out.println(employee.getEmail());
//        System.out.println(employee.getPhone_number());
//        System.out.println(employee.getBirth_date());
//        System.out.println(employee.getIpn());
//        System.out.println(employee.getWorkbook());
        model.addAttribute("name", employee.getFullname());
        model.addAttribute("surname", employee.getSurname());
        model.addAttribute("patron", employee.getPatron());
        model.addAttribute("email", employee.getEmail());
        model.addAttribute("phone", employee.getPhone_number());
        model.addAttribute("birth", employee.getBirth_date().toString());
        model.addAttribute("ipn", employee.getIpn());
        model.addAttribute("workbook", employee.getWorkbook());
        //Occupations occupation = employeesRepository.findOccupationById(employee.getId()).get(0);
        //Occupations occupation = occupationsRepository.findById();
        //Occupations occupation = employee.getId_occupation();
        Occupations occupation = occupationsRepository.findById(employee.getId_occupation().getId()).get();
        model.addAttribute("occupation", occupation.getOccupation());
        model.addAttribute("salary", occupation.getSalary());
        return "employee-profile";
    }

    @GetMapping("/employee/exit")
    public String exit(Model model){
        sessionId = null;
        return "general";
    }
}
