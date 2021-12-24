package com.example.my_hotel.controller;

import com.example.my_hotel.dto.RoomDTO;
import com.example.my_hotel.model.*;
import com.example.my_hotel.repository.EmployeesRepository;
import com.example.my_hotel.repository.OccupationsRepository;
import com.example.my_hotel.repository.RoomRepository;
import com.example.my_hotel.service.CustomService;
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
import java.util.*;

@Controller
public class EmployeeController {
    public static Integer sessionId = null;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private OccupationsRepository occupationsRepository;

    @Autowired
    private CustomService customService;

    @GetMapping("/employee")
    public String employee(Model model) {
        model.addAttribute("title", "Menu");
        if (sessionId == null){
            return "redirect:/login";
        }
        //List <Occupations> privileged =
        if (getEmployee().getId_occupation().isManagement()){
            model.addAttribute("response", "True");
            //TODO
            //model.addAttribute("custom", "/view");
            //model.addAttribute("view", "/viewServ");
        } else {
            model.addAttribute("response", null);
            //TODO
            //model.addAttribute("custom", "/employee/view");
            //model.addAttribute("view", "/employee/viewServ");
        }
        return "employee";
        //return "employee-profile";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (sessionId != null) {
            return "redirect:/employee";
        }
        model.addAttribute("title", "Log in");
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
            } else if (email_confirm && password.equals("")) {
                model.addAttribute("errorPass", "Please enter the password.");
            } else if (email_confirm) {
                model.addAttribute("errorPass", "Wrong password. Try again or call Technical Support.");
                //model.addAttribute("email", email);
            } else if (password_confirm) {
                model.addAttribute("errorEmail", "Cannot find your email address.");
            }
            ///return "redirect:Login?errorEmail";
            return "login";
        } catch (IndexOutOfBoundsException e) {
            model.addAttribute("errorEmail", "Cannot find your email address");
            return "login";
        }
    }

    @GetMapping("/employee/schedule")
    public String schedule(Model model) {
        model.addAttribute("title", "Schedules");
        if (sessionId == null){
            return "redirect:/login";
        }
        Schedules schedule = getEmployee().getId_schedule();
        //model.addAttribute("email","");;
//        if(schedule == null){
//            return "redirect:/employee";
//        }
//        Weeks weeks1 = schedule.getId_week1();
//        Weeks weeks2 = schedule.getId_week2();

        List <Boolean> week1 = new LinkedList<>();
        List <Boolean> week2 = new LinkedList<>();
        Weeks[] weeks = {schedule.getId_week1(), schedule.getId_week2()};
        week1.add(weeks[0].isSun());
        week1.add(weeks[0].isMon());
        week1.add(weeks[0].isTue());
        week1.add(weeks[0].isWed());
        week1.add(weeks[0].isThu());
        week1.add(weeks[0].isFri());
        week1.add(weeks[0].isSat());

        week2.add(weeks[1].isSun());
        week2.add(weeks[1].isMon());
        week2.add(weeks[1].isTue());
        week2.add(weeks[1].isWed());
        week2.add(weeks[1].isThu());
        week2.add(weeks[1].isFri());
        week2.add(weeks[1].isSat());

        model.addAttribute("week1", week1);
        model.addAttribute("week2", week2);
        return "schedules";
    }

    @GetMapping("/employee/profile")
    public String profile(Model model){
        model.addAttribute("title", "Profile");
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
//        Optional<Employees> opt;
//        try {
//            //System.out.println("\n\n\nId:" + sessionId + "\n\n\n");
//            opt = employeesRepository.findById(sessionId);
//            //System.out.println("\n\n\nId:" + opt.get().getId() + "\n\n\n");
//        }catch (IllegalArgumentException e){
//            return "general";
//        }
//        if(opt.isEmpty()){
//            return "general";
//        }
//
//        Employees employee = opt.get();
        Employees employee = getEmployee();
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
        model.addAttribute("title", "Exit");
        sessionId = null;
        return "general";
    }

    //TODO
//    @GetMapping("/employee/viewCustom")
//    public String viewCustom(Model model){
//        model.addAttribute("title", "Customer");
//        //sessionId = null;
//        return "watch-custom";
//    }

    //TODO
//    @GetMapping("/employee/viewService")
//    public String viewService(Model model){
//        model.addAttribute("title", "Services");
//        //sessionId = null;
//        return "general";
//    }

    public Employees getEmployee(){
        Optional<Employees> opt;
        try {
            //System.out.println("\n\n\nId:" + sessionId + "\n\n\n");
            opt = employeesRepository.findById(sessionId);
            //System.out.println("\n\n\nId:" + opt.get().getId() + "\n\n\n");
        }catch (IllegalArgumentException e){
            return null;
        }
        if(opt.isEmpty()){
            return null;
        }
        return opt.get();
    }
}
