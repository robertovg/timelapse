package timeLapse.controller.actions.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IObjetivosBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TlpObjetivo;

/**
 * 
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("objetivosAction")
@Scope("request")
public class ObjetivosAction extends UserValueAction {
	
	
	
	@Resource
	IObjetivosBO neg;
    
	private List<TlpObjetivo> objetivos;
	

	public ObjetivosAction(){
		
		super();
		this.objetivos = new ArrayList<TlpObjetivo>();
		
	
	}
	
	@Override
	public String execute() throws Exception {		
		
		List<TlpObjetivo> objetivosBD = neg.listaObjetivos(usuarioRegistrado.getRol());
		Iterator<TlpObjetivo> it = objetivosBD.iterator();
		while (it.hasNext()) {
			TlpObjetivo objBD = it.next();
			TlpObjetivo obj = new TlpObjetivo();
			obj.setOid(objBD.getOid());
			obj.setNombre(objBD.getNombre());
			obj.setDescripcion(objBD.getDescripcion());
			obj.setOrden(objBD.getOrden());
			objetivos.add(obj);
		}


		return SUCCESS;
	}
	
	public List<TlpObjetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<TlpObjetivo> objetivos) {
		this.objetivos = objetivos;
	}
	
	



}
