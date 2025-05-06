package org.finwise.java.finwise.repository;

import org.finwise.java.finwise.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> { // Cambia da Long a Integer

    List<Card> findAllByIdIn(List<Integer> ids);
}
