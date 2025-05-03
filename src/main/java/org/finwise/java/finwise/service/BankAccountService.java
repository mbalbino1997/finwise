package org.finwise.java.finwise.service;

import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount findById(Integer id) {
        return bankAccountRepository.findById(id).orElse(null);
    }

    public BankAccount save(BankAccount account) {
        return bankAccountRepository.save(account);
    }

    public void deleteById(Integer id) {
        bankAccountRepository.deleteById(id);
    }

    public boolean existsByAccountType(String accountType) {
        return bankAccountRepository.existsByAccountType(accountType);
    }
}
