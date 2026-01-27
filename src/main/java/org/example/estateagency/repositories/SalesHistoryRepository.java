package org.example.estateagency.repositories;

import org.example.estateagency.models.SalesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesHistoryRepository extends JpaRepository<SalesHistory, Integer> {
}
