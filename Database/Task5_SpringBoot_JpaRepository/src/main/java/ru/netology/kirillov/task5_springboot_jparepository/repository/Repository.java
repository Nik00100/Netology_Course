package ru.netology.kirillov.task5_springboot_jparepository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import ru.netology.kirillov.task5_springboot_jparepository.entity.Person;
import ru.netology.kirillov.task5_springboot_jparepository.jpa_repository.personCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class Repository implements CommandLineRunner {

    private final personCrudRepository personRepository;

    public List<Person> getPersonsByCity(String city) {
        return personRepository.findByCityOfLiving(city);
    }

    public List<Person> getPersonsByAge(int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    public Optional<Person> getPersonsByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
    }

}