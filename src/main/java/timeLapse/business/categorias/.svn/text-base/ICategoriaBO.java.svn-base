package timeLapse.business.categorias;

import java.util.List;

import timeLapse.resources.persistenceDTO.TldCategoria;
/**
 * Interfaz que define la lógica de negocio asociada directamente con las categorías
 * @author robe
 *
 */
public interface ICategoriaBO {	
	
	/**
	 * Lista las categorías que tiene en la aplicación el usuario pasado como parámetro
	 * @param oidUsuario
	 * @return
	 * @throws Exception
	 */
	public List<TldCategoria> listaCategorias(String oidUsuario)throws Exception;
	
	/**
	 * Función que devuelve todas las categorías del usuario pasado como parámetro y que han sido 
	 * asociadas a alguna tarea
	 * @param oidUsuario
	 * @param dejaSoloAsociadas si vale true, solo añade las asociadas, no todas
	 * @return
	 * @throws Exception
	 */
	public List<TldCategoria> listaCategoriasUtilizadas(String oidUsuario, boolean dejaSoloAsociadas)throws Exception;

	
	/**
	 * Actualiza la acción pasada como parámetro
	 * @param obj
	 * @throws Exception
	 */
	public void actualiza(TldCategoria obj) throws Exception;
	
	/**
	 * Elimina la acción pasada como parámetro
	 * @param oid
	 * @return La categoría que se elimina
	 * @throws Exception
	 *  
	 */
	public TldCategoria elimina(String oid) throws Exception;
	
	/**
	 * Función que devuelve la categoría con el oid pasado como parámetro
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TldCategoria encuentra(String oid) throws Exception;

}
