package timeLapse.controller.actions.tareas;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("desvinculaTareaTareaAction")
@Scope("request")
public class DesvinculaTareaTareaAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
	
	
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {
		
		neg.desvinculaTareaTareas(listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("DesvinculaTareaTareaAction.mensajeRespuesta");

		
		return SUCCESS;

	}


	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "DesvinculaTareaTareaAction.oidAcciones.obl")
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
