package com.apiips.ips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apiips.ips.models.CitaModel;

@Repository
public interface CitaRepository extends CrudRepository<CitaModel,Integer>{
    CitaModel findById(int id_cita);
}
