package org.example.estateagency.repositories;

import org.example.estateagency.models.Realtors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtorRepository extends JpaRepository<Realtors, Long> {

    List<Realtors> findByPerson_FirstnameContaining(String query);
    List<Realtors> findByPerson_SurnameContaining(String query);
    List<Realtors> findByPerson_LastnameContaining(String query);
}
