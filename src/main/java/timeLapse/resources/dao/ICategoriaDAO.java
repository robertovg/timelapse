package timeLapse.resources.dao;

import java.util.List;

import timeLapse.resources.persistenceDTO.TldCategoria;
/**
 * Se encarga de la persistencia sobre la tabla Categoria
 * @author robe
 *
 */
public interface ICategoriaDAO {
	
	
    /**
     * Función que devuelve una lista con todas las funcionalidades que existen en la BD, asociadas al usuario
     * de la aplicación
     * @return
     */
    public List<TldCategoria> findAll(String oidUsuario);  
    
    
    /**
     * Método que guarda la categoría que se le pasa como parámetro en la BD
     * @param dacc
     */
    public void save(TldCategoria categoria);
    /**
     * Función que devuelve la categoría con el oid pasado como parámetro
     * @param oid
     * @return
     */
    public TldCategoria find(String oid);
    /**
     * Método que borra de la base de datos la categoría con oid pasado como parámetro
     * @param oid
     * @return La categoría eliminada
     */
    public TldCategoria remove(String oid);


    /**
     * Función que devuelve todas las categorías del usuario pasado como parémtro que 
     * @param oidUsuario
     * @return
     */
	public List<TldCategoria> findAllUtilizados(String oidUsuario);

}
