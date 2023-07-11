package com.apiips.ips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apiips.ips.models.MedicoModel;


@Repository
public interface MedicoRepository extends CrudRepository<MedicoModel, Integer> {
    MedicoModel findByTarjetaProfesional(int tarjetaProfesional);
}

