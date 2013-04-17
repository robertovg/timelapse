package timeLapse.business.administracion.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.administracion.IUsuarioBO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;
@Service
@Scope("session")
public class UsuarioBO extends BusinessObjectTemplate implements IUsuarioBO {
	@Resource
	IUsuarioDAO usuarioDao;
	
	
	public void actualiza(TldUsuario obj) throws TimeLapseException {

		//Compruebo que el nombre de usuario no exista ya en la aplicación
		IUsuarioDAO dao = getUsuarioDao();
		List<TldUsuario> usuariosRepetidos = dao.findByNombre(obj.getNombreUsuario());
		if(usuariosRepetidos.size() >= 1){
			
			Mensaje mens = factoriaMens.getMensaje(ConstantesException.yaExisteUnUsuarioConEseNombre);			
			throw new TimeLapseException(mens);
		}		
    	dao.save(obj);
	}

	public IUsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(IUsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public List<UsuarioDTO> listaUsuariosNoAsociadosAlGrupo(String oidGrupo) {
		List<UsuarioDTO> resul = new ArrayList<UsuarioDTO>();
		List<TldUsuario> usuariosBD = usuarioDao.findAllNoGrupo(oidGrupo);
		//Itero los usuarios que me traigo la BD
		for(TldUsuario usuarioIterado : usuariosBD){
			UsuarioDTO usuarioDto = new UsuarioDTO(usuarioIterado);
			resul.add(usuarioDto);
		}
		
		
		return resul;
	}

	@Override
	public List<UsuarioDTO> listaUsuariosDeUnGrupo(String oidGrupo, String oidUsuarioRegistrado) {
		List<UsuarioDTO> resul = new ArrayList<UsuarioDTO>();
		List<TldUsuario> usuariosBD = usuarioDao.findAllGrupo(oidGrupo, oidUsuarioRegistrado);
		//Itero los usuarios que me traigo la BD
		for(TldUsuario usuarioIterado : usuariosBD){
			UsuarioDTO usuarioDto = new UsuarioDTO(usuarioIterado);
			resul.add(usuarioDto);
		}
		
		
		return resul;
	}

	@Override
	public List<UsuarioDTO> listaUsuariosDeUnGrupoIncluyeCreador(
			String oidGrupo, String oidUsuarioRegistrado) {
		List<UsuarioDTO> resul = new ArrayList<UsuarioDTO>();
		List<TldUsuario> usuariosBD = usuarioDao.findAllGrupoIncluyeCreador(oidGrupo, oidUsuarioRegistrado);
		//Itero los usuarios que me traigo la BD
		for(TldUsuario usuarioIterado : usuariosBD){
			UsuarioDTO usuarioDto = new UsuarioDTO(usuarioIterado);
			resul.add(usuarioDto);
		}
		
		
		return resul;
	}

	@Override
	public TldUsuario encuentra(String oid) {
		return usuarioDao.findById(oid);
	}

	@Override
	public void cambioPassword(TldUsuario obj) throws TimeLapseException {
		usuarioDao.save(obj);
		
	}

}
