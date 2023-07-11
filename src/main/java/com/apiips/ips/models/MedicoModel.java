package com.apiips.ips.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "Medico")
public class MedicoModel {
    @Id
    @Column(unique = true)
    private int tarjetaProfesional;
    private String nombre;
    private String apellido;
    private String consultorio;
    private String correo;

    @Transient
    private String nombreEspecialidad;

    @ManyToOne
    @JoinColumn(name = "idEspecialidad")
    private EspecialidadModel especialidad;

    @JsonIgnore
    @OneToMany(mappedBy = "medico")
    private List<CitaModel> citas;


    public MedicoModel(int tarjetaProfesional, String nombre, String apellido, String consultorio, String correo,
            String nombreEspecialidad) {
        this.tarjetaProfesional = tarjetaProfesional;
        this.nombre = nombre;
        this.apellido = apellido;
        this.consultorio = consultorio;
        this.correo = correo;
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


    @JsonProperty("nombreEspecialidad")
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    @JsonIgnore
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public EspecialidadModel getEspecialidad() {
        return especialidad;
    }


    public void setEspecialidad(EspecialidadModel especialidad) {
        this.especialidad = especialidad;
    }


    public List<CitaModel> getCitas() {
        return citas;
    }


    public void setCitas(List<CitaModel> citas) {
        this.citas = citas;
    }


    public MedicoModel() {

    }
}
