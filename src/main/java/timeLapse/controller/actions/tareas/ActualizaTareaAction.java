package timeLapse.controller.actions.tareas;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.UserValueAction;
import timeLapse.resources.persistenceDTO.TldCategoria;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * Action que se encarga de crear o actualizar tareas. Se comportará de manera especial según sea una tarea
 * propia o no.
 * La tarea será propia cuando:
 * -Se esté creando una nueva tarea.
 * -Cuando la tarea ya exista (actualización) y el usuario creador coincida con el usuario registrado.
 * En otro caso no será una tarea propia y por lo cual no se dejarán modificar.
 * -Usuario Creador
 * -Categoría
 * -Grupo
 * -TareaPadre
 * Y además si se modfica el grupo de una tarea propia, también se desvincula del usuario que tenga asociado
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("actualizaTareaAction")
@Scope("request")
public class ActualizaTareaAction extends UserValueAction {
	@Resource
	ITareaBO neg;
	
	
	private String formOID;
	private String formNombre;	
	private String formDesc;	
	private Integer formImportancia;
	private String formFechaInicio;
	private String formHoraInicio;
	private String formFechaFin;
	private String formHoraFin;
	private String formFechaRealizacion;
	private String formHoraRealizacion;
	private String formCategoria;
	private String formGrupo;
	private String formTareaPadre; 
	private String formLocalizacion;
	private Boolean formAutorrealizable;
	
	
	
	private Date dateFechaInicio;
	private Date dateHoraInicio;
	private Date dateFechaFin;
	private Date dateHoraFin;
	private Date dateFechaRealizacion;
	private Date dateHoraRealizacion;

	@Override
	public String execute() throws Exception {
		
		validacionesFechas();
		
		TldTarea tarea = new TldTarea();
		if (!StringUtils.isBlank(formOID)) {
			tarea.setOid(formOID);
		}
		TldTarea tareaOriginal = null;
		
		if (!StringUtils.isBlank(formOID)) {
			tareaOriginal = neg.encuentra(formOID);
		}
		/*
		 * La tarea será propia cuando:
		 * -Se esté creando una nueva tarea.
		 * -Cuando la tarea ya exista (actualización) y el usuario creador coincida con el usuario registrado.
		 * En otro caso no será una tarea propia y por lo cual no se dejarán modificar.
		 * -Usuario Creador
		 * -Categoría
		 * -Grupo
		 * -TareaPadre
		 * Y además si se modfica el grupo de una tarea propia, también se desvincula del usuario que tenga asociado
		 * 
		 */
		boolean esPropiaLaTarea = true;
		if(tareaOriginal != null){
			esPropiaLaTarea = tareaOriginal.getTldUsuarioByUsuarioCreadorOid().getOid().equals(usuarioRegistrado.getOid());
		}
			
		
		TldUsuario usuarioCreador = new TldUsuario();
		usuarioCreador.setOid(usuarioRegistrado.getOid());
		
		tarea.setNombre(formNombre);
		tarea.setDescripcion(formDesc);		
		tarea.setImportancia(formImportancia);		
		tarea.setFechaInicio(dateFechaInicio);
		tarea.setHoraInicio(dateHoraInicio);
		tarea.setFechaFin(dateFechaFin);
		tarea.setHoraFin(dateHoraFin);
		
		tarea.setAutorrealizable(formAutorrealizable != null? 1 : 0);
		/*
		 * Si la tareas se va a volver autorrealizable y se le intenta meter una fecha de realización, elebo una excepción para informar 
		 * de que una tarea autorrealizable no puede finalizarse.
		 */
		if(tareaOriginal != null && tarea.getAutorrealizable() == 1  
			&& (dateFechaRealizacion != null || dateHoraRealizacion != null)){
				Mensaje men = factoriaMens.getMensaje(ConstantesException.tareaAutorrealizableNoSePuedeFinalizar);
				throw new TimeLapseException(men);
		}
		tarea.setFechaRealizacion(dateFechaRealizacion);
		tarea.setHoraRealizacion(dateHoraRealizacion);
		//Solo se modifica la categoría si la tarea es propia
		if(esPropiaLaTarea){
			if (!StringUtils.isBlank(formCategoria) && !formCategoria.equals("-")){
				TldCategoria categoria = new TldCategoria();
				categoria.setOid(formCategoria);
				tarea.setTldCategoria(categoria);
			}
		}else{
			tarea.setTldCategoria(tareaOriginal.getTldCategoria());
		}
		
		//Solo se modifica el grupo si la tarea es propia
		if(esPropiaLaTarea){
			if(!StringUtils.isBlank(formGrupo) && !formGrupo.equals("-")){
				TldGrupo grupo = new TldGrupo();
				grupo.setOid(formGrupo);
				tarea.setTldGrupo(grupo);
				//Si se va a modificar la tarea, quito al usuario asociado
				String oidGrupoOrigina = (tareaOriginal != null && tareaOriginal.getTldGrupo() != null) ? tareaOriginal.getTldGrupo().getOid() : null;
				if(!formGrupo.equals(oidGrupoOrigina)){
					tarea.setTldUsuarioByUsuarioAsociadoOid(null);
				}
			}
		}else{
			tarea.setTldGrupo(tareaOriginal.getTldGrupo());
			tarea.setTldUsuarioByUsuarioAsociadoOid(tareaOriginal.getTldUsuarioByUsuarioAsociadoOid());
		}
		//Solo se modifica el grupo si la tarea es propia
		if(esPropiaLaTarea){
			if(!StringUtils.isBlank(formTareaPadre) && !formTareaPadre.equals("-")){
				TldTarea tareaPadre = new TldTarea();
				tareaPadre.setOid(formTareaPadre);
				tarea.setTldTarea(tareaPadre);			
			}
		}else{
			tarea.setTldTarea(tareaOriginal.getTldTarea());
		}
		
		tarea.setLocalizacionAsociada(formLocalizacion);
		
		//El usuario creador nunca se puede cambiar
		if(tareaOriginal != null){
			tarea.setTldUsuarioByUsuarioCreadorOid(tareaOriginal.getTldUsuarioByUsuarioCreadorOid());
		}else{
			tarea.setTldUsuarioByUsuarioCreadorOid(usuarioCreador);
		}
		
		//La fecha de creación solo se rellena cuando se está creando una tarea
		if ((formOID.equals(""))) {
			tarea.setFechaCreacion(new Date());
		}
		
		
			
		neg.actualiza(tarea);
		//Creo una nueva
		if ((formOID.equals(""))) {

			mensajeRespuesta = biblioteca.traduce("ActualizaTareaAction.nueva.mensajeRespuesta");

		}
		//Actualizo
		else{
			mensajeRespuesta = biblioteca.traduce("ActualizaTareaAction.modificar.mensajeRespuesta");

		}

		mensajeRespuesta += " " + formNombre;
		
		return SUCCESS;
	}

