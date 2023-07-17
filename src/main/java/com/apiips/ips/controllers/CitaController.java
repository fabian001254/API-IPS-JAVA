package com.apiips.ips.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apiips.ips.models.CitaModel;
import com.apiips.ips.services.CitaService;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponse>> obtenerCitas() {
        List<CitaModel> citasResponse = citaService.obtenerCitas();
        List<CitaResponse> citas = new ArrayList<>();

        for (CitaModel cita : citasResponse) {
            CitaResponse citaResponse = new CitaResponse();
            citaResponse.setId_Cita(cita.getIdCita());
            citaResponse.setDia_Cita(cita.getFecha());

            if (cita.getMedico() != null) {
                citaResponse.setNombre_medico(cita.getMedico().getNombre());
                citaResponse.setApellido_medico(cita.getMedico().getApellido());
            }

            if (cita.getPaciente() != null) {
                citaResponse.setCedula_paciente(cita.getPaciente().getCedula());
                citaResponse.setNombre_paciente(cita.getPaciente().getNombre());
                citaResponse.setApellido_paciente(cita.getPaciente().getApellido());
            }

            citas.add(citaResponse);
        }

        return ResponseEntity.ok(citas);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> guardarUsuario(@RequestBody CitaModel cita) {
        if (isAnyFieldEmpty(cita)) {
            return ResponseEntity.badRequest().body(new ApiResponse("Debe completar todos los campos", null));
        }
        CitaModel citaGuardada = citaService.guardarCita(cita);
        return ResponseEntity.status(201).body(new ApiResponse("Cita guardada correctamente", citaGuardada));
    }

    private boolean isAnyFieldEmpty(CitaModel cita) {
        return cita.getCedula_paciente() == 0 || cita.getTarjeta_profesional() == 0;
    }
}
