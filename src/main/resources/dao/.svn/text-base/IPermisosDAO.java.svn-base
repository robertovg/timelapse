package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.resources.persistenceDTO.TlrPermiso;

public interface IPermisosDAO {
	
	/**
	 * Efectua la vinculación de los Roles y las acciones
	 * @param rol
	 * @param acciones
	 */
	public void vinculaRolYAcciones(TlpRol rol, List<TlpAccion> acciones);
	
	/**
	 * Efectua la desvinculación de los Roles y las acciones
	 * @param rol
	 * @param acciones
	 */
	public void desvinculaRolYAcciones(TlpRol rol, List<TlpAccion> acciones);
	
	/**
	 * Función que busca los permisos, para el rol y acciones pasadas como parámetro
	 * @param rol
	 * @return
	 */
	public List<TlrPermiso> findAll(TlpRol rol, List<TlpAccion> acciones);
	
	/**
	 * Función que busca los permisos, para el rol y los path de las acciones pasadas como parámetro
	 * @param rol
	 * @return
	 */
	public List<TlrPermiso> findAllPorNombre(TlpRol rol, List<String> acciones);

}
