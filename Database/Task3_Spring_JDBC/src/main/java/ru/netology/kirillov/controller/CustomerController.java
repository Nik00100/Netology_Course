package ru.netology.kirillov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.kirillov.service.Service;

import java.util.List;

@RestController
@RequestMapping("products")
public class CustomerController {

    private final Service service;

    @Autowired
    public CustomerController(Service service) {
        this.service = service;
    }

    @GetMapping("/fetch-product")
    public List<String> getAuthorities(@RequestParam String name) {
        System.out.println(name);
        return service.getProductName(name);
    }
}
