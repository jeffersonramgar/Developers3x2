package com.develpers3x2.thymeleaf.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {
    private final Logger LOG = Logger.getLogger("" + TransactionController.class);
    private String titulo;
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user){
        LOG.log(Level.INFO, "index");
        LOG.log(Level.INFO, ""+user);
        titulo = "Manejador de usuarios";
        model.addAttribute("titulo",titulo);
        return "index";
    }
}
