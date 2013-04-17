package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;
/**
 * Se encarga de la persistencia sobre la tabla Acciones
 * @author robe
 *
 */
public interface IAccionDAO {
	/**
	 * Función que devuelve una lista con las acciones que tienen como funcionalidad la funcionalidad con el oid
	 * que se pasa como parámetro y para las que tenga permisos el rol
	 * @param objOID
	 * @return
	 */
	public List<TlpAccion> findAll(String funcionalidadOID, TlpRol rol) ;
    /**
     * Función que devuelve una lista con todas las funcionalidades que existen en la BD
     * @return
     */
    public List<TlpAccion> findAll();  
    
    
    /**
     * Función que devuelve las acciones no asociadas al rol pasado como parámetro
     * @param rol
     * @return
     */
    public List<TlpAccion> findNoAsociadas(TlpRol rol);
    
    
    /**
     * Función que devuelve las acciones asociadas al rol pasado como parámetro
     * @param rol
     * @return
     */
    public List<TlpAccion> findAsociadas(TlpRol rol);   
 
    
    /**
     * Método que guarda la acción que se le pasa como parámetro en la BD
     * @param dacc
     */
    public void save(TlpAccion dacc);
    /**
     * Función que devuelve la acción con el oid pasado como parámetro
     * @param oid
     * @return
     */
    public TlpAccion find(String oid);
    /**
     * Método que borra de la base de datos la acción con oid pasado como parámetro
     * @param oid
     * @return la acción devuelta
     */
    public TlpAccion remove(String oid);

}
