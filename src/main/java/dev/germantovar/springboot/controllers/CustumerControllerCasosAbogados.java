package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.entities.CustomerCasosAbogados; // Cambiar a la entidad correcta
import dev.germantovar.springboot.repository.CustomerRepositoryCasosAbogados;
import dev.germantovar.springboot.services.ICustomerServiceCasosAbogados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustumerControllerCasosAbogados {

        @Autowired
        CustomerRepositoryCasosAbogados customerCasosAbogadosRepository;


        @Autowired
        private ICustomerServiceCasosAbogados service; // Cambiar al tipo de servicio correcto



        @GetMapping("Casos")
        public List<CustomerCasosAbogados> getAll() { // Cambiar a la entidad correcta
                return service.getAll(); // Ahora debería devolver la lista de casos
        }

        @GetMapping("/Casos/{id}")
        public ResponseEntity<List<CustomerCasosAbogados>> getCasosByAbogadoId(@PathVariable Long id) {
                System.out.println("Buscando casos para abogado con ID: " + id);
                List<CustomerCasosAbogados> casos = customerCasosAbogadosRepository.findByAbogadoId(id);

                if (!casos.isEmpty()) {
                        return ResponseEntity.ok(casos);
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
        }

        @PostMapping("/RegistraCasos")
        public ResponseEntity<CustomerCasosAbogados> createCaso(@RequestBody CustomerCasosAbogados nuevoCaso) {
                try {
                        // Guardar el nuevo caso en la base de datos
                        CustomerCasosAbogados casoGuardado = customerCasosAbogadosRepository.save(nuevoCaso);
                        // Devolver la respuesta con el caso creado
                        return ResponseEntity.status(HttpStatus.CREATED).body(casoGuardado);
                } catch (Exception e) {
                        // En caso de error, devolver un estado 500
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
        }







}

