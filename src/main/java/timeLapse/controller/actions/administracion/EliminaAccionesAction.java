package timeLapse.controller.actions.administracion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IAccionesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("eliminaAccionesAction")
@Scope("request")
public class EliminaAccionesAction extends ActionTemplate {

	@Resource
	IAccionesBO neg;
	
	private List<String> listID;

	@Override
	public String execute() throws Exception {

		List<TlpAccion> accionesElim = new ArrayList<TlpAccion>();
		List<String> identificadoresAccionesNoEliminadas = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		Iterator<String> it = listID.iterator();
		while (it.hasNext()) {
			String accionIterada = it.next();
			try{				
				accionesElim.add(neg.elimina(accionIterada));
			
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresAccionesNoEliminadas.add(accionIterada);				
			
			}
		}
		
		if(accionesElim.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaAccionesAction.una.mensajeRespuesta");
		}else if(accionesElim.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaAccionesAction.muchas.mensajeRespuesta");
		}
		Iterator<TlpAccion> itAcc = accionesElim.iterator();
		while(itAcc.hasNext()){
			TlpAccion accIterada = itAcc.next();
			mensajeRespuesta += " " + accIterada.getNombre();
			if(itAcc.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}
		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresAccionesNoEliminadas.isEmpty() && !errores.isEmpty() && identificadoresAccionesNoEliminadas.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronAccionesSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronAccionesSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idAccionesNoEliminado : identificadoresAccionesNoEliminadas){
				TlpAccion accionesNoEliminada = neg.encuentra(idAccionesNoEliminado);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaAccionesAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, accionesNoEliminada.getNombre());
				mensaje += biblioteca.traduce("EliminaAccionesAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronAccionesSatisfactoriamente){
					seEliminaronAccionesSatisfactoriamente = false;
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
	@RequiredFieldValidator(key = "EliminaAccionesAction.listID.obl")
	public List<String> getListID() {
		return listID;
	}
	
	
}
