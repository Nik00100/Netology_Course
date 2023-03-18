package ru.netology.kirillov.task5_springboot_jparepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kirillov.task5_springboot_jparepository.entity.Person;
import ru.netology.kirillov.task5_springboot_jparepository.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<Person> getPersonsByCity(String city) {
        if (isEmpty(city)) {
            return null;
        }
        return repository.getPersonsByCity(city);
    }

    public List<Person> getPersonsByAge(int age) {
        if (isEmptyNumber(age)) {
            return null;
        }
        return repository.getPersonsByAge(age);
    }

    public Optional<Person> getPersonsByNameAndSurname(String name, String surname) {
        if (isEmpty(name) || isEmpty(surname)) {
            return null;
        }
        return repository.getPersonsByNameAndSurname(name, surname);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmptyNumber(int number) {
        return number < 0 || number > 120;
    }
}
