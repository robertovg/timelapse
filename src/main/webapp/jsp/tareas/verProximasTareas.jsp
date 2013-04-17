<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<span class="cajaInputOrden">
<span>
	<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.orden.title"/></span>
</span>	
<span>
	  <input dojotype="dijit.form.RadioButton" id="ordenImportancia"  name="filtroOrden" 
       				value="importancia" type="radio" /> 
	  <label for="ordenImportancia"><fmt:message key="timeLapse.jsp.tareas.orden.importancia"/></label>
	  <input dojoType="dijit.form.RadioButton" id="ordenFecha" name="filtroOrden" 
       				value="fecha" type="radio" />
	  <label for="ordenFecha"><fmt:message key="timeLapse.jsp.tareas.orden.fecha"/></label> 
</span>


</span>	 
<b class="divRedondo500">
<b class="divRedondo1"><b></b></b>
<b class="divRedondo2"><b></b></b>
<b class="divRedondo3"></b>
<b class="divRedondo4"></b>
<b class="divRedondo5"></b></b>
<div class="divNegro">
<h1><fmt:message key="timeLapse.jsp.verProximasTareas.proximasTareas.title"/></h1>
</div>
<div class="contenedorFormulario">
	<div class="partsContainerAmarillo"> 
		<div class="gridContainer"> 
			<table id="gridSegundo" dojoType="dojox.grid.DataGrid"
	       		query="{ nombre :'*' }" store="timeLapse.tareas.verProximasTareas.jsonStoreSegundo" 
	       		selectionMode="extended" queryOptions="{ignoreCase: true}" 
	       		noDataMessage='<fmt:message key="timeLapse.jsp.tareas.verProximasTareas.gridNoData"/>'>
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
		<div dojoType="dijit.form.Button" id="botonMarcarFinalizada" label="<fmt:message key="timeLapse.jsp.botonMarcarFinalizada"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonAdministrar" label="<fmt:message key="timeLapse.jsp.tareas.administrar.boton"/>" class="boton"></div>				
	</div>
	<div id="contenedorNumeroSegundo" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
	
	</div>
</div>




