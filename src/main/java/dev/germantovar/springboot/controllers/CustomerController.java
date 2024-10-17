package dev.germantovar.springboot.controllers;
import dev.germantovar.springboot.entities.Customer;

import dev.germantovar.springboot.repository.CustomerRepository;

import dev.germantovar.springboot.services.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> save(@RequestBody Customer customer) {
        customer.setRol("abogado");  // Asigna el rol automáticamente
        customer.setCreated_at(LocalDateTime.now().toString());
        customer.setUpdated_at(LocalDateTime.now().toString());

        try {
            // Guarda el cliente y recupera el objeto guardado con el id asignado
            Customer savedCustomer = service.save(customer);

            // Cambiar la respuesta para que incluya el id y el mensaje
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedCustomer.getId()); // Asegúrate de que tienes un método getId()
            response.put("message", "Registro exitoso");
            return ResponseEntity.ok(response);  // Devuelve un objeto JSON
        } catch (DataIntegrityViolationException e) {
            System.out.println("Error de integridad: " + e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "El correo ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




    @GetMapping("/Abogados/{id}")
    public ResponseEntity<Customer> getAbogadoById(@PathVariable Long id) {
        System.out.println("Buscando abogado con ID: " + id); // Log para verificar el ID
        Optional<Customer> abogado = customerRepository.findById(id);
        if (abogado.isPresent()) {
            return ResponseEntity.ok(abogado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Customer loginRequest) {
        // Busca al cliente usando el correo y la contraseña
        Customer customer = customerRepository.findByCorreoAndPassword(loginRequest.getCorreo(), loginRequest.getPassword());

        if (customer != null) {
            // Si el usuario existe, devolver solo el nombre
            Map<String, String> response = new HashMap<>();
            response.put("nombre", customer.getNombre()); // Devuelve solo el nombre del cliente
            response.put("id", String.valueOf(customer.getId()));
            return ResponseEntity.ok(response);
        } else {
            // Si el usuario no existe, devolver un mensaje de error
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



}

