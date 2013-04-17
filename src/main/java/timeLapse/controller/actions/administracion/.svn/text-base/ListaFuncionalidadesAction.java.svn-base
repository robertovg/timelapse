package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IFuncionalidadesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpObjetivo;

@SuppressWarnings("serial")
@Service("listaFuncionalidadesAction")
@Scope("request")
public class ListaFuncionalidadesAction extends ActionTemplate {

	@Resource
	IFuncionalidadesBO neg;
	
	private List<TlpFuncionalidad> listFuncionalidad;

	public ListaFuncionalidadesAction() {
		this.listFuncionalidad = new ArrayList<TlpFuncionalidad>();
	}

	@Override
	public String execute() throws Exception {

		
		List<TlpFuncionalidad> objetivos = neg.listaFuncionalidad();

		Iterator<TlpFuncionalidad> it = objetivos.iterator();
		while (it.hasNext()) {
			TlpFuncionalidad funBD = it.next();
			TlpObjetivo objBD = funBD.getTlpObjetivo();
			TlpObjetivo obj = new TlpObjetivo();
			obj.setOid(objBD.getOid());
			obj.setNombre(objBD.getNombre());
			TlpFuncionalidad fun = new TlpFuncionalidad();
			fun.setOid(funBD.getOid());
			fun.setTlpObjetivo(obj);
			fun.setNombre(funBD.getNombre());
			fun.setDescripcion(funBD.getDescripcion());
			fun.setOrden(funBD.getOrden());
			listFuncionalidad.add(fun);
		}

		return SUCCESS;
	}

	public List<TlpFuncionalidad> getListFuncionalidad() {
		return listFuncionalidad;
	}

}
