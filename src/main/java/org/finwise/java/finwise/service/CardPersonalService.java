package org.finwise.java.finwise.service;

import java.util.List;
import org.finwise.java.finwise.model.CardPersonal;
import org.finwise.java.finwise.model.ProfileUser;
import org.finwise.java.finwise.model.Card;
import org.finwise.java.finwise.repository.CardPersonalRepository;
import org.finwise.java.finwise.repository.ProfileUserRepository;
import org.finwise.java.finwise.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CardPersonalService {

    @Autowired
    private CardPersonalRepository cardPersonalRepository;

    @Autowired
    private ProfileUserRepository profileUserRepository;

    @Autowired
    private CardRepository cardRepository;

    public CardPersonal findById(Integer id) {
        return cardPersonalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CardPersonal con ID " + id + " non trovata"));
    }

    public List<CardPersonal> findAll() {
        return cardPersonalRepository.findAll();
    }

    public List<CardPersonal> findByProfileUserId(Integer profileUserId) {
        return cardPersonalRepository.findByProfileUserId(profileUserId);
    }

    public CardPersonal save(CardPersonal cardPersonal) {
        return cardPersonalRepository.save(cardPersonal);
    }

    public void deleteById(Integer id) {
        cardPersonalRepository.deleteById(id);
    }

    public List<ProfileUser> findAllProfiles() {
        return profileUserRepository.findAll();
    }

    public List<Card> findAllCardTypes() {
        return cardRepository.findAll();
    }
}