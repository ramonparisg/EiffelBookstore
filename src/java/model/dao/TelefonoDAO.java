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
import model.dto.Telefono;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class TelefonoDAO {
    Conexion c;

    public TelefonoDAO() {
        c = new Conexion();
    }
    
    public int insetar(Telefono t, String tabla){
        int res=0;
        String q ="Insert into telefono_"+tabla+" values("+t.getNro()+","+t.getRut()+")";
        res = c.ejecutarSQL(q);        
        return res;
    }
    public int modificar(Telefono t, String tabla){
        int res=0;
        String q ="update telefono_"+tabla+" set "                
                + ",rut="+t.getRut()+""
                + " where nro="+t.getNro();
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public int eliminar(int id, String tabla){
        int res =0;
        String q ="Delete from telefono_"+tabla+" where nro="+id;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Telefono buscar(int id, String tabla) {
        Telefono t = null;
        String q ="Select * from telefono_"+tabla+" where nro="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                t = new Telefono();
                t.setNro(rs.getInt(1));
                t.setRut(rs.getInt(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelefonoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    
    public ArrayList<Telefono> listar(String tabla) {
        ArrayList<Telefono> lista = new ArrayList<Telefono>();
        Telefono t = null;
        String q ="Select * from telefono_"+tabla;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                t = new Telefono();
                t.setNro(rs.getInt(1));
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
