package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IAccionesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;



/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaAccionesAction")
@Scope("request")
public class ListaAccionesAction extends ActionTemplate {
	@Resource
	IAccionesBO neg;
	
	private List<TlpAccion> listAcciones;

	public ListaAccionesAction() {
		this.listAcciones = new ArrayList<TlpAccion>();
	}

	@Override
	public String execute() throws Exception {
		
		List<TlpAccion> acciones = neg.listaAcciones();

		Iterator<TlpAccion> it = acciones.iterator();
		while (it.hasNext()) {
			TlpAccion accBD = it.next();
			TlpFuncionalidad funBD = accBD.getTlpFuncionalidad();
			TlpFuncionalidad func = new TlpFuncionalidad();
			func.setOid(funBD.getOid());
			func.setNombre(funBD.getNombre());
			TlpAccion acc = new TlpAccion();
			acc.setOid(accBD.getOid());
			acc.setTlpFuncionalidad(func);
			acc.setNombre(accBD.getNombre());
			acc.setPath(accBD.getPath());
			acc.setDescripcion(accBD.getDescripcion());
			acc.setOrden(accBD.getOrden());
			listAcciones.add(acc);
		}

		return SUCCESS;
	}

	public List<TlpAccion> getListAcciones() {
		return listAcciones;
	}

}
