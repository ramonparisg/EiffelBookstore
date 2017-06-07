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
import model.dto.Autor;
import util.Conexion;

/**
 *
 * @author raparisg
 */
public class AutorDAO {
    Conexion c;

    public AutorDAO() {
        c = new Conexion();
    }
    
    public int insertar(Autor a){
        int res =0;
        String q = "Insert into autor(nombre,ape_pat,ape_mat) values('"+a.getNombre()+"','"+a.getApePat()+"','"+a.getApeMat()+"')";                
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int modificar(Autor a){
        int res =0;
        String q = "update autor set "
                + "nombre='"+a.getNombre()+""
                + "',ape_pat='"+a.getApePat()+""
                + "',ape_mat='"+a.getApeMat()+"'"
                + " where id_autor="+a.getIdAutor();
        System.out.println(q);
        c = new Conexion();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public int eliminar(int id){
        int res =0;
        String q = "delete from autor where id_autor="+id;
        System.out.println(q);
        c = new Conexion();
        res = c.ejecutarSQL(q);
        return res;
    }
    
    public Autor buscar(int id){
        
        String q = "Select * from autor where id_autor="+id;
        c = new Conexion();        
            ResultSet rs=c.leerDatos(q);
            try {
                Autor a=new Autor();
                while (rs.next()){
                    a.setIdAutor(id);
                    a.setNombre(rs.getString("nombre"));
                    a.setApePat(rs.getString("ape_pat"));
                    a.setApeMat(rs.getString("ape_mat"));
                }
                
                c.desconecta();
                return a;
            } catch (SQLException ex) {
                Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
                        
        return null;
    }
    
    public ArrayList<Autor> listar(){
        ArrayList<Autor> lista = new ArrayList<Autor>();
        ResultSet rs;
        String q = "Select * from autor";
        c = new Conexion();
        
            rs = c.leerDatos(q);
            try {
                while (rs.next()){
                    lista.add(new Autor(rs.getInt("id_autor"),
                                        rs.getString("nombre"),
                                        rs.getString("ape_pat"),
                                        rs.getString("ape_mat")));                    
                }
                c.desconecta();
            } catch (SQLException ex) {
                Logger.getLogger(AutorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }        
        return lista;
    }
}
