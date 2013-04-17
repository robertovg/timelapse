/**
 * @author robe
 */
dojo.provide("timeLapse.tiempo.desvincularTareaTiempo");

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

dojo.requireLocalization("timeLapse","desvincularTareaTiempo");
//Inicializo los textos
timeLapse.tiempo.desvincularTareaTiempo.textos = dojo.i18n.getLocalization("timeLapse","desvincularTareaTiempo");
//Filtro de las tareas
timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero = null;


timeLapse.tiempo.desvincularTareaTiempo.init = function() {
	timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero = new timeLapse.tareas.filtroTareas(timeLapse.tiempo.desvincularTareaTiempo.recargaGridPrimero,timeLapse.utils.constantes.filtroTareas_TIPO_TEMPORIZADAS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero == undefined){
		var url = "../tareas/listaTareasAsociadasTiempo.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero.almacen);
		
		
		timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}	
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tiempo/desvincularTareaTiempo.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.tiempo.desvincularTareaTiempo.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonDesvincula"), "onclick",  timeLapse.tiempo.desvincularTareaTiempo.efectuarDesvinculacion);	
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.tiempo.desvincularTareaTiempo.filtraGridPrimero);	
	dojo.connect(dojo.byId("anclaFiltroTareasPrimero"), "onclick",timeLapse.tiempo.desvincularTareaTiempo.filtraTareasPrimero);
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.tiempo.desvincularTareaTiempo.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la primera parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasAsociadasTiempo.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero.almacen);
	
	timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridPrimero");
	dojo.connect(grid, "onRowClick", timeLapse.tiempo.desvincularTareaTiempo.muestraNumerosPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tiempo.desvincularTareaTiempo.filtraGridPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero.close();
	timeLapse.tiempo.desvincularTareaTiempo.jsonStorePrimero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la primera parrilla de Tareas");
	
}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.tiempo.desvincularTareaTiempo.efectuarDesvinculacion = function(){
	
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var rowsDeseleccionar = grid.selection.getSelected();
	
	//Inicializo los parámetros
	var parametros = {listOidEntidades:new Array()};
	
	
	if (rowsDeseleccionar.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tiempo.desvincularTareaTiempo.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.tiempo.desvincularTareaTiempo.recargaGridPrimero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tiempo/desvinculaTareasTiempo.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.tiempo.desvincularTareaTiempo.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tiempo.desvincularTareaTiempo.muestraNumerosPrimero();
}


/**
 * Unión del botón de filtro
 */
timeLapse.tiempo.desvincularTareaTiempo.persisteCambiosFiltroPrimero = function(){
	timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.tiempo.desvincularTareaTiempo.filtraTareasPrimero = function(){
    
	var filtroTareas = timeLapse.tiempo.desvincularTareaTiempo.filtroTareasPrimero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tiempo.desvincularTareaTiempo.persisteCambiosFiltroPrimero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.tiempo.desvincularTareaTiempo.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}