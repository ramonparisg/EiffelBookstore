/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto.arriendo;

/**
 *
 * @author Ramon Paris
 */
public class Arriendo {
    private int id;
    private int rutCliente;
    private int rutTrabajador;

    public Arriendo(int id, int rutCliente, int rutTrabajador) {
        this.id = id;
        this.rutCliente = rutCliente;
        this.rutTrabajador = rutTrabajador;
    }

    public Arriendo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
