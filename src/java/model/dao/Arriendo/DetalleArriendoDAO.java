/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.Arriendo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.arriendo.DetalleArriendo;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class DetalleArriendoDAO {
    Conexion c;

    public DetalleArriendoDAO() {
        c = new Conexion();
    }
    
    public int insertar(DetalleArriendo d){
        int res =0;
        String q = "insert into detalle_arriendo values("+d.getNroSerie()+","
                + ""+d.getIdArriendo()+","
                + "'"+d.getfDevEsti()+"',"
                + "'"+d.getfDevReal()+"',"
                + "'"+d.getfArriendo()+"',"
                + ""+d.getSubtotal()+","
                + ""+d.getMulta()+","
                + ""+d.getTotal()+ ")";
        c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(DetalleArriendo d){
        int res =0;
        String q = "update detalle_arriendo set "                            
                + "f_retorno_real='"+d.getfDevReal()+"',"                
                + "multa="+d.getMulta()+","
                + "costo_total="+d.getTotal()+ " where "
                + "id_arriendo="+d.getIdArriendo()+" and "
                + "nro_serie="+d.getNroSerie()+";";
        res =c.ejecutarSQL(q);
        return res;
    }
    
    public DetalleArriendo buscar(int id, int nro){
        DetalleArriendo d = null;
        String q = "Select * from detalle_arriendo where nro_serie="+nro+" and id_arriendo="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while(rs.next()){
                d = new DetalleArriendo();
                d.setNroSerie(rs.getInt(1));
                d.setIdArriendo(rs.getInt(2));
                d.setfDevEsti(rs.getString(3));
                d.setfDevReal(rs.getString(4));
                d.setfArriendo(rs.getString(5));
                d.setSubtotal(rs.getInt(6));
                d.setMulta(rs.getInt(7));
                d.setTotal(rs.getInt(8));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleArriendoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return d;
    }
    public ArrayList<DetalleArriendo> listar(){
        DetalleArriendo d = null;
        ArrayList<DetalleArriendo> lista = new ArrayList<DetalleArriendo>();
        String q = "Select * from detalle_arriendo";
        ResultSet rs = c.leerDatos(q);
        try {
            while(rs.next()){
                d = new DetalleArriendo();
                d.setNroSerie(rs.getInt(1));
                d.setIdArriendo(rs.getInt(2));
                d.setfDevEsti(rs.getString(3));
                d.setfDevReal(rs.getString(4));
                d.setfArriendo(rs.getString(5));
                d.setSubtotal(rs.getInt(6));
                d.setMulta(rs.getInt(7));
                d.setTotal(rs.getInt(8));                
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleArriendoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;
    }
    
    public ArrayList<DetalleArriendo> listarArriendo(int id){
        DetalleArriendo d = null;
        ArrayList<DetalleArriendo> lista = new ArrayList<DetalleArriendo>();
        String q = "Select * from detalle_arriendo where id_arriendo="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while(rs.next()){
                d = new DetalleArriendo();
                d.setNroSerie(rs.getInt(1));
                d.setIdArriendo(rs.getInt(2));
                d.setfDevEsti(rs.getString(3));
                d.setfDevReal(rs.getString(4));
                d.setfArriendo(rs.getString(5));
                d.setSubtotal(rs.getInt(6));
                d.setMulta(rs.getInt(7));
                d.setTotal(rs.getInt(8));                
                lista.add(d);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleArriendoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return null;
    }
    
    
}
