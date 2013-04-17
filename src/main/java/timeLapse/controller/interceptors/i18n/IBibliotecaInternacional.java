package timeLapse.controller.interceptors.i18n;

import java.util.Locale;

public interface IBibliotecaInternacional {

	
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale);
	
	/**
	 * Devuelve el mensage asociado a la key pasada como parámetro dependiendo del Locale
	 * @param key
	 * @return
	 */
	public String traduce(String key);
}
