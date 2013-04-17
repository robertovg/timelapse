package timeLapse.controller.actions.administracion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IUsuarioBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.Utiles;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * Action que se encarga del cambio de contraseña de un usuario en la aplicación
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("cambioPasswordAction")
@Scope("request")
public class CambioPasswordAction extends UserValueAction {
	@Resource
	IUsuarioBO neg;
	
	String formAntiguo;
	String formPasswd1;
	


	@Override
	public String execute() throws TimeLapseException, Exception {
		try{
			//Recojo los valores del formulario para crear el Usuario
			TldUsuario usuarioACambiar = neg.encuentra(usuarioRegistrado.getOid());
		
			boolean correcto = true;
			String contrasenaNuevaMD5 = Utiles.codificaMD5(formPasswd1);
			String contrasenaAntiguaMD5 = Utiles.codificaMD5(formAntiguo);

			//Compruebo que la contraseña guardada sea la misma que la antigua pasada como parémtro
			correcto = usuarioACambiar.getContrasenna().equals(contrasenaAntiguaMD5);
			
			if(correcto){

				//Cambio su contraseña y actualizo
				usuarioACambiar.setContrasenna(contrasenaNuevaMD5);
				
				neg.cambioPassword(usuarioACambiar);

				mensajeRespuesta = usuarioACambiar.getNombreUsuario();
				mensajeRespuesta += ", " + biblioteca.traduce("CambioPasswordAction.mensajeRespuesta");
			}else{
				
				
				mensajeRespuesta = biblioteca.traduce("CambioPasswordAction.mensajeRespuestaMalo");
			}
			
			


			
			return SUCCESS;
		}catch (Exception e) {
			throw e;
		}
		
	}



	



	/**
	 * @return the formApellido2
	 */	
	@RequiredStringValidator(key = "CambioPasswordAction.formAntiguo.obl")
	@StringLengthFieldValidator(key = "CambioPasswordAction.formAntiguo.long", maxLength = "100")
	public String getFormAntiguo() {
		return formAntiguo;
	}



	/**
	 * @param formApellido2 the formApellido2 to set
	 */
	public void setFormAntiguo(String formApellido2) {
		this.formAntiguo = formApellido2;
	}



	/**
	 * @return the formPasswd1
	 */
	@RequiredStringValidator(key = "CambioPasswordAction.formPasswd1.obl")
	@StringLengthFieldValidator(key = "CambioPasswordAction.formPasswd1.long", maxLength = "100")
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
