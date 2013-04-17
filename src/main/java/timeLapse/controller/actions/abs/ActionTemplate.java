package timeLapse.controller.actions.abs;

import javax.annotation.Resource;

import timeLapse.controller.interceptors.i18n.IBibliotecaInternacional;
import timeLapse.util.constantes.Globales;
import timeLapse.util.exceptions.IFactoriaMensaje;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase abstracta de la que eredan todos los actions
 * @author robe
 *
 */
@SuppressWarnings("serial")
public abstract class ActionTemplate extends ActionSupport implements Globales {
	@Resource
	protected IFactoriaMensaje factoriaMens;
	
	@Resource
	protected IBibliotecaInternacional biblioteca;
	
	protected String acciones = new String();
	
	public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
	
	public  String getAcciones(){
		return this.acciones;
	}
	
	protected String mensajeRespuesta;

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}


}
