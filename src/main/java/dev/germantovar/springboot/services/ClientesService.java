package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;

    private Long id;


    public Long getId() {
        return id;
    }


    public Cliente buscarClientePorId(Long id) {

        this.id = id;
        return clientesRepository.findById(id).orElse(null);
    }
}
