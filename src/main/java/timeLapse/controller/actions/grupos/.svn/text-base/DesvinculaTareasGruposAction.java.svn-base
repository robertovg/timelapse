package timeLapse.controller.actions.grupos;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("desvinculaTareasGruposAction")
@Scope("request")
public class DesvinculaTareasGruposAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
	
	
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {
		
		neg.desvinculaTareasGrupo(listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("DesvinculaTareasGruposAction.mensajeRespuesta");

		
		return SUCCESS;

	}


	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "DesvinculaTareasGruposAction.oidAcciones.obl")
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
