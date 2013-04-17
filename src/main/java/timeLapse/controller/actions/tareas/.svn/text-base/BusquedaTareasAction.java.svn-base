package timeLapse.controller.actions.tareas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ListaTareasActionTemplate;
import timeLapse.resources.dto.FiltroTareasDTO;
import timeLapse.resources.dto.TareaDTO;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.EstadoFilros;



/**
 * Action que se llama cuando se hacen búsquedas de tarea
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("busquedaTareasAction")
@Scope("request")
public class BusquedaTareasAction extends ListaTareasActionTemplate {
	@Resource
	ITareaBO neg;
	
	private String identifier;		
	private List<TareaDTO> items;
	
	private String fechaCreacionInferior;
	private String fechaCreacionSuperior;
	private String cadenaContiene;
	private String cadenaNoContiene;
	private String cadenaUbicacion;


	public BusquedaTareasAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		FiltroTareasDTO filtro = new FiltroTareasDTO();
		filtro.setOidUsuario(usuarioRegistrado.getOid());		
		if(tareasRealizadas != null){
			filtro.setTareasRealizadas(EstadoFilros.getFiltro(tareasRealizadas));
		}		
		if(temporizadas != null){
			filtro.setTemporizadas(EstadoFilros.getFiltro(temporizadas));
		}
		if(tareasQueSonPadre != null){
			filtro.setTareasQueSonPadre(EstadoFilros.getFiltro(tareasQueSonPadre));
		}
		if(autorrealizables != null){
			filtro.setAutorrealizables(EstadoFilros.getFiltro(autorrealizables));
		}
		if(periodicas != null){
			filtro.setPeriodicas(EstadoFilros.getFiltro(periodicas));
		}
		if(categorias != null){
			filtro.setCategorias(categorias);
		}
		if(grupos != null){
			filtro.setGrupos(grupos);
		}
		if(tareasPadres != null){
			filtro.setTareasPadres(tareasPadres);
		}
		filtro.setFechaPlanificacionInferior(Utiles.stringToDate(fechaPlanificacionInferior));
		filtro.setFechaPlanificacionSuperior(Utiles.stringToDate(fechaPlanificacionSuperior));
		filtro.setFechaRealizacionInferior(Utiles.stringToDate(fechaRealizacionInferior));
		filtro.setFechaRealizacionSuperior(Utiles.stringToDate(fechaRealizacionSuperior));
		
		//Añado los campos de búsqueda al objeto de filtro
		filtro.setFechaCreacionInferior(Utiles.stringToDate(fechaCreacionInferior));
		filtro.setFechaCreacionSuperior(Utiles.stringToDate(fechaCreacionSuperior));
		
		if(!StringUtils.isBlank(cadenaContiene)){
			filtro.setCadenaContiene(cadenaContiene);
		}
		
		if(!StringUtils.isBlank(cadenaNoContiene)){
			filtro.setCadenaNoContiene(cadenaNoContiene);			
		}
		
		if(!StringUtils.isBlank(cadenaUbicacion)){
			filtro.setUbicacion(cadenaUbicacion);			
		}
		
		items = neg.listaTareas(filtro,usuarioRegistrado);
		
		return SUCCESS;
	}

	public List<TareaDTO> getItems() {
		return items;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the fechaCreacionInferior
	 */
	public String getFechaCreacionInferior() {
		return fechaCreacionInferior;
	}

	/**
	 * @param fechaCreacionInferior the fechaCreacionInferior to set
	 */
	public void setFechaCreacionInferior(String fechaCreacionInferior) {
		this.fechaCreacionInferior = fechaCreacionInferior;
	}

	/**
	 * @return the fechaCreacionSuperior
	 */
	public String getFechaCreacionSuperior() {
		return fechaCreacionSuperior;
	}

	/**
	 * @param fechaCreacionSuperior the fechaCreacionSuperior to set
	 */
	public void setFechaCreacionSuperior(String fechaCreacionSuperior) {
		this.fechaCreacionSuperior = fechaCreacionSuperior;
	}

	/**
	 * @return the cadenaContiene
	 */
	public String getCadenaContiene() {
		return cadenaContiene;
	}

	/**
	 * @param cadenaContiene the cadenaContiene to set
	 */
	public void setCadenaContiene(String cadenaContiene) {
		this.cadenaContiene = cadenaContiene;
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
	 * @return the cadenaUbicacion
	 */
	public String getCadenaUbicacion() {
		return cadenaUbicacion;
	}

	/**
	 * @param cadenaUbicacion the cadenaUbicacion to set
	 */
	public void setCadenaUbicacion(String cadenaUbicacion) {
		this.cadenaUbicacion = cadenaUbicacion;
	}


}
