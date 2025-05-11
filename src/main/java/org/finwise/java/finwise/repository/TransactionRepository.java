package org.finwise.java.finwise.repository;

import java.util.List;

import org.finwise.java.finwise.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByCardPersonalId(Integer cardPersonalId);
}
