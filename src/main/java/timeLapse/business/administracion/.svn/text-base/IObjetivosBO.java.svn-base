package timeLapse.business.administracion;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpObjetivo;
import timeLapse.resources.persistenceDTO.TlpRol;


public interface IObjetivosBO{
	/**
	 * Lista todos los objetivos para los que existe al menus una funcionalidad con al menos una acción con  las que
	 * tiene permiso
	 * Traduce tanto el nombre como la  descripción al idioma en el que se realice el request
	 * @return
	 */
	public List<TlpObjetivo> listaObjetivos(TlpRol usuario)throws Exception;
	
	/**
	 * Lista todos los objetivos de la aplicación
	 * Traduce tanto el nombre como la  descripción al idioma en el que se realice el request
	 * @return
	 */
	public List<TlpObjetivo> listaObjetivos()throws Exception;
	
	
	/**
	 * Método que actualiza/salva el objetivo pasado como parámetro
	 * @param obj
	 * @throws Exception
	 */
	public void actualiza(TlpObjetivo obj) throws Exception;
	/**
	 * Eliminar el objetivo asociadao al oid pasado como parámetro
	 * @param oid
	 * @return objetivo eliminado
	 * @throws Exception
	 */
	public TlpObjetivo elimina(String oid) throws Exception;
	/**
	 * Función que encuentra el objetivo pasado como parámetro
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TlpObjetivo encuentra(String oid) throws Exception;


}
