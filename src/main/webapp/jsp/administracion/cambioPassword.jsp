<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<b class="divRedondo500">
<b class="divRedondo1"><b></b></b>
<b class="divRedondo2"><b></b></b>
<b class="divRedondo3"></b>
<b class="divRedondo4"></b>
<b class="divRedondo5"></b></b>
<div class="divNegro">
<h1><fmt:message key="timeLapse.jsp.administracion.cambioPassword.titulo"/></h1>
</div>

<div class="contenedorFormulario">
	<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
		encType="multipart/form-data" action="" method="">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<div class="divisionIzquierda">
					<fieldset id="contenedorFormularioPasswordAntgua" class="separadorFormulario">
					<legend class="letraGrande"><fmt:message key="timeLapse.jsp.administracion.cambioPassword.leyend.passwordAntiguo"/></legend>
						<div class="elemFormulario">								
							<span class="cajaInputFecha">									
								<input id="formAntiguo" 
									dojoType="dijit.form.ValidationTextBox"
									required="true"
									trim="true"  
									promptMessage="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.tooltip.passwordAntiguo"/>"
									invalidMessage ="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.advertencia.passwordAntiguo"/>"
									type="password"
									maxLength="100"
									name="formAntiguo"/>
				        	</span>
						</div>
					</fieldset>
				</div>
				<div class="divisionDerecha">
					<fieldset id="contenedorFormularioPassword" class="separadorFormulario">
					<legend class="letraGrande"><fmt:message key="timeLapse.jsp.administracion.cambioPassword.leyend.passwordNuevo"/></legend>
						<div class="elemFormulario">								
							<span id="contenedorFechaFin" class="cajaInputFecha">									
								<input id="formPasswd1"
									dojoType="dijit.form.ValidationTextBox"
									required="true"
									trim="true"  
									promptMessage="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.tooltip.passwordNuevo1"/>"
									invalidMessage ="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.advertencia.passwordNuevo1"/>"
									type="password"
									maxLength="100"
									name="formPasswd1"/>									
				        	</span>
				        	<span class="cajaInputFecha">									
								<input id="formPasswd2" 
									dojoType="dijit.form.ValidationTextBox"
									required="true"
									trim="true"  
									promptMessage="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.tooltip.passwordNuevo2"/>"
									invalidMessage ="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.advertencia.passwordNuevo2"/>"
									type="password"
									maxLength="100"
									name="formPasswd2"/>
				        	</span>
						</div>					
					</fieldset>
				</div>
			</div>
		</div>
					
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="efectuarCambio" label="<fmt:message key="timeLapse.jsp.administracion.cambioPassword.botones.efectuarCambio"/>" class="boton"></div>				
		</div>
	</form>
</div>
