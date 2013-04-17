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
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaCategoriasAction")
@Scope("request")
public class ListaCategoriasAction extends UserValueAction {
	@Resource
	ICategoriaBO neg;
	
	private List<TldCategoria> listCategorias;

	public ListaCategoriasAction() {
		this.listCategorias = new ArrayList<TldCategoria>();
	}

	@Override
	public String execute() throws Exception {
		
		listCategorias = neg.listaCategorias(usuarioRegistrado.getOid());

		return SUCCESS;
	}

	public List<TldCategoria> getListCategorias() {
		return listCategorias;
	}

}
