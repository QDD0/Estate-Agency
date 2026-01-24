package org.example.estateagency.services;

import jakarta.transaction.Transactional;
import org.example.estateagency.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.example.estateagency.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Page<Person> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return personRepository.findAll(pageRequest);
    }


    public Person findById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElse(null);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId_person(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
