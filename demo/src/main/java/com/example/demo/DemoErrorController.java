package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoErrorController implements ErrorController {

    @RequestMapping("/error")
    public String errorHandler() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
