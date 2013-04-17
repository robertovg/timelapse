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
<h1 ><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.title"/></h1>
</div>

<div class="contenedorFormulario">
	<div class="partsContainer"> 
		<div class="gridContainer"> 
			<table id="grid" dojoType="dojox.grid.DataGrid"
	       		query="{ oid :'*' }" store="timeLapse.administracion.adminFuncionalidades.jsonStore" 
	       		selectionMode="extended" queryOptions="{ignoreCase: true}"
	       		noDataMessage='<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.gridNoData"/>'>
			<thead>
				<tr>
					<th field="nombre" width="23%"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.column.nombre"/></th>
					<th field="nombreObj" width="25%"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.column.objetivo"/></th>
					<th field="descripcion" width="35%"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.column.descripcion"/></th>
					<th field="orden" width="15%"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.column.orden"/></th>
				
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
<div dojoType="dijit.Dialog" id="formularioPopUp" class="popUp" title="<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.titleFormulario"/>" >
	<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
		encType="multipart/form-data" action="" method="">
		<input type="hidden" name="formOID"id="formOID" dojoType="dijit.form.TextBox" />
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.inputNombre"/></span>
			</span>
			<span class="cajaInputGran">			
				<input id="formNombre" 
					dojoType="dijit.form.ValidationTextBox"
					required="true"
					trim="true"  
					promptMessage="<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.tooltipNombre"/>"
					type="text"
					maxLength="45"
					name="formNombre"/>
			</span>
		</div>
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.inputObjetivo"/></span>
			</span>
			<span class="cajaInputGran">
			    <input dojoType="dijit.form.FilteringSelect"
	               id="formObjetivo"
	               searchAttr="nombre"
	               name="formObjetivo"                    
	               autocomplete="true"
	               invalidMessage="<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.tooltipObjetivo"/>"
	               />
       		</span>
		</div>
		
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.inputDescripcion"/></span>
			</span>
			<span class="cajaInputGran">
				<input id="formDesc" 
					dojoType="dijit.form.ValidationTextBox"
					trim="true"
					promptMessage= "<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.tooltipDescripcion"/>"  
					type="text"
					maxLength="45"
					name="formDesc"/>
			</span>
		</div>
		<div class="elemFormulario">
			<span class="cajaTexto">
				<span class="labelTexto"><fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.inputOrden"/></span>
			</span>
			<span class="cajaInputMed">
				<input dojoType="dijit.form.NumberSpinner"
	               value="1"
	               smallDelta="1"
	               constraints="{min:1,max:1550,places:0}"
	               required="true"
	               promptMessage= "<fmt:message key="timeLapse.jsp.administracion.adminFuncionalidades.tooltipOrden"/>"
	               id="formOrden"
	               name="formOrden"/>
        	</span>
		</div>
		
		
		<div class="contenedorBotones" id="botonesPopUp">
			<div dojoType="dijit.form.Button" id="botonGuardar" label="<fmt:message key="timeLapse.jsp.botonGuardar"/>"></div>	
		</div>
	</form>


</div>




