package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.dto.FiltroTareasDTO;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.resources.persistenceDTO.TldUsuario;
/**
 * Se encarga de la persistencia sobre la tabla Tareas
 * @author robe
 *
 */
public interface ITareaDAO {
	
	/**
	 * Devuevle todas las tareas ordenadas y que cumplan el filtro
	 * @param filtro a través del que se obtendrán las tareas
	 * @return
	 */
	public List<TldTarea> findAll(FiltroTareasDTO filtro);
	
	/**
	 * Función que encuentra todas las tareas padres del usuario pasado como parámetro (creadas o asociadas
	 * a él)
	 * @param oidUsuario
	 * @return
	 */
	public List<TldTarea> findAllPadres(String oidUsuario);
	
	/**
	 * Función que encuentar todas las tareas padre del que no son padres y que no estén finalizadas.
	 * Devuelve solo tareas creadas por el usuario logueado.
	 * @param oidUsuario
	 * @param oidTareasPadre 
	 * @return
	 */
	public List<TldTarea> findTareasAAsociarComoPadres(String oidUsuario, String oidTareasPadre);
	
	/**
     * Método que guarda la tarea que se le pasa como parámetro en la BD
     * @param tarea
     */
    public void save(TldTarea tarea);
    /**
     * Función que devuelve la tarea con el oid pasado como parámetro
     * @param oid
     * @return
     */
    public TldTarea find(String oid);
    /**
     * Método que borra de la base de datos la tarea con oid pasado como parámetro. Una vez realizada la acción 
     * devuelve la tarea eliminada.
     * @param oid
     * @return
     */
    public TldTarea remove(String oid);
    
    /**
     * Función que comprueba que no exista ya una copia de la tarea que se intenta copiar, para 
     * el usuario que está registrado
     * @param nombreTarea
     * @param oidUsuarioRegistrado
     * @return
     */
    public boolean existeTareaConEsteNombre(String nombreTarea, String oidUsuarioRegistrado);
    
    /**
     * Método que se encarga de buscar todas las tareas creadas por alguno de esos usuarios que van a ser
     * desvinculados del grupo, y que se encuentren vinculados al grupo y desvincularlas
     * @param grupo
     * @param listaUsuariosADesasociar
     */
	public void desvinculaTareasGrupoDesvinculado(TldGrupo grupo,
			List<TldUsuario> listaUsuariosADesasociar);

	
	/**
	 * Método que se encarga de buscar las tareas relacionadas con cada uno de los usuarios que se van a desvincular
	 * del grupo y desvincular esas tareas de los respectivos usuarios
	 * @param grupo
	 * @param listaUsuariosADesasociar
	 */
	public void desvinculaTareasUsuariosDelGrupoADesvinculado(TldGrupo grupo,
			List<TldUsuario> listaUsuariosADesasociar);
    
    
    
    
    

}
