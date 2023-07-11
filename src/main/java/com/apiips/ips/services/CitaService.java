package com.apiips.ips.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apiips.ips.Errors.ErrorResponse;
import com.apiips.ips.models.CitaModel;
import com.apiips.ips.models.MedicoModel;
import com.apiips.ips.models.PacienteModel;
import com.apiips.ips.repositories.CitaRepository;
import com.apiips.ips.repositories.MedicoRepository;
import com.apiips.ips.repositories.PacienteRepository;

@Service
public class CitaService {
    @Autowired
    CitaRepository citaRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    public ArrayList<CitaModel> obtenerCitas(){
        return (ArrayList<CitaModel>)citaRepository.findAll();
    }

    public ResponseEntity<?> obtenerCitaPorId(int id){
       CitaModel cita =  citaRepository.findById(id);
        if(cita == null){
            ErrorResponse errorResponse = new ErrorResponse("No se encontró ninguna cita con el ID especificado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(errorResponse);
        }
        return ResponseEntity.ok(cita); 
    }


    public CitaModel guardarCita(CitaModel cita) {

        MedicoModel medico = medicoRepository.findByTarjetaProfesional(cita.getTarjeta_profesional());
        if (medico == null) {
            throw new IllegalArgumentException("El medico no existe");
        }
        PacienteModel paciente = pacienteRepository.findByCedula(cita.getCedula_paciente());
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no existe");
        }
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        return citaRepository.save(cita);
    }

}
