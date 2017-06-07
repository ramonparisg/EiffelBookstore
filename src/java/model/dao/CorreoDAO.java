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
import model.dto.Correo;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class CorreoDAO {
    Conexion c;

    public CorreoDAO() {
        c = new Conexion();
    }
    
    public int insetar(Correo t, String tabla){
        int res=0;
        String q ="Insert into correo_"+tabla+" values('"+t.getCorreo()+"',"+t.getRut()+")";
        res = c.ejecutarSQL(q);        
        return res;
    }
    public int modificar(Correo t, String tabla){
        int res=0;
        String q ="update correo_"+tabla+" set "                
                + ",rut="+t.getRut()+""
                + " where correo='"+t.getCorreo()+"'";
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public int eliminar(String id, String tabla){
        int res =0;
        String q ="Delete from correo_"+tabla+" where correo='"+id+"'";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Correo buscar(String id, String tabla) {
        Correo t = null;
        String q ="Select * from correo_"+tabla+" where correo='"+id+"'";
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                t = new Correo();
                t.setCorreo(rs.getString(1));
                t.setRut(rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelefonoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    public ArrayList<Correo> listar(String tabla) {
        ArrayList<Correo> lista = new ArrayList<Correo>();
        Correo t = null;
        String q ="Select * from correo_"+tabla;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                t = new Correo();
                t.setCorreo(rs.getString(1));
                t.setRut(rs.getInt(2));
                lista.add(t);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(TelefonoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
