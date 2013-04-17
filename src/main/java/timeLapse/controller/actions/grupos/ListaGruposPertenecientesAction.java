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
 * Action que devuelve todos los grupos de usuarios creados por el usuario registrado
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaGruposPertenecientesAction")
@Scope("request")
public class ListaGruposPertenecientesAction extends UserValueAction {
	@Resource
	IGrupoBO neg;
	
	private List<TldGrupo> listGrupos;

	public ListaGruposPertenecientesAction() {
		this.listGrupos = new ArrayList<TldGrupo>();
	}

	@Override
	public String execute() throws Exception {
		
		listGrupos = neg.listaGruposPertenecientes(usuarioRegistrado.getOid());

		return SUCCESS;
	}

	public List<TldGrupo> getListGrupos() {
		return listGrupos;
	}

}
