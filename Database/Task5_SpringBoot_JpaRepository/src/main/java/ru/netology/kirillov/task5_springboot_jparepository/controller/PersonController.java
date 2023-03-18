package ru.netology.kirillov.task5_springboot_jparepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.kirillov.task5_springboot_jparepository.entity.Person;
import ru.netology.kirillov.task5_springboot_jparepository.service.Service;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final Service service;

    @Autowired
    public PersonController(Service service) {
        this.service = service;
    }

    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        System.out.println(city);
        return service.getPersonsByCity(city);
    }

    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam int age) {
        System.out.println(age);
        return service.getPersonsByAge(age);
    }

    @GetMapping("/by-name-and-surname")
    public Optional<Person> getPersonsByNameAndSurname(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        System.out.println(name + " " + surname);
        return service.getPersonsByNameAndSurname(name, surname);
    }
}