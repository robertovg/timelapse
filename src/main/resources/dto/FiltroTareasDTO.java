package timeLapse.resources.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timeLapse.util.constantes.EstadoFilros;
import timeLapse.util.constantes.Globales;
import timeLapse.util.constantes.OrdenTareas;
/**
 * Clase que servirá para contener toda la información a través de la cual las lista de tareas se podrán
 * filtrar
 * @author robe
 */
public class FiltroTareasDTO implements Globales{
	private String oidUsuario;
	/**
	 * Según valga true o false, muestra también las tareas que no ha creado el usuario (las asociadas por 
	 * ejemplo). Por defecto vale true.
	 */
	private boolean incluyeNoCreadas;
	private EstadoFilros tareasRealizadas;
	private EstadoFilros tareasQueSonPadre;	
	private EstadoFilros temporizadas;
	private EstadoFilros autorrealizables;
	private EstadoFilros periodicas;
	private EstadoFilros vinculadasUsuario;
	
	/**
	 * Cuando uno de los filtos tiene unicamente tiene "*" significa todas las categorías, y "-"
	 * se traduce como las tareas no asociadas a categorías, "@" por asociadas.
	 */
	private List<String> categorias;
	/**
	 * Cuando uno de los filtos tiene unicamente tiene "*" significa todos los grupos, y "-"
	 * se traduce como las tareas no asociadas grupos, "@" por asociadas.
	 */
	private List<String> grupos;
	/**
	 * Cuando uno de los filtos tiene unicamente tiene "*" significa todas las tareas, y "-"
	 * se traduce como las tareas no asociadas tareas, "@" por asociadas. 
	 */
	private List<String> tareasPadres;
	
	private Date fechaPlanificacionInferior;
	private Date fechaPlanificacionSuperior;
	private Date fechaRealizacionInferior;
	private Date fechaRealizacionSuperior;
	private Date fechaCreacionInferior;
	private Date fechaCreacionSuperior;
	private String cadenaContiene;
	private String cadenaNoContiene;
	private String ubicacion;
	private List<String> listOidTareasEspecificas;
	private OrdenTareas orden;
	/**
	 * Constructor por defecto del FiltroTareasDTO
	 */
	public FiltroTareasDTO() {
		super();
		this.incluyeNoCreadas = true;
		this.tareasRealizadas = EstadoFilros.incluir;
		this.tareasQueSonPadre = EstadoFilros.incluir;
		this.temporizadas = EstadoFilros.incluir;
		this.autorrealizables = EstadoFilros.incluir;
		this.periodicas = EstadoFilros.incluir;
		this.vinculadasUsuario = EstadoFilros.incluir;
		this.categorias = new ArrayList<String>();
		this.grupos = new ArrayList<String>();
		this.tareasPadres = new ArrayList<String>();
		this.listOidTareasEspecificas = new ArrayList<String>();
		this.orden = OrdenTareas.importancia;
	}
	

	/**
	 * Constructor del Filtor con todos los parámetros
	 * @param oidUsuario
	 * @param tareasRealizadas
	 * @param tareasPadre
	 * @param temporizadas
	 * @param autorrealizables
	 * @param periodicas
	 * @param cadenaBusqueda
	 * @param categorias
	 * @param grupos
	 * @param tareasPadres
	 * @param fechaPlanificacionInferior
	 * @param fechaPlanificacionSuperior
	 * @param fechaRealizacionInferior
	 * @param fechaRealizacionSuperior
	 * @param orden
	 */
	public FiltroTareasDTO(String oidUsuario, EstadoFilros tareasRealizadas,
			EstadoFilros tareasPadre, EstadoFilros temporizadas,
			EstadoFilros autorrealizables, EstadoFilros periodicas,
			String cadenaBusqueda, List<String> categorias,
			List<String> grupos, List<String> tareasPadres,
			Date fechaPlanificacionInferior, Date fechaPlanificacionSuperior,
			Date fechaRealizacionInferior, Date fechaRealizacionSuperior,
			OrdenTareas orden) {
		super();
		this.oidUsuario = oidUsuario;
		this.tareasRealizadas = tareasRealizadas;
		this.tareasQueSonPadre = tareasPadre;
		this.temporizadas = temporizadas;
		this.autorrealizables = autorrealizables;
		this.periodicas = periodicas;
		this.cadenaContiene = cadenaBusqueda;
		this.categorias = categorias;
		this.grupos = grupos;
		this.tareasPadres = tareasPadres;
		this.fechaPlanificacionInferior = fechaPlanificacionInferior;
		this.fechaPlanificacionSuperior = fechaPlanificacionSuperior;
		this.fechaRealizacionInferior = fechaRealizacionInferior;
		this.fechaRealizacionSuperior = fechaRealizacionSuperior;
		this.orden = orden;
	}




