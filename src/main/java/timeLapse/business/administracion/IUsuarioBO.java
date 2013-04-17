package timeLapse.business.administracion;


import java.util.List;

import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.exceptions.TimeLapseException;

public interface IUsuarioBO {
	/**
	 * Método que sirve para actualizar / guardar los datos de un Usuario en la base de datos
	 * @param obj
	 * @throws TimeLapseException
	 */
	public void actualiza(TldUsuario obj) throws TimeLapseException;
	
	/**
	 * Método que muestra todos los usuarios de la aplicación
	 * @param oidGrupo 
	 * @return
	 */
	public List<UsuarioDTO> listaUsuariosNoAsociadosAlGrupo(String oidGrupo);

	/**
	 * Método que devuelve todos los usuarios pertenecientes a un grupo de usuarios
	 * si lo está ejecutando el usuario creador del grupo o al usuario logueado si (aunque no seas el usuario creador del grupo),
	 * estés vinculado con él. Esto permitirá desvincularte de un grupo al que estés vinculado. 
	 * @param oidGrupo
	 * @param oidUsuarioRegistrado
	 * @return
	 */
	public List<UsuarioDTO> listaUsuariosDeUnGrupo(String oidGrupo, String oidUsuarioRegistrado);
	
	/**
	 * Método que devuelve todos los usuarios pertenecientes a un grupo de usuarios
	 * si lo está ejecutando el usuario creador del grupo o al usuario logueado si (aunque no seas el usuario creador del grupo),
	 * estés vinculado con él. Esto permitirá desvincularte de un grupo al que estés vinculado. 
	 * @param oidGrupo
	 * @param oidUsuarioRegistrado
	 * @return
	 */
	public List<UsuarioDTO> listaUsuariosDeUnGrupoIncluyeCreador(String oidGrupo, String oidUsuarioRegistrado);
	
	
	/**
	 * Devuelve los datos del usuario con oid pasado como parámetro
	 * @param oid
	 * @return
	 */
	public TldUsuario encuentra(String oid);
	
	/**
	 * Cambia la contraseña del usuario pasado como parámetro
	 * @param obj
	 * @throws TimeLapseException
	 */
	public void cambioPassword(TldUsuario obj) throws TimeLapseException;

}
