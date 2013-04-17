package timeLapse.controller.interceptors.security;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Service;

import timeLapse.business.security.impl.SeguridadBO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.util.constantes.Globales;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Clase que se llama antes de ejecutar cualquier action (a través de Struts) y que se encarga de controlar que ningún action sea ejecutado
 * si el usuario no está autentificado en la aplicación
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("loginInterceptor")
public class LoginInterceptor extends AbstractInterceptor implements Globales,StrutsStatics{
	@Resource
	private IUsuarioDAO userDao;
	/**
	 * Método que se ejecutará siempre que se llame a cualquier action
	 */
	@Override
	public String intercept (ActionInvocation invocation) throws Exception {
	    //Me traigo la session del requiest
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest)context.get(HTTP_REQUEST);
	    HttpSession session =  request.getSession();


	    //Busco al usuario en session
	    UsuarioDTO usuarioRegistrado = (UsuarioDTO)session.getAttribute (NOMBRE_USUARIO_SESSION);
	    
        
        //Si el usuario ha cadudcado
        if (usuarioRegistrado == null) {
        	
        	/*
        	 * Si el usuario no está logueado miro a ver si existe la cookie con los datos necesarios para
        	 * realizar la autentificación del usuario, y si así fuera, reinicio la sesión
        	 */
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
			//Me fabrico "a mano" el objeto de negocio de la seguridad, para reutilizarlo.
			SeguridadBO seguridadBO = new SeguridadBO();			
						
			seguridadBO.completaAtributosSinFactoria(request.getLocale());			
			seguridadBO.setUsuDao(userDao);
			
			
			if(seguridadBO.procesadoLogin(session, nombreUsuario, password)){
				/*
				 * Si se acaba de insertar la sessión, inserto de nuevo las variables que dependieran de la
				 * sessión.
				 */
				
				
				return invocation.invoke();
			}else{
				return "usuario-nologueado";	
			}    
	        
	    } else {
	        return invocation.invoke ();

	    }
	}
	
	


}
