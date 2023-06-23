package com.ceiba.biblioteca.entidades;

import javax.persistence.*;

@Entity
@Table(name="prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="isbn",nullable = false,length = 10)
    private String isbn;

    @Column(name="identificacionUsuario",nullable = false)
    private String identificacionUsuario;

    @Column(name="fechaMaximaDevolucion")
    private String fechaMaximaDevolucion;

    @Column(name="tipoUsuario",nullable = false)
    private Integer tipoUsuario;

    public Prestamo() {
    }

    public Prestamo(Integer id, String isbn, String identificacionUsuario, String fechaMaximaDevolucion, Integer tipoUsuario) {
        this.id = id;
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
