package org.finwise.java.finwise.controller;

import java.util.List;

import org.finwise.java.finwise.model.HomepageDataDTO;
import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.model.Promotion;
import org.finwise.java.finwise.service.BankAccountService;
import org.finwise.java.finwise.service.PromotionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/bankaccount")
public class BankAccountRestController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public ResponseEntity<HomepageDataDTO> index() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        List<Promotion> promotions = promotionService.findAll();

        HomepageDataDTO homepageData = new HomepageDataDTO(bankAccounts, promotions);
        return ResponseEntity.ok(homepageData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> show(@PathVariable Integer id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankAccount);
    }
}
