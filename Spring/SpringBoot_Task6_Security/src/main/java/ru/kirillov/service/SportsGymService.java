package ru.kirillov.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kirillov.repository.SportsGymRepository;

@Service
public class SportsGymService {

    private final SportsGymRepository repository;

    @Autowired
    public SportsGymService(SportsGymRepository repository) {
        this.repository = repository;
    }

    public String start() {
        return repository.start();
    }
    public String signUp() {
        return repository.signUp();
    }
    public String update()  {return repository.update();}
    public String delete() {
        return repository.delete();
    }
}
