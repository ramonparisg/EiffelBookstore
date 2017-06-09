/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

/**
 *
 * @author Ramon Paris
 */
public class Libro {
    private int nroSerie;
    private int idEstado;
    private String isbn;

    public Libro() {
    }

    public Libro(int nroSerie, int idEstado, String isbn) {
        this.nroSerie = nroSerie;
        this.idEstado = idEstado;
        this.isbn = isbn;
    }

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
    
}
