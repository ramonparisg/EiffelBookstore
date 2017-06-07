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
import model.dto.Titulo;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class TituloDAO {
    Conexion c;

    public TituloDAO() {
        c = new Conexion();
    }
    
    public int insertar(Titulo t){
        int res =0;
        String q = "Insert into titulo(anyo_publicacion,id_Publicacion,precio_referencia,nro_paginas,id_Editorial)"
                                    + " values('"+t.getAnyoPublicacion()+"',"
                                                +t.getIdPublicacion()+ ","
                                                +t.getPrecioReferencia()+ ","
                                                +t.getNroPaginas()+ ","
                                                +t.getIdEditorial()+ ");";
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public int modificar(Titulo t){
        int res =0;
        String q = "Update titulo set "
                + "anyo_publicacion='"+t.getAnyoPublicacion()
                + "',id_Publicacion="+t.getIdPublicacion()
                + ",precio_referencia="+t.getPrecioReferencia()
                + ",nro_paginas="+t.getNroPaginas()
                + ",id_Editorial="+t.getIdEditorial()
                + " where isbn="+t.getIsbn();        
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public int eliminar(int isbn){
        int res=0;
        String q = "Delete from titulo where isbn="+isbn;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Titulo buscar(int isbn){
        Titulo t = null;
        ResultSet rs;
        String q = "Select * from titulo where isbn="+isbn;                        
        try {
            rs = c.leerDatos(q);
            while (rs.next()){
                t = new Titulo();
                t.setIsbn(rs.getInt(1));
                t.setAnyoPublicacion(rs.getString(2));
                t.setPrecioReferencia(rs.getInt(3));
                t.setNroPaginas(rs.getInt(4));
                t.setIdEditorial(rs.getInt(5));
                t.setIdPublicacion(rs.getInt(6));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    public ArrayList<Titulo> listar(){
        ArrayList<Titulo> lista = new ArrayList<Titulo>();
        Titulo t;
        ResultSet rs;
        String q = "Select * from titulo";
        try {
            rs = c.leerDatos(q);
            while (rs.next()){
                t = new Titulo();
                t.setIsbn(rs.getInt(1));
                t.setAnyoPublicacion(rs.getString(2));
                t.setPrecioReferencia(rs.getInt(3));
                t.setNroPaginas(rs.getInt(4));
                t.setIdEditorial(rs.getInt(5));
                t.setIdPublicacion(rs.getInt(6));
                lista.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return lista;
    }
}
