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
import model.dto.compra.DetalleCompra;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class DetalleCompraDAO {
    Conexion c;
    public DetalleCompraDAO() {
        c = new Conexion();
    }
    
    
    public ArrayList<DetalleCompra> listar(int id){
        ArrayList<DetalleCompra> lista = new ArrayList<DetalleCompra>();
        DetalleCompra dc;
        String q = "select \n" +
                    "t.isbn,t.nombre,\n" +
                    "i.desc_idioma,i.id_idioma,\n" +
                    "count(l.nro_serie),t.precio_referencia\n" +
                    "from libro_compra lc, libro l, titulo t, libro_idioma li, idioma i\n" +
                    "where lc.id_compra = "+id+"\n" +
                    "and lc.nro_serie = l.nro_serie\n" +
                    "and l.isbn = t.isbn\n" +
                    "and l.nro_serie = li.nro_serie\n" +
                    "and li.id_idioma = i.id_idioma\n" +
                    "group by l.isbn;";
        ResultSet rs=c.leerDatos(q);
        try {
            while (rs.next()){
                dc = new DetalleCompra();
                dc.setIsbn(rs.getString(1));
                dc.setTitulo(rs.getString(2));
                dc.setIdioma(rs.getString(3));
                dc.setIdIdioma(rs.getInt(4));
                dc.setCantidad(rs.getInt(5));
                dc.setPrecio(rs.getInt(6));
                lista.add(dc);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DetalleCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
