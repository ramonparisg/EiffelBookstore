/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raparisg
 */
public class Conexion {
    private String url="jdbc:mysql://localhost:3306/biblioteca";
    private String login="root";
    private String pass="";
    private Connection con;
    private Statement stm;

    public Conexion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,login,pass);
            stm = con.createStatement();
            Logger.getLogger(Conexion.class.getName()).log(Level.INFO, "Conexión exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error: "+ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error:" + ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error: "+ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error: "+ ex);
        }

    }    
    
    public void desconecta(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int ejecutarSQL(String q){
        int res=0;        
        try {
            res = stm.executeUpdate(q);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error: "+ ex + " En sentencia: "+ q);
        }        
        return res;
    }
    
    public ResultSet leerDatos(String q){
        ResultSet rs=null;        
        try {            
            rs = stm.executeQuery(q);            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error: "+ ex + " En sentencia: "+ q);
        }                      
        return rs;        
    }
}
