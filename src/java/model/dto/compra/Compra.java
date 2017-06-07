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
public class Compra {
    private int id;
    private int nroSerie;
    private int rutDistribuidor;
    private int folio;

    public Compra() {
    }

    public Compra(int id, int nroSerie, int rutDistribuidor, int folio) {
        this.id = id;
        this.nroSerie = nroSerie;
        this.rutDistribuidor = rutDistribuidor;
        this.folio = folio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(int nroSerie) {
        this.nroSerie = nroSerie;
    }

    public int getRutDistribuidor() {
        return rutDistribuidor;
    }

    public void setRutDistribuidor(int rutDistribuidor) {
        this.rutDistribuidor = rutDistribuidor;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }
    
    
}
