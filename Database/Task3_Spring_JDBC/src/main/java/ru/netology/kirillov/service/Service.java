package ru.netology.kirillov.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kirillov.repository.UserRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final UserRepository repository;

    @Autowired
    public Service(UserRepository repository) {
        this.repository = repository;
    }

    public List<String> getProductName(String name) {
        if (isEmpty(name)) {
            return null;
        }
        return repository.getProductName(name);
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

}