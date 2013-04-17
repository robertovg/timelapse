package timeLapse.controller.interceptors.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Service;

import timeLapse.business.security.impl.SeguridadBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.dao.IPermisosDAO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.Globales;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Clase que se llama antes de ejecutar cualquier action (a través de Struts) y que se encarga de controlar
 * que ningún action sea ejecutado sin que el usuario que lo está llamando tenga los permisos de ejecución
 * sobre la acción relacionada.
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("permissionsInterceptor")
public class PermissionsInterceptor extends AbstractInterceptor implements Globales,StrutsStatics{
	@Resource
	private IUsuarioDAO userDao;
	
	@Resource
	private IPermisosDAO permisoDao;
	
	private List<String> acciones = new ArrayList<String>();
	
	public void setAcciones(String acciones) {
        this.acciones = Utiles.stringToList(acciones);
    }
	/**
	 * Método que se ejecutará siempre que se llame a cualquier action
	 */
	@Override
	public String intercept (ActionInvocation invocation) throws Exception {
	    //Me traigo la session del requiest
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest)context.get(HTTP_REQUEST);
	    HttpSession session =  request.getSession();

	    //Inicializo las acciones si las hay
	    
	    ActionTemplate action = null;
	    try{
	    	action = (ActionTemplate) invocation.getAction();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    
	    
    	setAcciones(action != null && action.getAcciones() != null ? action.getAcciones() : null);
	    
	    		
	    
	    //Busco al usuario en session
	    UsuarioDTO usuarioRegistrado = (UsuarioDTO)session.getAttribute (NOMBRE_USUARIO_SESSION);
	    
        
        boolean hayPermisosParaSeguir = true;
        
        //Me fabrico "a mano" el objeto de negocio de la seguridad, para reutilizarlo.
		SeguridadBO seguridadBO = new SeguridadBO();			
					
		seguridadBO.completaAtributosSinFactoria(request.getLocale());			
		seguridadBO.setUsuDao(userDao);
		seguridadBO.setPermisoDao(permisoDao);

        /*
         * Si el action que se está ejecutando tiene alguna acción, compruebo que el rol del usuario logueado
         * tenga permisos para alguna de esas acciones.
         */        
        if(acciones.size() > 0){
        	hayPermisosParaSeguir = seguridadBO.tienePermisos(usuarioRegistrado.getRol(), acciones);
        }
			
		if(hayPermisosParaSeguir){
			return invocation.invoke();
		}else{
			return "usuario-sinPermisos";	
		}    
	        
	  
	}

	
	


}
