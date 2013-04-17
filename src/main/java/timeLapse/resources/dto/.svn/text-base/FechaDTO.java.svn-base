package timeLapse.resources.dto;

import java.util.Date;
import timeLapse.util.Utiles;
/**
 * Contenedor para las fechas cuando se devuelven datos a dojo
 * @author robe
 *
 */
public class FechaDTO {
	private String _type;
	private String _value;
	
	public FechaDTO(Date fecha){
		_type = "Date";
		
			
		_value = Utiles.dateTimeToString(fecha);
		
		
	}
	/**
	 * Crea la fecha DTO a partir de un Date
	 * @param fecha
	 * @param hora
	 */
	public FechaDTO(Date fecha, String hora){
		_type = "Date";
		if(hora == null){
			_value = Utiles.dateToString(fecha);
		}else{
			_value = Utiles.dateTimeToString(fecha);
		}
		
	}
	public String get_type() {
		return _type;
	}

	public String get_value() {
		return _value;
	}
	public void set_value(String value) {
		_value = value;
	}
	

}
