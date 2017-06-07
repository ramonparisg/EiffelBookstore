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
import model.dto.venta.Venta;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class VentaDAO {
    Conexion c;

    public VentaDAO() {
        c= new Conexion();
    }
    
    public int insertar(Venta v){
        int res =0;
        String q = "insert into venta(nro_serie,folio,rut_trabajador,rut_cliente) "
                + "values(" +v.getNro_serie()
                + ","+v.getBoleta()+""
                + ","+v.getRutTrabajador()+ ""
                + ","+v.getRutCliente()+""
                + ")";
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Venta v){
        int res =0;
        String q = "update venta set nro_serie="+v.getNro_serie()
                + ",folio="+v.getBoleta()
                + ",rut_trabajador="+v.getRutTrabajador()
                + ",rut_cliente="+v.getRutCliente()
                + " where id_venta="+v.getId();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id){
        int res =0;
        String q="delete from venta where id_venta="+id;
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Venta buscar(int id){
        Venta v = null;
        String q = "Select * from distribuidor where rut="+id;
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                v = new Venta();
                v.setId(rs.getInt(1));
                v.setNro_serie(rs.getInt(2));
                v.setBoleta(rs.getInt(3));
                v.setRutTrabajador(rs.getInt(4));
                v.setRutCliente(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public ArrayList<Venta> listar(int id){
        ArrayList<Venta> lista = new ArrayList<Venta>();
        Venta v = null;
        String q = "Select * from distribuidor";
        ResultSet rs = c.leerDatos(q);
        try {
            while (rs.next()){
                v = new Venta();
                v.setId(rs.getInt(1));
                v.setNro_serie(rs.getInt(2));
                v.setBoleta(rs.getInt(3));
                v.setRutTrabajador(rs.getInt(4));
                v.setRutCliente(rs.getInt(5));
                lista.add(v);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
