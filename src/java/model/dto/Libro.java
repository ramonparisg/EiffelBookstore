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
    private int isbn;

    public Libro() {
    }

    public Libro(int nroSerie, int idEstado, int isbn) {
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

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    
}
