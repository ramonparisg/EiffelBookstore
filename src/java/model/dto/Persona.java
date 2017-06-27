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
public class Persona {
    private int rut;
    private String nombre;
    private String apePat;
    private String apeMat;
    private String fechaNac;

    public Persona() {
        rut = 0;
        nombre ="";
        apePat="";
        apeMat="";
        fechaNac="";
    }

    public Persona(int rut, String nombre, String apePat, String apeMat, String fechaNac) {
        this.rut = rut;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;
        this.fechaNac = fechaNac;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
    
}
