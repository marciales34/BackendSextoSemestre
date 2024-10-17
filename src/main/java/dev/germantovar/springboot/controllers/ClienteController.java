package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/buscarCliente")
    public ResponseEntity<?> buscarCliente(@RequestParam String correo) {
        Cliente cliente = clienteService.buscarClientePorCorreo(correo);
        if (cliente != null) {
            // Retorna el cliente encontrado
            return ResponseEntity.ok(cliente);
        } else {
            // Retorna un mensaje de error si el cliente no se encuentra
            return ResponseEntity.status(404).body("Cliente no encontrado con el correo: " + correo);
        }
    }


}

