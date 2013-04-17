package timeLapse.controller.actions.categorias;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.categorias.ICategoriaBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TldCategoria;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("eliminaCategoriasAction")
@Scope("request")
public class EliminaCategoriasAction extends ActionTemplate {

	@Resource
	ICategoriaBO neg;
	
	private List<String> listID;

	@Override
	public String execute() throws Exception {

		List<TldCategoria> categoriasEliminadas = new ArrayList<TldCategoria>();
		List<String> identificadoresCategoriasNoEliminadas = new ArrayList<String>();
		TimeLapseException errores = new TimeLapseException();
		Iterator<String> it = listID.iterator();		
		while (it.hasNext()) {
			String tareaIterada = it.next();
			try{				
				categoriasEliminadas.add(neg.elimina(tareaIterada));
			}catch (TimeLapseException e) {
				errores.addMensajes(e);
				identificadoresCategoriasNoEliminadas.add(tareaIterada);				
			}
		}
		
		if(categoriasEliminadas.size() == 1){
			mensajeRespuesta = biblioteca.traduce("EliminaCategoriasAction.una.mensajeRespuesta");

		}else if(categoriasEliminadas.size() > 1){
			mensajeRespuesta = biblioteca.traduce("EliminaCategoriasAction.muchos.mensajeRespuesta");
			
		}
		Iterator<TldCategoria> itElim = categoriasEliminadas.iterator();
		while(itElim.hasNext()){
			TldCategoria cat = itElim.next();
			mensajeRespuesta += " " + cat.getNombre();
			if(itElim.hasNext()){
				mensajeRespuesta += ",";
			}else{
				mensajeRespuesta += ".";
			}
		}
		//Si existen errores, lo meto en el mensaje respuesta
		if(!identificadoresCategoriasNoEliminadas.isEmpty() && !errores.isEmpty() && identificadoresCategoriasNoEliminadas.size() == errores.sizeErrores()){
			//Inicializo la cadena respuesta si vale nula
			boolean seEliminaronCategoriasSatisfactoriamente = true;
			if(mensajeRespuesta == null){
				mensajeRespuesta = "";
				seEliminaronCategoriasSatisfactoriamente = false;				
			}else{
				mensajeRespuesta += "<br>";
			}
			Iterator<Mensaje> itErrores = errores.getIteratorErrores();
			for(String idObjetivoNoEliminada : identificadoresCategoriasNoEliminadas){
				TldCategoria categoriaNoEliminada = neg.encuentra(idObjetivoNoEliminada);
				Mensaje mensajeExcepcion = itErrores.next();
				String mensaje = biblioteca.traduce("EliminaCategoriasAction.errorUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, categoriaNoEliminada.getNombre());
				mensaje += biblioteca.traduce("EliminaCategoriasAction.causaUna.mensajeRespuesta");
				mensaje = mensaje.replace(CONSTANTE_A_REMPLAZAR_CONSTANTES, mensajeExcepcion.toString());
				if(itErrores.hasNext() || seEliminaronCategoriasSatisfactoriamente){
					seEliminaronCategoriasSatisfactoriamente = false;
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
	@RequiredFieldValidator(key = "EliminaCategoriasAction.listID.obl")
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
