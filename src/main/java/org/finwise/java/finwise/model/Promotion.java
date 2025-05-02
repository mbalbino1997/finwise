package org.finwise.java.finwise.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 400)
    @Size(min = 50, max = 400)
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "applicable_to", nullable = false)
    private String applicableTo; // account, card, both

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAccountMap> userAccountMaps = new ArrayList<>();


    // Getters, Setters, Constructors

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getApplicableTo() {
        return applicableTo;
    }

    public void setApplicableTo(String applicableTo) {
        this.applicableTo = applicableTo;
    }

    public List<UserAccountMap> getUserAccountMaps() {
        return userAccountMaps;
    }

    public void setUserAccountMaps(List<UserAccountMap> userAccountMaps) {
        this.userAccountMaps = userAccountMaps;
    }

    

    
    
}
