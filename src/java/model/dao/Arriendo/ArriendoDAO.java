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
import model.dto.arriendo.Arriendo;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class ArriendoDAO {
    Conexion c;

    public ArriendoDAO() {
        c = new Conexion();
    }
    
    public int insertar(Arriendo a){
        int res = 0;
        String q="insert into arriendo values(0,"+a.getRutCliente()+","+a.getRutTrabajador()+")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Arriendo a){
        int res =0;
        String q ="";        
        return res;
    }
    
    public Arriendo buscar(int id){
        Arriendo a = null;
        String q = "Select * from arriendo where id_arriendo="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                a = new Arriendo();
                a.setId(rs.getInt(1));
                a.setRutCliente(rs.getInt(2));
                a.setRutTrabajador(rs.getInt(3));               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArriendoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public ArrayList<Arriendo> listar(){
        Arriendo a = null;
        String q = "Select * from arriendo";
        ArrayList<Arriendo> lista = new ArrayList<Arriendo>();
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                a = new Arriendo();
                a.setId(rs.getInt(1));
                a.setRutCliente(rs.getInt(2));
                a.setRutTrabajador(rs.getInt(3));  
                lista.add(a);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ArriendoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
