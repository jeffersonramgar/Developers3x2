package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IndexController {
    @Autowired
    private IUsuarioService usuarioService;
    private Usuario usuario;

    private final Logger LOG = Logger.getLogger("" + TransactionController.class);
    private String titulo;
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        System.out.println(usuario);
        LOG.log(Level.INFO, ""+user);
        titulo = "Manejador de usuarios";
        model.addAttribute("titulo",titulo);
        model.addAttribute("usuario",usuario);
        return "index";
    }
}