	/**
	 * Método que comprueba que los datos que llegan en los campos de fechas, y horas son 
	 * validos y coherentes para transformarlos. Si no fuera así eleva una excepción
	 * @throws TimeLapseException
	 */
	private void validacionesFechas() throws TimeLapseException{
		TimeLapseException ex = new TimeLapseException();
		if(!StringUtils.isBlank(formHoraInicio) && StringUtils.isBlank(formFechaInicio)){
			
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.noSePuedeEnviarHoraInicioSinFechaInicio));
			
		}
		if(!StringUtils.isBlank(formHoraFin) && StringUtils.isBlank(formFechaFin)){
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.noSePuedeEnviarHoraFinSinFechaFin));
			
		}
		if(!StringUtils.isBlank(formHoraRealizacion) && StringUtils.isBlank(formFechaRealizacion)){
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.noSePuedeEnviarHoraRealizacionSinFechaRealizacion));
			
		}
		
		try{
			
			dateHoraInicio = Utiles.stringToTime(formHoraInicio);
			dateFechaInicio = Utiles.stringToDate(formFechaInicio);
			dateHoraFin = Utiles.stringToTime(formHoraFin);
			dateFechaFin = Utiles.stringToDate(formFechaFin);
			dateHoraRealizacion = Utiles.stringToTime(formHoraRealizacion);
			dateFechaRealizacion = Utiles.stringToDate(formFechaRealizacion);
		}catch (ParseException e) {
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.seProdujoUnErrorConElFormateoDeLasFechas));
		}
		//Si se produce algún error de validación, informo a través de excepciones
		if(!ex.isEmpty()){
			throw ex;
		}
	}


	/**
	 * @return the formOID
	 */
	public String getFormOID() {
		return formOID;
	}


	/**
	 * @param formOID the formOID to set
	 */
	public void setFormOID(String formOID) {
		this.formOID = formOID;
	}


	/**
	 * @return the formNombre
	 */
	@RequiredStringValidator(key = "ActualizaTareasAction.formNombre.obl")
	@StringLengthFieldValidator(key = "ActualizaTareasAction.formNombre.long", maxLength = "100")
	public String getFormNombre() {
		return formNombre;
	}


	/**
	 * @param formNombre the formNombre to set
	 */
	public void setFormNombre(String formNombre) {
		this.formNombre = formNombre;
	}


	/**
	 * @return the formDesc
	 */
	@RequiredStringValidator(key = "ActualizaTareasAction.formDesc.obl")
	@StringLengthFieldValidator(key = "ActualizaTareasAction.formDesc.long", maxLength = "500")
	public String getFormDesc() {
		return formDesc;
	}


	/**
	 * @param formDesc the formDesc to set
	 */
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}


	/**
	 * @return the formFechaInicio
	 */
	
	public String getFormFechaInicio() {
		return formFechaInicio;
	}


	/**
	 * @param formFechaInicio the formFechaInicio to set
	 */
	public void setFormFechaInicio(String formFechaInicio) {
		this.formFechaInicio = formFechaInicio;
	}


	/**
	 * @return the formHoraInicio
	 */
	public String getFormHoraInicio() {
		return formHoraInicio;
	}


	/**
	 * @param formHoraInicio the formHoraInicio to set
	 */
	public void setFormHoraInicio(String formHoraInicio) {
		this.formHoraInicio = formHoraInicio;
	}


	/**
	 * @return the formFechaFin
	 */
	public String getFormFechaFin() {
		return formFechaFin;
	}


	/**
	 * @param formFechaFin the formFechaFin to set
	 */
	public void setFormFechaFin(String formFechaFin) {
		this.formFechaFin = formFechaFin;
	}
	
	
	
	/**
	 * @return the formHoraFin
	 */
	public String getFormHoraFin() {
		return formHoraFin;
	}


	/**
	 * @param formHoraFin the formHoraFin to set
	 */
	public void setFormHoraFin(String formHoraFin) {
		this.formHoraFin = formHoraFin;
	}


	/**
	 * @return the formFechaRealizacion
	 */
	public String getFormFechaRealizacion() {
		return formFechaRealizacion;
	}


	/**
	 * @param formFechaRealizacion the formFechaRealizacion to set
	 */
	public void setFormFechaRealizacion(String formFechaRealizacion) {
		this.formFechaRealizacion = formFechaRealizacion;
	}
	


	/**
	 * @return the formHoraRealizacion
	 */
	public String getFormHoraRealizacion() {
		return formHoraRealizacion;
	}


	/**
	 * @param formHoraRealizacion the formHoraRealizacion to set
	 */
	public void setFormHoraRealizacion(String formHoraRealizacion) {
		this.formHoraRealizacion = formHoraRealizacion;
	}


	/**
	 * @return the formLocalizacion
	 */
	@StringLengthFieldValidator(key = "ActualizaTareasAction.formLocalizacion.long", maxLength = "500")
	public String getFormLocalizacion() {
		return formLocalizacion;
	}


	/**
	 * @param formLocalizacion the formLocalizacion to set
	 */
	public void setFormLocalizacion(String formLocalizacion) {
		this.formLocalizacion = formLocalizacion;
	}


	/**
	 * @return the formAutorrealizable
	 */	
	public Boolean getFormAutorrealizable() {
		return formAutorrealizable;
	}


	/**
	 * @param formAutorrealizable the formAutorrealizable to set
	 */
	public void setFormAutorrealizable(Boolean formAutorrealizable) {
		this.formAutorrealizable = formAutorrealizable;
	}


	/**
	 * @return the formImportancia
	 */
	@RequiredFieldValidator(key = "ActualizaTareasAction.formImportancia.obl")
	@IntRangeFieldValidator(key = "ActualizaTareasAction.formImportancia.long", min = "1", max = "10")
	public Integer getFormImportancia() {
		return formImportancia;
	}


	/**
	 * @param formImportancia the formImportancia to set
	 */
	public void setFormImportancia(Integer formImportancia) {
		this.formImportancia = formImportancia;
	}


	/**
	 * @return the oidCategoria
	 */
	public String getFormCategoria() {
		return formCategoria;
	}


	/**
	 * @param oidCategoria the oidCategoria to set
	 */
	public void setFormCategoria(String oidCategoria) {
		this.formCategoria = oidCategoria;
	}


	/**
	 * @return the oidGrupo
	 */
	public String getFormGrupo() {
		return formGrupo;
	}


	/**
	 * @param oidGrupo the oidGrupo to set
	 */
	public void setFormGrupo(String oidGrupo) {
		this.formGrupo = oidGrupo;
	}


	/**
	 * @return the oidTareaPadre
	 */
	public String getFormTareaPadre() {
		return formTareaPadre;
	}


	/**
	 * @param oidTareaPadre the oidTareaPadre to set
	 */
	public void setFormTareaPadre(String oidTareaPadre) {
		this.formTareaPadre = oidTareaPadre;
	}




	

}
