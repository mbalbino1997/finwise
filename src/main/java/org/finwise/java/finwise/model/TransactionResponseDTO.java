package org.finwise.java.finwise.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDTO {
    private Integer id;
    private BigDecimal amount;
    private String type;
    private String description;
    private LocalDateTime date;

    public TransactionResponseDTO() {}

    public TransactionResponseDTO(Integer id, BigDecimal amount, String type,
                                  String description, LocalDateTime date) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
