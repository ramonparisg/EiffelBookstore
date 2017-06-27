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
public class Direccion {
    private int id;
    private String nombre;
    private int nro;
    private int rut;

    public Direccion() {
        id=0;
        nombre="";
        nro=0;
        rut=0;
    }

    public Direccion(int id, String nombre, int nro, int rut) {
        this.id = id;
        this.nombre = nombre;
        this.nro = nro;
        this.rut = rut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }
    
}
