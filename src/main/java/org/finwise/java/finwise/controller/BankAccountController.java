package org.finwise.java.finwise.controller;

import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (bankAccountService.existsByAccountType(bankAccount.getAccountType())) {
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
}
