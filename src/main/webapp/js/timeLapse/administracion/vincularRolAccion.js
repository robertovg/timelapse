/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.vincularRolAccion");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");



dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");


dojo.requireLocalization("timeLapse","vincularRolAccion");
//Inicializo los textos
timeLapse.administracion.vincularRolAccion.textos = dojo.i18n.getLocalization("timeLapse","vincularRolAccion");


timeLapse.administracion.vincularRolAccion.init = function() {
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos de los roles 
	 */
	if(timeLapse.administracion.vincularRolAccion.rolesJsonStore == undefined){
		timeLapse.administracion.vincularRolAccion.rolesJsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
	/*
	 * Contiene los datos de las acciones
	 */
	if(timeLapse.administracion.vincularRolAccion.accionesJsonStore == undefined){
		timeLapse.administracion.vincularRolAccion.accionesJsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/vincularRolAccion.jsp"); 
	contenedor.attr('content',contenido);
	
	
	
	
	//Fabrico el grid de los roles
    timeLapse.administracion.vincularRolAccion.recargaGridRoles();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionAcciones"), "onclick",  timeLapse.administracion.vincularRolAccion.seleccionaAcciones);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.administracion.vincularRolAccion.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.administracion.vincularRolAccion.efectuarVinculacionPermisyAcciones);
	dojo.connect(dojo.byId("filtroGridRoles"), "onkeyup", timeLapse.administracion.vincularRolAccion.filtraGridRoles);
	dojo.connect(dojo.byId("filtroGridAcciones"), "onkeyup", timeLapse.administracion.vincularRolAccion.filtraGridAcciones);
	
		
}
timeLapse.administracion.vincularRolAccion.recargaGridRoles = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio carga parrilla de Roles");
	
	var gridRoles = dijit.byId("gridRoles");
	
	var vaBien = {
		accion: function(datosRolesBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.vincularRolAccion.filtraGridRoles("*");
			timeLapse.utils.limpieza.limpiaGrid(gridRoles);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosRolesBD.listRoles.length; i++){
				var obj = new Object();
				obj.oid = datosRolesBD.listRoles[i].oid;
				obj.nombre = datosRolesBD.listRoles[i].nombre;
				obj.descripcion = datosRolesBD.listRoles[i].descripcion;
				obj.grado = datosRolesBD.listRoles[i].orden;
				
				gridRoles.store.newItem(obj);
		   		
			}
			gridRoles.store.save();
			timeLapse.administracion.vincularRolAccion.filtraGridAcciones();
			dojo.connect(gridRoles, "onRowClick", timeLapse.administracion.vincularRolAccion.muestraNumerosRoles);
			console.log("Fin carga parrilla de Roles");
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaRoles.action",null, vaBien);
	
}
timeLapse.administracion.vincularRolAccion.recargaGridAcciones = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación parrilla de Administración de las Acciones");	
	var grid = dijit.byId("gridRoles");
	var row = grid.selection.getSelected();
	
	var gridAcciones = dijit.byId("gridAcciones");
	var vaBien = {
		accion: function(datosAccionesBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.vincularRolAccion.filtraGridAcciones("*");
			timeLapse.utils.limpieza.limpiaGrid(gridAcciones);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosAccionesBD.listAcciones.length; i++){
				var obj = new Object();
				obj.oid = datosAccionesBD.listAcciones[i].oid;
				obj.nombre = datosAccionesBD.listAcciones[i].nombre;
				obj.nombreFunc = datosAccionesBD.listAcciones[i].tlpFuncionalidad.nombre;
				obj.oidFunc = datosAccionesBD.listAcciones[i].tlpFuncionalidad.oid;
				obj.path = datosAccionesBD.listAcciones[i].path;
				obj.descripcion = datosAccionesBD.listAcciones[i].descripcion;
				obj.orden = datosAccionesBD.listAcciones[i].orden; 
				gridAcciones.store.newItem(obj);
		   		
			}
			gridAcciones.store.save();
			timeLapse.administracion.vincularRolAccion.filtraGridAcciones();
			dojo.connect(gridAcciones, "onRowClick", timeLapse.administracion.vincularRolAccion.muestraNumerosAcciones);
			console.log("Fin creación parrilla de Administración de las Acciones");
		}
	};
	
	var parametro = {oidRol:row[0].oid};
	
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaAccionesNoAsociadas.action",parametro, vaBien);	
	
	
}
/**
 * Función que se encarga de mostrar las acciones con las que se asociarán los roles
 */
timeLapse.administracion.vincularRolAccion.seleccionaAcciones = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridRoles");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.administracion.vincularRolAccion.recargaGridAcciones();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Rol");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.vincularRolAccion.textos.debesSeleccionarSoloUnRol);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPermisos","contenedorAcciones",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla de selección de los permisos
 */
timeLapse.administracion.vincularRolAccion.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorAcciones", "contenedorPermisos");

}
/**
 * Función que realiza la vinculación entre la acción seleccionada anteriormente y las acciones 
 * marcadas
 */
timeLapse.administracion.vincularRolAccion.efectuarVinculacionPermisyAcciones = function(){
	
	var grid = dijit.byId("gridAcciones");
	var rows = grid.selection.getSelected();
	
	
	var correcto = true;
	var grid = dijit.byId("gridRoles");
	var rowAccion = grid.selection.getSelected();
	
	//Inicializo los parámetros que voy a enviarle a la vinculación de Rol/Acción
	var parametros = {listOidAcciones:new Array(), oidRol:rowAccion[0].oid};
	
	
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Acción");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.vincularRolAccion.textos.debesSeleccionarAlMenosUnaAccion);

	}else{
		
		dojo.forEach(rows,
		    function(row) {
				
				parametros.listOidAcciones.push(row.oid);
		    }
		);
			
	}
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.administracion.vincularRolAccion.recargaGridAcciones();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("administracion/vinculaRolYAcciones.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.administracion.vincularRolAccion.filtraGridRoles = function(parametro){
	
	var idFiltro = "filtroGridRoles";
	var idGrid = "gridRoles";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.vincularRolAccion.muestraNumerosRoles();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.vincularRolAccion.filtraGridAcciones = function(parametro){
	
	var idFiltro = "filtroGridAcciones";
	var idGrid = "gridAcciones";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.vincularRolAccion.muestraNumerosAcciones();
	
}
/**
 * Muestra el total de elementos Roles
 */
timeLapse.administracion.vincularRolAccion.muestraNumerosRoles = function(){
	var grid = dijit.byId("gridRoles");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroRoles").attr("content","" + total);
	
}
/**
 * Muestra el total de elementos Acciones
 */
timeLapse.administracion.vincularRolAccion.muestraNumerosAcciones = function(){
	var grid = dijit.byId("gridAcciones");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroAcciones").attr("content","" + total);
	
}


