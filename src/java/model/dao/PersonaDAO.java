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
import model.dto.Persona;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class PersonaDAO {
    Conexion c;

    public PersonaDAO() {
        c = new Conexion();
    }
    
    public int insertar(Persona p, String tabla){
        int res =0;
        String q = "insert into "+tabla+" values("
                + p.getRut()+",'"
                + p.getNombre()+"','"
                + p.getApePat()+"','"
                + p.getApeMat()+"','"
                + p.getFechaNac()+"')";
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public int modificar(Persona p, String tabla){
        int res =0;
        String q = "update "+tabla+" set "                
                + "nombre='" +p.getNombre()+"',"
                + "ape_pat='" +p.getApePat()+"',"
                + "ape_mat='" +p.getApeMat()+"',"
                + "f_nac='" +p.getFechaNac()+"'"
                + " where rut=" + p.getRut();
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public int eliminar(int id,String tabla){
        int res =0;
        String q = "delete from "+tabla+" where rut="+id;
        res = c.ejecutarSQL(q);       
        return res;
    }
    
    public Persona buscar(int id,String tabla){
        Persona p = null;
        String q = "Select * from "+tabla+" where rut="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                p = new Persona();
                p.setRut(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApePat(rs.getString(3));
                p.setApeMat(rs.getString(4));
                p.setFechaNac(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return p;
    }
    
    public ArrayList<Persona> listar(String tabla){
        Persona p = null;
        ArrayList<Persona> lista = new ArrayList<Persona>();
        String q = "Select * from "+tabla;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                p = new Persona();
                p.setRut(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApePat(rs.getString(3));
                p.setApeMat(rs.getString(4));
                p.setFechaNac(rs.getString(5));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return null;
    }
    
}
