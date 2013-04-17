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
import timeLapse.util.constantes.EstadoFilros;
import timeLapse.util.constantes.OrdenTareas;



/**
 * Action utilizado para mostrar las próximas tareas, ordenadas por fecha o por importancia.
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("listaProximasTareasAction")
@Scope("request")
public class ListaProximasTareasAction extends ListaTareasActionTemplate {
	@Resource
	ITareaBO neg;
	
	private String identifier;
	
	private List<TareaDTO> items;
	private List<String> listOidTareasEspecificas;

	public ListaProximasTareasAction() {
		this.items = new ArrayList<TareaDTO>();
	}

	@Override
	public String execute() throws Exception {
		identifier = "oid";
		FiltroTareasDTO filtro = new FiltroTareasDTO();
		filtro.setOidUsuario(usuarioRegistrado.getOid());		
		
		if(orden != null){
			filtro.setOrden(OrdenTareas.getOrden(orden));
		}
		filtro.setTareasRealizadas(EstadoFilros.excluir);
		items = neg.listaProximasTareas(filtro,usuarioRegistrado);
		
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
