package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientesController {
    @Autowired
    private ClientesService clientesService;

    @GetMapping("/buscarCliente")
    public ResponseEntity<?> buscarCliente(@RequestParam Long id) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("ID debe ser un número positivo.");
        }

        try {

            Cliente cliente = clientesService.buscarClientePorId(id);
            if (cliente != null) {

                return ResponseEntity.ok(cliente);
            } else {

                return ResponseEntity.status(404).body("Cliente no encontrado con el id: " + id);
            }
        } catch (Exception e) {

            return ResponseEntity.status(500).body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
