/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.compra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.compra.Distribuidor;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class DistribuidorDAO {
    Conexion c;

    public DistribuidorDAO() {
        c = new Conexion();
    }
    
    public int insertar(Distribuidor d){
        int res =0;
        String q = "insert into distribuidor(rut,nombre,direccion,telefono,anyos_venta) "
                + "values(" +d.getRut()
                + ",'"+d.getNombre()+"'"
                + ",'"+d.getDireccion()+"'"
                + ",'"+d.getTelefono()+"'"
                + ","+d.getAnyo()+")";        
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Distribuidor d){
        int res =0;
        String q = "update distribuidor nombre='"+d.getNombre()
                + "',direccion='"+d.getDireccion()
                + "',telefono='"+d.getTelefono()
                + "',anyos_venta="+d.getAnyo()
                + " where rut="+d.getRut();        
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id){
        int res =0;
        String q = "Delete from distribuidor where rut="+id;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Distribuidor buscar(int id){
        Distribuidor d = null;
        String q = "Select * from distribuidor where rut="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                d = new Distribuidor();
                d.setRut(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setDireccion(rs.getString(3));
                d.setTelefono(rs.getString(4));            
                d.setAnyo(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DistribuidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public ArrayList<Distribuidor> listar(){
        Distribuidor d = null;
        ArrayList<Distribuidor> lista = new ArrayList<Distribuidor>();
        String q = "Select * from distribuidor";
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                d = new Distribuidor();
                d.setRut(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setDireccion(rs.getString(3));
                d.setTelefono(rs.getString(4));            
                d.setAnyo(rs.getInt(5));
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DistribuidorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
