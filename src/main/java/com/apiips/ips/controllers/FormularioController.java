package com.apiips.ips.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/formulario")
public class FormularioController {

    @Value("classpath:json-schema.json") // Ruta al archivo JSON
    private Resource jsonSchemaFile;

    @GetMapping("/{formulario}")
    public ResponseEntity<JsonNode> obtenerDefinicion(@PathVariable String formulario) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Cargar el archivo JSON
            JsonNode jsonSchema = objectMapper.readTree(jsonSchemaFile.getInputStream());

            // Obtener la definición del formulario
            JsonNode formularioDefinicion = jsonSchema.path("definitions").path(formulario);

            if (formularioDefinicion.isMissingNode()) {
                return ResponseEntity.badRequest().body(null);
            } else {
                return ResponseEntity.ok(formularioDefinicion);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

