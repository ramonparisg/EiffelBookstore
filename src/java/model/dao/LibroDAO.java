/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Libro;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class LibroDAO {
    Conexion c;
    
    public LibroDAO(){
        c = new Conexion();
    }
    
    public int insertar(Libro l){
        int res =0;
        String q = "insert into libro(id_estado,isbn) values("+l.getIdEstado()+","+l.getIsbn()+");";
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public int modificar(Libro l){
        int res =0;
        String q = "update libro set id_estado="+l.getIdEstado()+",isbn="+l.getIsbn()+" where nro_serie="+l.getNroSerie();
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public int eliminar(String id){
        int res =0;
        String q = "delete from libro where nro_serie="+id;
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public Libro buscar(String id){
        Libro l=null;
        ResultSet rs;
        String q = "Select * from libro where nro_serie="+id;
        rs = c.leerDatos(q);
        try {
            while (rs.next()){
                l = new Libro();
                l.setNroSerie(rs.getInt(1));
                l.setIdEstado(rs.getInt(2));
                l.setIsbn(rs.getString(3));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
    public ArrayList<Libro> listar(){
        ArrayList<Libro> lista = new ArrayList<Libro>();
        Libro l=null;
        ResultSet rs;
        String q = "Select * from libro";
        rs = c.leerDatos(q);
        try {
            while (rs.next()){
                l = new Libro();
                l.setNroSerie(rs.getInt(1));
                l.setIdEstado(rs.getInt(2));
                l.setIsbn(rs.getString(3)); 
                lista.add(l);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
