package timeLapse.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import timeLapse.resources.persistenceDTO.TldPeriodicidad;
import timeLapse.util.exceptions.TimeLapseException;

/**
 * 
 * @author robe
 *
 */
public class Utiles {
	
	/**
	 * Función que se encarga de codificar el mensaje pasado como parámetro a MD5
	 * @param texto
	 * @return 
	 */
	public static String codificaMD5(String texto){
		MessageDigest algorithm = null;
		String resul = null;
		if(texto != null){
			try{
				algorithm = MessageDigest.getInstance("MD5");
				algorithm.reset();
				algorithm.update(texto.getBytes());
				byte[] digest = algorithm.digest();
				
				resul = new String(digest);			
	
			}catch (NoSuchAlgorithmException e) {
			
			}
		}
		return resul;
	}
	
	
	/**
	 * Función que devuelve la representación "dd/MM/yyyy HH:mm" de la fecha pasada como parámetro
	 * @param fecha
	 * @return
	 */
	public static String dateTimeToString(Date fecha){
		
		return dateToString(fecha, "dd/MM/yyyy HH:mm");
		
	}
	/**
	 * Función que devuelve la representación "HH:mm" de la fecha pasada como parámetro
	 * @param fecha
	 * @return
	 */
	public static String timeToString(Date fecha){
		
		return dateToString(fecha, "HH:mm");
		
	}
	/**
	 * Función que devuelve la representación "dd/MM/yyyy" de la fecha pasada como parámetro
	 * @param fecha
	 * @return
	 */
	public static String dateToString(Date fecha){
		
		return dateToString(fecha, "dd/MM/yyyy");
		
	}
	
	
	/**
	 * 											
	 * Transforma una cadena con formato: "yyyy-MM-dd'T'HH:mm:ssZ" a un Date.
	 * @param cadFecha
	 * @return
	 */
	public static String dateToISO8601(Date cadFecha){
		return dateToString(cadFecha, "yyyy-MM-dd'T'HH:mm:ssZ");
	}
	/**
	 * Genera la periodicidad pasada como parámetro a una cadena
	 * @param periodicidad
	 * @return
	 */
	public static String periodicidadToString(TldPeriodicidad periodicidad){
		String resul = null;
		
		if(periodicidad != null){
			
			resul += "cada " + (!periodicidad.getCada().equals(new Integer(1)) ? periodicidad.getCada() : "") + " " + periodicidad.getTlpUnidadRepeticion().getNombre() + "(s)" ;
			resul +="( " + dateToString(periodicidad.getFechaInicio()) + " - " + dateToString(periodicidad.getFechaFin()) + " )";
		}
		return resul;	
		
	}
	/**
	 * Transforma una cadena con formato "dd/MM/yyyy" a un Date.
	 * @param cadFecha
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDate(String cadFecha) throws TimeLapseException, ParseException{
		
		return stringToDate(cadFecha, "dd/MM/yyyy");
		
	}
	/**
	 * Transforma una cadena con formato "HH:mm:" a un Date.
	 * @param fecha
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToTime(String cadTime) throws TimeLapseException, ParseException{
		
		return stringToDate(cadTime, "HH:mm");
	}

	
	
	/**
	 * Función para convertir un Date a String según el formato pasado como parámetro 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	private static String dateToString(Date fecha, String formato){
		
		String resul = null;
		
		if(fecha != null && formato != null){
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			resul  = sdf.format(fecha);			
		}
		return resul;	
	}

	private static Date stringToDate(String cadFecha, String formato) throws TimeLapseException, ParseException{
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
		
		Date resul = null;
		if(!StringUtils.isBlank(cadFecha) && formato != null){
			
			resul = formatoDelTexto.parse(cadFecha);
				
			
			
			
		}
		return resul;
	}
	public static boolean sonIguales(Date hora1, Date hora2){
		boolean resul = false;
		if(hora1 == null && hora2 == null){
			resul = true;
		}else if(hora1 == null && hora2 != null){
			resul = false;
		}else if(hora1 != null && hora2 == null){
			resul = false;
		}else if(hora1 != null && hora2 != null){
			
			resul = hora1.equals(hora2);
			
		}
			
		
		
		return resul;
	}
	
	 /**
     * Convierte la cadena recibida como parámetro en un Lista
     */
    @SuppressWarnings("unchecked")
	public static List<String> stringToList(String cadeana) {
        if (!StringUtils.isBlank(cadeana)) {
            String[] list = cadeana.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        } else {
            return Collections.EMPTY_LIST;
        }
    }    


}
