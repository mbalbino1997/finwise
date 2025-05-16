package org.finwise.java.finwise.model;

import java.util.List;
import org.finwise.java.finwise.model.BankAccount;
import org.finwise.java.finwise.model.Promotion;

public class HomepageDataDTO {
    private List<BankAccount> accounts;
    private List<Promotion> promotions;

    public HomepageDataDTO(List<BankAccount> accounts, List<Promotion> promotions) {
        this.accounts = accounts;
        this.promotions = promotions;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
