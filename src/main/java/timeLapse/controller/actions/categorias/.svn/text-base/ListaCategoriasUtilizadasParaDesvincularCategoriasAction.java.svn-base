package timeLapse.controller.actions.categorias;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.categorias.ICategoriaBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldCategoria;



/**
 * Action utilizado para devolver las categorias que han sido asociadas a alguna tarea en la desvinculación
 * de tareas con categorías
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaCategoriasUtilizadasParaDesvincularCategoriasAction")
@Scope("request")
public class ListaCategoriasUtilizadasParaDesvincularCategoriasAction extends UserValueAction {
	@Resource
	ICategoriaBO neg;
	
	private List<TldCategoria> items;
	
	private String identifier;	
	
	
	public ListaCategoriasUtilizadasParaDesvincularCategoriasAction() {
		this.items = new ArrayList<TldCategoria>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";


		items = neg.listaCategoriasUtilizadas(usuarioRegistrado.getOid(),true);


		return SUCCESS;
	}

	/**
	 * @return the items
	 */
	public List<TldCategoria> getItems() {
		return items;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	


	

	

}
