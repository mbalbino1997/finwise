package org.finwise.java.finwise.controller;

import java.util.List;

import org.finwise.java.finwise.model.CardPersonal;
import org.finwise.java.finwise.service.CardPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private CardPersonalService cardPersonalService;

    /**
     * Mostra tutte le carte e, per ciascuna, le sue transazioni.
     * URL: /transactions
     */
    @GetMapping
    public String listAllByCard(Model model) {
        List<CardPersonal> cards = cardPersonalService.findAll();
        model.addAttribute("cards", cards);
        return "transactions/list";
    }
}
