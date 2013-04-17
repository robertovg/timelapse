package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import timeLapse.business.administracion.IAccionesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;

@SuppressWarnings("serial")
@Service("listaAccionesNoAsociadasAction")
@Scope("request")
public class ListaAccionesNoAsociadasAction extends ActionTemplate{

	@Resource
	IAccionesBO neg;
	
	private String oidRol;
	private List<TlpAccion> listAcciones;
	
	
	@Override
	public String execute() throws Exception {
		List<TlpAccion> accionesNeg = neg.listaAccionesNoAsociadas(oidRol);
		listAcciones = new ArrayList<TlpAccion>();
		for(TlpAccion accBD :accionesNeg){
			TlpAccion acc = new TlpAccion();
			TlpFuncionalidad funBD = accBD.getTlpFuncionalidad();
			TlpFuncionalidad func = new TlpFuncionalidad();
			func.setOid(funBD.getOid());
			func.setNombre(funBD.getNombre());
			acc.setOid(accBD.getOid());			
			acc.setNombre(accBD.getNombre());
			acc.setTlpFuncionalidad(func);
			acc.setPath(accBD.getPath());
			acc.setDescripcion(accBD.getDescripcion());
			acc.setOrden(accBD.getOrden());
			listAcciones.add(acc);
		}

		return SUCCESS;

	}


	/**
	 * @return the oidRol
	 */
	@RequiredStringValidator(key = "ListaAccionesNoAsociadasAction.oidRol.obl")
	public String getOidRol() {
		return oidRol;
	}


	/**
	 * @param oidRol the oidRol to set
	 */
	public void setOidRol(String oidRol) {
		this.oidRol = oidRol;
	}


	/**
	 * @return the listAcciones
	 */
	public List<TlpAccion> getListAcciones() {
		return listAcciones;
	}


	/**
	 * @param listAcciones the listAcciones to set
	 */
	public void setListAcciones(List<TlpAccion> listAcciones) {
		this.listAcciones = listAcciones;
	}


	
	

	
}
