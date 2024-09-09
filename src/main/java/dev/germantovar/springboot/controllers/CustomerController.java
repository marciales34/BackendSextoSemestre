package dev.germantovar.springboot.controllers;
import dev.germantovar.springboot.entities.Customer;

import dev.germantovar.springboot.repository.CustomerRepository;

import dev.germantovar.springboot.services.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService service;


    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("Abogados")
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping("Enviar")
    public void save(@RequestBody Customer costumer) {

        service.save(costumer);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Customer loginRequest) {
        Customer customer = customerRepository.findByCorreoAndPassword(loginRequest.getCorreo(), loginRequest.getPassword());
        if (customer != null) {
            // Si el usuario existe, devolver un mensaje de éxito y redirigir a la página
            return ResponseEntity.ok("Login successful");
        } else {
            // Si el usuario no existe, devolver un mensaje de error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

}


