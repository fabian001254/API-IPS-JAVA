package com.apiips.ips.controllers;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apiips.ips.models.MedicoModel;
import com.apiips.ips.services.MedicoService;


@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    MedicoService medicoService;


    @GetMapping()
    public ArrayList<MedicoModel> obtenermedicos(){
        return medicoService.obtenerMedicos();
    }

    @GetMapping(path = "/{tarjeta}")
    public ResponseEntity<?> obtenerPacientesPorCedula(@PathVariable("tarjeta") int tarjeta){
        return medicoService.obtenerMedicoPorTarjeta(tarjeta);
    }


    @PostMapping()
    @Operation(summary = "Crea un nuevo medico")
    public ResponseEntity<ApiResponse> guardarUsuario(@RequestBody MedicoModel medico) {
        
        if (isAnyFieldEmpty(medico)) {
            MedicoResponse medicoResponse = new MedicoResponse(
                medico.getTarjetaProfesional(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getConsultorio(),
                medico.getCorreo(),
                medico.getNombreEspecialidad()
            );
            return ResponseEntity.status(422).body(new ApiResponse("Debe completar todos los campos",medicoResponse));
        }
            MedicoModel medicoGuardado = medicoService.guardarMedico(medico);

            MedicoResponse medicoResponse = new MedicoResponse(
                medicoGuardado.getTarjetaProfesional(),
                medicoGuardado.getNombre(),
                medicoGuardado.getApellido(),
                medicoGuardado.getConsultorio(),
                medicoGuardado.getCorreo(),
                medicoGuardado.getEspecialidad().getIdEspecialidad()
            );
            return ResponseEntity.status(201).body(new ApiResponse("Medico guardado correctamente", medicoResponse));
    }

    @Hidden
    private boolean isAnyFieldEmpty(MedicoModel medico) {
        return medico.getTarjetaProfesional() == 0
                || medico.getNombre() == null
                || medico.getApellido() == null
                || medico.getConsultorio() == null
                || medico.getCorreo() == null
                || medico.getNombreEspecialidad() == null;
    }



}
