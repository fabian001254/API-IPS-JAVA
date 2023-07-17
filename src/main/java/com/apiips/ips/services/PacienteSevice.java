package com.apiips.ips.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.apiips.ips.Errors.ErrorResponse;
import com.apiips.ips.models.PacienteModel;
import com.apiips.ips.repositories.PacienteRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PacienteSevice {
    @Autowired
    PacienteRepository pacienteRepository;
    
    public ArrayList<PacienteModel> obtenerPacientes() {
        return (ArrayList<PacienteModel>) pacienteRepository.findAll();
    }


    public ResponseEntity<?> obtenerPacientesPorCedula(int cedula) {
        PacienteModel paciente =  pacienteRepository.findByCedula(cedula);
        if(paciente == null){
            ErrorResponse errorResponse = new ErrorResponse("No se encontró ningún paciente con la cédula especificada");
            return ResponseEntity.status(400)
                                 .body(errorResponse);
        }
        return ResponseEntity.ok(paciente);
    }


    public PacienteModel guardarPaciente(PacienteModel pacienteModel) {
        if (!pacienteModel.getNombre().matches("[a-zA-Z]+") || !pacienteModel.getApellido().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("El nombre y/o apellido solo debe contener letras");
        }

        int cedula = pacienteModel.getCedula();
        PacienteModel pacienteModeli = pacienteRepository.findByCedula(cedula);
        if (pacienteModeli != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El número de cédula ya existe");
        }

        return pacienteRepository.save(pacienteModel);
    }



    
    
    
    
}
