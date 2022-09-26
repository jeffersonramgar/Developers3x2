package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Transaction;
import com.develpers3x2.thymeleaf.entidad.Usuario;
import com.develpers3x2.thymeleaf.service.ITransactionService;
import com.develpers3x2.thymeleaf.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class TransactionController {

    private String titulo;
    private Usuario usuario;

    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IUsuarioService usuarioService;

    private final Logger LOG = Logger.getLogger("" + TransactionController.class);

    @GetMapping("/movements/{id_enterprise}/list")
    public String GetListTransactionByenterprise(@PathVariable("id_enterprise") int id_enterprise,  Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        List<Transaction> transactions = transactionService.findAll(id_enterprise);
        System.out.println(transactions);
        Double total = 0.0;

        for (Transaction mov : transactions)
            if(mov.getAmount() == null)
                total = total+0;
            else
                total+=mov.getAmount();

        titulo = "Transacciones de " + usuario.getEnterprise().getName();
        model.addAttribute("titulo", titulo);
        model.addAttribute("usuario", usuario);
        model.addAttribute("movimientos", transactions);
        model.addAttribute("total", total);

        return "movimientos/listar";
    }
    @GetMapping("/movements/modificar")
    public String createTransaction(Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());
        LOG.log(Level.INFO, "createTransaction");

        Transaction transaction = new Transaction();

        titulo = "Datos de transacción";

        model.addAttribute("titulo", titulo);
        model.addAttribute("transaction", transaction);
        model.addAttribute("usuario", usuario);

        return "movimientos/modificar";

    }
    @PostMapping("/guardar")
    public String saveTransaction(@RequestParam("typeTransaction") boolean typeTransaction, @Valid Transaction transaction, BindingResult error, Model model, @AuthenticationPrincipal User user){
        usuario = usuarioService.findByUsername(user.getUsername());

        for (ObjectError e : error.getAllErrors())
            System.out.println(e.toString());

        if (error.hasErrors()){
            titulo = "Datos de transacción";

            model.addAttribute("titulo", titulo);
            model.addAttribute("usuario", usuario);

            return "movimientos/modificar";
        }

        if(!typeTransaction)
            transaction.setAmount(transaction.getAmount() * -1);

        transaction.setEstado(true);
        transaction = transactionService.createTransaction((int) usuario.getEnterprise().getId(), transaction);

        return "redirect:/movements/" + usuario.getEnterprise().getId() + "/list";


    }




}
