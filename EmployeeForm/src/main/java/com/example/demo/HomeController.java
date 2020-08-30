package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/employeeform")
    public String loadEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeform";

    }

    @PostMapping("employeeform")
    public String loadFromEmployeeForm(Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "confirmEmployee";
    }
}
