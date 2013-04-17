package timeLapse.controller.actions.tareas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("eliminaTareasAction")
@Scope("request")
public class EliminaTareasAction extends UserValueAction {

	@Resource
	ITareaBO neg;
	
	private List<String> listID;

	@Override
	public String execute() throws Exception {

		
		Iterator<String> it = listID.iterator();
		List<TldTarea> tareasEliminadas = new ArrayList<TldTarea>();
		List<String> identificadoresTareasNoEliminadas = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		//Realizo la eliminación de las tareas
		
		while (it.hasNext()) {
			
			String tareaIterada = it.next();
			try{				
				tareasEliminadas.add(neg.elimina(tareaIterada, usuarioRegistrado.getOid()));
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresTareasNoEliminadas.add(tareaIterada);				
			}
		}
		
		
		
		//Se genera el mensaje de resultado		
		if(tareasEliminadas.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaTareasAction.una.mensajeRespuesta");
		}else if(tareasEliminadas.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaTareasAction.muchas.mensajeRespuesta");
		}		
		Iterator<TldTarea> itEliminadas = tareasEliminadas.iterator();
		while(itEliminadas.hasNext()){
			
			TldTarea tareaIterada = itEliminadas.next();			
			mensajeRespuesta += " " + tareaIterada.getNombre();
			if(itEliminadas.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}

		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresTareasNoEliminadas.isEmpty() && !errores.isEmpty() && identificadoresTareasNoEliminadas.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronTareasSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronTareasSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idTareaNoEliminada : identificadoresTareasNoEliminadas){
				TldTarea tareaNoEliminada = neg.encuentra(idTareaNoEliminada);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaTareasAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, tareaNoEliminada.getNombre());
				mensaje += biblioteca.traduce("EliminaTareasAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronTareasSatisfactoriamente){
					seEliminaronTareasSatisfactoriamente = false;
					mensajeRespuesta += mensaje + ".<br>";
				}else{
					mensajeRespuesta += mensaje + ".";
				}
				
			}
			
		}

		return SUCCESS;
	}

	/**
	 * @return the listID
	 */
	@RequiredFieldValidator(key = "EliminaTareasAction.listID.obl")
	public List<String> getListID() {
		return listID;
	}

	/**
	 * @param listID the listID to set
	 */
	public void setListID(List<String> listID) {
		this.listID = listID;
	}

	
}
