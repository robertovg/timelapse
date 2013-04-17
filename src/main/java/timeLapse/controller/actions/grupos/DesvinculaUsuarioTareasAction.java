package timeLapse.controller.actions.grupos;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
/**
 * Clase que se encarga de realizar la desvinculación entre los usuarios y los grupos pasados como parámetro
 * @author roberto
 *
 */
@SuppressWarnings("serial")
@Service("desvinculaUsuarioTareasAction")
@Scope("request")
public class DesvinculaUsuarioTareasAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
		
	private List<String> listOidEntidades;
	
	
	@Override
	public String execute() throws Exception {

		neg.desvinculaUsuarioTareas(listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("DesvinculaUsuarioTareasAction.mensajeRespuesta");


		return SUCCESS;
	
	}


	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "DesvinculaUsuarioTareasAction.listOidEntidades.obl")
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
