/**
 * @author robe		
 */
dojo.provide("timeLapse.grupos.vincularTareaGrupo");

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

dojo.requireLocalization("timeLapse","vincularTareaGrupo");
//Inicializo los textos
timeLapse.grupos.vincularTareaGrupo.textos = dojo.i18n.getLocalization("timeLapse","vincularTareaGrupo");

//Filtro de las tareas
timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo = null;


timeLapse.grupos.vincularTareaGrupo.init = function() {
	
	
	timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo = new timeLapse.tareas.filtroTareas(timeLapse.grupos.vincularTareaGrupo.recargaGridSegundo,timeLapse.utils.constantes.filtroTareas_TIPO_SIN_GRUPOS);
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.vincularTareaGrupo.jsonStorePrimero == undefined){
		timeLapse.grupos.vincularTareaGrupo.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.grupos.vincularTareaGrupo.jsonStoreSegundo == undefined){
		var url = "../tareas/listaTareasNoAsociadasGrupos.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo.almacen);
		
		timeLapse.grupos.vincularTareaGrupo.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/vincularTareaGrupo.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.vincularTareaGrupo.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.grupos.vincularTareaGrupo.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.grupos.vincularTareaGrupo.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.grupos.vincularTareaGrupo.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.vincularTareaGrupo.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.grupos.vincularTareaGrupo.filtraGridSegundo);
	
	dojo.connect(dojo.byId("anclaFiltroTareasSegundo"), "onclick",timeLapse.grupos.vincularTareaGrupo.filtraTareasSegundo);

	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.vincularTareaGrupo.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.vincularTareaGrupo.filtraGridPrimero("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosGruposBD.listGrupos.length; i++){
				var obj = new Object();
				obj.oid = datosGruposBD.listGrupos[i].oid;
				obj.nombre = datosGruposBD.listGrupos[i].nombre;				
				obj.descripcion = datosGruposBD.listGrupos[i].descripcion;				 
				grid.store.newItem(obj);
		   		
			}
			grid.store.save();
			timeLapse.grupos.vincularTareaGrupo.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularTareaGrupo.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposPertenecientes.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.grupos.vincularTareaGrupo.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasNoAsociadasGrupos.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo.almacen);
	
	timeLapse.grupos.vincularTareaGrupo.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularTareaGrupo.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.vincularTareaGrupo.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.vincularTareaGrupo.jsonStoreSegundo.close();
	timeLapse.grupos.vincularTareaGrupo.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.grupos.vincularTareaGrupo.seleccionSiguiente = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.vincularTareaGrupo.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularTareaGrupo.textos.debesSeleccionarSoloUnaGrupo);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.grupos.vincularTareaGrupo.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.vincularTareaGrupo.efectuarVinculacion = function(){
	
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
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularTareaGrupo.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.grupos.vincularTareaGrupo.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("grupos/vinculaTareasGrupos.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.vincularTareaGrupo.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularTareaGrupo.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.vincularTareaGrupo.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularTareaGrupo.muestraNumerosSegundo();
}


/**
 * Unión del botón de filtro
 */
timeLapse.grupos.vincularTareaGrupo.persisteCambiosFiltroSegundo = function(){
	timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.grupos.vincularTareaGrupo.filtraTareasSegundo = function(){
    
	var filtroTareas = timeLapse.grupos.vincularTareaGrupo.filtroTareasSegundo;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.grupos.vincularTareaGrupo.persisteCambiosFiltroSegundo);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularTareaGrupo.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularTareaGrupo.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}