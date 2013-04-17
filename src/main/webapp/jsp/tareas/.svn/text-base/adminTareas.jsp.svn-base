<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<span class="cajaInputFiltro">
<span id="filtroTareas">
<a id="anclaQuitarTareasEspecificas"  style="display:none;">
<img id="imagenCerrarTareasEspecificas" src="../images/close.png" />
</a>
</span>
<span id="filtroTareas">
<a id="anclaFiltroTareas">
<img id="imagenFiltro" src="../images/filtro.png"/>
</a>
</span>
<span id="filtroGridContainer">
<input id="filtroGrid" 
				dojoType="dijit.form.ValidationTextBox"
				required="false"
				trim="true"  
				promptMessage="<fmt:message key="timeLapse.jsp.ayudasFiltro1"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro2"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro3"/>"
				type="text"
				name="filtroGrid"/>

</span>
</span>	   
<b class="divRedondo500">
<b class="divRedondo1"><b></b></b>
<b class="divRedondo2"><b></b></b>
<b class="divRedondo3"></b>
<b class="divRedondo4"></b>
<b class="divRedondo5"></b></b>
<div class="divNegro">
<h1><fmt:message key="timeLapse.jsp.tareas.adminTareas.title" /></h1>
</div>
<div class="contenedorFormulario">
	<div class="partsContainer"> 
		<div class="gridContainer"> 
			<table id="grid" dojoType="dojox.grid.DataGrid"
	       		query="{ nombre :'*' }" store="timeLapse.tareas.adminTareas.jsonStore" 
	       		selectionMode="extended" queryOptions="{ignoreCase: true}" 
	       		noDataMessage='<fmt:message key="timeLapse.jsp.tareas.adminTareas.gridNoData"/>' >
			<thead>
				<tr>
					<th field="nombre" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.nombre"/></th>					
					<th field="descripcion" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.descripcion"/></th>					
					<th field="importancia" ><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.importancia"/></th>
					<th field="fechaInicio" formatter="timeLapse.utils.formato.dateToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.fechaInicio"/></th>				
					<th field="fechaFin" formatter="timeLapse.utils.formato.dateToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.fechaFin"/></th>
					<th field="nombreCategoria" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.nombreCategoria"/></th>
					<th field="nombreGrupo" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.nombreGrupo"/></th>
					<th field="autorrealizable" formatter="timeLapse.utils.formato.booleanToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.autorrealizable"/></th>
					<th field="localizacionAsociada" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.localizacionAsociada"/></th>
					<th field="fechaCreacion" formatter="timeLapse.utils.formato.dateToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.fechaCreacion"/></th>					
					<th field="nombreTareaPadre" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.nombreTareaPadre"/></th>
					<th field="fechaRealizacion" formatter="timeLapse.utils.formato.dateToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.fechaRealizacion"/></th>
					<th field="nombreUsuarioCreador" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.usuarioCreador"/></th>
					<th field="nombreUsuarioUsuarioAsociado" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.tareas.adminTareas.column.usaurioAsociado"/></th>		
				</tr>
			</thead>
		</thead>
		</table>
		</div> 
	</div> 
	
	<div class="contenedorBotones" id="botonesPrin">
		<div dojoType="dijit.form.Button" id="botonNuevo" label="<fmt:message key="timeLapse.jsp.botonNuevo"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonModificar" label="<fmt:message key="timeLapse.jsp.botonModificar"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonEliminar" label="<fmt:message key="timeLapse.jsp.botonEliminar"/>" class="boton"></div>	
		<div dojoType="dijit.form.Button" id="botonMarcarFinalizada" label="<fmt:message key="timeLapse.jsp.botonMarcarFinalizada"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonClonar" label="<fmt:message key="timeLapse.jsp.botonClonar"/>" class="boton"></div>
	</div>
	<div id="contenedorNumero" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
	</div>
	
