package org.example.estateagency.repositories;

import org.example.estateagency.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFirstnameContainingIgnoreCase(String firstname);
    List<Person> findBySurnameContainingIgnoreCase(String surname);
    List<Person> findByLastnameContainingIgnoreCase(String lastname);
}