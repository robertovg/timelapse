package timeLapse.controller.actions.administracion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import timeLapse.business.administracion.IFuncionalidadesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpObjetivo;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("actualizaFuncionalidadAction")
@Scope("request")
public class ActualizaFuncionalidadAction extends ActionTemplate {
	@Resource
	IFuncionalidadesBO neg;
	
	private String formOID;
	private String formNombre;
	private String formObjetivo;
	private String formDesc;
	private Integer formOrden;
	

	@Override
	public String execute() throws Exception {

		TlpFuncionalidad fun = new TlpFuncionalidad();
		if (!(formOID.equals(""))) {
			fun.setOid(formOID);
		}
		TlpObjetivo obj = new TlpObjetivo();
		obj.setOid(formObjetivo);
		fun.setTlpObjetivo(obj);
		fun.setNombre(formNombre);
		fun.setDescripcion(formDesc);
		fun.setOrden(formOrden);

		 
		neg.actualiza(fun);

		//Fabrico los mensajes de vuelta
		if ((formOID.equals(""))) {

			mensajeRespuesta = biblioteca.traduce("ActualizaFuncionalidadAction.nueva.mensajeRespuesta");

		}
		//Actualizo
		else{
			mensajeRespuesta = biblioteca.traduce("ActualizaFuncionalidadAction.modificar.mensajeRespuesta");

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
	@RequiredStringValidator(key = "ActualizaFuncionalidadAction.formNombre.obl")
	@StringLengthFieldValidator(key = "ActualizaFuncionalidadAction.formNombre.long", maxLength = "45")
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
	 * @return the formObjetivo
	 */
	@RequiredFieldValidator(key = "ActualizaFuncionalidadAction.formObjetivo.obl")
	public String getFormObjetivo() {
		return formObjetivo;
	}


	/**
	 * @param formObjetivo the formObjetivo to set
	 */
	public void setFormObjetivo(String formObjetivo) {
		this.formObjetivo = formObjetivo;
	}


	/**
	 * @return the formDesc
	 */
	@StringLengthFieldValidator(key = "ActualizaFuncionalidadAction.formDesc.long", maxLength = "45")
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
	@RequiredFieldValidator(key = "ActualizaFuncionalidadAction.formOrden.obl")
	@IntRangeFieldValidator(key = "ActualizaFuncionalidadAction.formOrden.long", min = "1", max = "1550")
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
