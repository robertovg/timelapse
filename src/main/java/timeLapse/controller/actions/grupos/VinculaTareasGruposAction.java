package timeLapse.controller.actions.grupos;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
/**
 * Action encargado de vincular tareas con grupos de usuarios
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Service("vinculaTareasGruposAction")
@Scope("request")
public class VinculaTareasGruposAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
	
	private String oidEntidad;
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {

		neg.vinculaTareasGrupo(oidEntidad, listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("VinculaTareasGruposAction.mensajeRespuesta");


		return SUCCESS;
	
	}


	/**
	 * @return the oidRol
	 */
	@RequiredStringValidator(key = "VinculaTareasGruposAction.oidEntidad.obl")
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
	@RequiredFieldValidator(key = "VinculaTareasGruposAction.listOidEntidades.obl")
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
