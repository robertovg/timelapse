package timeLapse.business.security;

import java.util.List;

import javax.servlet.http.HttpSession;

import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.util.exceptions.TimeLapseException;

public interface ISeguridadBO {
	
	/**
	 * Función que se encargará de comprobar si el nombre de usuario, contraseña que se le pasa como 
	 * parámetros son correctas. La contraseña se encuentra codificada en MD5 para guardar la confidencialidad
	 * de los datos del usuario
	 * @param usuario
	 * @param password
	 * @return
	 * @throws TimeLapseException 
	 */
	public UsuarioDTO login(String nombreUsuario, String passwordMD5) throws TimeLapseException;
	
	/**
	 * Método que se encarga de devolver el usuario de la base de datos. Envía una cookie para recordar
	 * el nombreUsuario, contraseña en un futuro
	 * @param session
	 * @param nombreUsuario
	 * @return passwordMD5
	 * @throws Exception
	 */
	public boolean procesadoLogin (HttpSession session, String nombreUsuario, String password);
	
	/**
	 * Función que se encarga de comprobar si el rol pasado como parámetro tiene permisos para
	 * ejecutar alguna de las acciones pasadas como parámetro
	 * @param rol
	 * @param acciones
	 * @return
	 */
	public boolean tienePermisos(TlpRol rol, List<String> acciones);


}
