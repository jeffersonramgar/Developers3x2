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
public class UsuariosController extends EncriptarPassword {
    private String titulo;
    private Usuario usuarioLog;

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IEnterpriseService enterpriseService;

    private final Logger LOG = Logger.getLogger("" + IndexController.class);

    @GetMapping("/usuarios/listar")
    public String getListUsuarios(Model model, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        titulo = "Todos los usuarios";
        LOG.log(Level.INFO, "getListUsuarios");

        List<Usuario> usuarios = usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioLog", this.usuarioLog);
        model.addAttribute("titulo", titulo);
        return "usuarios/listar";
    }

    @GetMapping("/usuarios/modificar")
    public String createUsuario(Model model, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        titulo = "Datos de Usuario";


        LOG.log(Level.INFO, "createUsuario");
        Usuario usuarioNew = new Usuario();
        List<Enterprise> enterprises = enterpriseService.findAll();


        model.addAttribute("titulo", titulo);
        model.addAttribute("usuarioLog", this.usuarioLog);
        model.addAttribute("usuario", usuarioNew);
        model.addAttribute("enterprises", enterprises);
        return "usuarios/modificar";
    }

    @RequestMapping(value ="/usuarios/editar/{id}", method = RequestMethod.GET)
    public String editUsuario(@PathVariable("id") int id, Model modelo, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        titulo = "Editar usuario";

        LOG.log(Level.INFO, "editUsuario");
        Usuario usuarioNew = usuarioService.findById(id);

        List<Enterprise> enterprises = enterpriseService.findAll();

        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("usuarioLog", this.usuarioLog);
        modelo.addAttribute("usuario", usuarioNew);
        modelo.addAttribute("enterprises", enterprises);
        return "usuarios/modificar";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@RequestParam("pass_old") String pass_old, @Valid Usuario usuarioNew, BindingResult error, Model modelo, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        titulo = "Editar usuario";
        System.out.println(usuarioNew);

        LOG.log(Level.INFO, "guardarUsuario");


        if(error.hasErrors()){
            System.out.println("SI hay errores");

            List<Enterprise> enterprises = enterpriseService.findAll();
            modelo.addAttribute("titulo", titulo);
            modelo.addAttribute("usuarioLog", this.usuarioLog);
            modelo.addAttribute("enterprises", enterprises);
            return "usuarios/modificar";}

        if(!usuarioNew.getPassword().isEmpty())
            usuarioNew.setPassword(encriptarPassword(usuarioNew.getPassword()));
        else
            usuarioNew.setPassword(pass_old);

        usuarioNew.setEstado(true);
        usuarioNew.setCreatedAt(new Date());

        usuarioNew = usuarioService.createUsuario(usuarioNew);
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/usuarios/eliminar/{id}", method = RequestMethod.GET)
    public String eliminarUsuario(@PathVariable("id") int id){
        LOG.log(Level.INFO, "eliminarUsuario");

        Usuario usuario = usuarioService.findById(id);
        usuario.setEstado(false);
        usuario = usuarioService.createUsuario(usuario);
        return "redirect:/usuarios/listar";
    }
}
