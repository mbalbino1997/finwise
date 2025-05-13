package org.finwise.java.finwise.controller;

import java.util.List;

import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin    
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {
    @Autowired
    private BankAccountService bankAccountService;
    
    @GetMapping
    public List<BankAccount> index() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        return bankAccounts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> show(@Valid @PathVariable Integer id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BankAccount>(bankAccount,HttpStatus.OK);
    }
    
}
