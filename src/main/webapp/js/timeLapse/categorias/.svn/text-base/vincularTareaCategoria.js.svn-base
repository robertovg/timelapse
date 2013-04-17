/**
 * @author robe		
 */
dojo.provide("timeLapse.categorias.vincularTareaCategoria");

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

dojo.requireLocalization("timeLapse","vincularTareaCategoria");
//Inicializo los textos
timeLapse.categorias.vincularTareaCategoria.textos = dojo.i18n.getLocalization("timeLapse","vincularTareaCategoria");

//Filtro de las tareas
timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo = null;


timeLapse.categorias.vincularTareaCategoria.init = function() {
	
	
	timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo = new timeLapse.tareas.filtroTareas(timeLapse.categorias.vincularTareaCategoria.recargaGridSegundo,timeLapse.utils.constantes.filtroTareas_TIPO_NO_CATEGORIZADAS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.categorias.vincularTareaCategoria.jsonStorePrimero == undefined){
		timeLapse.categorias.vincularTareaCategoria.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.categorias.vincularTareaCategoria.jsonStoreSegundo == undefined){
		var url = "../tareas/listaTareasNoAsociadasCategorias.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo.almacen);
		
		timeLapse.categorias.vincularTareaCategoria.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/categorias/vincularTareaCategoria.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.categorias.vincularTareaCategoria.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.categorias.vincularTareaCategoria.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.categorias.vincularTareaCategoria.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.categorias.vincularTareaCategoria.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.categorias.vincularTareaCategoria.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.categorias.vincularTareaCategoria.filtraGridSegundo);
	
	dojo.connect(dojo.byId("anclaFiltroTareasSegundo"), "onclick",timeLapse.categorias.vincularTareaCategoria.filtraTareasSegundo);

	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.categorias.vincularTareaCategoria.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Categorías")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosCategoriasBD){
			//Hago una limpieza de sus datos
			timeLapse.categorias.vincularTareaCategoria.filtraGridPrimero("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosCategoriasBD.listCategorias.length; i++){
				var obj = new Object();
				obj.oid = datosCategoriasBD.listCategorias[i].oid;
				obj.nombre = datosCategoriasBD.listCategorias[i].nombre;				
				obj.descripcion = datosCategoriasBD.listCategorias[i].descripcion;				 
				grid.store.newItem(obj);
		   		
			}
			grid.store.save();
			timeLapse.categorias.vincularTareaCategoria.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.categorias.vincularTareaCategoria.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Categorías")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("categorias/listaCategorias.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.categorias.vincularTareaCategoria.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasNoAsociadasCategorias.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo.almacen);
	
	timeLapse.categorias.vincularTareaCategoria.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.categorias.vincularTareaCategoria.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.categorias.vincularTareaCategoria.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.categorias.vincularTareaCategoria.jsonStoreSegundo.close();
	timeLapse.categorias.vincularTareaCategoria.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.categorias.vincularTareaCategoria.seleccionSiguiente = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.categorias.vincularTareaCategoria.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.vincularTareaCategoria.textos.debesSeleccionarSoloUnaCategoria);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.categorias.vincularTareaCategoria.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.categorias.vincularTareaCategoria.efectuarVinculacion = function(){
	
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
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.vincularTareaCategoria.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.categorias.vincularTareaCategoria.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("categorias/vinculaTareasCategoria.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.categorias.vincularTareaCategoria.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.categorias.vincularTareaCategoria.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.categorias.vincularTareaCategoria.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.categorias.vincularTareaCategoria.muestraNumerosSegundo();
}


/**
 * Unión del botón de filtro
 */
timeLapse.categorias.vincularTareaCategoria.persisteCambiosFiltroSegundo = function(){
	timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.categorias.vincularTareaCategoria.filtraTareasSegundo = function(){
    
	var filtroTareas = timeLapse.categorias.vincularTareaCategoria.filtroTareasSegundo;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.categorias.vincularTareaCategoria.persisteCambiosFiltroSegundo);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.categorias.vincularTareaCategoria.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.categorias.vincularTareaCategoria.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}