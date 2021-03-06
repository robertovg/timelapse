<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="contenedorPrimero">
<span class="cajaInputFiltro">

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
	<h1><fmt:message key="timeLapse.jsp.grupos.desvincularUsuarioGrupo.grupos.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridPrimero" dojoType="dojox.grid.DataGrid"
		       		query="{ nombre :'*' }" store="timeLapse.grupos.desvincularUsuarioGrupo.jsonStorePrimero" 
		       		selectionMode="single" queryOptions="{ignoreCase: true}"
		       		noDataMessage='<fmt:message key="timeLapse.jsp.grupos.adminGrupos.gridNoData"/>' >
				<thead>
					<tr>
						<th field="nombre" width="23%"><fmt:message key="timeLapse.jsp.grupos.adminGrupos.column.nombre"/></th>					
						<th field="descripcion" width="25%"><fmt:message key="timeLapse.jsp.grupos.adminGrupos.column.descripcion"/></th>				
			
					</tr>
				</thead>
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonSeleccionSiguiente" label="<fmt:message key="timeLapse.jsp.grupos.desvincularUsuarioGrupo.botones.seleccionarSegundo"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroPrimero" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>
<div id="contenedorSegundo" style="display:none;">
<span class="cajaInputFiltro">

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
	<h1><fmt:message key="timeLapse.jsp.grupos.desvincularUsuarioGrupo.usuariosAplicacion.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridSegundo" dojoType="dojox.grid.DataGrid"
		       		query="{ nombre :'*' }" store="timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo" 
		       		selectionMode="extended" queryOptions="{ignoreCase: true}"
		       		noDataMessage='<fmt:message key="timeLapse.jsp.usuarios.gridNoData"/>' >
				<thead>
					<tr>
						<th field="nombre" width="25%" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.grupos.vincularUsuarioGrupo.column.nombreUsuario"/></th>					
						<th field="nombrePropio" width="25%" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.grupos.vincularUsuarioGrupo.column.nombrePropio"/></th>					
						<th field="apellido1" width="25%" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.grupos.vincularUsuarioGrupo.column.apellido1"/></th>
						<th field="apellido2" width="25%" formatter="timeLapse.utils.formato.stringToGrid"><fmt:message key="timeLapse.jsp.grupos.vincularUsuarioGrupo.column.apellido2"/></th>								
					</tr>
				</thead>
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonVolver" label="<fmt:message key="timeLapse.jsp.botonVolver"/>" class="boton"></div>
			<div dojoType="dijit.form.Button" id="botonVincular" label="<fmt:message key="timeLapse.jsp.grupos.desvincularUsuarioGrupo.botones.desvincular"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroSegundo" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>



