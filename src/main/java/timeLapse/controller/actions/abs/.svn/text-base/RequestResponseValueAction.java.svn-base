package timeLapse.controller.actions.abs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 * Clase abstract de la que se hereda para facilitar el acceso al los objetos request y al response
 * @author robe
 *
 */
@SuppressWarnings("serial")
public abstract class RequestResponseValueAction extends UserValueAction implements ServletRequestAware,ServletResponseAware{

	protected HttpServletRequest request;
	protected HttpServletResponse response;	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
}
