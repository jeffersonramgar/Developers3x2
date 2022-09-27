package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Enterprise;
import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.service.IEnterpriseService;
import com.develpers3x2.thymeleaf.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class EnterpriseController{

    @Autowired
    private IEnterpriseService enterpriseService;
    @Autowired
    private IUsuarioService usuarioService;

    private final Logger LOG = Logger.getLogger("" + EnterpriseController.class);

    private Usuario usuarioLog;
    private String titulo;


    @GetMapping("/enterprises/listar")
    public String getListEnterprise(Model model, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        LOG.log(Level.INFO, "getListEnterprise");
        titulo = "Listado de empresas";

        List<Enterprise> enterprises = enterpriseService.findAll();

        model.addAttribute("enterprises", enterprises);
        model.addAttribute("usuarioLog", this.usuarioLog);
        model.addAttribute("titulo", this.titulo);
        return "empresas/listar";
    }



    @GetMapping("/enterprise/modificar")
    public String createEnterprise(Model modelo, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        LOG.log(Level.INFO, "createEnterprise");
        titulo = "Datos de empresas";
        Enterprise enterprise = new Enterprise();
        modelo.addAttribute("enterprise", enterprise);
        modelo.addAttribute("usuarioLog", this.usuarioLog);
        modelo.addAttribute("titulo", this.titulo);
        return "empresas/modificar";

    }

    @RequestMapping(value = "/enterprise/editar/{id}", method = RequestMethod.GET)
    public String editEnterprise(@PathVariable("id") long id, Model modelo, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());
        LOG.log(Level.INFO, "createEnterprise");
        titulo = "Datos de empresas";

        Enterprise enterprise = enterpriseService.findById((int) id);

        modelo.addAttribute("usuarioLog", this.usuarioLog);
        modelo.addAttribute("titulo", this.titulo);
        modelo.addAttribute("enterprise", enterprise);
        return "empresas/modificar";
    }

    @PostMapping("/enterprise/guardar")
    public String guardarEnterprise(@Valid Enterprise enterprise, BindingResult error, Model modelo, @AuthenticationPrincipal User usuarioLog){
        this.usuarioLog = usuarioService.findByUsername(usuarioLog.getUsername());

        for (ObjectError e : error.getAllErrors())
            System.out.println(e.toString());

        if (error.hasErrors()){
            titulo = "Datos de empresa";

            modelo.addAttribute("titulo", titulo);
            modelo.addAttribute("usuarioLog", this.usuarioLog);

            return "empresas/modificar";
        }

        enterprise.setEstado(true);
        enterprise = enterpriseService.createEnterprise(enterprise);

        return "redirect:/enterprises/listar";
    }

    @RequestMapping(value = "/enterprise/eliminar/{id}", method = RequestMethod.GET)
    public String deleteEnterprise(@PathVariable("id") long id, Model modelo) {
        LOG.log(Level.INFO, "eliminarEmpresa");

        Enterprise enterprise = enterpriseService.findById((int) id);
        enterprise.setEstado(false);
        enterprise = enterpriseService.createEnterprise(enterprise);
        return "redirect:/enterprises/listar";
    }

}