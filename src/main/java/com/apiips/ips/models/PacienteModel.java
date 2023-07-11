package com.apiips.ips.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Paciente")
public class PacienteModel {
    @Id
    @Column(unique = true)    
    private int cedula;
    @NotNull
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    @NotNull
    private String apellido;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    @NotNull
    private LocalDate fechadenacimiento;
    @Column(nullable = false)
    @NotNull
    private Long telefono;
    
    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<CitaModel> citas;

    public PacienteModel(int cedula, @NotNull String nombre, @NotNull String apellido,
            @NotNull LocalDate fechadenacimiento, @NotNull Long telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechadenacimiento = fechadenacimiento;
        this.telefono = telefono;
    }

    public PacienteModel(){
        
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechadenacimiento() {
        return fechadenacimiento;
    }

    @JsonSetter("fechadenacimiento")
    public void setFechadenacimiento(String fechaNacimientoString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, formatter);
            this.fechadenacimiento = fechaNacimiento;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha debe ser dd-MM-yyyy");
        }
    }


    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }


    public List<CitaModel> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaModel> citas) {
        this.citas = citas;
    }

    
    
    
}