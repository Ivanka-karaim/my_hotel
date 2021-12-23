/*package com.example.my_hotel.controller;

import com.example.my_hotel.model.Employees;
import com.example.my_hotel.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    //public static boolean authorized = false;
    public static Integer sessionId = null;

    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping("/login")
    public String login(Model model){
        if(sessionId != null){
            return "redirect:/employee";
        }
        model.addAttribute("title", "Вхід");
        //model.addAttribute("email","");
        model.addAttribute("errorEmail","");
        model.addAttribute("errorPass","");
        return "login";
    }
    @PostMapping("/login")
    public String authorize(@RequestParam String email, @RequestParam String password, Model model){
        model.addAttribute("title", "Вхід");
        List<Employees> employees = employeesRepository.findAll();
        boolean email_confirm = false;
        boolean password_confirm;
        int idList = -1;
        for (int i = 0; i < employees.size(); i++){
            if (email.equals(employees.get(i).getEmail())){
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
            if (email_confirm && password_confirm){
                sessionId = employees.get(idList).getId();
                return "redirect:/employee";
            } else if(email_confirm){
                model.addAttribute("errorPass","Wrong password. Try again or call Technical Support.");
                //model.addAttribute("email", email);
            } else if(password_confirm){
                model.addAttribute("errorEmail", "Cannot find your email address");
            }
            ///return "redirect:Login?errorEmail";
            return "login";
        }catch (IndexOutOfBoundsException e){
            model.addAttribute("errorEmail","Cannot find your email address");
             return "login";
        }
        /*
        if(authorized) {
            return "employee";
        }

        //return "employee";
    }
}
*/