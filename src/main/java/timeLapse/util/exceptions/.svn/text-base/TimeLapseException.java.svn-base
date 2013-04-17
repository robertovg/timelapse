package timeLapse.util.exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import timeLapse.util.constantes.Severidad;


/**
 * Excepción genérica utilizada en timeLapse para comunicar un conjunto de 
 * mensajes
 * @author robe
 *
 */
@SuppressWarnings("serial")
public class TimeLapseException extends Exception {
	
	private List<Mensaje> infos;
	private List<Mensaje> errores;
	
	
	/**
	 * Constructor vacío, solo prepara la clase para poder utilizarla.
	 */
	public TimeLapseException(){
		super();
		this.infos = new ArrayList<Mensaje>();
		this.errores = new ArrayList<Mensaje>();
	}
	/**
	 * Constructor que recibe un MensajeModel
	 * @param men
	 */
	public TimeLapseException(Mensaje men){
		this();
		addMensaje(men);
	}
	/**
	 * Constructor que  recibe otra TimeLapseException
	 * @param te
	 */
	public TimeLapseException(TimeLapseException te){
		this();
		addMensajes(te);
	}
	
	
	
	
	/**
	 * 
	 * Recibe la constante del mensaje Model para buscarla en la factoríaMensajes pero además da como
	 * opción añadirle información personalizada a cada mensaje.
	 * @param key
	 * @param infoAdicional Cadena que describe información para añadir al mensaje
	 */
	public void addMensaje(Mensaje mensaje){
		
		if(mensaje.getSeveridad().equals(Severidad.info)){
			this.infos.add(mensaje);
		}else if(mensaje.getSeveridad().equals(Severidad.error)){
			this.errores.add(mensaje);			
		}
	}
	
	/**
	 * Metodo para añadir todos los mensajes de otra RecetaException 
	 * @param mensajes Objeto RecetaException a añadir
	 */
	public void addMensajes(TimeLapseException mensajes){
		Iterator<Mensaje> itInfos = mensajes.getIteratorInfos();
		while(itInfos.hasNext()){
			this.addMensaje(itInfos.next());
		}
		Iterator<Mensaje> itErrores = mensajes.getIteratorErrores();
		while(itErrores.hasNext()){
			this.addMensaje(itErrores.next());
		}
		
		
	}
	
	/**
	 * Método que devuelve un Iterador sobre la lista existente de MensajesModel
	 * @return
	 */
	public Iterator<Mensaje> getIteratorInfos(){
		return this.infos.iterator();
		
	}
	
	/**
	 * Método que devuelve un Iterador sobre la lista existente de MensajesModel
	 * @return
	 */
	public Iterator<Mensaje> getIteratorErrores(){
		return this.errores.iterator();
		
	}
	
	/**
	 * Método para comprobar si contiene algun mensaje la clase
	 * @return
	 */
	public boolean isEmpty(){
		return ((this.infos.isEmpty()) && (this.errores.isEmpty()));
	}
	/**
	 * Método que devuelve el número de infos que alverga esta TimeLapseException
	 * @return
	 */
	public int sizeInfos(){
		return this.infos.size();
	}
	/**
	 * Método que devuelve el número de errores que alverga esta TimeLapseException
	 * @return
	 */
	public int sizeErrores(){
		return this.errores.size();
		
	}
	/**
	 *  Devuelve el campo mensajes
	 */
	public List<Mensaje> getInfos() {
		return infos;
	}

	/**	
	 * Actualiza el campo mensajes con el valor recibido.
	 *
	 * @param mensajes Nuevo valor para el atributo mensajes
	 */
	public void setInfos(List<Mensaje> mensajes) {
		this.infos = mensajes;
	}
	
	/**
	 * @return the errores
	 */
	public List<Mensaje> getErrores() {
		return errores;
	}

	/**
	 * @param errores the errores to set
	 */
	public void setErrores(List<Mensaje> errores) {
		this.errores = errores;
	}
	

	
	/**
	 * Se sobrescribe el método
	 */
	@Override
	public String toString(){
		String cad = "";
		Iterator<Mensaje> it = getIteratorInfos();
		int i = 1;
		while(it.hasNext()){
			
			Mensaje objMen = (Mensaje)it.next();
			cad += "Mensaje - " + i + ": " + objMen + "\n";			
			
		}
		return cad;
			
	}

}
