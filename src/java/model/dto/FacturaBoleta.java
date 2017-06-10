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
public class FacturaBoleta {
    private int folio;
    private String fechaCompra;
    private int precioIva;
    private int precioNeto;
    private int iva;
    private String horaCompra;
    private int metodoPago;

    public FacturaBoleta() {
        this.folio = 0;
        this.fechaCompra = "";
        this.precioIva = 0;
        this.precioNeto = 0;
        this.iva = 19;
        this.horaCompra = "";
        this.metodoPago = 0;
    }

    public FacturaBoleta(int folio, String fechaCompra, int precioIva, int precioNeto, String horaCompra, int metodoPago) {
        this.folio = folio;
        this.fechaCompra = fechaCompra;
        this.precioIva = precioIva;
        this.precioNeto = precioNeto;
        this.iva = 19;
        this.horaCompra = horaCompra;
        this.metodoPago = metodoPago;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(int precioIva) {
        this.precioIva = precioIva;
    }

    public int getPrecioNeto() {
        return precioNeto;
    }

    public void setPrecioNeto(int precioNeto) {
        this.precioNeto = precioNeto;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public String getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(String horaCompra) {
        this.horaCompra = horaCompra;
    }

    public int getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(int metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
}
