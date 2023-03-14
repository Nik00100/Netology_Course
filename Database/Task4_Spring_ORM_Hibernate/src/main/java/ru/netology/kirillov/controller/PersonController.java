package ru.netology.kirillov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.kirillov.entity.Person;
import ru.netology.kirillov.service.Service;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final Service service;

    @Autowired
    public PersonController(Service service) {
        this.service = service;
    }

    @GetMapping("/by-city")
    public List<Person> getAuthorities(@RequestParam("city") String city) {
        return service.getPersonsByCity(city);
    }
}
