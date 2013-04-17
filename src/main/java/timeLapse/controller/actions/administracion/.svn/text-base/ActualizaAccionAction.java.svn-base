package timeLapse.controller.actions.administracion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;

import timeLapse.business.administracion.IAccionesBO;
import timeLapse.controller.actions.abs.ActionTemplate;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;

/**
 * 
 * @author robe
 * 
 */
@SuppressWarnings("serial")
@Service("actualizaAccionAction")
@Scope("request")
public class ActualizaAccionAction extends ActionTemplate {
	@Resource
	IAccionesBO neg;
	
	private String formOID;
	private String formNombre;
	private String formFuncionalidad;
	private String formDesc;
	private String formPath;
	private Integer formOrden;
	

	@Override
	public String execute() throws Exception {

		TlpAccion acc = new TlpAccion();
		if (!(formOID.equals(""))) {
			acc.setOid(formOID);
		}
		TlpFuncionalidad fun = new TlpFuncionalidad();
		fun.setOid(formFuncionalidad);
		acc.setTlpFuncionalidad(fun);
		acc.setNombre(formNombre);
		acc.setDescripcion(formDesc);
		acc.setPath(formPath);
		acc.setOrden(formOrden);

		
		neg.actualiza(acc);

		//Fabrico los mensajes de vuelta
		if ((formOID.equals(""))) {

			mensajeRespuesta = biblioteca.traduce("ActualizaAccionAction.nueva.mensajeRespuesta");

		}
		//Actualizo
		else{
			mensajeRespuesta = biblioteca.traduce("ActualizaAccionAction.modificar.mensajeRespuesta");

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
	@RequiredStringValidator(key = "ActualizaAccionAction.formNombre.obl")
	@StringLengthFieldValidator(key = "ActualizaAccionAction.formNombre.long", maxLength = "45")
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
	 * @return the formFuncionalidad
	 */
	@RequiredFieldValidator(key = "ActualizaAccionAction.formFuncionalidad.obl")
	public String getFormFuncionalidad() {
		return formFuncionalidad;
	}


	/**
	 * @param formFuncionalidad the formFuncionalidad to set
	 */
	public void setFormFuncionalidad(String formFuncionalidad) {
		this.formFuncionalidad = formFuncionalidad;
	}


	/**
	 * @return the formDesc
	 */
	
	@StringLengthFieldValidator(key = "ActualizaAccionAction.formDesc.long", maxLength = "45")
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
	 * @return the formPath
	 */
	@RequiredStringValidator(key = "ActualizaFuncionalidadAction.formPath.obl")
	@StringLengthFieldValidator(key = "ActualizaFuncionalidadAction.formPath.long", maxLength = "100")
	public String getFormPath() {
		return formPath;
	}


	/**
	 * @param formPath the formPath to set
	 */
	public void setFormPath(String formPath) {
		this.formPath = formPath;
	}


	/**
	 * @return the formOrden
	 */
	@RequiredFieldValidator(key = "ActualizaAccionAction.formOrden.obl")
	@IntRangeFieldValidator(key = "ActualizaAccionAction.formOrden.long", min = "1", max = "1550")
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