	/**
	 * @return the oidUsuario
	 */
	public String getOidUsuario() {
		return oidUsuario;
	}

	/**
	 * @param oidUsuario the oidUsuario to set
	 */
	public void setOidUsuario(String oidUsuario) {
		this.oidUsuario = oidUsuario;
	}

	/**
	 * @return the incluyeNoCreadas
	 */
	public boolean isIncluyeNoCreadas() {
		return incluyeNoCreadas;
	}


	/**
	 * @param incluyeNoCreadas the incluyeNoCreadas to set
	 */
	public void setIncluyeNoCreadas(boolean incluyeNoCreadas) {
		this.incluyeNoCreadas = incluyeNoCreadas;
	}


	/**
	 * @return the tareasRealizadas
	 */
	public EstadoFilros getTareasRealizadas() {
		return tareasRealizadas;
	}

	/**
	 * @param tareasRealizadas the tareasRealizadas to set
	 */
	public void setTareasRealizadas(EstadoFilros tareasRealizadas) {
		this.tareasRealizadas = tareasRealizadas;
	}

	/**
	 * @return the incluirTareasPadre
	 */
	public EstadoFilros getTareasQueSonPadre() {
		return tareasQueSonPadre;
	}

	/**
	 * @param incluirTareasPadre the incluirTareasPadre to set
	 */
	public void setTareasQueSonPadre(EstadoFilros incluirTareasPadre) {
		this.tareasQueSonPadre = incluirTareasPadre;
	}

	/**
	 * @return the incluirTemporizadas
	 */
	public EstadoFilros getTemporizadas() {
		return temporizadas;
	}
	/**
	 * @param incluirTemporizadas the incluirTemporizadas to set
	 */
	public void setTemporizadas(EstadoFilros incluirTemporizadas) {
		this.temporizadas = incluirTemporizadas;
	}
	
	
	
	/**
	 * @return the autorrealizables
	 */
	public EstadoFilros getAutorrealizables() {
		return autorrealizables;
	}


	/**
	 * @param autorrealizables the autorrealizables to set
	 */
	public void setAutorrealizables(EstadoFilros autorrealizables) {
		this.autorrealizables = autorrealizables;
	}


	/**
	 * @return the periodicas
	 */
	public EstadoFilros getPeriodicas() {
		return periodicas;
	}


	/**
	 * @param periodicas the periodicas to set
	 */
	public void setPeriodicas(EstadoFilros periodicas) {
		this.periodicas = periodicas;
	}


	/**
	 * @return the vinculadasUsuario
	 */
	public EstadoFilros getVinculadasUsuario() {
		return vinculadasUsuario;
	}


	/**
	 * @param vinculadasUsuario the vinculadasUsuario to set
	 */
	public void setVinculadasUsuario(EstadoFilros vinculadasUsuario) {
		this.vinculadasUsuario = vinculadasUsuario;
	}


	/**
	 * @return the cadenaBusqueda
	 */
	public String getCadenaContiene() {
		return cadenaContiene;
	}


