package timeLapse.business.grupos;

import java.util.List;

import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.util.exceptions.TimeLapseException;
/**
 * Interfaz que define la lógica de negocio asociada directamente con los grupos
 * @author robe
 *
 */
public interface IGrupoBO{	
	
	/**
	 * Función que devuelve los grupos creados por el usuario con OID pasado como parámetro
	 * @param oidUsuario
	 * @return
	 * @throws Exception
	 */
	public List<TldGrupo> listaGruposCreados(String oidUsuario)throws Exception;
	
	/**
	 * Función que devuelve los grupos utilizados por el usuario pasado como parámetro, y 
	 * que han sido asociados a alguna tarea
	 * @param oidUsuario
	 * @param dejaSoloAsociadas si vale true, solo añade las asociadas, no todas
	 * @return
	 * @throws Exception
	 */
	public List<TldGrupo> listaGruposUtilizados(String oidUsuario, boolean dejaSoloAsociadas)throws Exception;
	
	/**
	 * Función que devuelve los grupos a los que pertenece o es creador el usuario con el OID pasado como parámertro
	 * @param oidUsuario
	 * @return
	 * @throws Exception
	 */
	public List<TldGrupo> listaGruposPertenecientes(String oidUsuario)throws Exception;
	
	/**
	 * Actualiza o crea el grupo pasado como parámetro.
	 * @param grupo
	 * @throws Exception
	 */
	public void actualiza(TldGrupo grupo) throws Exception;
	
	/**
	 * Elimina el grupo pasado como parámetro
	 * @param oid
	 * @return el grupo eliminado
	 * @throws Exception
	 */
	public TldGrupo elimina(String oid) throws Exception;
	
	/**
	 * Función que devuelve el grupo con oid pasado como parámetro
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TldGrupo encuentra(String oid) throws Exception;
	
	
	/**
	 * Método que se encarga de crear las relaciones entre los usuario y grupo pasados como parámetro
	 * @param oidGrupo
	 * @param listOidUsuarios
	 * @throws TimeLapseException
	 */
	public void vinculaUsuariosGrupo(String oidGrupo, List<String> listOidUsuarios) throws TimeLapseException;
	
	/**
	 * Método que se encarga de eliminar las relaciones entre los usuarios y grupo pasados como parámetro
	 * @param oidGrupo
	 * @param listOidUsuarios
	 * @throws TimeLapseException
	 */
	public void desvinculaUsuarioGrupo(String oidGrupo, List<String> listOidUsuarios) throws TimeLapseException;

}
