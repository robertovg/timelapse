package timeLapse.util.exceptions;

import timeLapse.util.constantes.ConstantesException;

public interface IFactoriaMensaje {

	/**
	 * Devuelve el mensaje asociado a la constante que se le pasa como parámetro. Si no existe,
	 * eleva un MensajeModel en el que se especifica que la factoría no encontró ese código. Adminte 
	 * información adicional.
	 * @param key
	 * @param infoAdicc
	 * @return
	 */
	public abstract Mensaje getMensaje(ConstantesException key, String infoAdicc);

	/**
	 * Devuelve el mensaje asociado a la constante que se le pasa como parámetro. Si no existe,
	 * eleva un Mensaje en el que se especifica que la factoría no encontró ese código. 
	 * @param key
	 * @return
	 */

	public abstract Mensaje getMensaje(ConstantesException key);

}