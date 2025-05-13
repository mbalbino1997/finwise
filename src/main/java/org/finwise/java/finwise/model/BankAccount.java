package org.finwise.java.finwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Promotion number is mandatory")
    @Column(name = "promotion_number", nullable = false, unique = true)
    private String promotionNumber;

    @NotBlank(message = "Account type is mandatory")
    @Column(name = "account_type", nullable = false, unique = true)
    private String accountType; // base, premium, giovani

    @Column(name = "monthly_fee", nullable = false)
    private BigDecimal monthlyFee;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @NotNull(message = "Il saldo minimo non può essere nullo.")
    @DecimalMin(value = "0.01", message = "Il saldo minimo deve essere maggiore di zero.")
    @Column(name = "min_balance", nullable = false)
    private BigDecimal minBalance;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "bank_account_card",
        joinColumns = @JoinColumn(name = "bank_account_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
        )
    @JsonManagedReference
    private List<Card> cards = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "bank_account_promotion",
        joinColumns = @JoinColumn(name = "bank_account_id"),
        inverseJoinColumns = @JoinColumn(name = "promotion_id")
        )
    @JsonManagedReference
    private List<Promotion> promotions = new ArrayList<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromotionNumber() {
        return promotionNumber;
    }

    public void setPromotionNumber(String promotionNumber) {
        this.promotionNumber = promotionNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
