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
public class DetalleArriendo {
    private int idArriendo;
    private int nroSerie;
    private String fArriendo;
    private String fDevEsti;
    private String fDevReal;
    private int subtotal;
    private int iva;
    private int multa;

    public DetalleArriendo() {
    }

    public DetalleArriendo(int idArriendo, int nroSerie, String fArriendo, String fDevEsti, String fDevReal, int subtotal, int iva, int multa) {
        this.idArriendo = idArriendo;
        this.nroSerie = nroSerie;
        this.fArriendo = fArriendo;
        this.fDevEsti = fDevEsti;
        this.fDevReal = fDevReal;
        this.subtotal = subtotal;
        this.iva = iva;
        this.multa = multa;
    }

    public int getIdArriendo() {
        return idArriendo;
    }

    public void setIdArriendo(int idArriendo) {
        this.idArriendo = idArriendo;
    }

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getfArriendo() {
        return fArriendo;
    }

    public void setfArriendo(String fArriendo) {
        this.fArriendo = fArriendo;
    }

    public String getfDevEsti() {
        return fDevEsti;
    }

    public void setfDevEsti(String fDevEsti) {
        this.fDevEsti = fDevEsti;
    }

    public String getfDevReal() {
        return fDevReal;
    }

    public void setfDevReal(String fDevReal) {
        this.fDevReal = fDevReal;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
    
}
