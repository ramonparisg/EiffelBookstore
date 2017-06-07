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
import model.dto.compra.Compra;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class CompraDAO {
    Conexion c;
    
    public CompraDAO(){
        c = new Conexion();
    }
    
    public int insertar(Compra com){
        int res=0;
        String q = "insert into compra(nro_serie,rut_distribuidor,folio) values("+com.getNroSerie()+","+com.getRutDistribuidor()+","+com.getFolio()+")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Compra com){
        int res=0;
        String q = "update compra set nro_serie="+com.getNroSerie()+",rut_distribuidor="+com.getRutDistribuidor()+",folio="+com.getFolio() +
                " where id_compra="+com.getId();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id){
        int res=0;
        String q = "delete from compra where id_compra="+id;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Compra buscar(int id){
        Compra com = null;
        String q = "Select * from compra where id_compra="+id;
        ResultSet rs = c.leerDatos(q);        
        try {
            while(rs.next()){
                com = new Compra();
                com.setId(rs.getInt(1));
                com.setNroSerie(rs.getInt(2));
                com.setRutDistribuidor(rs.getInt(3));
                com.setFolio(rs.getInt(4));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return com;
    }
    
    public ArrayList<Compra> listar(){
        ArrayList<Compra> lista = new ArrayList<Compra>();
        String q = "Select * from compra";
        ResultSet rs = c.leerDatos(q);
        Compra com = null;
        try {
            while(rs.next()){
                com = new Compra();
                com.setId(rs.getInt(1));
                com.setNroSerie(rs.getInt(2));
                com.setRutDistribuidor(rs.getInt(3));
                com.setFolio(rs.getInt(4));  
                lista.add(com);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return null;
    }
}
