package com.apiips.ips.controllers;
import java.util.ArrayList;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apiips.ips.models.EspecialidadModel;
import com.apiips.ips.services.EspecialidadService;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    @Autowired
    EspecialidadService especialidadService;

    @Operation(summary = "Obtener especialidades")
    @GetMapping
    public ArrayList<EspecialidadModel> obtenerEspecialidades(){
        return especialidadService.obtenerEspecialidades();
    }

    @Operation(summary = "Obtener especialidad por ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> obtenerPacientesPorCedula(@PathVariable("id") int id){
        return especialidadService.obtenerEspecialidadPorId(id);
    }

    @Operation(summary = "Guardar citas")
    @PostMapping()
    public ResponseEntity<ApiResponse> guardarUsuario(@RequestBody EspecialidadModel especialidad){
        if (isAnyFieldEmpty(especialidad)) {
            return ResponseEntity.status(422).body(new ApiResponse("Debe completar todos los campos",especialidad));
        }
        EspecialidadModel especialidadGuardado = especialidadService.guardarEspecialidad(especialidad);
        return ResponseEntity.status(201).body(new ApiResponse("Especialidad guardada correctamente", especialidadGuardado));
    }

    @Hidden
    private boolean isAnyFieldEmpty(EspecialidadModel especialidad) {
        return especialidad.getNombre() == null;
                
    }


}
