package org.finwise.java.finwise.controller;

import java.util.ArrayList;
import java.util.List;

import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.model.Card;
import org.finwise.java.finwise.service.BankAccountService;
import org.finwise.java.finwise.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CardService cardService;
    @GetMapping
    public String listBankAccounts(Model model) {
        model.addAttribute("bankAccounts", bankAccountService.findAll());
        return "bank-accounts/list"; // templates/bank-accounts/list.html
    }

    @GetMapping("/details/{id}")
public String showDetails(@PathVariable("id") Integer id, Model model) {
    BankAccount account = bankAccountService.findById(id);
    if (account != null) {
        model.addAttribute("bankAccount", account);
        model.addAttribute("allCards", cardService.findAll());
        return "bank-accounts/show";
    }
    return "redirect:/bank-accounts";
}

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bankAccount", new BankAccount());
        return "bank-accounts/form"; // templates/bank-accounts/form.html
    }

    @PostMapping("/save")
    public String saveBankAccount(@Valid @ModelAttribute("bankAccount") BankAccount bankAccount, BindingResult bindingresult, Model model) {
        if (bankAccount.getId() == null && bankAccountService.existsByAccountType(bankAccount.getAccountType())) {
            bindingresult.rejectValue("accountType", "error.bankAccount", "Tipo di conto già esistente.");
            return "bank-accounts/form"; // ritorna al form di creazione con il messaggio di errore
        }
        if(bindingresult.hasErrors()) {
            return "bank-accounts/form";
        }
    // try {
    //     bankAccountService.save(bankAccount);
    // } catch (DataIntegrityViolationException e) {
    //     // Se si verifica un errore di duplicazione, aggiungi un errore personalizzato
    //     bindingresult.rejectValue("promotionNumber", "error.bankAccount.duplicate", "Il numero della promozione o il tipo di account è già presente.");
    //     return "bank-accounts/create";
    // }
        bankAccountService.save(bankAccount);
        return "redirect:/bank-accounts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        BankAccount account = bankAccountService.findById(id);
        if (account != null) {
            model.addAttribute("bankAccount", account);
            return "bank-accounts/form";
        }
        return "redirect:/bank-accounts";
    }

    @GetMapping("/delete/{id}")
    public String deleteBankAccount(@PathVariable("id") Integer id) {
        bankAccountService.deleteById(id);
        return "redirect:/bank-accounts";
    }

    @GetMapping("/add-cards/{id}")
public String showAddCardsModal(@PathVariable Integer id, Model model) {
    BankAccount bankAccount = bankAccountService.findById(id);
    if (bankAccount != null) {
        model.addAttribute("bankAccount", bankAccount);
        List<Card> allCards = cardService.findAll(); // Recupera tutti i tipi di carta disponibili
        model.addAttribute("allCards", allCards);
        return "bank-accounts/show"; // Indica che stai tornando alla vista dettagli
    }
    return "redirect:/bank-accounts";
}


    @PostMapping("/add-cards/{id}")
public String addCardsToAccount(@PathVariable Integer id, @RequestParam(name = "cards", required = false) List<Integer> cards) {
    BankAccount bankAccount = bankAccountService.findById(id);
   List<Card> selectedCards = (cards != null) ? cardService.findByIds(cards) : new ArrayList<>();
    bankAccount.setCards(selectedCards);
    bankAccountService.save(bankAccount);
    return "redirect:/bank-accounts/details/" + id;
}
}
