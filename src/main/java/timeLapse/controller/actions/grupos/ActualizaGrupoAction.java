package timeLapse.controller.actions.grupos;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import timeLapse.business.grupos.IGrupoBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldUsuario;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("actualizaGrupoAction")
@Scope("request")
public class ActualizaGrupoAction extends UserValueAction {
	@Resource
	IGrupoBO neg;
	private String formOID;
	private String formNombre;	
	private String formDesc;
	

	@Override
	public String execute() throws Exception {

		TldGrupo grupo = new TldGrupo();
		if (!(formOID.equals(""))) {
			grupo.setOid(formOID);
		}
		TldUsuario usuario = new TldUsuario();
		usuario.setOid(usuarioRegistrado.getOid());
		
		grupo.setNombre(formNombre);
		grupo.setDescripcion(formDesc);
		grupo.setTldUsuario(usuario);
		
		neg.actualiza(grupo);

		//Creo una nueva
		if ((formOID.equals(""))) {
			
			mensajeRespuesta = biblioteca.traduce("ActualizaGrupoAction.nueva.mensajeRespuesta");			
		}
		//Actualizo
		else{
			
			mensajeRespuesta = biblioteca.traduce("ActualizaGrupoAction.modificar.mensajeRespuesta");
		}

		mensajeRespuesta += " " + formNombre;
		
		
		return SUCCESS;
	}


	/**
	 * @return the formOID
	 */
	public String getFormOID() {
		return formOID;
	}


	/**
	 * @param formOID the formOID to set
	 */
	public void setFormOID(String formOID) {
		this.formOID = formOID;
	}


	/**
	 * @return the formNombre
	 */
	@RequiredStringValidator(key = "ActualizaCategoriaAction.formNombre.obl")
	@StringLengthFieldValidator(key = "ActualizaCategoriaAction.formNombre.long", maxLength = "100")
	public String getFormNombre() {
		return formNombre;
	}


	/**
	 * @param formNombre the formNombre to set
	 */
	public void setFormNombre(String formNombre) {
		this.formNombre = formNombre;
	}


	/**
	 * @return the formDesc
	 */
	@RequiredStringValidator(key = "ActualizaCategoriaAction.formDesc.obl")
	@StringLengthFieldValidator(key = "ActualizaCategoriaAction.formDesc.long", maxLength = "500")
	public String getFormDesc() {
		return formDesc;
	}


	/**
	 * @param formDesc the formDesc to set
	 */
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	

}
