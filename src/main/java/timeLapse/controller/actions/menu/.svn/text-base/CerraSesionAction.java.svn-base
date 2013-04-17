package timeLapse.controller.actions.menu;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.controller.actions.abs.RequestResponseValueAction;

/**
 * Esta Clase se encarga tanto de eliminar el usuario de la sesión, así como de eliminar
 * la cookie que se le envía al cliente para recordar el usuario/contraseña
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("cerraSesionAction")
@Scope("request")
public class CerraSesionAction extends RequestResponseValueAction{

	public CerraSesionAction(){
		super();
	}
	@Override
	public String execute() throws Exception {
		HttpSession sesion = request.getSession();
		//Si exsite la sesión
		if(sesion != null){
			sesion.removeAttribute(NOMBRE_USUARIO_SESSION);			
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				/*
				 * Recorro las cookies hasta encontrar la introducida para guardar el nombre usuario/ contraseña
				 * y la elimino
				 */
				for (Cookie cookie : cookies) {
					if (NOMBRE_USUARIO_COOKIE.equals(cookie.getName())) {
						cookie.setValue("");
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}

				}
			}
		}
		    
			
		    
		
		
		
        
		return SUCCESS;
	}
	

	
	
	
	



}
