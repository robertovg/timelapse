/**
 * @author robe
 */
dojo.provide("timeLapse.tareas.vincularTareaTarea");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");

dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.tareas.filtroTareasDTO");

dojo.requireLocalization("timeLapse","vincularTareaTarea");
//Inicializo los textos
timeLapse.tareas.vincularTareaTarea.textos = dojo.i18n.getLocalization("timeLapse","vincularTareaTarea");
//Filtro de las tareas
timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero = null;
timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo = null;


timeLapse.tareas.vincularTareaTarea.init = function() {
	timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero = new timeLapse.tareas.filtroTareas(timeLapse.tareas.vincularTareaTarea.recargaGridPrimero,timeLapse.utils.constantes.filtroTareas_TIPO_TODAS);
	
	timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo = new timeLapse.tareas.filtroTareas(timeLapse.tareas.vincularTareaTarea.recargaGridSegundo,timeLapse.utils.constantes.filtroTareas_TIPO_HUERFANAS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.tareas.vincularTareaTarea.jsonStorePrimero == undefined){
		var url = "../tareas/listaTareasParaVincularComoPadres.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero.almacen);
		
		
		timeLapse.tareas.vincularTareaTarea.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.tareas.vincularTareaTarea.jsonStoreSegundo == undefined){
		var url = "../tareas/listaTareasNoAsociadasTareas.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo.almacen);
		
		timeLapse.tareas.vincularTareaTarea.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/vincularTareaTarea.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.tareas.vincularTareaTarea.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.tareas.vincularTareaTarea.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.tareas.vincularTareaTarea.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.tareas.vincularTareaTarea.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.tareas.vincularTareaTarea.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.tareas.vincularTareaTarea.filtraGridSegundo);
	dojo.connect(dojo.byId("anclaFiltroTareasPrimero"), "onclick",timeLapse.tareas.vincularTareaTarea.filtraTareasPrimero);
	dojo.connect(dojo.byId("anclaFiltroTareasSegundo"), "onclick",timeLapse.tareas.vincularTareaTarea.filtraTareasSegundo);

	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.tareas.vincularTareaTarea.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la primera parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasParaVincularComoPadres.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero.almacen);
	
	timeLapse.tareas.vincularTareaTarea.jsonStorePrimero._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridPrimero");
	dojo.connect(grid, "onRowClick", timeLapse.tareas.vincularTareaTarea.muestraNumerosPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.vincularTareaTarea.filtraGridPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.vincularTareaTarea.jsonStorePrimero.close();
	timeLapse.tareas.vincularTareaTarea.jsonStorePrimero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la primera parrilla de Tareas");
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.tareas.vincularTareaTarea.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasNoAsociadasTareas.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo.almacen);
	
	timeLapse.tareas.vincularTareaTarea.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.tareas.vincularTareaTarea.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.vincularTareaTarea.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.vincularTareaTarea.jsonStoreSegundo.close();
	timeLapse.tareas.vincularTareaTarea.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.tareas.vincularTareaTarea.seleccionSiguiente = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.tareas.vincularTareaTarea.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.vincularTareaTarea.textos.debesSeleccionarSoloUnaTarea);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.tareas.vincularTareaTarea.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.tareas.vincularTareaTarea.efectuarVinculacion = function(){
	
	var grid = dijit.byId("gridSegundo");
	var rowsSegundo = grid.selection.getSelected();
	
	
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var rowPrimero = grid.selection.getSelected();
	
	//Inicializo los parámetros que voy a enviarle a la vinculación de Rol/Acción
	var parametros = {listOidEntidades:new Array(), oidEntidad:rowPrimero[0].oid};
	
	
	if (rowsSegundo.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.vincularTareaTarea.textos.debesSeleccionarAlMenosUnaTarea);

	}else{
		
		dojo.forEach(rowsSegundo,
		    function(row) {
				
				parametros.listOidEntidades.push(row.oid);
		    }
		);
			
	}
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.tareas.vincularTareaTarea.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/vinculaTareaTarea.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.tareas.vincularTareaTarea.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tareas.vincularTareaTarea.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.tareas.vincularTareaTarea.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tareas.vincularTareaTarea.muestraNumerosSegundo();
}


/**
 * Unión del botón de filtro
 */
timeLapse.tareas.vincularTareaTarea.persisteCambiosFiltroPrimero = function(){
	timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.tareas.vincularTareaTarea.filtraTareasPrimero = function(){
    
	var filtroTareas = timeLapse.tareas.vincularTareaTarea.filtroTareasPrimero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tareas.vincularTareaTarea.persisteCambiosFiltroPrimero);
	
};

/**
 * Unión del botón de filtro
 */
timeLapse.tareas.vincularTareaTarea.persisteCambiosFiltroSegundo = function(){
	timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.tareas.vincularTareaTarea.filtraTareasSegundo = function(){
    
	var filtroTareas = timeLapse.tareas.vincularTareaTarea.filtroTareasSegundo;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tareas.vincularTareaTarea.persisteCambiosFiltroSegundo);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.tareas.vincularTareaTarea.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.tareas.vincularTareaTarea.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}