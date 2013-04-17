package timeLapse.controller.actions.tareas;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldTarea;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("finalizarTareasAction")
@Scope("request")
public class FinalizarTareasAction extends UserValueAction {
	@Resource
	ITareaBO neg;
	
	
	private List<String> listID;
	

	@Override
	public String execute() throws Exception {

		List<TldTarea> tareaFinalizada = neg.finalizarTareas(listID);
		
		//Genero los mensajes de respuesta		
		if(tareaFinalizada.size() == 1){
			mensajeRespuesta = biblioteca.traduce("FinalizarTareasAction.una.mensajeRespuesta");
		}else{
			mensajeRespuesta = biblioteca.traduce("FinalizarTareasAction.muchas.mensajeRespuesta");
		}
		
		Iterator<TldTarea> it = tareaFinalizada.iterator();
		while(it.hasNext()){
			
			TldTarea tareaIterada = it.next();
			mensajeRespuesta += " " + tareaIterada.getNombre();
			if(it.hasNext()){
				mensajeRespuesta += ",";
			}
			 
		}
		
		
		
		return SUCCESS;
	}


	/**
	 * @return the listID
	 */
	@RequiredFieldValidator(key="FinalizarTareasAction.listID.obl")
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
