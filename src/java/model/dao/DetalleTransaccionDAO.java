/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.compra.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.DetalleTransaccion;
import model.dto.Libro;
import model.dto.compra.DetalleCompra;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class DetalleTransaccionDAO {
    Conexion c;
    public DetalleTransaccionDAO() {
        c = new Conexion();
    }
    
    
    public ArrayList<DetalleTransaccion> listar(){
        ArrayList<DetalleTransaccion> lista = new ArrayList<DetalleTransaccion>();
        DetalleTransaccion d;
        String q = "select\n" +
                    "t.isbn,t.nombre,\n" +
                    "i.desc_idioma,i.id_idioma,\n" +
                    "count(l.nro_serie),t.precio_referencia\n" +
                    "from libro l, titulo t, libro_idioma li, idioma i\n" +
                    "where l.id_estado=1\n" +
                    "and l.isbn = t.isbn\n" +
                    "and l.nro_serie = li.nro_serie\n" +
                    "and li.id_idioma = i.id_idioma\n" +
                    "group by l.isbn,i.id_idioma;";
        ResultSet rs=c.leerDatos(q);
        try {
            while (rs.next()){
                d = new DetalleTransaccion();
                d.setIsbn(rs.getString(1));
                d.setTitulo(rs.getString(2));
                d.setIdioma(rs.getString(3));
                d.setIdIdioma(rs.getInt(4));
                d.setCantidad(rs.getInt(5));
                d.setPrecio(rs.getInt(6));
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleTransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Libro libro(String isbn,int nro){
        ArrayList<Libro> lista = new ArrayList<Libro>();
        Libro l;
        String q = "select\n" +
                    "l.nro_serie,l.isbn\n" +
                    "from libro l, titulo t, libro_idioma li, idioma i\n" +
                    "where l.id_estado=1\n" +
                    "and l.isbn = t.isbn\n" +
                    "and t.isbn="+isbn+"\n" +
                    "and l.nro_serie = li.nro_serie\n" +
                    "and li.id_idioma = i.id_idioma\n" +
                    "and i.id_idioma="+nro+";";
        ResultSet rs=c.leerDatos(q);
        try {
            while (rs.next()){
                l = new Libro();
                l.setNroSerie(rs.getInt(1));
                l.setIsbn(rs.getString(2));
                lista.add(l);
            }
            
            return lista.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleTransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public DetalleTransaccion buscarPorNroSerie(int nro){
        ArrayList<DetalleTransaccion> lista = new ArrayList<DetalleTransaccion>();
        DetalleTransaccion d=null;
        String q = "select\n" +
                    "t.isbn,t.nombre,t.precio_referencia,\n" +
                    "i.desc_idioma,i.id_idioma\n" +
                    "from libro l, titulo t, libro_idioma li, idioma i\n" +
                    "where l.nro_serie="+nro+"\n" +
                    "and l.isbn = t.isbn\n" +
                    "and l.nro_serie = li.nro_serie\n" +
                    "and li.id_idioma = i.id_idioma;";
        ResultSet rs=c.leerDatos(q);
        try {
            while (rs.next()){
                d = new DetalleTransaccion();
                d.setIsbn(rs.getString(1));
                d.setTitulo(rs.getString(2));
                d.setPrecio(rs.getInt(3));                
                d.setIdioma(rs.getString(4));
                d.setIdIdioma(rs.getInt(5));                                
            }            
        } catch (SQLException ex) {
            Logger.getLogger(DetalleTransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
}
