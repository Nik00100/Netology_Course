package com.example.springboot_task2_authorizationservice.repository;

import com.example.springboot_task2_authorizationservice.authorities.Authorities;
import com.example.springboot_task2_authorizationservice.authorities.User;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class UserRepository {

    private final User manager = new User("manager", "password");
    private final User engineer = new User("engineer", "123456");
    private final User worker = new User("worker", "987654");
    private final List<Authorities> managerAuthorities = Arrays.asList(Authorities.DELETE, Authorities.READ, Authorities.WRITE);
    private final List<Authorities> engineerAuthorities = Arrays.asList(Authorities.DELETE, Authorities.READ);
    private final List<Authorities> workerAuthorities = List.of(Authorities.READ);


    public List<Authorities> getUserAuthorities(String user, String password) {
        if (manager.getName().equals(user) && (manager.getPassword().equals(password))) {
            return managerAuthorities;
        } else if (engineer.getName().equals(user) && (engineer.getPassword().equals(password))) {
            return engineerAuthorities;
        } else if (worker.getName().equals(user) && (worker.getPassword().equals(password))) {
            return workerAuthorities;
        }
        return new ArrayList<>();
    }
}