package com.apiips.ips.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.apiips.ips.Errors.ErrorResponse;
import com.apiips.ips.models.EspecialidadModel;
import com.apiips.ips.models.MedicoModel;
import com.apiips.ips.repositories.EspecialidadRepository;
import com.apiips.ips.repositories.MedicoRepository;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;

    public ArrayList<MedicoModel> obtenerMedicos() {
        return (ArrayList<MedicoModel>) medicoRepository.findAll();
    }


    public ResponseEntity<?> obtenerMedicoPorTarjeta(int tarjeta) {

        MedicoModel medico =  medicoRepository.findByTarjetaProfesional(tarjeta);
        if(medico == null){
            ErrorResponse errorResponse = new ErrorResponse("No se encontró ningún medico con la tarjeta profesional especificada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(errorResponse);
        }
        return ResponseEntity.ok(medico);
    }


    public MedicoModel guardarMedico(MedicoModel medico) {

        if (!medico.getNombre().matches("[a-zA-Z]+") || !medico.getApellido().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("El nombre y/o apellido solo debe contener letras");
        }

        MedicoModel medicoModeli = medicoRepository.findByTarjetaProfesional(medico.getTarjetaProfesional());
        if (medicoModeli != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"El número de tarjeta ya existe");
        }

        String especialidadNombre = medico.getNombreEspecialidad();
        if (especialidadNombre == null) {
            throw new IllegalArgumentException("El nombre de la especialidad no puede ser null");
        }

        EspecialidadModel especialidadModel = especialidadRepository.findByNombre(especialidadNombre.toUpperCase());

        if (especialidadModel == null) {
            throw new IllegalArgumentException("La especialidad no existe");
        }

        medico.setEspecialidad(especialidadModel);

        return medicoRepository.save(medico);
    }
}


    
    
    
    
    
