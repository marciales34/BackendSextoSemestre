package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarClientePorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }
}
