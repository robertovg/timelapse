package timeLapse.controller.interceptors.i18n;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 * @author robe
 * DTO que contendrá el Locale del hilo que se esté ejecutando, junto con el bean que contiene los 
 * mensajes.
 *
 */
@Service
@Scope("session")
public class BibliotecaInternacional implements IBibliotecaInternacional{
	
	private MessageSource messages;
	
	private Locale locale;
	
	public BibliotecaInternacional(){
		super();
	}

	/**
	 * @param messages the messages to set
	 */
    @Resource
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public String traduce(String key){
		String resul = null;
		
		if(locale == null){
			locale = Locale.getDefault();			
		}
		
		if(key != null){
			try{
				resul = messages.getMessage(key, null, this.locale);
			}catch(NoSuchMessageException ex){
				resul = key;
			}
			
		}
		
		return resul;		
	}

}
