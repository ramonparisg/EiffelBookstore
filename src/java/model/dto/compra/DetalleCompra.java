/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto.compra;

/**
 *
 * @author Ramon Paris
 */
public class DetalleCompra {
    private String isbn;
    private String titulo;
    private String idioma;
    private int idIdioma;
    private int cantidad;
    private double precio;

    public DetalleCompra() {
    }

    public DetalleCompra(String isbn, String titulo, String idioma, int idIdioma, int cantidad, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.idIdioma = idIdioma;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
