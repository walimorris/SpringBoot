package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/emailRequestForm")
    public String requestEmailForm() {
        return "emailFormTemplate";
    }

    @RequestMapping("/processEmailRequestForm")
    public String loadFromEmailPage(@RequestParam("email") String email, Model model) {
        model.addAttribute("emailVal", email);
        return "emailConfirmationPage";
    }
}
