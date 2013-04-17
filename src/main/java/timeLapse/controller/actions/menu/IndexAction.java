package timeLapse.controller.actions.menu;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.security.ISeguridadBO;
import timeLapse.controller.actions.abs.RequestResponseValueAction;

/**
 * 
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("indexAction")
@Scope("request")
public class IndexAction extends RequestResponseValueAction {
	@Resource
	private ISeguridadBO seguridad;
	
	@Override
	public String execute() throws Exception {		
		String resul = SUCCESS;
		Locale locale = request.getLocale();
		
        biblioteca.setLocale(locale);
					
		//Si el usuario no está registrado
		if(usuarioRegistrado == null){
			
			HttpSession sesion = request.getSession();

							
			Cookie[] cookies = request.getCookies();
			String nombreUsuario = null;
			String password = null;
			if (cookies != null) {
				/*
				 * Recorro las cookies hasta encontrar la introducida para guardar el nombre usuario/ contraseña
				 * y la elimino
				 */
				for (Cookie cookie : cookies) {
					if (NOMBRE_USUARIO_COOKIE.equals(cookie.getName())) {	
						String valor = cookie.getValue();
						if(valor != null){
							String[] split = valor.split("==");
							nombreUsuario = split[0];
							password = split[1];
						}
					}
				}
			}
			//Si se realiza el login
			if(seguridad.procesadoLogin(sesion, nombreUsuario, password)){
				resul = "login-success";
			}else
			//Si no nos logueamos, 
			{
				resul = "login";	
			}
			
			
			
		}else{
			
			resul = "login-success";
		}


		return resul;
	}
	


}
