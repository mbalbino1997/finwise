package org.finwise.java.finwise.controller;

import org.finwise.java.finwise.model.Card;
import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.service.CardService;
import org.finwise.java.finwise.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public String listCards(Model model) {
        model.addAttribute("cards", cardService.findAll());
        return "cards/list"; // templates/cards/list.html
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("card", new Card());
        model.addAttribute("bankAccounts", bankAccountService.findAll());
        return "cards/form"; // templates/cards/form.html
    }

    @PostMapping("/save")
    public String saveCard(@Valid @ModelAttribute("card") Card card,BindingResult bindingresult, Model model) {
        if(bindingresult.hasErrors()){
            return "cards/form"; // templates/cards/form.html
        }
        cardService.save(card);
        return "redirect:/cards";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Card card = cardService.findById(id);
        if (card != null) {
            model.addAttribute("card", card);
            model.addAttribute("bankAccounts", bankAccountService.findAll());
            return "cards/form";
        }
        return "redirect:/cards";
    }

    @GetMapping("/delete/{id}")
    public String deleteCard(@PathVariable("id") Integer id) {
        cardService.deleteById(id);
        return "redirect:/cards";
    }
}
