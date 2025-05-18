package org.finwise.java.finwise.model;

import java.time.LocalDate;
import java.util.List;

public class CardResponseDTO {
    private Integer id;
    private String cardNumber;
    private String cvv;
    private LocalDate expirationDate;
    private List<TransactionResponseDTO> transactions;

    public CardResponseDTO() {}

    public CardResponseDTO(Integer id, String cardNumber, String cvv,
                           LocalDate expirationDate,
                           List<TransactionResponseDTO> transactions) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.transactions = transactions;
    }

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public List<TransactionResponseDTO> getTransactions() { return transactions; }
    public void setTransactions(List<TransactionResponseDTO> transactions) { this.transactions = transactions; }
}
