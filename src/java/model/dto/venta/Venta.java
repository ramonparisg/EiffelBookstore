/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto.venta;

/**
 *
 * @author Ramon Paris
 */
public class Venta {
    private int id;
    private int nro_serie;
    private int boleta;
    private int rutCliente;
    private int rutTrabajador;

    public Venta() {
    }

    public Venta(int id, int nro_serie, int boleta, int rutCliente, int rutTrabajador) {
        this.id = id;
        this.nro_serie = nro_serie;
        this.boleta = boleta;
        this.rutCliente = rutCliente;
        this.rutTrabajador = rutTrabajador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNro_serie() {
        return nro_serie;
    }

    public void setNro_serie(int nro_serie) {
        this.nro_serie = nro_serie;
    }

    public int getBoleta() {
        return boleta;
    }

    public void setBoleta(int boleta) {
        this.boleta = boleta;
    }

    public int getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(int rutCliente) {
        this.rutCliente = rutCliente;
    }

    public int getRutTrabajador() {
        return rutTrabajador;
    }

    public void setRutTrabajador(int rutTrabajador) {
        this.rutTrabajador = rutTrabajador;
    }
    
}
