package com.apiips.ips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.apiips.ips.models.PacienteModel;

@Repository
public interface PacienteRepository extends CrudRepository<PacienteModel, Integer> {
    PacienteModel findByCedula(int cedula);
}
