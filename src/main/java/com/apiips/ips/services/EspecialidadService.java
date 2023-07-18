package com.apiips.ips.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiips.ips.Errors.ErrorResponse;
import com.apiips.ips.models.EspecialidadModel;
import com.apiips.ips.repositories.EspecialidadRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EspecialidadService {
    @Autowired
    EspecialidadRepository especialidadRepository;

    public ArrayList<EspecialidadModel> obtenerEspecialidades(){
        return (ArrayList<EspecialidadModel>)especialidadRepository.findAll();
    }

    public ResponseEntity<?> obtenerEspecialidadPorId(int id){
       EspecialidadModel especialidad =  especialidadRepository.findById(id);
        if(especialidad == null){
            ErrorResponse errorResponse = new ErrorResponse("No se encontró ninguna especialidad con el ID especificado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(errorResponse);
        }
        return ResponseEntity.ok(especialidad); 
    }

    public EspecialidadModel guardarEspecialidad(EspecialidadModel especialidad){
       
        if (!especialidad.getNombre().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("El nombre solo debe contener letras");
        }

        EspecialidadModel especialidadModeli = especialidadRepository.findByNombre(especialidad.getNombre().toUpperCase());
        if(especialidadModeli != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La especialidad ya existe");
        }
        especialidad.setNombre(especialidad.getNombre().toUpperCase());
        return especialidadRepository.save(especialidad);
    }

    




}
