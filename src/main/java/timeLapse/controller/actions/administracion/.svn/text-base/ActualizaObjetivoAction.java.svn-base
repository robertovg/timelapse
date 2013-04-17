package timeLapse.controller.actions.administracion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IObjetivosBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpObjetivo;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

/**
 * @author robe
 */
@SuppressWarnings("serial")
@Service("actualizaObjetivoAction")
@Scope("request")
public class ActualizaObjetivoAction extends ActionTemplate {
	@Resource
	IObjetivosBO neg;
	
	
	private String formOID;
	private String formNombre;	
	private String formDesc;
	private Integer formOrden;
	

	@Override
	public String execute() throws Exception {

		TlpObjetivo obj = new TlpObjetivo();
		if (!(formOID.equals(""))) {
			obj.setOid(formOID);
		}
		obj.setNombre(formNombre);
		obj.setDescripcion(formDesc);
		obj.setOrden(formOrden);
		 
		neg.actualiza(obj);
		
		
		//Fabrico los mensajes de vuelta
		if ((formOID.equals(""))) {

			mensajeRespuesta = biblioteca.traduce("ActualizaObjetivoAction.nueva.mensajeRespuesta");

		}
		//Actualizo
		else{
			mensajeRespuesta = biblioteca.traduce("ActualizaObjetivoAction.modificar.mensajeRespuesta");

		}
		mensajeRespuesta += " " + biblioteca.traduce(formNombre);

		return SUCCESS;
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
	@RequiredStringValidator(key = "ActualizaObjetivoAction.formNombre.obl")
	@StringLengthFieldValidator(key = "ActualizaObjetivoAction.formNombre.long", maxLength = "45")
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
	@StringLengthFieldValidator(key = "ActualizaObjetivoAction.formDesc.long", maxLength = "45")	
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
	 * @return the formOrden
	 */
	@RequiredFieldValidator(key = "ActualizaObjetivoAction.formOrden.obl")
	@IntRangeFieldValidator(key = "ActualizaObjetivoAction.formOrden.long", min = "1", max = "1550")
	public Integer getFormOrden() {
		return formOrden;
	}

	/**
	 * @param formOrden the formOrden to set
	 */
	public void setFormOrden(Integer formOrden) {
		this.formOrden = formOrden;
	}

	

}
