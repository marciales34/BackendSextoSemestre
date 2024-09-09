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
    public ResponseEntity<String> save(@RequestBody Customer costumer) {
        service.save(costumer);
        try {
            ejecutarComandosGit();
            return ResponseEntity.ok("{\"message\": \"Registro exitoso y cambios subidos a GitHub.\"}");
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Error al subir cambios a GitHub.\"}");
        }
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

    // Método para ejecutar los comandos Git después del registro

    private void ejecutarComandosGit() throws IOException, InterruptedException {
        String proyectoDir = "C:\\DocumentosDiego\\BackendAbogados\\BackendSextoSemestre";
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c",
                "cd " + proyectoDir + " && git add . && git commit -m \"Nuevo registro de abogado agregado automáticamente\" && git push origin main"
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Cambios subidos a GitHub correctamente.");
        } else {
            System.out.println("Error al subir cambios a GitHub. Código de salida: " + exitCode);
        }
    }
}


