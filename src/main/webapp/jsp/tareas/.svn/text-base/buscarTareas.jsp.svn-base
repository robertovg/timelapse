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
	<h1><fmt:message key="timeLapse.jsp.tareas.busqueda.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<form onsubmit="return false" dojoType="dijit.form.Form" id="myForm" 
			encType="multipart/form-data" action="" method="">
			<div class="partsContainer"> 
				<div class="gridContainer"> 
					<div class="divisionIzquierda">
						<fieldset id="contenedorFiltrosInclExcl" class="separadorFormulario">
						<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.texto"/></legend>
							<div class="elemFormulario" id="elemTareasRealizadas">
								<span class="cajaTexto"> 
									<span class="labelTexto">
										<fmt:message key="timeLapse.jsp.tareas.contiene.title" />
									</span>
							 	</span> 
								<span class="cajaInputMed">
									<input id="formContiene" 
										dojoType="dijit.form.ValidationTextBox"										
										trim="true"
										type="text"
										maxLength="50"
										name="formContiene"/>
								</span>
							</div>
							<div class="elemFormulario" id="elemTareasRealizadas">
								<span class="cajaTexto"> 
									<span class="labelTexto">
										<fmt:message key="timeLapse.jsp.tareas.noContiene.title" />
									</span>
							 	</span> 
								<span class="cajaInputMed">
									<input id="formNoContiene" 
										dojoType="dijit.form.ValidationTextBox"										
										trim="true"
										type="text"
										maxLength="50"
										name="formNoContiene"/>
								</span>
							</div>
							<div class="elemFormulario" id="elemTareasRealizadas">
								<span class="cajaTexto"> 
									<span class="labelTexto">
										<fmt:message key="timeLapse.jsp.tareas.ubicacion.title" />
									</span>
							 	</span> 
								<span class="cajaInputMed">
									<input id="formUbicacion" 
										dojoType="dijit.form.ValidationTextBox"										
										trim="true"
										type="text"
										maxLength="50"
										name="formUbicacion"/>
								</span>
							</div>				
						</fieldset>
						<fieldset id="contenedorAsociaciones" class="separadorFormulario">
						<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.asociaciones"/></legend>			
							<div class="elemFormulario" id="elemCategorias">
								<span class="cajaTexto">
									<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.categorias.title"/></span>
								</span>	
								<span class="cajaInputMed">
									<input dojoType="dijit.form.FilteringSelect"
						               id="filtroCategorias"
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
								<span class="cajaInputMed">
									<input dojoType="dijit.form.FilteringSelect"
						               id="filtroGrupos"
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
								<span class="cajaInputMed">
									<input dojoType="dijit.form.FilteringSelect"
						               id="filtroTareasPadres"
						               searchAttr="nombre"
						               name="filtroTareasPadres"                    
						               autocomplete="true"						               
						               />
					        	</span>	               
							</div>
						</fieldset>
					</div>
					<div class="divisionDerecha">
						<fieldset id="contenedorFiltrosInclExcl" class="separadorFormulario">
						<legend class="letraGrande"><fmt:message key="timeLapse.jsp.tareas.leyend.excluirIncluir"/></legend>
							<div class="elemFormulario" id="elemTareasRealizadas">
								<span class="cajaTexto"> 
									<span class="labelTexto">
										<fmt:message key="timeLapse.jsp.tareas.realizadas.title" />
									</span>
							 	</span> 
								<span class="cajaInputMed">
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
								<span class="cajaInputMed">
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
								<span class="cajaInputMed">
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
								<span class="cajaInputMed">
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
							<div class="elemFormulario" id="elemFechaCreacion">
								<span class="cajaTexto">
									<span class="labelTexto"><fmt:message key="timeLapse.jsp.tareas.fechaCreacion.title"/></span>
								</span>
								<span class="cajaInputChic">			
									<input dojoType="dijit.form.DateTextBox"
						               required="false"		               
						               id="filtroFechaCreacionInf"
						               name="filtroFechaCreacionInf"
						               datePattern="dd/MM/yyyy"/>
					        	</span>
					        	<span class="cajaInputChic">			
									<input dojoType="dijit.form.DateTextBox"
						               required="false"		               
						               id="filtroFechaCreacionSup"
						               name="filtroFechaCreacionSup"
						               datePattern="dd/MM/yyyy"/>
					        	</span>
							</div>			
						</fieldset>
						
					</div>
				</div>
			</div>
						
			<div class="contenedorBotones" id="botonesPrin">
				<div dojoType="dijit.form.Button" id="botonSeleccionReset" label="<fmt:message key="timeLapse.jsp.tareas.reset.boton"/>" class="boton"></div>
				<div dojoType="dijit.form.Button" id="botonSeleccionSiguiente" label="<fmt:message key="timeLapse.jsp.tareas.busqueda.boton"/>" class="boton"></div>			
			</div>
			
		</form>
	</div>
</div>
<div id="contenedorSegundo" style="display:none;">	 
	<b class="divRedondo500">
	<b class="divRedondo1"><b></b></b>
	<b class="divRedondo2"><b></b></b>
	<b class="divRedondo3"></b>
	<b class="divRedondo4"></b>
	<b class="divRedondo5"></b></b>
	<div class="divNegro">
	<h1><fmt:message key="timeLapse.jsp.tareas.resultado.title"/></h1>
	</div>
	
	<div class="contenedorFormulario">
		<div class="partsContainerAzul"> 
			<div class="gridContainer"> 
				<table id="gridSegundo" dojoType="dojox.grid.DataGrid"
		       		query="{ nombre :'*' }" store="timeLapse.tareas.buscarTareas.jsonStoreSegundo" 
		       		selectionMode="none" queryOptions="{ignoreCase: true}" 
		       		noDataMessage='<fmt:message key="timeLapse.jsp.tareas.buscarTareas.gridNoData"/>'>
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
			<div dojoType="dijit.form.Button" id="botonVolver" label="<fmt:message key="timeLapse.jsp.tareas.busquedaDeNuevo.boton"/>" class="boton"></div>
			<div dojoType="dijit.form.Button" id="botonAdministrar" label="<fmt:message key="timeLapse.jsp.tareas.administrar.boton"/>" class="boton"></div>				
		</div>
		<div id="contenedorNumeroSegundo" class="contenedorNumericosGrid" dojoType="dijit.layout.ContentPane">
		
		</div>
	</div>
</div>



