/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.compra.FacturaDAO;

/**
 *
 * @author Ramon Paris
 */
public class CurrentID {
    Conexion c;

    public CurrentID() {
        c = new Conexion();
    }
    
     public int currentId(String tabla){
         
        int id = -1;
        String q = "SELECT AUTO_INCREMENT\n" +
                    "FROM information_schema.TABLES\n" +
                    "WHERE TABLE_SCHEMA = \"biblioteca\"\n" +
                    "AND TABLE_NAME = '"+tabla+"';";
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                id = rs.getInt(1);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (id-1);
    }
}
