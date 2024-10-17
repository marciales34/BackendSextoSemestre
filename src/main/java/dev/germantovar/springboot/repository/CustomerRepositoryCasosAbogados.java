package dev.germantovar.springboot.repository;

import dev.germantovar.springboot.entities.CustomerCasosAbogados; // Cambia a la entidad correcta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepositoryCasosAbogados extends CrudRepository<CustomerCasosAbogados, Long> {

    @Query("SELECT c FROM CustomerCasosAbogados c WHERE c.id_abogado_encargado = :idAbogado")
    List<CustomerCasosAbogados> findByAbogadoId(@Param("idAbogado") Long idAbogado);

}



