/**
 * @author robe
 */	
dojo.provide("timeLapse.categorias.desvincularTareaCategoria");

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

dojo.requireLocalization("timeLapse","desvincularTareaCategoria");
//Inicializo los textos
timeLapse.categorias.desvincularTareaCategoria.textos = dojo.i18n.getLocalization("timeLapse","desvincularTareaCategoria");
//Filtro de las tareas
timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero = null;


timeLapse.categorias.desvincularTareaCategoria.init = function() {
	timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero = new timeLapse.tareas.filtroTareas(timeLapse.categorias.desvincularTareaCategoria.recargaGridPrimero,timeLapse.utils.constantes.filtroTareas_TIPO_CATEGORIZADAS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.categorias.desvincularTareaCategoria.jsonStorePrimero == undefined){
		var url = "../tareas/listaTareasAsociadasCategorias.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero.almacen);
		
		
		timeLapse.categorias.desvincularTareaCategoria.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}	
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/categorias/desvincularTareaCategoria.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.categorias.desvincularTareaCategoria.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonDesvincula"), "onclick",  timeLapse.categorias.desvincularTareaCategoria.efectuarDesvinculacion);	
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.categorias.desvincularTareaCategoria.filtraGridPrimero);	
	dojo.connect(dojo.byId("anclaFiltroTareasPrimero"), "onclick",timeLapse.categorias.desvincularTareaCategoria.filtraTareasPrimero);
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.categorias.desvincularTareaCategoria.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la primera parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasAsociadasCategorias.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero.almacen);
	
	timeLapse.categorias.desvincularTareaCategoria.jsonStorePrimero._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridPrimero");
	dojo.connect(grid, "onRowClick", timeLapse.categorias.desvincularTareaCategoria.muestraNumerosPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.categorias.desvincularTareaCategoria.filtraGridPrimero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.categorias.desvincularTareaCategoria.jsonStorePrimero.close();
	timeLapse.categorias.desvincularTareaCategoria.jsonStorePrimero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la primera parrilla de Tareas");
	
}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.categorias.desvincularTareaCategoria.efectuarDesvinculacion = function(){
	
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var rowsDeseleccionar = grid.selection.getSelected();
	
	//Inicializo los parámetros
	var parametros = {listOidEntidades:new Array()};
	
	
	if (rowsDeseleccionar.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.desvincularTareaCategoria.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.categorias.desvincularTareaCategoria.recargaGridPrimero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("categorias/desvinculaTareasCategorias.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.categorias.desvincularTareaCategoria.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.categorias.desvincularTareaCategoria.muestraNumerosPrimero();
}


/**
 * Unión del botón de filtro
 */
timeLapse.categorias.desvincularTareaCategoria.persisteCambiosFiltroPrimero = function(){
	timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.categorias.desvincularTareaCategoria.filtraTareasPrimero = function(){
    
	var filtroTareas = timeLapse.categorias.desvincularTareaCategoria.filtroTareasPrimero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.categorias.desvincularTareaCategoria.persisteCambiosFiltroPrimero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.categorias.desvincularTareaCategoria.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}