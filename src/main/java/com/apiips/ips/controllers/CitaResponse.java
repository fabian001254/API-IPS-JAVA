package com.apiips.ips.controllers;

import io.swagger.v3.oas.annotations.Hidden;

import java.time.LocalDate;

@Hidden
public class CitaResponse {
    private int id_Cita;
    private String Nombre_medico;
    private String Apellido_medico;
    private String Nombre_paciente;
    private String Apellido_paciente;
    private int Cedula_paciente;
    private LocalDate Dia_Cita;
    public int getId_Cita() {
        return id_Cita;
    }
    public void setId_Cita(int id_Cita) {
        this.id_Cita = id_Cita;
    }
    public String getNombre_medico() {
        return Nombre_medico;
    }
    public void setNombre_medico(String nombre_medico) {
        Nombre_medico = nombre_medico;
    }
    public String getApellido_medico() {
        return Apellido_medico;
    }
    public void setApellido_medico(String apellido_medico) {
        Apellido_medico = apellido_medico;
    }
    public String getNombre_paciente() {
        return Nombre_paciente;
    }
    public void setNombre_paciente(String nombre_paciente) {
        Nombre_paciente = nombre_paciente;
    }
    public String getApellido_paciente() {
        return Apellido_paciente;
    }
    public void setApellido_paciente(String apellido_paciente) {
        Apellido_paciente = apellido_paciente;
    }
    public int getCedula_paciente() {
        return Cedula_paciente;
    }
    public void setCedula_paciente(int cedula_paciente) {
        Cedula_paciente = cedula_paciente;
    }
    public LocalDate getDia_Cita() {
        return Dia_Cita;
    }
    public void setDia_Cita(LocalDate dia_Cita) {
        Dia_Cita = dia_Cita;
    }

}

