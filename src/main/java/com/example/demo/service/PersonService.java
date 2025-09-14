package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return repository.findById(id);
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(Long id, Person updated) {
        return repository.findById(id).map(person -> {
            person.setName(updated.getName());
            person.setEmail(updated.getEmail());
            return repository.save(person);
        }).orElseThrow(() -> new RuntimeException("Person not found with id " + id));
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
