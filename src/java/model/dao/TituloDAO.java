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
        String q = "Insert into titulo(isbn,nombre,anyo_publicacion,id_Publicacion,precio_referencia,nro_paginas,id_Editorial)"
                                    + " values("+t.getIsbn()
                                                + ",'"+ t.getNombre()
                                                + "','"+t.getAnyoPublicacion()+"',"
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
                + "nombre='"+t.getNombre()
                + "',anyo_publicacion='"+t.getAnyoPublicacion()
                + "',id_Publicacion="+t.getIdPublicacion()
                + ",precio_referencia="+t.getPrecioReferencia()
                + ",nro_paginas="+t.getNroPaginas()
                + ",id_Editorial="+t.getIdEditorial()
                + " where isbn="+t.getIsbn();        
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public int eliminar(String isbn){
        int res=0;
        String q = "Delete from titulo where isbn="+isbn;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Titulo buscar(String isbn){
        Titulo t = null;
        ResultSet rs;
        String q = "Select * from titulo where isbn="+isbn;                        
        try {
            rs = c.leerDatos(q);
            while (rs.next()){
                t = new Titulo();
                t.setIsbn(rs.getString(1));
                t.setNombre(rs.getString(2));
                t.setAnyoPublicacion(rs.getString(3));
                t.setPrecioReferencia(rs.getInt(4));
                t.setNroPaginas(rs.getInt(5));
                t.setIdEditorial(rs.getInt(6));
                t.setIdPublicacion(rs.getInt(7));                
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
                t.setIsbn(rs.getString(1));
                t.setNombre(rs.getString(2));
                t.setAnyoPublicacion(rs.getString(3));
                t.setPrecioReferencia(rs.getInt(4));
                t.setNroPaginas(rs.getInt(5));
                t.setIdEditorial(rs.getInt(6));
                t.setIdPublicacion(rs.getInt(7));
                lista.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return lista;
    }
    
    public int insertarMuchosAMuchos(String tabla, int id,String isbn){
        int res =0;
        String q ="insert into titulo_"+tabla+" values("+id+","+isbn+")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificarMuchosAMuchos(String tabla, int id,String isbn){
        int res =0;
        String q ="update titulo_"+tabla+" set id_"+tabla+"="+id+",isbn="+isbn;
        res = c.ejecutarSQL(q);
        return res;
    }
}
