package timeLapse.controller.actions.tareas;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.UserValueAction;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("clonarTareaAction")
@Scope("request")
public class ClonarTareaAction extends UserValueAction {
	@Resource
	ITareaBO neg;
	
	
	private String oidTarea;
	
	

	@Override
	public String execute() throws Exception {
		
		String nombreNuevaTarea = neg.clonarTarea(oidTarea, usuarioRegistrado.getOid());
		
		mensajeRespuesta = biblioteca.traduce("ClonarTareaAction.mensajeRespuesta");
		mensajeRespuesta += " " + nombreNuevaTarea;
		

		return SUCCESS;
	}


	/**
	 * @return the oidTarea
	 */
	@RequiredFieldValidator(key="ClonarTareaAction.oidTarea.obl")
	public String getOidTarea() {
		return oidTarea;
	}



	/**
	 * @param oidTarea the oidTarea to set
	 */
	public void setOidTarea(String oidTarea) {
		this.oidTarea = oidTarea;
	}



	

	




	

}
