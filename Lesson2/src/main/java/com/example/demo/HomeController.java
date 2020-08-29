package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("sayHello", "Earthlings: Whoa! What are these creatures?!");
        model.addAttribute("sayHello2", "Aliens: We come in peace");
        return "homeTemplate";
    }
}
