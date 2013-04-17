/**
 * @author robe
 */	
dojo.provide("timeLapse.grupos.desvincularTareaGrupo");

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

dojo.requireLocalization("timeLapse","desvincularTareaGrupo");
//Inicializo los textos
timeLapse.grupos.desvincularTareaGrupo.textos = dojo.i18n.getLocalization("timeLapse","desvincularTareaGrupo");
//Filtro de las tareas
timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero = null;


timeLapse.grupos.desvincularTareaGrupo.init = function() {
	timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero = new timeLapse.tareas.filtroTareas(timeLapse.grupos.desvincularTareaGrupo.recargaGridPrimero,timeLapse.utils.constantes.filtroTareas_CON_GRUPOS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.desvincularTareaGrupo.jsonStorePrimero == undefined){
		var url = "../tareas/listaTareasAsociadasGrupos.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero.almacen);
		
		
		timeLapse.grupos.desvincularTareaGrupo.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}	
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/desvincularTareaGrupo.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.desvincularTareaGrupo.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonDesvincula"), "onclick",  timeLapse.grupos.desvincularTareaGrupo.efectuarDesvinculacion);	
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.desvincularTareaGrupo.filtraGridPrimero);	
	dojo.connect(dojo.byId("anclaFiltroTareasPrimero"), "onclick",timeLapse.grupos.desvincularTareaGrupo.filtraTareasPrimero);
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.desvincularTareaGrupo.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la primera parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasAsociadasGrupos.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero.almacen);
	
	timeLapse.grupos.desvincularTareaGrupo.jsonStorePrimero._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridPrimero");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularTareaGrupo.muestraNumerosPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.desvincularTareaGrupo.filtraGridPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.desvincularTareaGrupo.jsonStorePrimero.close();
	timeLapse.grupos.desvincularTareaGrupo.jsonStorePrimero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la primera parrilla de Tareas");
	
}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.desvincularTareaGrupo.efectuarDesvinculacion = function(){
	
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var rowsDeseleccionar = grid.selection.getSelected();
	
	//Inicializo los parámetros
	var parametros = {listOidEntidades:new Array()};
	
	
	if (rowsDeseleccionar.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularTareaGrupo.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.grupos.desvincularTareaGrupo.recargaGridPrimero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("grupos/desvinculaTareasGrupos.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.desvincularTareaGrupo.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularTareaGrupo.muestraNumerosPrimero();
}


/**
 * Unión del botón de filtro
 */
timeLapse.grupos.desvincularTareaGrupo.persisteCambiosFiltroPrimero = function(){
	timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.grupos.desvincularTareaGrupo.filtraTareasPrimero = function(){
    
	var filtroTareas = timeLapse.grupos.desvincularTareaGrupo.filtroTareasPrimero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.grupos.desvincularTareaGrupo.persisteCambiosFiltroPrimero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularTareaGrupo.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}