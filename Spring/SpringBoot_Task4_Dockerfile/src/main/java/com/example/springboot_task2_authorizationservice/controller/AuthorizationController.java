package com.example.springboot_task2_authorizationservice.controller;

import com.example.springboot_task2_authorizationservice.authorities.Authorities;
import com.example.springboot_task2_authorizationservice.service.AuthorizationService;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class AuthorizationController {

    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") @Size(min = 2, max = 20) String user,
                                            @RequestParam("password") @Size(min = 5, max = 25) String password) {
        return service.getAuthorities(user, password);
    }
}