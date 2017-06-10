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
public class Distribuidor {
    private int rut;
    private String direccion;
    private String telefono;
    private String nombre;
    private int anyo;

    public Distribuidor(int rut, String direccion, String telefono, String nombre, int anyo) {
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre = nombre;
        this.anyo = anyo;
    }

    public Distribuidor() {
        this.rut = 0;
        this.direccion = "";
        this.telefono = "";
        this.nombre = "";
        this.anyo = 0;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
    
}
