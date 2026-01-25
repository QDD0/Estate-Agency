package org.example.estateagency.repositories;

import org.example.estateagency.models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Properties, Integer> {
}
