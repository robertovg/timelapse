package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IObjetivosBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpObjetivo;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("eliminaObjetivosAction")
@Scope("request")
public class EliminaObjetivosAction extends ActionTemplate {
	@Resource
	IObjetivosBO neg;

	private List<String> listID;

	@Override
	public String execute() throws Exception {

		
		List<TlpObjetivo> objElim = new ArrayList<TlpObjetivo>();
		List<String> identificadoresObjetivosNoEliminados = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		Iterator<String> it = listID.iterator();
		while (it.hasNext()) {
			
			String objetivoIterado = it.next();
			try{				
				objElim.add(neg.elimina(objetivoIterado));
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresObjetivosNoEliminados.add(objetivoIterado);				
			}
		}
		//Fabrico los mensajes de vuelta
		if(objElim.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaObjetivosAction.una.mensajeRespuesta");
		}else if(objElim.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaObjetivosAction.muchas.mensajeRespuesta");
		}
		
		Iterator<TlpObjetivo> itObjEliminados = objElim.iterator();
		while(itObjEliminados.hasNext()){
			TlpObjetivo objIterado = itObjEliminados.next();
			mensajeRespuesta += " " + objIterado.getNombre();
			if(itObjEliminados.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}
			
		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresObjetivosNoEliminados.isEmpty() && !errores.isEmpty() && identificadoresObjetivosNoEliminados.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronObjetivosSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronObjetivosSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idObjetivoNoEliminado : identificadoresObjetivosNoEliminados){
				TlpObjetivo	objetivoNoEliminada = neg.encuentra(idObjetivoNoEliminado);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaObjetivosAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, objetivoNoEliminada.getNombre());
				mensaje += biblioteca.traduce("EliminaObjetivosAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronObjetivosSatisfactoriamente){
					seEliminaronObjetivosSatisfactoriamente = false;
					mensajeRespuesta += mensaje + ".<br>";
				}else{
					mensajeRespuesta += mensaje + ".";
				}
				
			}
			
		}
		
		
		
		return SUCCESS;
	}

	public void setListID(List<String> listID) {
		this.listID = listID;
	}

	/**
	 * @return the listID
	 */
	@RequiredFieldValidator(key = "EliminaObjetivosAction.listID.obl")	
	public List<String> getListID() {
		return listID;
	}
	

}
