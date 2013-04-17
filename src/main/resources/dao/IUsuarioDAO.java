package timeLapse.resources.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import timeLapse.resources.persistenceDTO.TldUsuario;

/**
 * Se encarga de la persistencia sobre la tabla Usuario
 * 
 * @author robe
 * 
 */
public interface IUsuarioDAO {
	/**
	 * Función que devuelve una lista con los usuario de la aplicación, no asociados con el grupo
	 * pasado como parámetro
	 * @param oidGrupo
	 * @return
	 */
	public List<TldUsuario> findAllNoGrupo(String oidGrupo);

	/**
	 * Método que se encarga de la persistencia del objeto Usuario
	 * 
	 * @param usuario
	 */
	public void save(TldUsuario usuario);

	/**
	 * Función que busca al usuario por nombre usuario y contraseña en la base
	 * de datos de la aplicación, si no lo encuentra devuelve un null
	 * 
	 * @param nombreUsuario
	 * @param passwordMD5
	 * @return
	 * @throws NonUniqueResultException
	 * @throws NoResultException 
	 */
	public TldUsuario findByNombreUsuarioYPassword(String nombreUsuario,
			String passwordMD5) throws NonUniqueResultException, NoResultException;
	/**
	 * Función que se encarga de buscar los usuarios registrados en la aplicación con el nombre pasado como parámetro
	 * @param nombreUsuario
	 * @return
	 */
	public List<TldUsuario> findByNombre(String nombreUsuario);
	
	/**
	 * Función que se encarga de buscar los usuarios pertenecientes al grupo de usuario pasado como parámetro 
	 * si lo está ejecutando el usuario creador del grupo o al usuario logueado si (aunque no seas el usuario
	 *  creador del grupo), estés vinculado con él. Esto permitirá desvincularte de un grupo al que estés vinculado. 
	 * @param oidGrupo identificador del grupo
	 * @param oidUsuarioRegistrado identificador del usuario registrado
	 * @return
	 */
	public List<TldUsuario> findAllGrupo(String oidGrupo, String oidUsuarioRegistrado);
	
	/**
	 * Función que se encarga de buscar los usuarios pertenecientes al grupo de usuario pasado como parámetro 
	 * si lo está ejecutando el usuario creador del grupo o  al usuario logueado si 
	 * (aunque no seas el usuario creador del grupo), estés vinculado con él. Esto permitirá desvincularte
	 *  de un grupo al que estés vinculado. 
	 * @param oidGrupo identificador del grupo
	 * @param oidUsuarioRegistrado identificador del usuario registrado
	 * @return
	 */
	public List<TldUsuario> findAllGrupoIncluyeCreador(String oidGrupo, String oidUsuarioRegistrado);
	
	/**
	 * Función que devuelve el usuario con el oid pasado como parámetro
	 * @param oidUsuario
	 * @return
	 */
	public TldUsuario findById(String oidUsuario);
	

}
