package timeLapse.controller.actions.tareas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
 * Action utilizado para la asociación de una tarea como tarea padre de tareas que no tienen tareas padre.
 * Devuelve solo tareas creadas por el usuario logueado. 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaTareasNoAsociadasTareasAction")
@Scope("request")
public class ListaTareasNoAsociadasTareasAction extends ListaTareasActionTemplate {
	@Resource
	ITareaBO neg;
	
	private String identifier;
	
	private List<TareaDTO> items;


	public ListaTareasNoAsociadasTareasAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		FiltroTareasDTO filtro = new FiltroTareasDTO();
		filtro.setIncluyeNoCreadas(false);
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
		/*
		 * Da igual las tareas padre que vengan en la petición, siempre se muestran las tareas no asociadas 
		 * a otras tareas. Las tareas huerfanas.
		 */
		List<String> tareasNoAsociadas = new ArrayList<String>();
		tareasNoAsociadas.add(VALOR_NO_ASOCIADAS_EN_LISTAS);		
		filtro.setTareasPadres(tareasNoAsociadas);
		
		filtro.setFechaPlanificacionInferior(Utiles.stringToDate(fechaPlanificacionInferior));
		filtro.setFechaPlanificacionSuperior(Utiles.stringToDate(fechaPlanificacionSuperior));
		filtro.setFechaRealizacionInferior(Utiles.stringToDate(fechaRealizacionInferior));
		filtro.setFechaRealizacionSuperior(Utiles.stringToDate(fechaRealizacionSuperior));
		if(orden != null){
			filtro.setOrden(OrdenTareas.getOrden(orden));
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


}
