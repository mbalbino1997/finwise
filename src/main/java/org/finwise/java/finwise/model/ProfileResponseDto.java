package org.finwise.java.finwise.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProfileResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private BigDecimal incomeRange;
    private String riskTolerance;
    private List<String> roles;
    private List<CardResponseDTO> cards;      // ← nuova lista di carte

    public ProfileResponseDto() {}

    public ProfileResponseDto(Integer id, String firstName, String lastName,
                              LocalDate birthDate, BigDecimal incomeRange,
                              String riskTolerance, List<String> roles,
                              List<CardResponseDTO> cards) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.incomeRange = incomeRange;
        this.riskTolerance = riskTolerance;
        this.roles = roles;
        this.cards = cards;
    }

    // getters & setters (includi anche quelli di cards)
    // ...
    public List<CardResponseDTO> getCards() { return cards; }
    public void setCards(List<CardResponseDTO> cards) { this.cards = cards; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(BigDecimal incomeRange) {
        this.incomeRange = incomeRange;
    }

    public String getRiskTolerance() {
        return riskTolerance;
    }

    public void setRiskTolerance(String riskTolerance) {
        this.riskTolerance = riskTolerance;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    
}
