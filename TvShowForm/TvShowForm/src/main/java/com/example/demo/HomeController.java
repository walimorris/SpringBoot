package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @GetMapping("/tvshowform")
    public String loadTvForm(Model model) {
        model.addAttribute("tvshow", new TvShow());
        return "tvshowform";

    }

    @PostMapping("/tvshowform")
    public String processTvForm(@Valid TvShow tvshow, BindingResult result) {
        if ( result.hasErrors() ) {
            return "tvshowform";
        }
        return "tvshowconfirm";
    }
}
