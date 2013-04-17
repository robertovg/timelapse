/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.desvincularRolAccion");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");


dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");

dojo.requireLocalization("timeLapse","desvincularRolAccion");
//Inicializo los textos
timeLapse.administracion.desvincularRolAccion.textos = dojo.i18n.getLocalization("timeLapse","desvincularRolAccion");


timeLapse.administracion.desvincularRolAccion.init = function() {
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos de los roles 
	 */
	if(timeLapse.administracion.desvincularRolAccion.rolesJsonStore == undefined){
		timeLapse.administracion.desvincularRolAccion.rolesJsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
	/*
	 * Contiene los datos de las acciones
	 */
	if(timeLapse.administracion.desvincularRolAccion.accionesJsonStore == undefined){
		timeLapse.administracion.desvincularRolAccion.accionesJsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/desvincularRolAccion.jsp"); 
	contenedor.attr('content',contenido);
	
	
	var vaBien = {
		accion: function(datosRoles){
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			var datosRoles = new Array();
			for (var i = 0; i < datosRoles.listRoles.length; i++) {
				var obj = new Object();
				obj.oid = datosRoles.listRoles[i].oid;
				obj.nombre = datosRoles.listRoles[i].nombre;
				obj.descripcion = datosRoles.listRoles[i].descripcion;
				obj.grado = datosRoles.listRoles[i].grado;
				datosRoles.push(obj);
				
			}
			
			var myData = {
				identifier: 'oid',
				items: datosRoles
			};
			var selectStore = new dojo.data.ItemFileReadStore({
				data: myData
			});
			var select = dijit.byId("formFuncionalidad");
			
			select.store = selectStore;
			select.startup();
		}
			
		
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaFuncionalidades.action",null, vaBien);
	
	//Fabrico el grid de los roles
    timeLapse.administracion.desvincularRolAccion.recargaGridRoles();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionAcciones"), "onclick",  timeLapse.administracion.desvincularRolAccion.seleccionaAcciones);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.administracion.desvincularRolAccion.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonDesvincular"), "onclick",  timeLapse.administracion.desvincularRolAccion.efectuarDesvinculacionPermisyAcciones);
	dojo.connect(dojo.byId("filtroGridRoles"), "onkeyup", timeLapse.administracion.desvincularRolAccion.filtraGridRoles);
	dojo.connect(dojo.byId("filtroGridAcciones"), "onkeyup", timeLapse.administracion.desvincularRolAccion.filtraGridAcciones);
		
}
timeLapse.administracion.desvincularRolAccion.recargaGridRoles = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio carga parrilla de Roles");
	
	
	var gridRoles = dijit.byId("gridRoles");

	var vaBien = {
		accion: function(datosRolesBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.desvincularRolAccion.filtraGridRoles("*");
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
			timeLapse.administracion.desvincularRolAccion.filtraGridRoles();
			dojo.connect(gridRoles, "onRowClick", timeLapse.administracion.desvincularRolAccion.muestraNumerosRoles);
			console.log("Fin carga parrilla de Roles");
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaRoles.action",null, vaBien);
	
}
timeLapse.administracion.desvincularRolAccion.recargaGridAcciones = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación parrilla de Administración de las Acciones");	
	var grid = dijit.byId("gridRoles");
	var row = grid.selection.getSelected();
	var gridAcciones = dijit.byId("gridAcciones");

	var vaBien = {
	accion: function(datosAccionesBD){
		//Hago una limpieza de sus datos
		timeLapse.administracion.desvincularRolAccion.filtraGridAcciones("*");
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
		timeLapse.administracion.desvincularRolAccion.filtraGridAcciones();
		dojo.connect(gridAcciones, "onRowClick", timeLapse.administracion.desvincularRolAccion.muestraNumerosAcciones);
		console.log("Fin creación parrilla de Administración de las Acciones");
		
	}
	};
	
	var parametro = {oidRol:row[0].oid};
	
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaAccionesAsociadas.action",parametro, vaBien);	
	
	
}
/**
 * Función que se encarga de mostrar las acciones con las que se asociarán los roles
 */
timeLapse.administracion.desvincularRolAccion.seleccionaAcciones = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridRoles");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.administracion.desvincularRolAccion.recargaGridAcciones();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Rol");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.desvincularRolAccion.textos.debesSeleccionarSoloUnRol);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPermisos","contenedorAcciones",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla de selección de los permisos
 */
timeLapse.administracion.desvincularRolAccion.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorAcciones", "contenedorPermisos");

}
/**
 * Función que realiza la vinculación entre la acción seleccionada anteriormente y las acciones 
 * marcadas
 */
timeLapse.administracion.desvincularRolAccion.efectuarDesvinculacionPermisyAcciones = function(){
	
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
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.desvincularRolAccion.textos.debesSeleccionarAlMenosUnaAccion);

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
				
				timeLapse.administracion.desvincularRolAccion.recargaGridAcciones();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("administracion/desvinculaRolYAcciones.action",parametros, vaBien);
		
	}
	
}

/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.desvincularRolAccion.filtraGridRoles = function(parametro){

	var idFiltro = "filtroGridRoles";
	var idGrid = "gridRoles";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.desvincularRolAccion.muestraNumerosRoles();
}

/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.desvincularRolAccion.filtraGridAcciones = function(parametro){

	var idFiltro = "filtroGridAcciones";
	var idGrid = "gridAcciones";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.desvincularRolAccion.muestraNumerosAcciones();
}


/**
 * Muestra el total de elementos Roles
 */
timeLapse.administracion.desvincularRolAccion.muestraNumerosRoles = function(){
	var grid = dijit.byId("gridRoles");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroRoles").attr("content","" + total);
	
}
/**
 * Muestra el total de elementos Acciones
 */
timeLapse.administracion.desvincularRolAccion.muestraNumerosAcciones = function(){
	var grid = dijit.byId("gridAcciones");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroAcciones").attr("content","" + total);
	
}
