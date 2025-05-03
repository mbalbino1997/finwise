package org.finwise.java.finwise.repository;

import org.finwise.java.finwise.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    boolean existsByAccountType(String accountType);
}
