package timeLapse.controller.actions.abs;

import java.util.List;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;

/**
 * Clase abstacta de la que heredarán todos los actions que devuelven una lista de tareas. Este action
 * contiene los atributos necesarios para realizar los filtros de las tareas.
 * @author robe
 *
 */
@SuppressWarnings("serial")
public abstract class ListaTareasActionTemplate extends UserValueAction {
	
	protected Integer tareasRealizadas;		
	protected Integer temporizadas;
	protected Integer tareasQueSonPadre;
	protected Integer autorrealizables;
	protected Integer periodicas;
	protected List<String> categorias;
	protected List<String> grupos;
	protected List<String> tareasPadres;
	protected String fechaPlanificacionInferior;
	protected String fechaPlanificacionSuperior;
	protected String fechaRealizacionInferior;
	protected String fechaRealizacionSuperior;
	protected String cadenaBusqueda;
	protected Integer orden;
	
	
	/**
	 * @return the tareasRealizadas
	 */
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.tareasRealizadas", min = "-1", max = "1")
	public Integer getTareasRealizadas() {
		return tareasRealizadas;
	}
	/**
	 * @param tareasRealizadas the tareasRealizadas to set
	 */
	public void setTareasRealizadas(Integer tareasRealizadas) {
		this.tareasRealizadas = tareasRealizadas;
	}
	
	/**
	 * @return the temporizadas
	 */
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.temporizadas", min = "-1", max = "1")
	public Integer getTemporizadas() {
		return temporizadas;
	}
	/**
	 * @param temporizadas the temporizadas to set
	 */
	public void setTemporizadas(Integer temporizadas) {
		this.temporizadas = temporizadas;
	}
	/**
	 * @return the categorias
	 */
	public List<String> getCategorias() {
		return categorias;
	}
	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	/**
	 * @return the grupos
	 */
	public List<String> getGrupos() {
		return grupos;
	}
	/**
	 * @param grupos the grupos to set
	 */
	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}
	/**
	 * @return the tareasPadres
	 */
	public List<String> getTareasPadres() {
		return tareasPadres;
	}
	/**
	 * @param tareasPadres the tareasPadres to set
	 */
	public void setTareasPadres(List<String> tareasPadres) {
		this.tareasPadres = tareasPadres;
	}
	/**
	 * @return the fechaPlanificacionInferior
	 */
	public String getFechaPlanificacionInferior() {
		return fechaPlanificacionInferior;
	}
	/**
	 * @param fechaPlanificacionInferior the fechaPlanificacionInferior to set
	 */
	public void setFechaPlanificacionInferior(String fechaPlanificacionInferior) {
		this.fechaPlanificacionInferior = fechaPlanificacionInferior;
	}
	/**
	 * @return the fechaPlanificacionSuperior
	 */
	public String getFechaPlanificacionSuperior() {
		return fechaPlanificacionSuperior;
	}
	/**
	 * @param fechaPlanificacionSuperior the fechaPlanificacionSuperior to set
	 */
	public void setFechaPlanificacionSuperior(String fechaPlanificacionSuperior) {
		this.fechaPlanificacionSuperior = fechaPlanificacionSuperior;
	}
	/**
	 * @return the fechaRealizacionInferior
	 */
	public String getFechaRealizacionInferior() {
		return fechaRealizacionInferior;
	}
	/**
	 * @param fechaRealizacionInferior the fechaRealizacionInferior to set
	 */
	public void setFechaRealizacionInferior(String fechaRealizacionInferior) {
		this.fechaRealizacionInferior = fechaRealizacionInferior;
	}
	/**
	 * @return the fechaRealizacionSuperior
	 */
	public String getFechaRealizacionSuperior() {
		return fechaRealizacionSuperior;
	}
	/**
	 * @param fechaRealizacionSuperior the fechaRealizacionSuperior to set
	 */
	public void setFechaRealizacionSuperior(String fechaRealizacionSuperior) {
		this.fechaRealizacionSuperior = fechaRealizacionSuperior;
	}
	/**
	 * @return the orden
	 */
	
	public Integer getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.orden", min = "0", max = "1")
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	/**
	 * @return the tareasQueSonPadre
	 */
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.tareasQueSonPadre", min = "-1", max = "1")
	public Integer getTareasQueSonPadre() {
		return tareasQueSonPadre;
	}
	/**
	 * @param tareasQueSonPadre the tareasQueSonPadre to set
	 */
	public void setTareasQueSonPadre(Integer tareasQueSonPadre) {
		this.tareasQueSonPadre = tareasQueSonPadre;
	}
	/**
	 * @return the autorrealizables
	 */
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.autorrealizables", min = "-1", max = "1")
	public Integer getAutorrealizables() {
		return autorrealizables;
	}
	/**
	 * @param autorrealizables the autorrealizables to set
	 */
	public void setAutorrealizables(Integer autorrealizables) {
		this.autorrealizables = autorrealizables;
	}
	/**
	 * @return the periodicas
	 */
	@IntRangeFieldValidator(key = "ListaTareasActionTemplate.periodicas", min = "-1", max = "1")
	public Integer getPeriodicas() {
		return periodicas;
	}
	/**
	 * @param periodicas the periodicas to set
	 */
	public void setPeriodicas(Integer periodicas) {
		this.periodicas = periodicas;
	}
	/**
	 * @return the cadenaBusqueda
	 */	
	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}
	/**
	 * @param cadenaBusqueda the cadenaBusqueda to set
	 */
	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}
	
	
	
}
