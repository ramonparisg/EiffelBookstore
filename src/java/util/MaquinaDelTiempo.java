/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ramon Paris
 */
public class MaquinaDelTiempo {
    public Date sumarRestarDiasFecha(Date fecha, int dias){
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(fecha); // Configuramos la fecha que se recibe
	calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
	return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }
}
