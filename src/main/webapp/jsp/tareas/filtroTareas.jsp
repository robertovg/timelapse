<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div dojoType="dijit.Dialog" id="filtroPopUp" class="popUp" title="<fmt:message key="timeLapse.jsp.tareas.filtro.titulo"/>" >
	
	<form onsubmit="return false" dojoType="dijit.form.Form"  
		encType="multipart/form-data" action="" method="">
		<fieldset id="contenedorFiltrosInclExcl" class="separadorFormulario">
		<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.excluirIncluir"/></legend>
			<div class="elemFormulario" id="elemTareasRealizadas">
				<span class="cajaTexto"> 
					<span class="labelTexto">
						<fmt:message key="timeLapse.jsp.tareas.realizadas.title" />
					</span>
			 	</span> 
				<span class="cajaInputGran">
					<div id="filtroTareasRealizadas" name="filtroTareasRealizadas" dojoType="dijit.form.HorizontalSlider" value="0"
					minimum="-1" maximum="1" discreteValues="3" intermediateChanges="true" style="width:200px;"
					showButtons="false">				   
					    <div dojoType="dijit.form.HorizontalRule" container="bottomDecoration"
					    count=3 >
					    </div>
					    <ol dojoType="dijit.form.HorizontalRuleLabels" container="bottomDecoration"  >
					        <li>
					           <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.excluir" />
					        </li>
					        <li>
					          	<fmt:message key="timeLapse.jsp.tareas.estadoFiltros.incluir" />
					        </li>
					        <li>
					            <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.exclusivo" />
					        </li>
					    </ol>
					</div>
				</span>
			</div>			
			<div class="elemFormulario" id="elemTemporizadas">
				<span class="cajaTexto"> 
					<span class="labelTexto">
						<fmt:message key="timeLapse.jsp.tareas.temporizadas.title" />
					</span>
			 	</span> 
				<span class="cajaInputGran">
					<div id="filtroTemporizadas" name="filtroTemporizadas" dojoType="dijit.form.HorizontalSlider" value="0"
					minimum="-1" maximum="1" discreteValues="3" intermediateChanges="true" style="width:200px;"
					showButtons="false">				   
					    <div dojoType="dijit.form.HorizontalRule" container="bottomDecoration"
					    count=3 >
					    </div>
					    <ol dojoType="dijit.form.HorizontalRuleLabels" container="bottomDecoration" >
					        <li>
					           <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.excluir" />
					        </li>
					        <li>
					          	<fmt:message key="timeLapse.jsp.tareas.estadoFiltros.incluir" />
					        </li>
					        <li>
					            <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.exclusivo" />
					        </li>
					    </ol>
					</div>
				</span>
			</div>
			<div class="elemFormulario" id="elemTareasQueSonPadre">
				<span class="cajaTexto"> 
					<span class="labelTexto">
						<fmt:message key="timeLapse.jsp.tareas.tareasQueSonPadre.title" />
					</span>
			 	</span> 
				<span class="cajaInputGran">
					<div id="filtroTareasQueSonPadre" name="filtroTareasQueSonPadre" dojoType="dijit.form.HorizontalSlider" value="0"
					minimum="-1" maximum="1" discreteValues="3" intermediateChanges="true" style="width:200px;"
					showButtons="false">				   
					    <div dojoType="dijit.form.HorizontalRule" container="bottomDecoration"
					    count=3 >
					    </div>
					    <ol dojoType="dijit.form.HorizontalRuleLabels" container="bottomDecoration" >
					        <li>
					           <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.excluir" />
					        </li>
					        <li>
					          	<fmt:message key="timeLapse.jsp.tareas.estadoFiltros.incluir" />
					        </li>
					        <li>
					            <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.exclusivo" />
					        </li>
					    </ol>
					</div>
				</span>
			</div>
			<div class="elemFormulario" id="elemAutorrealizables">
				<span class="cajaTexto"> 
					<span class="labelTexto">
						<fmt:message key="timeLapse.jsp.tareas.autorrealizables.title" />
					</span>
			 	</span> 
				<span class="cajaInputGran">
					<div id="filtroAutorrealizables" name="filtroAutorrealizables" dojoType="dijit.form.HorizontalSlider" value="0"
					minimum="-1" maximum="1" discreteValues="3" intermediateChanges="true" style="width:200px;"
					showButtons="false">				   
					    <div dojoType="dijit.form.HorizontalRule" container="bottomDecoration"
					    count=3 >
					    </div>
					    <ol dojoType="dijit.form.HorizontalRuleLabels" container="bottomDecoration" >
					        <li>
					           <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.excluir" />
					        </li>
					        <li>
					          	<fmt:message key="timeLapse.jsp.tareas.estadoFiltros.incluir" />
					        </li>
					        <li>
					            <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.exclusivo" />
					        </li>
					    </ol>
					</div>
				</span>
			</div>
			<div class="elemFormulario" id="elemPeriodicas">
				<span class="cajaTexto"> 
					<span class="labelTexto">
						<fmt:message key="timeLapse.jsp.tareas.periodicas.title" />
					</span>
			 	</span> 
				<span class="cajaInputGran">
					<div id="filtroPeriodicas" name="filtroPeriodicas" dojoType="dijit.form.HorizontalSlider" value="0"
					minimum="-1" maximum="1" discreteValues="3" intermediateChanges="true" style="width:200px;"
					showButtons="false">				   
					    <div dojoType="dijit.form.HorizontalRule" container="bottomDecoration"
					    count=3 >
					    </div>
					    <ol dojoType="dijit.form.HorizontalRuleLabels" container="bottomDecoration" >
					        <li>
					           <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.excluir" />
					        </li>
					        <li>
					          	<fmt:message key="timeLapse.jsp.tareas.estadoFiltros.incluir" />
					        </li>
					        <li>
					            <fmt:message key="timeLapse.jsp.tareas.estadoFiltros.exclusivo" />
					        </li>
					    </ol>
					</div>
				</span>
			</div>			
		</fieldset>
		<fieldset id="contenedorAsociaciones" class="separadorFormulario">
		<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.asociaciones"/></legend>			
			<div class="elemFormulario" id="elemCategorias">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.categorias.title"/></span>
				</span>	
				<span class="cajaInputGran">
					<input dojoType="dijit.form.FilteringSelect"
		               id="filtroCategorias"
	                   value="0"
		               searchAttr="nombre"
		               name="filtroCategorias"                    
		               autocomplete="true"	               
		               />
	        	</span>	               
			</div>
			<div class="elemFormulario" id="elemGrupos">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.grupos.title"/></span>
				</span>	
				<span class="cajaInputGran">
					<input dojoType="dijit.form.FilteringSelect"
		               id="filtroGrupos"
		               value="0"
		               searchAttr="nombre"
		               name="filtroGrupos"                    
		               autocomplete="true"	               
		               />
	        	</span>	               
			</div>
			<div class="elemFormulario" id="elemTareasPadres">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.tareasPadre.title"/></span>
				</span>	
				<span class="cajaInputGran">
					<input dojoType="dijit.form.FilteringSelect"
		               id="filtroTareasPadres"
		               value="0"
		               searchAttr="nombre"
		               name="filtroTareasPadres"                    
		               autocomplete="true"	               
		               />
	        	</span>	               
			</div>
		</fieldset>
		<fieldset id="contenedorFechas" class="separadorFormulario">
		<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.fechas"/></legend>
		
			<div class="elemFormulario" id="elemFechaPlanificacion">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.fechaPlanificacion.title"/></span>
				</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"		               
		               id="filtroFechaPlanificacionInf"
		               name="filtroFechaPlanificacionInf"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"		               
		               id="filtroFechaPlanificacionSup"
		               name="filtroFechaPlanificacionSup"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
			</div>
			<div class="elemFormulario" id="elemFechaRealizacion">
				<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.fechaRealizacion.title"/></span>
				</span>
				<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"		               
		               id="filtroFechaRealizacionInf"
		               name="filtroFechaRealizacionInf"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
	        	<span class="cajaInputChic">			
					<input dojoType="dijit.form.DateTextBox"
		               required="false"		               
		               id="filtroFechaRealizacionSup"
		               name="filtroFechaRealizacionSup"
		               datePattern="dd/MM/yyyy"/>
	        	</span>
			</div>			
		</fieldset>
		<fieldset id="contenedorOrden" class="separadorFormulario">
		<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.Orden"/></legend>
		  	<div class="elemFormulario" id="elemOrden">
		  		<span class="cajaTexto">
					<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.orden.title"/></span>
				</span>	
				<span class="cajaInputGran">
					  <input dojotype="dijit.form.RadioButton" id="ordenImportancia"  name="filtroOrden" 
           				value="importancia" type="radio" /> 
					  <label for="ordenImportancia"><fmt:message key="timeLapse.jsp.tareas.orden.importancia"/></label>
					  <input dojoType="dijit.form.RadioButton" id="ordenFecha" name="filtroOrden" 
           				value="fecha" type="radio" />
       				  <label for="ordenFecha"><fmt:message key="timeLapse.jsp.tareas.orden.fecha"/></label> 
	        	</span>
        	</div>
		
		</fieldset>

		<div class="contenedorBotones" id="botonesPopUp">
			<div dojoType="dijit.form.Button" id="botonFiltrar" label="<fmt:message key="timeLapse.jsp.tareas.filtro.boton" />"></div>	
		</div>
	</form>

</div>
		