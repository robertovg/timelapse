package timeLapse.controller.actions.grupos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.grupos.IGrupoBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldGrupo;



/**
 * Action utilizado para devolver los grupos asociados a alguna tarea, utilizado para los filtros de tareas
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaGruposUtilizadosAction")
@Scope("request")
public class ListaGruposUtilizadosAction extends UserValueAction {
	@Resource
	IGrupoBO neg;
	
	private String identifier;
	
	private List<TldGrupo> items;

	public ListaGruposUtilizadosAction() {
		this.items = new ArrayList<TldGrupo>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";

		items = neg.listaGruposUtilizados(usuarioRegistrado.getOid(), false);

		return SUCCESS;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the items
	 */
	public List<TldGrupo> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<TldGrupo> items) {
		this.items = items;
	}
	
	

}
