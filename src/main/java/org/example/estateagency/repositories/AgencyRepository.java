package org.example.estateagency.repositories;

import org.example.estateagency.models.Agencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agencies, Integer> {
    List<Agencies> findByName(String name);
}
