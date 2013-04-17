package timeLapse.controller.actions.tiempo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.tareas.ITareaBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.TimeLapseException;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

@SuppressWarnings("serial")
@Service("vinculaTareasTiempoAction")
@Scope("request")
public class VinculaTareasTiempoAction extends ActionTemplate{

	@Resource
	ITareaBO neg;
	
	
	private List<String> listOidEntidades;
	private String formFechaInicio;
	private String formHoraInicio;
	private String formFechaFin;
	private String formHoraFin;
	private Date dateFechaInicio;
	private Date dateHoraInicio;
	private Date dateFechaFin;
	private Date dateHoraFin;
	
	@Override
	public String execute() throws Exception {

		validacionesFechas();		
		
		neg.vinculaTareasTiempo(dateFechaInicio, dateHoraInicio, dateFechaFin, dateHoraFin, listOidEntidades);
		mensajeRespuesta = biblioteca.traduce("VinculaTareasTiempoAction.mensajeRespuesta");


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
		try{			
			dateHoraInicio = Utiles.stringToTime(formHoraInicio);
			dateFechaInicio = Utiles.stringToDate(formFechaInicio);
			dateHoraFin = Utiles.stringToTime(formHoraFin);
			dateFechaFin = Utiles.stringToDate(formFechaFin);
			
		}catch (ParseException e) {
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.seProdujoUnErrorConElFormateoDeLasFechas));
		}
		//Si las dos fechas nulas también elebo una excepción
		if(dateFechaInicio == null && dateFechaFin == null){
			ex.addMensaje(factoriaMens.getMensaje(ConstantesException.tareaConFechaFinMenorFechaInicio));
		}
		//Ahora compruebo que las fechas de Incio son menores o iguales que las de fin
		if(ex.isEmpty()){
			if(dateFechaInicio != null && dateFechaFin != null){
				if(dateFechaInicio.after(dateFechaFin)){
					ex.addMensaje(factoriaMens.getMensaje(ConstantesException.tareaConFechaFinMenorFechaInicio));
				}else if(dateFechaInicio.equals(dateFechaFin)){				
					if((dateHoraInicio != null && dateHoraFin != null) &&dateHoraInicio.after(dateHoraFin)){
						ex.addMensaje(factoriaMens.getMensaje(ConstantesException.tareaConFechaFinMenorFechaInicio));
					}
				}
				
			}
		}
		//Si se produce algún error de validación, informo a través de excepciones
		if(!ex.isEmpty()){
			throw ex;
		}
	}

	/**
	 * @return the listOidAcciones
	 */
	@RequiredFieldValidator(key = "VinculaTareasTiempoAction.listOidEntidades.obl")
	public List<String> getListOidEntidades() {
		return listOidEntidades;
	}


	/**
	 * @param listOidAcciones the listOidAcciones to set
	 */
	public void setListOidEntidades(List<String> listOidAcciones) {
		this.listOidEntidades = listOidAcciones;
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
	 * @return the dateFechaInicio
	 */
	public Date getDateFechaInicio() {
		return dateFechaInicio;
	}

	/**
	 * @param dateFechaInicio the dateFechaInicio to set
	 */
	public void setDateFechaInicio(Date dateFechaInicio) {
		this.dateFechaInicio = dateFechaInicio;
	}

	/**
	 * @return the dateHoraInicio
	 */
	public Date getDateHoraInicio() {
		return dateHoraInicio;
	}

	/**
	 * @param dateHoraInicio the dateHoraInicio to set
	 */
	public void setDateHoraInicio(Date dateHoraInicio) {
		this.dateHoraInicio = dateHoraInicio;
	}

	/**
	 * @return the dateFechaFin
	 */
	public Date getDateFechaFin() {
		return dateFechaFin;
	}

	/**
	 * @param dateFechaFin the dateFechaFin to set
	 */
	public void setDateFechaFin(Date dateFechaFin) {
		this.dateFechaFin = dateFechaFin;
	}

	/**
	 * @return the dateHoraFin
	 */
	public Date getDateHoraFin() {
		return dateHoraFin;
	}

	/**
	 * @param dateHoraFin the dateHoraFin to set
	 */
	public void setDateHoraFin(Date dateHoraFin) {
		this.dateHoraFin = dateHoraFin;
	}




	
}
