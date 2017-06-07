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
public class Auxiliar {
    private int id;
    private String detalle;

    public Auxiliar(int id, String detalle) {
        this.id = id;
        this.detalle = detalle;
    }

    public Auxiliar() {
        this.id=0;
        this.detalle="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
}
