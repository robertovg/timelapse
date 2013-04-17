package timeLapse.util.exceptions;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.controller.interceptors.i18n.IBibliotecaInternacional;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.constantes.Severidad;



/**
 * Factoría utilizada para generar los mensajes.
 * @author robe
 *
 */
@Service
@Scope("session")
public class FactoriaMensaje implements IFactoriaMensaje {

	
	private IBibliotecaInternacional bibliotecaI18n;
	
	
	public FactoriaMensaje(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see timeLapse.util.exceptions.IFactoriaMensaje#getMensaje(timeLapse.util.constantes.ConstantesException, java.lang.String)
	 */
	public Mensaje getMensaje(ConstantesException key, String infoAdicc){
		Mensaje men = null;
		if(key == null){
			men = getMensaje(ConstantesException.usoErroneoFactoriaMensajes);
		}else{
			String prefijo = null;
			if(key.getSeveridad().equals(Severidad.error)){
				prefijo = "ERR-";
			}else if(key.getSeveridad().equals(Severidad.info)){
				prefijo = "INF-";
			}
			String mensage = bibliotecaI18n.traduce(key.getTexto());
			men = new Mensaje(prefijo + key.ordinal(), mensage, infoAdicc, key.getSeveridad());
			
			
		}
		
		
		return men;
	}
	
	/* (non-Javadoc)
	 * @see timeLapse.util.exceptions.IFactoriaMensaje#getMensaje(timeLapse.util.constantes.ConstantesException)
	 */	
	public Mensaje getMensaje(ConstantesException key){
		return this.getMensaje(key, null);
	}
	
	/**
	 * @param bibliotecaI18n the bibliotecaI18n to set
	 */
	@Resource
	public void setBibliotecaI18n(IBibliotecaInternacional bibliotecaI18n) {
		this.bibliotecaI18n = bibliotecaI18n;
	}


}
