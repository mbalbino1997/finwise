package org.finwise.java.finwise.repository;

import org.finwise.java.finwise.model.UserAccountMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountMapRepository extends JpaRepository<UserAccountMap, Integer> {
}
