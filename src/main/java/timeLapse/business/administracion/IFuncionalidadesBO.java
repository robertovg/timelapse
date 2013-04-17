package timeLapse.business.administracion;

import java.util.List;

import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpRol;


public interface IFuncionalidadesBO {
	
	/**
	 * Devuelve todas las funcionalidadeds de la aplicación
	 * @return
	 * @throws Exception
	 */
	public List<TlpFuncionalidad> listaFuncionalidad()throws Exception;

	/**
	 * Devuelve todas las funcionalidades que pertenezcan al objetivo pasado como parámetro y para los que tenga
	 * permisos el rol.También se encarga de hacer que las acciones asociadasa este sean solo las para las que
	 * tenga permisos el rol
	 * @param objetivoOid
	 * @param rol
	 * @return
	 * @throws Exception
	 */
	public List<TlpFuncionalidad> listaFuncionalidades(String objetivoOid, TlpRol rol) throws Exception;
	/**
	 * Método que actualiza la funcionalidad pasada como parámetro en la BBDD
	 * @param obj
	 * @throws Exception
	 */
	public void actualiza(TlpFuncionalidad obj) throws Exception;
	/**
	 * Función que elimina de la base de datos la funcionaliad con ID pasado como parámetro
	 * @param oid
	 * @return la funcionalidad eliminada
	 * @throws Exception
	 */
	public TlpFuncionalidad elimina(String oid) throws Exception;
	/**
	 * Función que devuelve la función con el oid pasado como parámetro
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public TlpFuncionalidad encuentra(String oid) throws Exception;


}
