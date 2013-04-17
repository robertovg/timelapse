package timeLapse.controller.actions.grupos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.grupos.IGrupoBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("eliminaGruposAction")
@Scope("request")
public class EliminaGruposAction extends ActionTemplate {

	@Resource
	IGrupoBO neg;
	
	private List<String> listID;

	@Override
	public String execute() throws Exception {

		
		Iterator<String> it = listID.iterator();
		List<TldGrupo> gruposEliminados = new ArrayList<TldGrupo>();
		List<String> identificadoresGruposNoEliminados = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		while (it.hasNext()) {
			String grupoIterado = it.next();
			try{				
				gruposEliminados.add(neg.elimina(grupoIterado));
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresGruposNoEliminados.add(grupoIterado);				
			}
		}
		
		//Fabrico el mensaje de vuelta
		if(gruposEliminados.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaGruposAction.una.mensajeRespuesta");
		}else if(gruposEliminados.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaGruposAction.muchos.mensajeRespuesta");
		}
		Iterator<TldGrupo> itElim = gruposEliminados.iterator();
		while(itElim.hasNext()){
			TldGrupo grupoIterado = itElim.next();			
			mensajeRespuesta += " " + grupoIterado.getNombre();
			if(itElim.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}
				
		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresGruposNoEliminados.isEmpty() && !errores.isEmpty() && identificadoresGruposNoEliminados.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronGruposSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronGruposSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idGrupoNoEliminada : identificadoresGruposNoEliminados){
				TldGrupo grupoNoEliminado = neg.encuentra(idGrupoNoEliminada);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaGruposAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, grupoNoEliminado.getNombre());
				mensaje += biblioteca.traduce("EliminaGruposAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronGruposSatisfactoriamente){
					seEliminaronGruposSatisfactoriamente = false;
					mensajeRespuesta += mensaje + ".<br>";
				}else{
					mensajeRespuesta += mensaje + ".";
				}
				
			}
			
		}

		return SUCCESS;
	}

	/**
	 * @return the listID
	 */
	@RequiredFieldValidator(key = "EliminaGruposAction.listID.obl")
	public List<String> getListID() {
		return listID;
	}

	/**
	 * @param listID the listID to set
	 */
	public void setListID(List<String> listID) {
		this.listID = listID;
	}

	
}
