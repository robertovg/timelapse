package timeLapse.controller.actions.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

import timeLapse.business.administracion.IFuncionalidadesBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("funcionalidadesAction")
@Scope("request")
public class FuncionalidadesAction extends UserValueAction {
	
		
	@Resource
	IFuncionalidadesBO neg;

	private String oid;
	private List<TlpFuncionalidad> listFuncionalidad;

	public FuncionalidadesAction() {
		super();
		this.listFuncionalidad = new ArrayList<TlpFuncionalidad>();
	}

	@Override
	public String execute() throws Exception {
		try {
			
			

			List<TlpFuncionalidad> func = neg.listaFuncionalidades(oid, usuarioRegistrado.getRol());

			Iterator<TlpFuncionalidad> it = func.iterator();
			while (it.hasNext()) {
				TlpFuncionalidad funcBD = it.next();
				TlpFuncionalidad objFunc = new TlpFuncionalidad();
				objFunc.setOid(funcBD.getOid());
				objFunc.setNombre(funcBD.getNombre());
				objFunc.setOrden(funcBD.getOrden());

				Iterator<TlpAccion> it2 = funcBD.getTlpAccions().iterator();
				List<TlpAccion> sAcc = new ArrayList<TlpAccion>();
				while (it2.hasNext()) {
					TlpAccion accBD = it2.next();
					TlpAccion accObj = new TlpAccion();
					accObj.setOid(accBD.getOid());
					accObj.setNombre(accBD.getNombre());
					accObj.setPath(accBD.getPath());
					accObj.setOrden(accBD.getOrden());
					sAcc.add(accObj);

				}
				objFunc.setTlpAccions(sAcc);

				listFuncionalidad.add(objFunc);

			}

		} catch (Exception e) {

			throw e;

		}

		return SUCCESS;
	}

	/**
	 * @return the oid
	 */
	@RequiredFieldValidator(key = "FuncionalidadesAction.OID.obl")
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the listFuncionalidad
	 */
	public List<TlpFuncionalidad> getListFuncionalidad() {
		return listFuncionalidad;
	}

	/**
	 * @param listFuncionalidad the listFuncionalidad to set
	 */
	public void setListFuncionalidad(List<TlpFuncionalidad> listFuncionalidad) {
		this.listFuncionalidad = listFuncionalidad;
	}

	

}
