package ru.netology.kirillov.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kirillov.entity.Person;
import ru.netology.kirillov.repository.PersonRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final PersonRepository repository;

    @Autowired
    public Service(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getPersonsByCity(String name) {
        if (isEmpty(name)) {
            return null;
        }
        return repository.getPersonsByCity(name);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}