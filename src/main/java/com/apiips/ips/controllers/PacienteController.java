package com.apiips.ips.controllers;

import org.springframework.web.bind.annotation.*;

import com.apiips.ips.models.PacienteModel;
import com.apiips.ips.services.PacienteSevice;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteSevice pacienteService;

    @GetMapping()
    public List<PacienteModel> obtenerPacientes() {
        return pacienteService.obtenerPacientes();
    }
    

    @GetMapping(path = "/{cedula}")
    public ResponseEntity<?> obtenerPacientesPorCedula(@PathVariable("cedula") int cedula){
        return pacienteService.obtenerPacientesPorCedula(cedula);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> guardarUsuario(@RequestBody PacienteModel paciente) {
        // Validación de campos vacíos
        if (isAnyFieldEmpty(paciente)) {
            return ResponseEntity.badRequest().body(new ApiResponse("Debe completar todos los campos",paciente));
        }
    
        PacienteModel pacienteGuardado = pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok(new ApiResponse("Paciente guardado correctamente", pacienteGuardado));
    }
    
    private boolean isAnyFieldEmpty(PacienteModel paciente) {
        return paciente.getCedula() == 0
                || paciente.getNombre() == null
                || paciente.getApellido() == null
                || paciente.getFechadenacimiento() == null
                || paciente.getTelefono() == null;
    }
    
    
    
}
