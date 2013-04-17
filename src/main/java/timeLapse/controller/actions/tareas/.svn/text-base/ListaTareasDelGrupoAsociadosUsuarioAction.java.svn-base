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
import timeLapse.util.constantes.OrdenTareas;



/**
 * Action utilizado para muchos casos, por ejemplo cuadno se administran las tareas. Es el action que 
 * lista tareas que tiene todas las opciones del filtro opcionales.
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaTareasDelGrupoAsociadosUsuarioAction")
@Scope("request")
public class ListaTareasDelGrupoAsociadosUsuarioAction extends ListaTareasActionTemplate {
	@Resource
	ITareaBO neg;
	
	private String oidUsuario;
	
	private String identifier;
	
	private List<TareaDTO> items;


	public ListaTareasDelGrupoAsociadosUsuarioAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		//Solo devuelvo algo si se pasa el oid del usuario
		if(!StringUtils.isBlank(oidUsuario)){
			
			FiltroTareasDTO filtro = new FiltroTareasDTO();
			filtro.setOidUsuario(oidUsuario);		
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
			//Hago que solo se muestren las tareas sin usuarios vinculado
			filtro.setVinculadasUsuario(EstadoFilros.exclusivo);
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
			if(orden != null){
				filtro.setOrden(OrdenTareas.getOrden(orden));
			}
			
			
			items = neg.listaTareas(filtro,usuarioRegistrado);
	}
		return SUCCESS;
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

	public List<TareaDTO> getItems() {
		return items;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}


}
