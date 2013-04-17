package timeLapse.controller.actions.tareas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.dto.TareaDTO;
/**
 * Action utilizado para mostrar las tareas a las que se pueden asociar otra tarea a la hora de
 * crerla o modificarla
 * @author roberto 
 */
@SuppressWarnings("serial")
@Service("listaTareasAAsociarComoPadresAction")
@Scope("request")
public class ListaTareasAAsociarComoPadresAction extends UserValueAction {
	@Resource
	ITareaBO neg;
	
	private String oidTareasPadre;
	
	private String identifier;	
	
	private List<TareaDTO> items;


	public ListaTareasAAsociarComoPadresAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		
		
		items = neg.listaTareasAAsociarComoPadres(usuarioRegistrado.getOid(),oidTareasPadre);
		
		return SUCCESS;
	}

	public List<TareaDTO> getItems() {
		return items;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param oidTareasPadre the oidTareasPadre to set
	 */
	public void setOidTareasPadre(String oidTareasPadre) {
		this.oidTareasPadre = oidTareasPadre;
	}

}
