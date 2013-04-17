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
 * 
 * Action que se llama cuando se cargan los filtros de las tareas, para mostrarlas en 
 * el select de tareas padre
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Service("listaTareasQueSonPadreAction")
@Scope("request")
public class ListaTareasQueSonPadreAction extends UserValueAction {
	@Resource
	ITareaBO neg;
	
	private String identifier;
	
	private List<TareaDTO> items;


	public ListaTareasQueSonPadreAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		
		
		items = neg.listaTareasPadre(usuarioRegistrado.getOid(), false);
		
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

}
