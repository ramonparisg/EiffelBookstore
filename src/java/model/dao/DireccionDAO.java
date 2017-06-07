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
import model.dto.Direccion;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class DireccionDAO {
    Conexion c;

    public DireccionDAO() {
        c = new Conexion();
    }
    
    public int insertar(Direccion d, String tabla){
        int res=0;
        String q ="insert into direccion_"+tabla+"(nombre_calle,nro_calle,rut) values('"
                + d.getNombre()+"',"
                + d.getNro()+""
                + d.getRut()+")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Direccion d, String tabla){
        int res=0;
        String q ="update direccion_"+tabla+""
                + "nombre_calle='"+d.getNombre()
                + "',nro_calle="+d.getNro()
                + ",rut="+d.getRut()
                + " where id_direccion="+d.getId();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(String tabla,int id){
        int res=0;
        String q ="Delete from direccion_"+tabla+" where id_direccion="+id;
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public Direccion buscar(int id, String tabla){
       Direccion d = null;
       String q ="Select * from direccion_"+tabla+" where id_direccion="+id;
       ResultSet rs = c.leerDatos(q);
        try {
            while(rs.next()){
                d = new Direccion();
                d.setId(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setNro(rs.getInt(3));
                d.setRut(rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return d;
    }
    
    public ArrayList<Direccion> listar(String tabla){
       Direccion d = null;
       ArrayList<Direccion> lista = new ArrayList<Direccion>();
       String q ="Select * from direccion_"+tabla;
       ResultSet rs = c.leerDatos(q);
        try {
            while(rs.next()){
                d = new Direccion();
                d.setId(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setNro(rs.getInt(3));
                d.setRut(rs.getInt(4));
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DireccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
}
