package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IFuncionalidadesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("eliminaFuncionalidadesAction")
@Scope("request")
public class EliminaFuncionalidadesAction extends ActionTemplate {
	@Resource
	IFuncionalidadesBO neg;
	
	
	private List<String> listID;

	@Override
	public String execute() throws Exception {

		
		List<TlpFuncionalidad> funcionalidadesEliminadas = new ArrayList<TlpFuncionalidad>();
		List<String> identificadoresFuncionalidadesNoEliminadas = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		Iterator<String> it = listID.iterator();
		while (it.hasNext()) {
			String funcionadidadIterado = it.next();
			try{				
				funcionalidadesEliminadas.add(neg.elimina(funcionadidadIterado));
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresFuncionalidadesNoEliminadas.add(funcionadidadIterado);				
			}
			
		}
		
		if(funcionalidadesEliminadas.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaFuncionalidadesAction.una.mensajeRespuesta");
		}else if(funcionalidadesEliminadas.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaFuncionalidadesAction.muchas.mensajeRespuesta");
		}
		
		Iterator<TlpFuncionalidad> itElim = funcionalidadesEliminadas.iterator();
		while(itElim.hasNext()){
			TlpFuncionalidad funcionalidadIterada = itElim.next();
			mensajeRespuesta += " " + funcionalidadIterada.getNombre();
			if(itElim.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}
		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresFuncionalidadesNoEliminadas.isEmpty() && !errores.isEmpty() && identificadoresFuncionalidadesNoEliminadas.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronFuncionalidadesSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronFuncionalidadesSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idFuncionalidadesNoEliminado : identificadoresFuncionalidadesNoEliminadas){
				TlpFuncionalidad funcionalidadNoEliminada = neg.encuentra(idFuncionalidadesNoEliminado);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaFuncionalidadesAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, funcionalidadNoEliminada.getNombre());
				mensaje += biblioteca.traduce("EliminaFuncionalidadesAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronFuncionalidadesSatisfactoriamente){
					seEliminaronFuncionalidadesSatisfactoriamente = false;
					mensajeRespuesta += mensaje + ".<br>";
				}else{
					mensajeRespuesta += mensaje + ".";
				}
				
			}
		
		}
		return SUCCESS;
	}
	
	public void setListID(List<String> listID) {
		this.listID = listID;
	}

	/**
	 * @return the listID
	 */
	@RequiredFieldValidator(key = "EliminaFuncionalidadesAction.listID.obl")
	public List<String> getListID() {
		return listID;
	}
	
	

}
