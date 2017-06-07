/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.Venta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.compra.FacturaDAO;
import model.dto.FacturaBoleta;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class BoletaDAO {
    Conexion c;

    public BoletaDAO() {
        c = new Conexion();
    }
    
    public int insertar(FacturaBoleta f){
        int res =0;
        String q = "Insert into boleta(f_venta,precio_iva,iva,precio_neto,metodo_pago) "
                + "values ('"+f.getFechaCompra()+"',"
                + f.getPrecioIva()+","
                + f.getIva()+","
                + f.getPrecioNeto()+","
                + f.getMetodoPago()+""
                + ")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(FacturaBoleta f){
        int res =0;
        String q ="update boleta set"
                + "f_venta='"+f.getFechaCompra()
                + "',precio_iva="+f.getPrecioIva()
                + ",iva="+f.getIva()
                + ",precio_neto="+f.getPrecioNeto()
                + ",metodo_pago="+f.getMetodoPago()
                + " where folio="+f.getFolio();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id){
        int res =0;
        String q ="Delete from boleta where folio="+id;
        res = c.ejecutarSQL(q);        
        return res;
    }
    
    public FacturaBoleta buscar(int id){
        FacturaBoleta f = null;
        String q = "Select * from boleta where folio="+id;        
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                f = new FacturaBoleta();
                f.setFolio(rs.getInt(1));
                f.setFechaCompra(rs.getString(2));
                f.setPrecioIva(rs.getInt(3));
                f.setIva(rs.getInt(4));
                f.setPrecioNeto(rs.getInt(5));
                f.setMetodoPago(rs.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return f;
    }
    
    public ArrayList<FacturaBoleta> listar(){
        ArrayList<FacturaBoleta> lista = new ArrayList<FacturaBoleta>();
        FacturaBoleta f = null;
        String q = "Select * from boleta";        
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                f = new FacturaBoleta();
                f.setFolio(rs.getInt(1));
                f.setFechaCompra(rs.getString(2));
                f.setPrecioIva(rs.getInt(3));
                f.setIva(rs.getInt(4));
                f.setPrecioNeto(rs.getInt(5));
                f.setMetodoPago(rs.getInt(6));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }                
        return null;
    }
}
