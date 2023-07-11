package com.apiips.ips;
import com.apiips.ips.models.PacienteModel;
import com.apiips.ips.repositories.PacienteRepository;
import com.apiips.ips.services.PacienteSevice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteControllerTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteSevice pacienteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerPacientes() {
        // Crear una lista ficticia de pacientes
        List<PacienteModel> pacientes = new ArrayList<>();
        pacientes.add(new PacienteModel(1, "John", "Doe", LocalDate.now(), 123456789L));
        pacientes.add(new PacienteModel(2, "Jane", "Smith", LocalDate.now(), 987654321L));
    
        // Mockear el repositorio para que devuelva la lista ficticia de pacientes
        Mockito.when(pacienteRepository.findAll()).thenReturn(pacientes);
    
        // Llamar al método de servicio y verificar que devuelva la lista de pacientes correctamente
        List<PacienteModel> pacientesObtenidos = pacienteService.obtenerPacientes();
        ResponseEntity<List<PacienteModel>> response = ResponseEntity.ok(pacientesObtenidos);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(pacientes.size(), response.getBody().size());
    }
    

    @Test
    public void testGuardarPaciente() {
        // Crear un paciente ficticio para guardar
        PacienteModel paciente = new PacienteModel(1, "John", "Doe", LocalDate.now(),123456789L);
    
        // Mockear el repositorio para que devuelva el paciente ficticio guardado
        Mockito.when(pacienteRepository.save(Mockito.any(PacienteModel.class))).thenReturn(paciente);
    
        // Llamar al método de servicio y verificar que devuelva el paciente guardado correctamente
        PacienteModel pacienteGuardado = pacienteService.guardarPaciente(paciente);
        ResponseEntity<PacienteModel> response = ResponseEntity.ok(pacienteGuardado);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }
    
    
    
    


    // Agrega más pruebas unitarias según sea necesario

}
