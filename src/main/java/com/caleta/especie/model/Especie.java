package com.caleta.especie.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie")
    private Long id;

    @Column(name = "nombre_especie",nullable = false)
    private String nombre;

    @Column(name = "en_veda", nullable = false)
    private boolean enVeda;

    //constructor vacio
    public Especie(){}

    //constructor con parametros
    public Especie(long id, String nombre, boolean enVeda) {
        this.id = id;
        this.nombre = nombre;
        this.enVeda = enVeda;
    }

    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnVeda() {
        return enVeda;
    }

    public void setEnVeda(boolean enVeda) {
        this.enVeda = enVeda;
    }
}
