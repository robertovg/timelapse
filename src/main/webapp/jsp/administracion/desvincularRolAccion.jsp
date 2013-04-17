<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="contenedorPermisos">
<span class="cajaInputFiltro">
<span id="filtroGridContainer">
<input id="filtroGridRoles" 
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
	<h1><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.roles.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridRoles" dojoType="dojox.grid.DataGrid"
		       		query="{ oid :'*' }" store="timeLapse.administracion.desvincularRolAccion.rolesJsonStore" 
		       		selectionMode="single" queryOptions="{ignoreCase: true}"
		       		noDataMessage='<fmt:message key="timeLapse.jsp.administracion.vincularRolAccion.gridNoData"/>'>
				<thead>
					<tr>
						<th field="nombre"  width="25%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridRoles.nombre"/></th>					
						<th field="descripcion" width="70%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridRoles.descripcion"/></th>
						<th field="grado" width="5%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridRoles.grado"/></th>
					</tr>
				</thead>
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonSeleccionAcciones" label="<fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.botones.seleccionarAcciones"/>" class="boton"></div>
				
		</div>
		<div id="contenedorNumeroRoles" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>
<div id="contenedorAcciones" style="display:none;">
<span class="cajaInputFiltro">

<span id="filtroGridContainer">
<input id="filtroGridAcciones" 
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
	<h1><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.acciones.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainer"> 
			<div class="gridContainer"> 
				<table id="gridAcciones" dojoType="dojox.grid.DataGrid"
		       		query="{ oid :'*' }" store="timeLapse.administracion.desvincularRolAccion.accionesJsonStore" 
		       		selectionMode="extended" queryOptions="{ignoreCase: true}">
				<thead>
					<tr>
						<th field="nombre" width="23%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridAcciones.nombre"/></th>
						<th field="nombreFunc" width="20%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridAcciones.funcionalidad"/></th>
						<th field="path" width="15%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridAcciones.path"/></th>
						<th field="descripcion" width="25%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridAcciones.descripcion"/></th>
						<th field="orden" width="15%"><fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.gridAcciones.orden"/></th>
					</tr>
				</thead>
			</table>
			</div> 
		</div> 
		
		<div class="contenedorBotones" id="botonesPrin">
			<div dojoType="dijit.form.Button" id="botonVolver" label="<fmt:message key="timeLapse.jsp.botonVolver"/>" class="boton"></div>
			<div dojoType="dijit.form.Button" id="botonDesvincular" label="<fmt:message key="timeLapse.jsp.administracion.desvincularRolAccion.botones.desvincular"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroAcciones" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>



