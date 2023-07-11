package com.apiips.ips.controllers;

public class MedicoResponse {
    private int tarjetaProfesional;
    private String nombre;
    private String apellido;
    private String consultorio;
    private String correo;
    private int idEspecialidad;
    private String nombreEspecialidad;

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(int tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
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

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public MedicoResponse(int tarjetaProfesional, String nombre, String apellido, String consultorio, String correo,String nombreEspecialidad) {
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.consultorio = consultorio;
        this.correo = correo;
        this.nombreEspecialidad = nombreEspecialidad;
    }
    
    public MedicoResponse(int tarjetaProfesional, String nombre, String apellido, String consultorio, String correo, int idEspecialidad) {
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.consultorio = consultorio;
        this.correo = correo;
        this.idEspecialidad = idEspecialidad;
    }





}

