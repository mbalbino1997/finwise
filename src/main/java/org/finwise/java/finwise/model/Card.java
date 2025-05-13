package org.finwise.java.finwise.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_type", nullable = false)
    private String cardType; // debito, credito, prepagata

    @Column(name = "spending_limit", nullable = false)
    private BigDecimal spendingLimit;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CardPersonal> cardPersonals = new ArrayList<>();

    @ManyToMany(mappedBy = "cards")
    @JsonBackReference
    private List<BankAccount> bankAccounts = new ArrayList<>();



    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(BigDecimal spendingLimit) {
        this.spendingLimit = spendingLimit;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<CardPersonal> getCardPersonals() {
        return cardPersonals;
    }
    
    public void setCardPersonals(List<CardPersonal> cardPersonals) {
        this.cardPersonals = cardPersonals;
    }
}