</div>
<div dojoType="dijit.Dialog" id="formularioPopUp" class="popUp" title="<fmt:message key="timeLapse.jsp.tareas.adminTareas.titleFormulario"/>" >
	<form  onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
		encType="multipart/form-data" action="" method="">
		<input type="hidden" name="formOID"id="formOID" dojoType="dijit.form.TextBox" />
		<fieldset id="contenedorFormularioGeneral" class="separadorFormulario">
		<legend><fmt:message key="timeLapse.jsp.tareas.adminTareas.leyend.general"/></legend>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputNombre"/></span>
				</span>
				<span class="cajaInputGran">
					<input id="formNombre" 
						dojoType="dijit.form.ValidationTextBox"
						required="true"
						trim="true"  
						promptMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipNombre"/>"
						type="text"
						maxLength="100"
						name="formNombre"/>
				</span>
			</div>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputDescripcion"/></span>
				</span>
				<span class="cajaInputGran">
					<input id="formDesc" 
						dojoType="dijit.form.ValidationTextBox"
						required="true"						
						trim="true"
						promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipDescripcion"/>"  
						type="text"
						maxLength="500"
						name="formDesc"/>
						
				</span>
			</div>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputImportancia"/></span>
				</span>
				<span class="cajaInputMed">			
					<input dojoType="dijit.form.NumberSpinner"
		               value="1"
		               smallDelta="1"
		               constraints="{min:1,max:10,places:0}"
		               required="true"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipImportancia"/>"
		               id="formImportancia"
		               name="formImportancia"/>
	        	</span>
			</div>			
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputLocalizacion"/></span>
				</span>
				<span class="cajaInputGran">
					<input id="formLocalizacion" 
						dojoType="dijit.form.ValidationTextBox"
						trim="true"  
						promptMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipLocalizacion"/>"
						type="text"
						maxLength="500"
						name="formLocalizacion"/>
				</span>
			</div>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputAutorrealizable"/></span>
				</span>
				<span class="cajaInputGran">
					<input id="formAutorrealizable" 
						dojoType="dijit.form.CheckBox"					  
						promptMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipAutorrealizable"/>"					
						name="formAutorrealizable" value="on"/>
				</span>
			</div>		
			
		</fieldset>		
		<fieldset id="contenedorFormularioFechas" class="separadorFormulario">
		<legend><fmt:message key="timeLapse.jsp.tareas.adminTareas.leyend.fechas"/></legend>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputFechaInicio"/></span>
				</span>
				<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipFechaInicio"/>"
		               id="formFechaInicio"
		               name="formFechaInicio"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.TimeTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipHoraInicio"/>"
		               id="formHoraInicio"
		               timePattern="HH:mm:ss"
		               name="formHoraInicio"/>
	        	</span>
			</div>			
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputFechaFin"/></span>
				</span>
				<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.inputFechaFin"/>"
		               id="formFechaFin"
		               name="formFechaFin"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.TimeTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipHoraFin"/>"
		               id="formHoraFin"
		               name="formHoraFin"
		               timePattern="HH:mm:ss"/>
	        	</span>
			</div>
			
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputFechaRealizacion"/></span>
				</span>
				<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipFechaRealizacion"/>"
		               id="formFechaRealizacion"
		               name="formFechaRealizacion"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.TimeTextBox"
		               required="false"
		               promptMessage= "<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipHoraRealizacion"/>"
		               id="formHoraRealizacion"
		               name="formHoraRealizacion"
		               timePattern="HH:mm:ss"/>
	        	</span>
			</div>			
		</fieldset>
	</form>
	<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm2" 
	encType="multipart/form-data" action="" method="">
		<fieldset id="contenedorFormularioAsociaciones" class="separadorFormulario">
		<legend><fmt:message key="timeLapse.jsp.tareas.adminTareas.leyend.asociaciones"/></legend>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputCategoria"/></span>
				</span>
				<span class="cajaInputGran">
				    <input dojoType="dijit.form.FilteringSelect"
		               id="formCategoria"
		               searchAttr="nombre"
		               name="formCategoria"                    
		               autocomplete="true"
		               value="-"
		               invalidMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipCategoria"/>"
		               />
	       		</span>
			</div>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputGrupo"/></span>
				</span>
				<span class="cajaInputGran">
				    <input dojoType="dijit.form.FilteringSelect"
		               id="formGrupo"
		               searchAttr="nombre"
		               name="formGrupo"                    
		               autocomplete="true"
		               value="-"
		               invalidMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipGrupo"/>"
		               />
	       		</span>
			</div>
			<div class="elemFormulario">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.adminTareas.inputTareaPadre"/></span>
				</span>
				<span class="cajaInputGran">
				    <input dojoType="dijit.form.FilteringSelect"
		               id=formTareaPadre
		               searchAttr="nombre"
		               name="formTareaPadre"                    
		               autocomplete="true"
		               value="-"
		               invalidMessage="<fmt:message key="timeLapse.jsp.tareas.adminTareas.tooltipTareaPadre"/>"
		               />
	       		</span>
			</div>
			</fieldset>
		</form>
			
		<div class="contenedorBotones" id="botonesPopUp">
			<div dojoType="dijit.form.Button" id="botonGuardar" label="<fmt:message key="timeLapse.jsp.botonGuardar"/>"></div>	
		</div>



</div>




