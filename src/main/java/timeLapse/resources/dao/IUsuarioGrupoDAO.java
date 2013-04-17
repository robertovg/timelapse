package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlrUsuarioGrupo;
/**
 * Dao de la clase TlrUsuarioGrupo
 * @author roberto
 *
 */
public interface IUsuarioGrupoDAO {
	
	/**
	 * Efectua la vinculación de un grupo y una lista de usuarios
	 * @param grupo
	 * @param usuarios
	 */
	public void vinculaUsuarioGrupo(TldGrupo grupo, List<TldUsuario> usuarios);
	
	/**
	 * Efectua la desvinculación de un grupo y una lista de usuarios
	 *  * @param grupo
	 * @param usuarios
	 */
	public void desvinculaUsuarioGrupo(TldGrupo grupo, List<TldUsuario> usuarios);
	
	/**
	 * Función que busca las relaciones entre usuarios y grupos
	 * @param rol
	 * @return
	 */
	public List<TlrUsuarioGrupo> findAll(TldGrupo grupo, List<TldUsuario> usuarios);
	

}
