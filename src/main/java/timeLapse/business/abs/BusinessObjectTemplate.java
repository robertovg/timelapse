package timeLapse.business.abs;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.support.ResourceBundleMessageSource;

import timeLapse.controller.interceptors.i18n.BibliotecaInternacional;
import timeLapse.controller.interceptors.i18n.IBibliotecaInternacional;
import timeLapse.util.constantes.Globales;
import timeLapse.util.exceptions.FactoriaMensaje;
import timeLapse.util.exceptions.IFactoriaMensaje;

/**
 * 
 * @author robe
 * Clase padre de todas las clases de negocio
 */
public abstract class BusinessObjectTemplate implements Globales{
	
	@Resource
	protected IFactoriaMensaje factoriaMens;
	
	@Resource
	protected IBibliotecaInternacional bibliotecaI18n;
    
	/**
	 * Método para "inyectar" la bibliotecaI18n y factoriaMensajes a mano. Utilizar este
	 * método para cuando se quieren crear los objetos sin utilizar Spring.
	 *  
	 * @param localidad
	 */
	public void completaAtributosSinFactoria(Locale localidad){
		BibliotecaInternacional bibliotecaI18n = new BibliotecaInternacional();
		bibliotecaI18n.setLocale(localidad);
		ResourceBundleMessageSource messages = new ResourceBundleMessageSource();
		String [] basenames = {"messages", "exceptions"};
		messages.setBasenames(basenames);
		bibliotecaI18n.setMessages(messages);
		FactoriaMensaje factoriaMens = new FactoriaMensaje();
		factoriaMens.setBibliotecaI18n(bibliotecaI18n);

		//Hago lo que siempre hace Spring, inyectar los objetos de la seguridad.
		this.bibliotecaI18n = bibliotecaI18n;
		this.factoriaMens = factoriaMens;
		
	}
    
	
	

}