	/**
	 * @param cadenaBusqueda the cadenaBusqueda to set
	 */
	public void setCadenaContiene(String cadenaBusqueda) {
		this.cadenaContiene = cadenaBusqueda;
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
	public Date getFechaPlanificacionInferior() {
		return fechaPlanificacionInferior;
	}

	/**
	 * @param fechaPlanificacionInferior the fechaPlanificacionInferior to set
	 */
	public void setFechaPlanificacionInferior(Date fechaPlanificacionInferior) {
		this.fechaPlanificacionInferior = fechaPlanificacionInferior;
	}

	/**
	 * @return the fechaPlanificacionSuperior
	 */
	public Date getFechaPlanificacionSuperior() {
		return fechaPlanificacionSuperior;
	}

	/**
	 * @param fechaPlanificacionSuperior the fechaPlanificacionSuperior to set
	 */
	public void setFechaPlanificacionSuperior(Date fechaPlanificacionSuperior) {
		this.fechaPlanificacionSuperior = fechaPlanificacionSuperior;
	}

	/**
	 * @return the fechaRealizacionInferior
	 */
	public Date getFechaRealizacionInferior() {
		return fechaRealizacionInferior;
	}

	/**
	 * @param fechaRealizacionInferior the fechaRealizacionInferior to set
	 */
	public void setFechaRealizacionInferior(Date fechaRealizacionInferior) {
		this.fechaRealizacionInferior = fechaRealizacionInferior;
	}

	/**
	 * @return the fechaRealizacionSuperior
	 */
	public Date getFechaRealizacionSuperior() {
		return fechaRealizacionSuperior;
	}

	/**
	 * @param fechaRealizacionSuperior the fechaRealizacionSuperior to set
	 */
	public void setFechaRealizacionSuperior(Date fechaRealizacionSuperior) {
		this.fechaRealizacionSuperior = fechaRealizacionSuperior;
	}
	/**
	 * @return the orden
	 */
	public OrdenTareas getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(OrdenTareas orden) {
		this.orden = orden;
	}
	

	/**
	 * Método que comprueba que el estado del filtro sea coherente. Es importante que al DAO le llegue
	 * validado ya que sino se podrían obtener fallos derivados 
	 * @return
	 */
	public boolean esCorrecto(){
 		boolean resul = true;
		//Primero compurebo nulos.
//		if(StringUtils.isBlank(this.oidUsuario)){
//			resul = false;
//		}
		if(resul && this.tareasRealizadas == null){
			resul = false;
		}
		if(resul && this.tareasQueSonPadre == null){
			resul = false;
		}
		if(resul && this.temporizadas == null){
			resul = false;
		}
		if(resul && this.categorias == null){
			resul = false;
		}
		if(resul && this.grupos == null){
			resul = false;
		}
		if(resul && this.tareasPadres == null){
			resul = false;
		}
		if(resul && this.orden == null){
			resul = false;
		}
			
		//Luego compruebo que las listas tengan datos coherentes
		if(resul){			
			resul = compruebaCoherenciaLista(this.categorias);			
		}
		if(resul){
			resul = compruebaCoherenciaLista(this.grupos);
		}
		if(resul){
			resul = compruebaCoherenciaLista(this.tareasPadres);
		}
		//Compruebo incoherencias con EstadosFiltros y las listas asociadas
		if(resul){
			resul = !(this.tareasRealizadas.equals(EstadoFilros.excluir) && (this.fechaRealizacionInferior != null || this.fechaRealizacionSuperior != null));
		}
		if(resul){
			resul = !(this.temporizadas.equals(EstadoFilros.excluir) && (this.fechaPlanificacionInferior != null || this.fechaPlanificacionSuperior != null));
		}
		
		return resul;
	}
	/**
	 * Función privada para comprobar la coherencia de los valores de las listas.
	 * @param listaAComprobar
	 * @return
	 */
	private boolean compruebaCoherenciaLista(List<String> listaAComprobar) {
		boolean resul = true;
		boolean hayTodos = listaAComprobar.contains(VALOR_TODAS_EN_LISTAS);
		boolean hayNoAsociadas = listaAComprobar.contains(VALOR_NO_ASOCIADAS_EN_LISTAS);
		boolean hayAsociadas = listaAComprobar.contains(VALOR_ASOCIADAS_EN_LISTAS);
		if(hayTodos && listaAComprobar.size() > 1){
			resul = false;
		}
		//Si envío que estén todas y aparte añado otra rescricción a la lista, no es coherente
		if(resul && (hayAsociadas && listaAComprobar.size() > 1)){
			resul = false;
		}
		//Esto es equivalente a poner que estén todas
		if(resul && (hayNoAsociadas && hayAsociadas)){
			resul = false;
		}
			
		return resul;
	}


	/**
	 * @return the fechaCreacionInferior
	 */
	public Date getFechaCreacionInferior() {
		return fechaCreacionInferior;
	}


	/**
	 * @param fechaCreacionInferior the fechaCreacionInferior to set
	 */
	public void setFechaCreacionInferior(Date fechaCreacionInferior) {
		this.fechaCreacionInferior = fechaCreacionInferior;
	}


	/**
	 * @return the fechaCreacionSuperior
	 */
	public Date getFechaCreacionSuperior() {
		return fechaCreacionSuperior;
	}


	/**
	 * @param fechaCreacionSuperior the fechaCreacionSuperior to set
	 */
	public void setFechaCreacionSuperior(Date fechaCreacionSuperior) {
		this.fechaCreacionSuperior = fechaCreacionSuperior;
	}


	/**
	 * @return the cadenaNoContiene
	 */
	public String getCadenaNoContiene() {
		return cadenaNoContiene;
	}


	/**
	 * @param cadenaNoContiene the cadenaNoContiene to set
	 */
	public void setCadenaNoContiene(String cadenaNoContiene) {
		this.cadenaNoContiene = cadenaNoContiene;
	}


	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}


	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	/**
	 * @return the listOidTareasEspecificas
	 */
	public List<String> getListOidTareasEspecificas() {
		return listOidTareasEspecificas;
	}


	/**
	 * @param listOidTareasEspecificas the listOidTareasEspecificas to set
	 */
	public void setListOidTareasEspecificas(List<String> listOidTareasEspecificas) {
		this.listOidTareasEspecificas = listOidTareasEspecificas;
	}
	
	
	
	
}


