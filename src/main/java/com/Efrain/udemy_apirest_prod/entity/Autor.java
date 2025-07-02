package com.Efrain.udemy_apirest_prod.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="autores") //las tablas van en prular
public class Autor {
    @Id //lo mapea con esta llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "nombres")
    private String nombre;
    @Column(name = "apellidos")
    private String apellido;
    private String telefono;

    public Autor() {
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
