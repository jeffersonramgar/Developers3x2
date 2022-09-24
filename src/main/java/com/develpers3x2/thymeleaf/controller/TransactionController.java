package com.develpers3x2.thymeleaf.controller;

import com.develpers3x2.thymeleaf.entidad.Transaction;
import com.develpers3x2.thymeleaf.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class TransactionController {

    private String titulo;

    @Autowired
    private ITransactionService transactionService;

    private final Logger LOG = Logger.getLogger("" + TransactionController.class);

    @GetMapping("/movements/list")
    public String GetListTransactionByenterprise(Model model){
        LOG.log(Level.INFO, "GetListTransactionByenterprise");
        List<Transaction> transacciones = transactionService.findAll(1);
        titulo = "Lista de transacciones";
        model.addAttribute("titulo", titulo);
        model.addAttribute("movimientos", transacciones);

        return "movimientos/listar";
    }




}
