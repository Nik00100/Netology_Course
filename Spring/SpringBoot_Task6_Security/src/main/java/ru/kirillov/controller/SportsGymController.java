package ru.kirillov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kirillov.service.SportsGymService;

@RestController
public class SportsGymController {

    private final SportsGymService service;

    @Autowired
    public SportsGymController(SportsGymService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String start() {
        return service.start();
    }

    @GetMapping("/signup")
    public String signUp() {
        return service.signUp();
    }

    @GetMapping("/update")
    public String update() {
        return service.update();
    }

    @GetMapping("/delete")
    public String delete() {
        return service.delete();
    }

}
