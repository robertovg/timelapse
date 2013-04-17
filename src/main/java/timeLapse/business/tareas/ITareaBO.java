package timeLapse.business.tareas;

import java.util.Date;
import java.util.List;

import timeLapse.resources.dto.FiltroTareasDTO;
import timeLapse.resources.dto.TareaDTO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.util.exceptions.TimeLapseException;
/**
 * Interfaz que define la lógica de negocio asociada directamente con las Tareas
 * @author robe
 *
 */
public interface ITareaBO{	
	
	/**
	 * Devuevle todas las tareas del usuario pasado como parámetro o asociadas a él, según los
	 * paramétros indicados en el filtro tarea. Se le pasa el usuario registrado, para que indique si las
	 * tareas que devuelve están creadas por el usuario logueado.
	 * @param filtroTareas
	 * @param usuarioRegistrado
	 * @return
	 * @throws Exception
	 */
	public List<TareaDTO> listaTareas(FiltroTareasDTO filtroTareas, UsuarioDTO usuarioRegistrado) throws Exception;
	
	/**
	 * Lista las proximas n tarea que tengan el usuario
	 * @param filtroTareas
	 * @param usuarioRegistrado
	 * @return
	 * @throws Exception
	 */
	public List<TareaDTO> listaProximasTareas(FiltroTareasDTO filtroTareas, UsuarioDTO usuarioRegistrado) throws Exception;
	
	/**
	 * Función necesaria para listar las tareas padres del usuario conectado (neceasria para filtrar
	 * por ellas a la hora de buscar una tarea)
	 * @param oidUsuario
	 * @param dejaSoloAsociadas si vale true, solo añade las asociadas, no todas
	 * @return
	 */
	public List<TareaDTO> listaTareasPadre(String oidUsuario, boolean dejaSoloAsociadas);
	/**
	 * Función necesaria para listar las tareas que se pueden asociar como padres del usuario conectado
	 * (neceasria para asociarlas por ellas a la hora de crear/modificar una tarea).
	 * Devuelve solo tareas creadas por el usuario logueado.
	 * @param oidUsuario
	 * @param oidTareasPadre Si viene informado lo añade a la lista de tareas Padre (para poder modificar una tarea ya asociada
	 * @return
	 */
	public List<TareaDTO> listaTareasAAsociarComoPadres(String oidUsuario, String oidTareasPadre);
	/**
     * Método que guarda la tarea que se le pasa como parámetro en la BD
     * @param tarea
     */
	public void actualiza(TldTarea tarea) throws Exception;
	
	 /**
     * Método que borra de la base de datos la tarea con oid pasado como parámetro
     * @param oid
     * @param oidUsuarioRegistrado
     * @return devuelve la tarea eliminada
     */
	public TldTarea elimina(String oid, String oidUsuarioRegistrado) throws Exception;
	
    /**
     * Función que clona tarea pasad como parámetro
     * @param oidTarea 
     * @param oidUsuarioRegistrado
     * @return
     */
	public String clonarTarea(String oidTarea, String oidUsuarioRegistrado);

	/**
	 * Función que pone como fecha de finalización la fecha pasada como parámetro. Si se produce algún error no finaliza ninguna
	 * de las tareas pasadas y eleva una excepción diciendo cuales son las que han fallado.
	 * @param listID
	 * @return lista de tareas finalizadas
	 * @throws TimeLapseException
	 */
	public List<TldTarea> finalizarTareas(List<String> listID) throws TimeLapseException;
	
	/**
	 * Función que devuelve la tarea con oid pasado como parámetro
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TldTarea encuentra(String oid) throws Exception;

	/**
	 * Método que vincula la tareaPadre pasada como parámetro a las listTareasHijas 
	 * @param oidTareaPadre
	 * @param listOidTareasHijas
	 * @throws TimeLapseException 
	 */
	public void vinculaTareaTareas(String oidTareaPadre, List<String> listOidTareasHijas) throws TimeLapseException;

	/**
	 * Método que desvincula las tareasHijas pasadas como parámetro de sus respectivas tareas
	 * padres
	 * @param listaOIdTareasHijas
	 * @throws TimeLapseException 
	 */
	public void desvinculaTareaTareas(List<String> listaOIdTareasHijas) throws TimeLapseException;

	/**
	 * Método que vincula la lista de tareas pasadas como parámetros a las fechas y horas seleccionadas
	 * @param dateFechaInicio
	 * @param dateHoraInicio
	 * @param dateFechaFin
	 * @param dateHoraFin
	 * @param listOidEntidades
	 * @throws TimeLapseException
	 */
	public void vinculaTareasTiempo(Date dateFechaInicio, Date dateHoraInicio ,Date dateFechaFin, Date dateHoraFin,  List<String> listOidEntidades) throws TimeLapseException;
	
	
	/**
	 * Método que desvincula las tareas pasadas como parámetro de sus respectivas fechas de planificación
	 * padres
	 * @param listaOIdTareas
	 * @throws TimeLapseException 
	 */
	public void desvinculaTareasTiempo(List<String> listaOIdTareas) throws TimeLapseException;

	/**
	 * Método que vincula las tareas pasadas como parámetro con la categoría cuyo oid corresponde con el pasádo como 
	 * parámetro 
	 * @param oidCategoria
	 * @param listOidTareas
	 * @throws TimeLapseException 
	 */
	public void vinculaTareasCategoria(String oidCategoria, List<String> listOidTareas) throws TimeLapseException;

	/**
	 * Método que desvincula las tareas pasadas como parámetro de sus respectivas categorías
	 * @param listaOidTareas
	 * @throws TimeLapseException 
	 */
	public void desvinculaTareasCategoria(List<String> listaOidTareas) throws TimeLapseException;
	
	/**
	 * Método que vincula las tareas pasadas como parámetro con el grupo cuyo oid corresponde con el pasádo como 
	 * parámetro
	 * @param oidGrupo
	 * @param listOidTareas
	 * @throws TimeLapseException 
	 */
	public void vinculaTareasGrupo(String oidGrupo, List<String> listOidTareas) throws TimeLapseException;

	/**
	 * Método que desvincula las tareas pasadas como parámetro de sus respectivos grupos
	 * @param listaOidTareas
	 * @throws TimeLapseException 
	 */
	public void desvinculaTareasGrupo(List<String> listaOidTareas) throws TimeLapseException;
	
	/**
	 * Método que vincula las tareas pasadas como parámetro al usuario que 
	 * @param oidUsuario
	 * @param listOidTareas
	 * @throws TimeLapseException 
	 */
	public void vinculaUsuarioTareas(String oidUsuario, List<String> listOidTareas) throws TimeLapseException;

	/**
	 * Método que desvincula las tareas pasadas como de sus respectivos usuarios
	 * @param listaOidTareas
	 * @throws TimeLapseException 
	 */
	public void desvinculaUsuarioTareas(List<String> listaOidTareas) throws TimeLapseException;
	
	
}
