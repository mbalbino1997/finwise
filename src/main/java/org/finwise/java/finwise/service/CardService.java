package org.finwise.java.finwise.service;

import org.finwise.java.finwise.model.Card;
import org.finwise.java.finwise.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    /**
     * Recupera tutte le carte.
     *
     * @return Lista di tutte le carte.
     */
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    /**
     * Recupera una carta per l'ID dato.
     *
     * @param id ID della carta.
     * @return La carta trovata, o null se non esiste.
     */
    public Card findById(Integer id) {
        Optional<Card> card = cardRepository.findById(id);
        return card.orElse(null); // Restituisce la carta se trovata, altrimenti null
    }

    /**
     * Salva una carta.
     *
     * @param card La carta da salvare.
     * @return La carta salvata.
     */
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    /**
     * Elimina una carta per ID.
     *
     * @param id ID della carta da eliminare.
     */
    public void deleteById(Integer id) {
        cardRepository.deleteById(id);
    }

    /**
     * Recupera una lista di carte per gli ID dati.
     *
     * @param ids Lista degli ID delle carte.
     * @return Lista delle carte corrispondenti agli ID.
     */
    public List<Card> findByIds(List<Integer> ids) {
        return cardRepository.findAllByIdIn(ids); // Usa il nuovo metodo che accetta una lista di IDs
    }

    /**
     * Salva la lista di carte.
     *
     * @param cards Lista di carte da salvare.
     * @return Lista di carte salvate.
     */
    public List<Card> saveAll(List<Card> cards) {
        return cardRepository.saveAll(cards); // Salva le carte nel database
    }
}
