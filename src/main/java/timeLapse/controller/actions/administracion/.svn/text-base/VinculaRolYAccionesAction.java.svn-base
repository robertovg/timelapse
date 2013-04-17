package timeLapse.controller.actions.administracion;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import timeLapse.business.administracion.IPermisosBO;
import timeLapse.controller.actions.abs.ActionTemplate;

@SuppressWarnings("serial")
@Service("vinculaRolYAccionesAction")
@Scope("request")
public class VinculaRolYAccionesAction extends ActionTemplate{

	@Resource
	IPermisosBO neg;
	
	private String oidRol;
	private List<String> listOidAcciones;
	
	
	@Override
	public String execute() throws Exception {

		
		neg.vinculaRolYAcciones(oidRol, listOidAcciones);
		mensajeRespuesta = biblioteca.traduce("VinculaRolYAccionesAction.mensajeRespuesta");


		return SUCCESS;
	
	}


	/**
	 * @return the oidRol
	 */
	@RequiredStringValidator(key = "VinculaRolYAccionesAction.oidRol.obl")
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
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "VinculaRolYAccionesAction.oidAcciones.obl")
	public List<String> getListOidAcciones() {
		return listOidAcciones;
	}


	/**
	 * @param listOidAcciones the listOidAcciones to set
	 */
	public void setListOidAcciones(List<String> listOidAcciones) {
		this.listOidAcciones = listOidAcciones;
	}




	
}
