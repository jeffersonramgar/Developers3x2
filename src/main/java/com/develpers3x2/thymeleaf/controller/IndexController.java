package com.develpers3x2.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private String titulo;
    @GetMapping("/")
    public String index(Model model){
        titulo = "Manejador de usuarios";
        model.addAttribute("titulo",titulo);
        return "index";
    }
}
