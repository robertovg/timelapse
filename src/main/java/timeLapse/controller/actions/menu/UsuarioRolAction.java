package timeLapse.controller.actions.menu;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.dto.UsuarioDTO;

/**
 * 
 * @author robe
 *
 */
@SuppressWarnings("serial")
@Service("usuarioRolAction")
@Scope("request")
public class UsuarioRolAction extends UserValueAction {
	
	private UsuarioDTO usuario;
	
	@Override
	public String execute() throws Exception {		
		
		usuario = new UsuarioDTO();
		usuario.setNombre(usuarioRegistrado.getNombre());
		usuario.setRol(usuarioRegistrado.getRol());
		usuario.setOid(usuarioRegistrado.getOid());

		return SUCCESS;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

}
