package timeLapse.util.exceptions;

import timeLapse.util.constantes.Severidad;


/**
 * Clase que engloba los datos que representan de los mensajes que la aplicación utiliza para comunicar
 * errores de ejecución, información
 * @author robe
 *
 */
@SuppressWarnings("serial")
public class Mensaje implements java.io.Serializable {
	private static final String preInfoAdiccional =  " ( ";
	private static final String postInfoAdiccional = " )";
	
	private final String codigo;
	private final String mensaje;
	private final Exception excepcionOriginal;
	private final Severidad severidad;

	Mensaje(String codigo, String mensaje, String infoAdicional, Exception excepcionOriginal, Severidad severidad) {
		this.codigo = codigo;
		if (infoAdicional != null){
			this.mensaje = mensaje + preInfoAdiccional + infoAdicional + postInfoAdiccional; 
		}else{
			this.mensaje = mensaje;
		}
		this.excepcionOriginal = excepcionOriginal;
		this.severidad = severidad;
		
		
	}
	
	Mensaje(String codigo, String mensaje, String infoAdicional, Severidad severidad) {
		this(codigo, mensaje, infoAdicional, null, severidad);
		
	}

	Mensaje(String codigo, String mensaje, Severidad severidad) {
		this(codigo, mensaje, null, severidad);
	}

	
	/**
	 *  Devuelve el campo mensaje seguido de una información adicional si lista existe.
	 */
	public String getMensaje() {
		
		return this.mensaje;
		
	}

	/**
	 * Devuelve el campo codigo 
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Devuelve la excepción original que provocó el error.
	 * @return
	 */
	public Exception getExcepcionOriginal() {
		return excepcionOriginal;
	}

	/**
	 * @return the severidad
	 */
	public Severidad getSeveridad() {
		return severidad;
	}
	
	@Override
	public String toString(){
		String cad = "";
		
		cad += getCodigo() + " (" + getMensaje() + ")";
		
		return cad;
	}
	
}
