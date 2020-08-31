package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String listPeople(Model model) {
        model.addAttribute(personRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String loadPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "personform";
    }

    @PostMapping("/process")
    public String processPersonForm(@Valid Person person, BindingResult result) {
        if ( result.hasErrors() ) {
            return "personform";
        }
        return "redirect:/";
    }

}
