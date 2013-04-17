<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<span class="cajaInputFiltro">
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
<h1 ><fmt:message key="timeLapse.jsp.categorias.adminCategorias.title" /></h1>
</div>

<div class="contenedorFormulario">
	<div class="partsContainer"> 
		<div class="gridContainer"> 
			<table id="grid" dojoType="dojox.grid.DataGrid"
	       		query="{ nombre :'*' }" store="timeLapse.categorias.adminCategorias.jsonStore" 
	       		selectionMode="extended" queryOptions="{ignoreCase: true}"
	       		noDataMessage='<fmt:message key="timeLapse.jsp.categorias.adminCategorias.gridNoData"/>'>
			<thead>
				<tr>
					<th field="nombre" width="23%"><fmt:message key="timeLapse.jsp.categorias.adminCategorias.column.nombre"/></th>					
					<th field="descripcion" width="25%"><fmt:message key="timeLapse.jsp.categorias.adminCategorias.column.descripcion"/></th>				
		
				</tr>
			</thead>
		</table>
		</div> 
	</div> 
	
	<div class="contenedorBotones" id="botonesPrin">
		<div dojoType="dijit.form.Button" id="botonNuevo" label="<fmt:message key="timeLapse.jsp.botonNuevo"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonModificar" label="<fmt:message key="timeLapse.jsp.botonModificar"/>" class="boton"></div>
		<div dojoType="dijit.form.Button" id="botonEliminar" label="<fmt:message key="timeLapse.jsp.botonEliminar"/>" class="boton"></div>	
	</div>
	<div id="contenedorNumero" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
	</div>
</div>
<div dojoType="dijit.Dialog" id="formularioPopUp" class="popUp" title="<fmt:message key="timeLapse.jsp.categorias.adminCategorias.titleFormulario"/>" >
	<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
		encType="multipart/form-data" action="" method="">
		<input type="hidden" name="formOID"id="formOID" dojoType="dijit.form.TextBox" />
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.categorias.adminCategorias.inputNombre"/></span>
			</span>
			<span class="cajaInputGran">
				<input id="formNombre" 
					dojoType="dijit.form.ValidationTextBox"
					required="true"
					trim="true"  
					promptMessage="<fmt:message key="timeLapse.jsp.categorias.adminCategorias.tooltipNombre"/>"
					type="text"
					maxLength="100"
					name="formNombre"/>
			</span>
		</div>
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.categorias.adminCategorias.inputDescripcion"/></span>
			</span>
			<span class="cajaInputGran">
				<input id="formDesc" 
					dojoType="dijit.form.ValidationTextBox"
					trim="true"
					promptMessage= "<fmt:message key="timeLapse.jsp.categorias.adminCategorias.tooltDescripcion"/>"  
					type="text"
					maxLength="500"
					name="formDesc"/>
			</span>
		</div>
		<div class="contenedorBotones" id="botonesPopUp">
			<div dojoType="dijit.form.Button" id="botonGuardar" label="<fmt:message key="timeLapse.jsp.botonGuardar"/>"></div>	
		</div>
	</form>


</div>




