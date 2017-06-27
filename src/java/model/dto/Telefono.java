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
public class Telefono {
    private int nro;
    private int rut;

    public Telefono() {
        nro =0;
        rut =0;
    }

    public Telefono(int nro, int rut) {
        this.nro = nro;
        this.rut = rut;
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
