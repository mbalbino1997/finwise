package org.finwise.java.finwise.controller;

import java.util.List;
import org.finwise.java.finwise.model.CardPersonal;
import org.finwise.java.finwise.model.Transaction;
import org.finwise.java.finwise.service.CardPersonalService;
import org.finwise.java.finwise.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card-personals")
public class CardPersonalController {

    @Autowired
    private CardPersonalService cardPersonalService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String list(Model model) {
        List<CardPersonal> cards = cardPersonalService.findAll();
        model.addAttribute("cards", cards);
        return "card-personals/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        CardPersonal cardPersonal = cardPersonalService.findById(id);
        List<Transaction> transactions = transactionService.findByCardPersonalId(id);
        model.addAttribute("cardPersonal", cardPersonal);
        model.addAttribute("transactions", transactions);
        return "card-personals/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("cardPersonal", new CardPersonal());
        model.addAttribute("allProfiles", cardPersonalService.findAllProfiles());
        model.addAttribute("allCards", cardPersonalService.findAllCardTypes());
        return "card-personals/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        CardPersonal cardPersonal = cardPersonalService.findById(id);
        model.addAttribute("cardPersonal", cardPersonal);
        model.addAttribute("allProfiles", cardPersonalService.findAllProfiles());
        model.addAttribute("allCards", cardPersonalService.findAllCardTypes());
        return "card-personals/form";
    }

    @PostMapping("/save")
    public String save(CardPersonal cardPersonal) {
        cardPersonalService.save(cardPersonal);
        return "redirect:/card-personals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        cardPersonalService.deleteById(id);
        return "redirect:/card-personals";
    }
}