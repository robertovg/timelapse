package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IRolesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpRol;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaRolesAction")
@Scope("request")
public class ListaRolesAction extends ActionTemplate {

	@Resource
	IRolesBO neg;
	
	private List<TlpRol> listRoles;

	public ListaRolesAction() {

		listRoles = new ArrayList<TlpRol>();

	}

	@Override
	public String execute() throws Exception {

		
		List<TlpRol> roles = neg.listaRoles();
		Iterator<TlpRol> it = roles.iterator();
		while (it.hasNext()) {
			TlpRol objBD = it.next();
			TlpRol obj = new TlpRol();
			obj.setOid(objBD.getOid());
			obj.setNombre(objBD.getNombre());
			obj.setDescripcion(objBD.getDescripcion());
			obj.setGrado(objBD.getGrado());
					
			
			listRoles.add(obj);
		}

		return SUCCESS;
	}

	public List<TlpRol> getListRoles() {
		return listRoles;
	}

}
