package timeLapse.controller.actions.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IUsuarioBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.dto.UsuarioDTO;



/**
 * Action utilizado para mostrar todos los usuarios de un grupo de usuarios (si el usuario logeado es el creador
 * del grupo de usuarios) y el mismo si no es el creador del grupo
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaUsuariosAsociadosGrupoAction")
@Scope("request")
public class ListaUsuariosAsociadosGrupoAction extends UserValueAction {
	@Resource
	IUsuarioBO neg;
	
	private String oidGrupo;
	
	private String identifier;
	
	private List<UsuarioDTO> items;


	public ListaUsuariosAsociadosGrupoAction() {
		this.items = new ArrayList<UsuarioDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		
		if(!StringUtils.isBlank(oidGrupo)){
			items = neg.listaUsuariosDeUnGrupo(oidGrupo, usuarioRegistrado.getOid());
		}
		
		
		return SUCCESS;
	}

	/**
	 * @return the oidGrupo
	 */	
	public String getOidGrupo() {
		return oidGrupo;
	}

	/**
	 * @param oidGrupo the oidGrupo to set
	 */
	public void setOidGrupo(String oidGrupo) {
		this.oidGrupo = oidGrupo;
	}

	public List<UsuarioDTO> getItems() {
		return items;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}


}
