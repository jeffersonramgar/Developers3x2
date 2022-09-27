package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Enterprise;
import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.service.IEnterpriseService;
import com.develpers3x2.thymeleaf.service.IUsuarioService;
import com.develpers3x2.thymeleaf.util.EncriptarPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UsuariosController {
    private String titulo;
    private Usuario usuario;

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IEnterpriseService enterpriseService;

    private final Logger LOG = Logger.getLogger("" + IndexController.class);

    @GetMapping("/usuarios/listar")
    public String getListUsuarios(Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        titulo = "Todos los usuarios";
        LOG.log(Level.INFO, "getListUsuarios");
        List<Usuario> usuarios = usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", titulo);
        return "usuarios/listar";
    }

    @GetMapping("/usuarios/modificar")
    public String createUsuario(Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        titulo = "Datos de Usuario";


        LOG.log(Level.INFO, "createUsuario");
        Usuario usuarioNew = new Usuario();
        List<Enterprise> enterprises = enterpriseService.findAll();


        model.addAttribute("titulo", titulo);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuario_new", usuarioNew);
        model.addAttribute("enterprises", enterprises);
        return "usuarios/modificar";
    }

    @RequestMapping(value ="/usuarios/editar/{id}", method = RequestMethod.GET)
    public String editUsuario(@PathVariable("id") int id, Model modelo, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        titulo = "Editar usuario";

        LOG.log(Level.INFO, "editUsuario");
        Usuario usuarioNew = usuarioService.findById(id);

        List<Enterprise> enterprises = enterpriseService.findAll();

        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("usuario_new", usuarioNew);
        modelo.addAttribute("enterprises", enterprises);
        return "usuarios/modificar";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(Usuario userNew, BindingResult error, Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        titulo = "Editar usuario";

        LOG.log(Level.INFO, "guardarUsuario");


        if(error.hasErrors()){

            System.out.println("SI hay errores");

            return "usuarios/modificar";}

        userNew.setEstado(true);

        userNew.setCreatedAt(new Date());
        userNew = usuarioService.createUsuario(userNew);
        return "redirect:/usuarios/listar";
    }
}
