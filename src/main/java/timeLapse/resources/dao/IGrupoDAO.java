package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TldGrupo;
/**
 * Se encarga de la persistencia sobre la tabla tldGrupo
 * @author robe
 *
 */
public interface IGrupoDAO {
	
	
    /**
     * Función que devuelve una lista con todas los grupos creados por el usuario
     * pasado com parámetro
     * @return
     */
    public List<TldGrupo> findAllCreados(String oidUsuario);  
    
    /**
     * Función que devulve todos los grupos a los que pertenece el usuario pasado como parámetro
     * @param oidUsuario
     * @return
     */
    public List<TldGrupo> findAllPertenecientes(String oidUsuario);  
    
    /**
     * Método que guarda la categoría que se le pasa como parámetro en la BD
     * @param grupo
     */
    public void save(TldGrupo grupo);
    /**
     * Función que devuelve el grupo con el oid pasado como parámetro
     * @param oid
     * @return
     */
    public TldGrupo find(String oid);
    /**
     * Método que borra de la base de datos el grupo con oid pasado como parámetro
     * @param oid
     * @return el grupo eliminado
     */
    public TldGrupo remove(String oid);

    /**
     * Función que devuelve los grupos creados o asociados al usuario pasado como parámetro y que han sido 
     * asociados a alguna tarea
     * @param oidUsuario
     * @return
     */
	public List<TldGrupo> findAllUtilizados(String oidUsuario);

}
