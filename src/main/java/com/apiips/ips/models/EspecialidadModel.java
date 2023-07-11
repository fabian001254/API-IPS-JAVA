package com.apiips.ips.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table (name = "Especialidad")
public class EspecialidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidad;
    @Column(unique = true)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "especialidad")
    private List<MedicoModel> medico;


    public EspecialidadModel(int idEspecialidad, String nombre, List<MedicoModel> medico) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.medico = medico;
    }

    public EspecialidadModel(){
        
    }
    public int getIdEspecialidad() {
        return idEspecialidad;
    }
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MedicoModel> getMedico() {
        return medico;
    }
    public void setMedico(List<MedicoModel> medico) {
        this.medico = medico;
    }
    
}