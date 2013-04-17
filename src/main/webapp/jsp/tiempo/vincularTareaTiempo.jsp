<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="contenedorPrimero">

	<b class="divRedondo500">
	<b class="divRedondo1"><b></b></b>
	<b class="divRedondo2"><b></b></b>
	<b class="divRedondo3"></b>
	<b class="divRedondo4"></b>
	<b class="divRedondo5"></b></b>
	<div class="divNegro">
	<h1><fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.title.fechas"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
			encType="multipart/form-data" action="" method="">
			<div class="partsContainer"> 
				<div class="gridContainer"> 
					<div class="divisionIzquierda">
						<fieldset id="contenedorFormularioFechasInicio" class="separadorFormulario">
						<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.leyend.fechasInicio"/></legend>
							<div class="elemFormulario">								
								<span id="contenedorFechaInicio" class="cajaInputFecha">			
									<img class="imagenReloj" src="../images/clendar.gif"/>
									<input dojoType="dijit.form.DateTextBox"
						               required="false"
						               promptMessage= "<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.tooltipFechaInicio"/>"
						               id="formFechaInicio"
						               name="formFechaInicio"
						               datePattern="dd/MM/yyyy"/>
					        	</span>
					        	<span class="cajaInputHora">
									<img class="imagenReloj" src="../images/time.gif"/>									
									<input dojoType="dijit.form.TimeTextBox"
						               required="false"
						               promptMessage= "<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.tooltipHoraInicio"/>"
						               id="formHoraInicio"
						               timePattern="HH:mm:ss"
						               name="formHoraInicio"/>
					        	</span>
							</div>
						</fieldset>
					</div>
					<div class="divisionDerecha">
						<fieldset id="contenedorFormularioFechasFin" class="separadorFormulario">
						<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.leyend.fechasFin"/></legend>
							<div class="elemFormulario">								
								<span id="contenedorFechaFin" class="cajaInputFecha">			
									<img class="imagenReloj" src="../images/clendar.gif"/>
									<input dojoType="dijit.form.DateTextBox"
						               required="false"
						               promptMessage= "<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.tooltipFechaFin"/>"
						               id="formFechaFin"
						               name="formFechaFin"
						               datePattern="dd/MM/yyyy"/>									
					        	</span>
					        	<span class="cajaInputHora">			
									<img class="imagenReloj" src="../images/time.gif"/>
									<input dojoType="dijit.form.TimeTextBox"
						               required="false"
						               promptMessage= "<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.tooltipHoraFin"/>"
						               id="formHoraFin"
						               name="formHoraFin"
						               timePattern="HH:mm:ss"/>
					        	</span>
							</div>					
						</fieldset>
					</div>
				</div>
			</div>
						
			<div class="contenedorBotones" id="botonesPrin">
				<div dojoType="dijit.form.Button" id="botonSeleccionSiguiente" label="<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.botones.seleccionarSegundo"/>" class="boton"></div>				
			</div>
		</form>
	</div>
</div>
<div id="contenedorSegundo" style="display:none;">
<span class="cajaInputFiltro">
<span id="filtroTareas">
<a id="anclaFiltroTareasSegundo">
<img id="imagenFiltro" src="../images/filtro.png"/>
</a>
</span>
<span id="filtroGridContainer">
<input id="filtroGridSegundo" 
				dojoType="dijit.form.ValidationTextBox"
				required="false"
				trim="true"  
				promptMessage="<fmt:message key="timeLapse.jsp.ayudasFiltro1"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro2"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro3"/>"
				type="text"
				name="filtroGridSegundo"/>

</span>
</span>	 
	<b class="divRedondo500">
	<b class="divRedondo1"><b></b></b>
	<b class="divRedondo2"><b></b></b>
	<b class="divRedondo3"></b>
	<b class="divRedondo4"></b>
	<b class="divRedondo5"></b></b>
	<div class="divNegro">
	<h1><fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.title.tareas"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridSegundo" dojoType="dojox.grid.DataGrid"
		       		query="{ nombre :'*' }" store="timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo" 
		       		rowSelector="20px" selectionMode="extended" queryOptions="{ignoreCase: true}" 
		       		noDataMessage='<fmt:message key="timeLapse.jsp.tareas.adminTareas.gridNoData"/>'>
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
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonVolver" label="<fmt:message key="timeLapse.jsp.botonVolver"/>" class="boton"></div>
			<div dojoType="dijit.form.Button" id="botonVincular" label="<fmt:message key="timeLapse.jsp.tiempo.vincularTareaTiempo.botones.vincular"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroSegundo" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>



