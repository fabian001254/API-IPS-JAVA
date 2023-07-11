package com.apiips.ips.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.*;

@Entity
@Table(name = "Cita")
public class CitaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cedula")
    private PacienteModel paciente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tarjetaProfesional")
    private MedicoModel medico;


    @Transient
    private int tarjeta_profesional;


    @Transient
    private int cedula_paciente;


    public int getTarjeta_profesional() {
        return tarjeta_profesional;
    }

    public void setTarjeta_profesional(int tarjeta_profesional) {
        this.tarjeta_profesional = tarjeta_profesional;
    }

    public int getCedula_paciente() {
        return cedula_paciente;
    }

    public void setCedula_paciente(int cedula_paciente) {
        this.cedula_paciente = cedula_paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel medico) {
        this.medico = medico;
    }

        @JsonSetter("fecha")
    public void setfecha(String fechacita) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fechaNacimiento = LocalDate.parse(fechacita, formatter);
            this.fecha = fechaNacimiento;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El formato de fecha debe ser dd-MM-yyyy");
        }
    }

    public CitaModel(){
        
    }

    public CitaModel(LocalDate fecha, int tarjeta_profesional, int cedula_paciente) {
        this.fecha = fecha;
        this.tarjeta_profesional = tarjeta_profesional;
        this.cedula_paciente = cedula_paciente;
    }



}

/*
 * model Cita {
  idcita             Int       @id @default(autoincrement())
  fecha              DateTime
  pacienteCedula     Int?
  tarjetaProfesional Int?
  Medico             Medico?   @relation(fields: [tarjetaProfesional], references: [tarjetaProfesional])
  Paciente           Paciente? @relation(fields: [pacienteCedula], references: [cedula])
}
 */