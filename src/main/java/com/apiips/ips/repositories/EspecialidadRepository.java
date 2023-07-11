package com.apiips.ips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apiips.ips.models.EspecialidadModel;


@Repository
public interface EspecialidadRepository extends CrudRepository<EspecialidadModel, Integer>{
    EspecialidadModel findById(int id_especialidad);
    EspecialidadModel findByNombre(String nombre);
}
