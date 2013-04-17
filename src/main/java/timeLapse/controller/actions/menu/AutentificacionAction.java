package timeLapse.controller.actions.menu;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.security.ISeguridadBO;
import timeLapse.controller.actions.abs.RequestResponseValueAction;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

/**
 * 
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("autentificacionAction")
@Scope("request")
public class AutentificacionAction extends RequestResponseValueAction {
	
	@Resource
	private ISeguridadBO seguridad;
	
	private String usuario;
	private String password;
	private Boolean usuarioLogueado;
	private Boolean recordarme;
  
    
	@Override
	public String execute() throws Exception {		
		Locale locale = request.getLocale();

        biblioteca.setLocale(locale);

        //Proces el nombre de usuario y contraseña enviados.
		usuarioLogueado = seguridad.procesadoLogin(request.getSession(), usuario, password);
	    

		//Si el usuario no es válido elevo una excepción
		if(!usuarioLogueado){
//			Mensaje men = factoriaMens.getMensaje(ConstantesException.nombreUsuarioPasswordIncorrecto);
//			throw new TimeLapseException(men);
			mensajeRespuesta = biblioteca.traduce("AutentificacionAction.passwordNoValido");

		}else{
			//Si el usuario selecciona recordar la contraseña, se genera una cookie que se envía al cliente.
			if(recordarme != null){
				addUsuarioToRequest(usuario, password);
			}
			//Lo limpio para no devolverlos en la respuesta.
			usuario = "";
			password = "";
			recordarme = false;

		}
		
		return SUCCESS;
	}
	/**
	 * Crea y añade una cookie con el nombre de usuario y contraseña que perdurarán en cliente (siempre y cuando este
	 * no la borre) un año.
	 * @param usuario
	 * @param passwordMD5
	 */
	private void addUsuarioToRequest(String usuario, String passwordMD5) {
	 
	 
	     Cookie cookie = new Cookie(NOMBRE_USUARIO_COOKIE, usuario + "==" + passwordMD5);
	     cookie.setVersion(1);
	     cookie.setPath("/"); 
	     cookie.setMaxAge(60 * 60 * 24 * 365);
	     response.addCookie(cookie);
	}

	/**
	 * @return the usuario
	 */
	@RequiredStringValidator(key = "AutentificacionAction.usuario.obl")
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	@RequiredStringValidator(key = "AutentificacionAction.password.obl")
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @param recordarme
	 */
	public void setRecordarme(Boolean recordarme) {
		this.recordarme = recordarme;
	}
	/**
	 * @return the usuarioLogueado
	 */
	public Boolean getUsuarioLogueado() {
		return usuarioLogueado;
	}
	/**
	 * @param usuarioLogueado the usuarioLogueado to set
	 */
	public void setUsuarioLogueado(boolean usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
	
	

}
