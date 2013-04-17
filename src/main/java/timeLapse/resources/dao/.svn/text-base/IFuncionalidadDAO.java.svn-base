package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpRol;
/**
 * Se encarga de la persistencia sobre la tabla Funcionalidad
 * @author robe
 *
 */
public interface IFuncionalidadDAO {
	/**
	 * Función que devuelve la funcionalidad cuyo oid coincide con el pasado por parámetro
	 * @param oid
	 * @return
	 */
	public TlpFuncionalidad find(String oid);
	/**
	 * Función que se encarga de devolver todas las funcionalidades cuyo objetivo tenga como 
	 * oid el pasado como parámetro y para las que tenga permisos el rol pasado como parámetro
	 * @param objOID
	 * @return
	 */
    public List<TlpFuncionalidad> findAll(String objOID, TlpRol rol);
    /**
     * Función que se encarga de devolver todas las funcionalidades que se encuentran en la aplicación
     * @return
     */
    public List<TlpFuncionalidad> findAll();
    /**
     * Método que guarda en la base de datos a la funcionalidad pasada como parámetro
     * @param dfunc
     */
    public void save(TlpFuncionalidad dfunc);
    /**
     * Método que borra de la base de datos la funcionalidad que tiene como oid el oid pasado como parámetro
     * @param oid
     * @return funcionalidad eliminada
     */
    public TlpFuncionalidad remove(String oid);

}
