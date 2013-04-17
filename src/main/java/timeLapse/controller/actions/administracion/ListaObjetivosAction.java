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

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaObjetivosAction")
@Scope("request")
public class ListaObjetivosAction extends ActionTemplate {

	@Resource
	IObjetivosBO neg;
	
	private List<TlpObjetivo> listObjetivos;

	public ListaObjetivosAction() {

		listObjetivos = new ArrayList<TlpObjetivo>();

	}

	@Override
	public String execute() throws Exception {

		
		List<TlpObjetivo> objetivos = neg.listaObjetivos();
		Iterator<TlpObjetivo> it = objetivos.iterator();
		while (it.hasNext()) {
			TlpObjetivo objBD = it.next();
			TlpObjetivo obj = new TlpObjetivo();
			obj.setOid(objBD.getOid());
			obj.setNombre(objBD.getNombre());
			obj.setDescripcion(objBD.getDescripcion());
			obj.setOrden(objBD.getOrden());
			listObjetivos.add(obj);
		}

		return SUCCESS;
	}

	public List<TlpObjetivo> getListObjetivos() {
		return listObjetivos;
	}

}
