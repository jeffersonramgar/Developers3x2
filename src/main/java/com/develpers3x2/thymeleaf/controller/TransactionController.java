package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Transaction;
import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.service.ITransactionService;
import com.develpers3x2.thymeleaf.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class TransactionController {

    private String titulo;

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IUsuarioService usuarioService;

    private final Logger LOG = Logger.getLogger("" + TransactionController.class);

    @GetMapping("/movements/list")
    public String GetListTransactionByenterprise(Model model){
        LOG.log(Level.INFO, "GetListTransactionByenterprise");
        List<Transaction> transacciones = transactionService.findAll(1);
        Double total = 0.0;

        for (Transaction mov : transacciones)
            total+=mov.getAmount();

        titulo = "Lista de transacciones";
        model.addAttribute("titulo", titulo);
        model.addAttribute("movimientos", transacciones);
        model.addAttribute("total", total);

        return "movimientos/listar";
    }
    @GetMapping("/movements/modificar")
    public String createTransaction(Model model){
        LOG.log(Level.INFO, "createTransaction");

        Transaction transaccion = new Transaction();

        List<Usuario> usuarios = usuarioService.findAll();

        titulo = "Datos de transacción";

        model.addAttribute("titulo", titulo);
        model.addAttribute("transaccion", transaccion);
        model.addAttribute("usuarios", usuarios);

        return "movimientos/modificar";

    }
    @PostMapping("/guardar")
    public String saveTransaction(Transaction trans, BindingResult error, Model model){
        LOG.log(Level.INFO, "saveTransaction");

        if (error.hasErrors()){
            System.out.println("Si hay errores");
        }

        trans.setEstado(true);
        trans = transactionService.createTransaction(1,trans);

        return "redirect:/movements/list";


    }




}
