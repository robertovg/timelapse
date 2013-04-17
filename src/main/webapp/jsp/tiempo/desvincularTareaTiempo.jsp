<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="contenedorPrimero">
<span class="cajaInputFiltro">
<span id="filtroTareas">
<a id="anclaFiltroTareasPrimero">
<img id="imagenFiltro" src="../images/filtro.png"/>
</a>
</span>
<span id="filtroGridContainer">
<input id="filtroGridPrimero" 
				dojoType="dijit.form.ValidationTextBox"
				required="false"
				trim="true"  
				promptMessage="<fmt:message key="timeLapse.jsp.ayudasFiltro1"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro2"/> <br> <fmt:message key="timeLapse.jsp.ayudasFiltro3"/>"
				type="text"
				name="filtroGridPrimero"/>

</span>
</span>	 
	<b class="divRedondo500">
	<b class="divRedondo1"><b></b></b>
	<b class="divRedondo2"><b></b></b>
	<b class="divRedondo3"></b>
	<b class="divRedondo4"></b>
	<b class="divRedondo5"></b></b>
	<div class="divNegro">
	<h1><fmt:message key="timeLapse.jsp.tiempo.desvincularTareaTiempo.tareasTemporizadas.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridPrimero" dojoType="dojox.grid.DataGrid"
		       		query="{ nombre :'*' }" store="timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero" 
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
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonDesvincula" label="<fmt:message key="timeLapse.jsp.tiempo.desvincularTareaTiempo.botones.desvincular"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroPrimero" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>