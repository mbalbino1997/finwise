package org.finwise.java.finwise.repository;

import java.util.List;

import org.finwise.java.finwise.model.CardPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPersonalRepository extends JpaRepository<CardPersonal, Integer> {

    List<CardPersonal> findByProfileUserId(Integer profileUserId);
}
