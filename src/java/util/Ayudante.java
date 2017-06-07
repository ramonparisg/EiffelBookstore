/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


public class Ayudante {
    public static String getAccion(String ruta){
    int index = ruta.lastIndexOf('/');
    String accion = ruta.substring(index+1, ruta.length());
    return accion;
    }
}
