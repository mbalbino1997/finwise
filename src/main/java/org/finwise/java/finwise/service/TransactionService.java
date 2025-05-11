package org.finwise.java.finwise.service;

import java.util.List;

import org.finwise.java.finwise.model.Transaction;
import org.finwise.java.finwise.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findByCardPersonalId(Integer cardPersonalId) {
        return transactionRepository.findByCardPersonalId(cardPersonalId);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
