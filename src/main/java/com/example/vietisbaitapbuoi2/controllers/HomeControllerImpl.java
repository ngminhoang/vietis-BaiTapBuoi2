package com.example.vietisbaitapbuoi2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerImpl implements Homecontroller{

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring Boot!");
        return "index";
    }
}
