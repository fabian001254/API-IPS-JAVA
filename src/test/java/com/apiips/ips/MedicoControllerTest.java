package com.apiips.ips;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apiips.ips.controllers.MedicoController;
import com.apiips.ips.models.MedicoModel;
import com.apiips.ips.services.MedicoService;

import java.util.ArrayList;
import java.util.List;

public class MedicoControllerTest {

    @Mock
    private MedicoService medicoService;

    @InjectMocks
    private MedicoController medicoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerMedicos() {
        List<MedicoModel> medicos = new ArrayList<>();
        medicos.add(new MedicoModel(123, "John", "Doe", "Consultorio A", "john.doe@example.com", "Pediatria"));
        medicos.add(new MedicoModel(456, "Jane", "Smith", "Consultorio B", "jane.smith@example.com", "Cardiologia"));
    
        // Llamar al método de servicio y verificar que devuelva la lista de pacientes correctamente
        Mockito.when(medicoService.obtenerMedicos()).thenReturn((ArrayList<MedicoModel>) medicos);

        List<MedicoModel> medicoObtenidos = medicoController.obtenermedicos();
        ResponseEntity<List<MedicoModel>> response = ResponseEntity.ok(medicoObtenidos);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(medicos.size(), response.getBody().size());
    }


    @Test
    public void testGuardarMedico() {
        MedicoModel medico = new MedicoModel(123, "John", "Doe", "Consultorio A", "john.doe@example.com", "Pediatria");
        Mockito.when(medicoService.guardarMedico(Mockito.any(MedicoModel.class))).thenReturn(medico);

        MedicoModel medicoGuardado = medicoService.guardarMedico(medico);
        ResponseEntity<MedicoModel> response = ResponseEntity.ok(medicoGuardado);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

    // Agrega más pruebas unitarias según sea necesario

}
