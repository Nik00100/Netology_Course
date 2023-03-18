package ru.netology.kirillov.task5_springboot_jparepository.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.kirillov.task5_springboot_jparepository.entity.Person;
import ru.netology.kirillov.task5_springboot_jparepository.entity.PersonDataPrimaryKey;

import java.util.*;

public interface personCrudRepository extends JpaRepository<Person, PersonDataPrimaryKey> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    Optional<Person> findByNameAndSurname(String name, String surname);

}
