package timeLapse.controller.actions.abs;

import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.util.annotations.SessionValue;
/**
 * Clase abstracta de la que heredarán los actions que quieran acceder al objeto usuario guardado
 * en la sesión
 * @author robe
 *
 */
@SuppressWarnings("serial")
public abstract class UserValueAction extends ActionTemplate{
	protected UsuarioDTO usuarioRegistrado;
	
	@SessionValue(id = NOMBRE_USUARIO_SESSION)
    public void setUsuarioRegistrado(UsuarioDTO usuarioRegistrado) {
		this.usuarioRegistrado = usuarioRegistrado;
	}
}
