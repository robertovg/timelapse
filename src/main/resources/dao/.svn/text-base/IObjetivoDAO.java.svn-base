package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpObjetivo;
import timeLapse.resources.persistenceDTO.TlpRol;
/**
 * Clase que se encarga de gestionar la persistencia sobre la tabla de Objetivos
 * @author robe
 *
 */
public interface IObjetivoDAO {
    /**
     * Función que devuelve el objetivo con oid pasado como parámetro
     * @param oid
     * @return
     */
	public TlpObjetivo find(String oid);
	
	/**
     * Función que devuelve a todos los objetivos de la aplicación
     * @return
     */
	public List<TlpObjetivo> findAll();
	/**
	 * Función que devuelve los objetivos para los que tiene permisos el rol pasado como parámetro
	 * @param rol
	 * @return
	 */
	public List<TlpObjetivo> findAll(TlpRol rol);
	/**
	 * Método que hace persistentes al objetivo pasado como parámetro
	 * @param TlpObjetivo
	 */
    public void save(TlpObjetivo TlpObjetivo);
    /**
     * Método que elimina de la aplicación al objetivo cuyo id sea el pasado como parámetro
     * @param oid
     * @return objetivo eliminado
     */
    public TlpObjetivo remove(String oid);

}
