package org.finwise.java.finwise.controller;

import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bankAccount", new BankAccount());
        return "bank-accounts/form"; // templates/bank-accounts/form.html
    }

    @PostMapping("/save")
    public String saveBankAccount(@ModelAttribute("bankAccount") BankAccount bankAccount) {
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
