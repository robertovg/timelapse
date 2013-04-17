package timeLapse.controller.actions.tiempo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("desvinculaTareasTiempoAction")
@Scope("request")
public class DesvinculaTareasTiempoAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
	
	
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {
		
		neg.desvinculaTareasTiempo(listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("DesvinculaTareasTiempoAction.mensajeRespuesta");

		
		return SUCCESS;

	}


	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "DesvinculaTareasTiempoAction.oidAcciones.obl")
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
