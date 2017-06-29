/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import model.dto.compra.*;

/**
 *
 * @author Ramon Paris
 */
public class DetalleTransaccion {
    private String isbn;
    private String titulo;
    private String idioma;
    private int idIdioma;
    private int cantidad;
    private int precio;
    private int nroserie;

    public DetalleTransaccion() {
    }

    public DetalleTransaccion(String isbn, String titulo, String idioma, int idIdioma, int cantidad, int precio, int nroserie) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.idIdioma = idIdioma;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nroserie = nroserie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getNroserie() {
        return nroserie;
    }

    public void setNroserie(int nroserie) {
        this.nroserie = nroserie;
    }

    
    
    
    
}
