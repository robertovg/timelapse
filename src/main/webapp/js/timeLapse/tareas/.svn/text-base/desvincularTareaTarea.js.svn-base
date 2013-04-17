/**
 * @author robe
 */
dojo.provide("timeLapse.tareas.desvincularTareaTarea");

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

dojo.requireLocalization("timeLapse","desvincularTareaTarea");
//Inicializo los textos
timeLapse.tareas.desvincularTareaTarea.textos = dojo.i18n.getLocalization("timeLapse","desvincularTareaTarea");
//Filtro de las tareas
timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero = null;


timeLapse.tareas.desvincularTareaTarea.init = function() {
	timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero = new timeLapse.tareas.filtroTareas(timeLapse.tareas.desvincularTareaTarea.recargaGridPrimero,timeLapse.utils.constantes.filtroTareas_TIPO_HIJAS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.tareas.desvincularTareaTarea.jsonStorePrimero == undefined){
		var url = "../tareas/listaTareasAsociadasTareas.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero.almacen);
		
		
		timeLapse.tareas.desvincularTareaTarea.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}	
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/desvincularTareaTarea.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.tareas.desvincularTareaTarea.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonDesvincula"), "onclick",  timeLapse.tareas.desvincularTareaTarea.efectuarDesvinculacion);	
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.tareas.desvincularTareaTarea.filtraGridPrimero);	
	dojo.connect(dojo.byId("anclaFiltroTareasPrimero"), "onclick",timeLapse.tareas.desvincularTareaTarea.filtraTareasPrimero);
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.tareas.desvincularTareaTarea.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la primera parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasAsociadasTareas.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero.almacen);
	
	timeLapse.tareas.desvincularTareaTarea.jsonStorePrimero._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridPrimero");
	dojo.connect(grid, "onRowClick", timeLapse.tareas.desvincularTareaTarea.muestraNumerosPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.desvincularTareaTarea.filtraGridPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.desvincularTareaTarea.jsonStorePrimero.close();
	timeLapse.tareas.desvincularTareaTarea.jsonStorePrimero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la primera parrilla de Tareas");
	
}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.tareas.desvincularTareaTarea.efectuarDesvinculacion = function(){
	
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var rowsDeseleccionar = grid.selection.getSelected();
	
	//Inicializo los parámetros
	var parametros = {listOidEntidades:new Array()};
	
	
	if (rowsDeseleccionar.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.desvincularTareaTarea.textos.debesSeleccionarAlMenosUnaTarea);

	}else{
		
		dojo.forEach(rowsDeseleccionar,
		    function(row) {
				
				parametros.listOidEntidades.push(row.oid);
		    }
		);
			
	}
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.tareas.desvincularTareaTarea.recargaGridPrimero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/desvinculaTareaTarea.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.tareas.desvincularTareaTarea.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tareas.desvincularTareaTarea.muestraNumerosPrimero();
}


/**
 * Unión del botón de filtro
 */
timeLapse.tareas.desvincularTareaTarea.persisteCambiosFiltroPrimero = function(){
	timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.tareas.desvincularTareaTarea.filtraTareasPrimero = function(){
    
	var filtroTareas = timeLapse.tareas.desvincularTareaTarea.filtroTareasPrimero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tareas.desvincularTareaTarea.persisteCambiosFiltroPrimero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.tareas.desvincularTareaTarea.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}