package timeLapse.controller.actions.grupos;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.grupos.IGrupoBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
/**
 * Action encargado de vincular grupos con usuarios
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Service("vinculaUsuariosGrupoAction")
@Scope("request")
public class VinculaUsuariosGrupoAction extends ActionTemplate{

	@Resource
	IGrupoBO neg;
	
	private String oidEntidad;
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {

		neg.vinculaUsuariosGrupo(oidEntidad, listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("VinculaUsuariosGrupoAction.mensajeRespuesta");


		return SUCCESS;
	
	}


	/**
	 * @return the oidRol
	 */
	@RequiredStringValidator(key = "VinculaUsuariosGrupoAction.oidEntidad.obl")
	public String getOidEntidad() {
		return oidEntidad;
	}


	/**
	 * @param oidRol the oidRol to set
	 */
	public void setOidEntidad(String oidRol) {
		this.oidEntidad = oidRol;
	}


	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "VinculaUsuariosGrupoAction.listOidEntidades.obl")
	public List<String> getListOidEntidades() {
		return listOidEntidades;
	}


	/**
	 * @param listOidAcciones the listOidAcciones to set
	 */
	public void setListOidEntidades(List<String> listOidAcciones) {
		this.listOidEntidades = listOidAcciones;
	}




	
}
