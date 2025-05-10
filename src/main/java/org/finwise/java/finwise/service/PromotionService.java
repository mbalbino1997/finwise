package org.finwise.java.finwise.service;

import org.finwise.java.finwise.model.Card;
import org.finwise.java.finwise.model.Promotion;
import org.finwise.java.finwise.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    public Promotion findById(Integer id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return promotion.orElse(null);
    }

    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void deleteById(Integer id) {
        promotionRepository.deleteById(id);
    }

    public List<Promotion> findByIds(List<Integer> ids) {
        return promotionRepository.findAllByIdIn(ids); // Usa il nuovo metodo che accetta una lista di IDs
    }
}
