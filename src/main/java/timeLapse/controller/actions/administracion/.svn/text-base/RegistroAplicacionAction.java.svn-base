package timeLapse.controller.actions.administracion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import timeLapse.business.administracion.IUsuarioBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.util.Utiles;
import timeLapse.util.exceptions.TimeLapseException;

/**
 * Action que se encarga de la creación de nuevos usuarios en la aplicación
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("registroAplicacionAction")
@Scope("request")
public class RegistroAplicacionAction extends ActionTemplate {
	@Resource
	IUsuarioBO neg;
	
	String formNombreUsu;
	String formNombrePropio;	
	String formApellido1;
	String formApellido2;
	String formPasswd1;
	


	@Override
	public String execute() throws TimeLapseException, Exception {
		try{
			//Recojo los valores del formulario para crear el Usuario
			TldUsuario usuario = new TldUsuario();
			usuario.setNombreUsuario(formNombreUsu);
			usuario.setNombrePropio(formNombrePropio);
			usuario.setApellido1(formApellido1);
			usuario.setApellido2(formApellido2);
			usuario.setContrasenna(Utiles.codificaMD5(formPasswd1));
			//Le añado el rol de usuario
			TlpRol rolDeUsuario = new TlpRol(OID_ROL_USUARIO, "Usuario");
			usuario.setTlpRol(rolDeUsuario);
			neg.actualiza(usuario);

			mensajeRespuesta = formNombreUsu;
			mensajeRespuesta += ", " + biblioteca.traduce("RegistroAplicacionAction.mensajeRespuesta");

			
			return SUCCESS;
		}catch (Exception e) {
			throw e;
		}
		
	}



	/**
	 * @return the formNombreUsu
	 */
	@RequiredStringValidator(key = "RegistroAplicacionAction.formNombreUsu.obl")
	@StringLengthFieldValidator(key = "RegistroAplicacionAction.formNombreUsu.long", maxLength = "50")
	public String getFormNombreUsu() {
		return formNombreUsu;
	}



	/**
	 * @param formNombreUsu the formNombreUsu to set
	 */
	public void setFormNombreUsu(String formNombreUsu) {
		this.formNombreUsu = formNombreUsu;
	}



	/**
	 * @return the formNombrePropio
	 */
	@RequiredStringValidator(key = "RegistroAplicacionAction.formNombrePropio.obl")
	@StringLengthFieldValidator(key = "RegistroAplicacionAction.formNombrePropio.long", maxLength = "100")
	public String getFormNombrePropio() {
		return formNombrePropio;
	}



	/**
	 * @param formNombrePropio the formNombrePropio to set
	 */
	public void setFormNombrePropio(String formNombrePropio) {
		this.formNombrePropio = formNombrePropio;
	}



	/**
	 * @return the formApellido1
	 */
	@RequiredStringValidator(key = "RegistroAplicacionAction.formApellido1.obl")
	@StringLengthFieldValidator(key = "RegistroAplicacionAction.formApellido1.long", maxLength = "100")
	public String getFormApellido1() {
		return formApellido1;
	}



	/**
	 * @param formApellido1 the formApellido1 to set
	 */
	public void setFormApellido1(String formApellido1) {
		this.formApellido1 = formApellido1;
	}



	/**
	 * @return the formApellido2
	 */	
	@StringLengthFieldValidator(key = "RegistroAplicacionAction.formApellido2.long", maxLength = "100")
	public String getFormApellido2() {
		return formApellido2;
	}



	/**
	 * @param formApellido2 the formApellido2 to set
	 */
	public void setFormApellido2(String formApellido2) {
		this.formApellido2 = formApellido2;
	}



	/**
	 * @return the formPasswd1
	 */
	@RequiredStringValidator(key = "RegistroAplicacionAction.formPasswd1.obl")
	@StringLengthFieldValidator(key = "RegistroAplicacionAction.formPasswd1.long", maxLength = "100")
	public String getFormPasswd1() {
		return formPasswd1;
	}



	/**
	 * @param formPasswd1 the formPasswd1 to set
	 */
	public void setFormPasswd1(String formPasswd1) {
		this.formPasswd1 = formPasswd1;
	}
	
	






}
