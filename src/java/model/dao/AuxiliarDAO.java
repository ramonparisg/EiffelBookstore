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
import model.dto.Auxiliar;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class AuxiliarDAO {
    Conexion c;

    public AuxiliarDAO() {
        c = new Conexion();
    }
    
    public int agregar(Auxiliar a, String tabla){
        int res =0;
        String q = "Insert into "+tabla+"(desc_"+tabla+") values('"+a.getDetalle()+"');";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Auxiliar a, String tabla){
        int res =0;
        String q = "Update "+tabla+" set desc_"+tabla+"='"+a.getDetalle()+"' where id_"+tabla+"="+a.getId();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id, String tabla){
        int res =0;
        String q = "Delete from "+tabla+" where id_"+tabla+"="+id;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Auxiliar buscar(int id,String tabla){
        Auxiliar a=null;
        ResultSet rs;
        String q = "Select * from "+tabla+" where id_"+tabla+"="+id;
        
        try {
            rs = c.leerDatos(q);
            while (rs.next()){
                a = new Auxiliar();
                a.setId(rs.getInt(1));
                a.setDetalle(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuxiliarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public ArrayList<Auxiliar> listar(String tabla){
        ArrayList<Auxiliar> lista = new ArrayList<Auxiliar>();
        Auxiliar a;
        ResultSet rs;
        String q = "Select * from "+tabla;
        
        try {
            rs = c.leerDatos(q);
            while (rs.next()){
                a = new Auxiliar();
                a.setId(rs.getInt(1));
                a.setDetalle(rs.getString(2));
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(AuxiliarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
