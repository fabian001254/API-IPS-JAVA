package com.apiips.ips;

import com.apiips.ips.controllers.ApiResponse;
import com.apiips.ips.controllers.CitaController;
import com.apiips.ips.controllers.CitaResponse;
import com.apiips.ips.models.CitaModel;
import com.apiips.ips.services.CitaService;
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

public class CitaControllerTest {

    @Mock
    private CitaService citaService;

    @InjectMocks
    private CitaController citaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerCitas() {
        List<CitaModel> citas = new ArrayList<>();
        citas.add(new CitaModel(LocalDate.now(), 1010, 1));
        citas.add(new CitaModel(LocalDate.now(), 2020, 2));

        Mockito.when(citaService.obtenerCitas()).thenReturn(new ArrayList<>(citas));

        // Llamar al método del controlador y obtener la respuesta
        ResponseEntity<List<CitaResponse>> response = citaController.obtenerCitas();

        // Verificar que la respuesta sea exitosa
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(citas.size(), response.getBody().size());

        // Verificar los valores de cada CitaModel en la respuesta
        List<CitaResponse> citaResponses = response.getBody();
        for (int i = 0; i < citas.size(); i++) {
            CitaModel cita = citas.get(i);
            CitaResponse citaResponse = citaResponses.get(i);

            Assertions.assertEquals(cita.getIdCita(), citaResponse.getId_Cita());
            Assertions.assertEquals(cita.getFecha(), citaResponse.getDia_Cita());

            // Verificar las propiedades del MedicoModel solo si no es nulo
            if (cita.getMedico() != null) {
                Assertions.assertEquals(cita.getMedico().getNombre(), citaResponse.getNombre_medico());
                Assertions.assertEquals(cita.getMedico().getApellido(), citaResponse.getApellido_medico());
            }

            // Verificar las propiedades del PacienteModel solo si no es nulo
            if (cita.getPaciente() != null) {
                Assertions.assertEquals(cita.getPaciente().getNombre(), citaResponse.getNombre_paciente());
                Assertions.assertEquals(cita.getPaciente().getApellido(), citaResponse.getApellido_paciente());
                Assertions.assertEquals(cita.getPaciente().getCedula(), citaResponse.getCedula_paciente());
            }
        }
    }

    @Test
    public void testGuardarCita() {
        CitaModel cita = new CitaModel(LocalDate.now(), 1010, 10);
        Mockito.when(citaService.guardarCita(Mockito.any(CitaModel.class))).thenReturn(cita);

        // Llamar al método del controlador para guardar la cita
        ResponseEntity<ApiResponse> responseEntity = citaController.guardarUsuario(cita);

        // Verificar que la respuesta sea exitosa
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Obtener el objeto ApiResponse de la respuesta
        ApiResponse response = responseEntity.getBody();

        // Verificar el mensaje y el objeto en el ApiResponse
        Assertions.assertEquals("Cita guardada correctamente", response.getMessage());
        Assertions.assertNotNull(response.getObjeto());

        // Obtener la cita guardada del objeto ApiResponse
        CitaModel citaGuardada = (CitaModel) response.getObjeto();

        // Realizar aserciones en los datos de la cita guardada
        Assertions.assertEquals(cita.getIdCita(), citaGuardada.getIdCita());
        Assertions.assertEquals(cita.getFecha(), citaGuardada.getFecha());
        // ... otras aserciones para otros campos de la cita guardada
    }

}
