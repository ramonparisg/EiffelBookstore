/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

/**
 *
 * @author raparisg
 */
public class Autor {
    private int idAutor;
    private String nombre;
    private String apePat;
    private String apeMat;

    public Autor() {
        this.idAutor=0;
        this.nombre="";
        this.apeMat="";
        this.apePat="";
    }

    public Autor(int idAutor, String nombre, String apePat, String apeMat) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }
    
    
}
