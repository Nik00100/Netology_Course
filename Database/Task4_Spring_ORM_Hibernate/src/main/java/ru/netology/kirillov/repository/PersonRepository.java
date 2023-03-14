package ru.netology.kirillov.repository;

import org.springframework.stereotype.Repository;
import ru.netology.kirillov.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Person> getPersonsByCity(String city) {
        return entityManager.createQuery("SELECT person FROM Person person where person.cityOfLiving = :city")
                .setParameter("city", city)
                .getResultList();
    }
}
