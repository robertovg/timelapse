package timeLapse.business.administracion;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;

public interface IAccionesBO {	
	
	/**
	 * Función que devuelve una lista con las acciones que tienen como funcionalidad la funcionalidad con el oid
	 * que se pasa como parámetro y para las que tenga permisos el rol
	 * @param objOID
	 * @return
	 */
	public List<TlpAccion> findAll(String funcionalidadOID, TlpRol rol) ;
	
	/**
	 * Lista las acciones que tiene la aplicación
	 * @return
	 * @throws Exception
	 */
	public List<TlpAccion> listaAcciones()throws Exception;
	/**
	 * Lista las acciones no asociadas al rol
	 * @param rol
	 * @return
	 */
	public List<TlpAccion> listaAccionesNoAsociadas(String oidRol);
	
	/**
	 * Lista las acciones asociadas al rol
	 * @param rol
	 * @return
	 */
	public List<TlpAccion> listaAccionesAsociadas(String oidRol);
	
	/**
	 * Actualiza la acción pasada como parámetro
	 * @param obj
	 * @throws Exception
	 */
	public void actualiza(TlpAccion obj) throws Exception;
	
	/**
	 * Elimina la acción pasada como parámetro
	 * @param oid
	 * @return la acción eliminada
	 * @throws Exception
	 */
	public TlpAccion elimina(String oid) throws Exception;
	
	/**
	 * Función que devuelve la acción que tiene el oid pasado como parámetro 
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TlpAccion encuentra(String oid) throws Exception;


}
