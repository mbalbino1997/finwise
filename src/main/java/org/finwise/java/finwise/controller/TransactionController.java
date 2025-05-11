package org.finwise.java.finwise.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.finwise.java.finwise.model.CardPersonal;
import org.finwise.java.finwise.model.Transaction;
import org.finwise.java.finwise.service.CardPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private CardPersonalService cardPersonalService;

    /**
     * Mostra tutte le transazioni in un'unica tabella.
     * URL: /transactions?sortField=amount|date&sortDir=asc|desc&q=ricerca
     */
    @GetMapping
    public String listAllTransactions(
            @RequestParam(name = "sortField", defaultValue = "amount") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "desc") String sortDir,
            @RequestParam(name = "q", required = false) String q,
            Model model) {
        // Ottieni tutte le carte
        List<CardPersonal> cards = cardPersonalService.findAll();

        // FlatMap di tutte le transazioni
        List<Transaction> transactions = cards.stream()
            .flatMap(card -> card.getTransactions().stream())
            // Filtro di ricerca su nome o cognome
            .filter(tx -> {
                if (q == null || q.isBlank()) return true;
                String keyword = q.trim().toLowerCase(Locale.ITALIAN);
                String first = tx.getCardPersonal().getProfileUser().getFirstName().toLowerCase(Locale.ITALIAN);
                String last = tx.getCardPersonal().getProfileUser().getLastName().toLowerCase(Locale.ITALIAN);
                return first.contains(keyword) || last.contains(keyword);
            })
            .collect(Collectors.toList());

        // Definizione comparatore
        Comparator<Transaction> comparator;
        if ("date".equalsIgnoreCase(sortField)) {
            comparator = Comparator.comparing(Transaction::getDate);
        } else {
            comparator = Comparator.comparing(Transaction::getAmount);
        }
        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }

        // Applicazione ordinamento
        transactions = transactions.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

        model.addAttribute("transactions", transactions);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("q", q);
        return "transactions/list";
    }
}