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
public class Titulo {
    private String isbn;
    private String nombre;
    private String anyoPublicacion;
    private int precioReferencia;
    private int nroPaginas;
    private int idEditorial;
    private int idPublicacion;

    public Titulo() {
        this.isbn="";
        this.nombre="";
        this.anyoPublicacion="";
        this.precioReferencia=0;
        this.nroPaginas=0;
        this.idEditorial=0;
        this.idPublicacion=0;
    }

    public Titulo(String isbn, String nombre, String anyoPublicacion, int precioReferencia, int nroPaginas, int idEditorial, int idPublicacion) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.anyoPublicacion = anyoPublicacion;
        this.precioReferencia = precioReferencia;
        this.nroPaginas = nroPaginas;
        this.idEditorial = idEditorial;
        this.idPublicacion = idPublicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(String anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public int getPrecioReferencia() {
        return precioReferencia;
    }

    public void setPrecioReferencia(int precioReferencia) {
        this.precioReferencia = precioReferencia;
    }

    public int getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    
    
    
    
    
}
