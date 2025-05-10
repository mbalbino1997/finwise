package org.finwise.java.finwise.repository;

import java.util.List;
import org.finwise.java.finwise.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findAllByIdIn(List<Integer> ids);
}
