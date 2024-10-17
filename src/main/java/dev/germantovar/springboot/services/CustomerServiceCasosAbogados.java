package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.entities.CustomerCasosAbogados; // Cambiar a la entidad correcta
import dev.germantovar.springboot.repository.CustomerRepositoryCasosAbogados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceCasosAbogados implements ICustomerServiceCasosAbogados {

    @Autowired
    private CustomerRepositoryCasosAbogados customerRepository;

    @Override
    public List<CustomerCasosAbogados> getAll() {
        return (List<CustomerCasosAbogados>) customerRepository.findAll(); // Cambiar a la entidad correcta
    }

    @Override
    public CustomerCasosAbogados getById(Long id) {
        Optional<CustomerCasosAbogados> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null); // Manejo de caso no encontrado
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public CustomerCasosAbogados save(CustomerCasosAbogados customer) {
        return customerRepository.save(customer); // Cambiar a la entidad correcta
    }
}



