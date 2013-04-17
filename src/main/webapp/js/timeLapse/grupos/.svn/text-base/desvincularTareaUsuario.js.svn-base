/**
 * @author robe		
 */
dojo.provide("timeLapse.grupos.desvincularTareaUsuario");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");

dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.utils.constantes");
dojo.require("timeLapse.tareas.filtroTareasDTO");

dojo.requireLocalization("timeLapse","desvincularTareaUsuario");
//Inicializo los textos
timeLapse.grupos.desvincularTareaUsuario.textos = dojo.i18n.getLocalization("timeLapse","desvincularTareaUsuario");

//Filtro de las tareas
timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero = null;


timeLapse.grupos.desvincularTareaUsuario.init = function() {
	
	timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero = new timeLapse.tareas.filtroTareas(timeLapse.grupos.desvincularTareaUsuario.recargaGridTercero,timeLapse.utils.constantes.filtroTareas_PARA_VINCULAR_USUARIO);

	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.desvincularTareaUsuario.jsonStorePrimero == undefined){
		timeLapse.grupos.desvincularTareaUsuario.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.grupos.desvincularTareaUsuario.jsonStoreSegundo == undefined){
		var url = "../usuarios/listaUsuariosAsociadosGrupoIncluyendoCreador.action";
				
		timeLapse.grupos.desvincularTareaUsuario.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url ,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
	/*
	 * Contiene los datos del tercer grid
	 */
	if(timeLapse.grupos.desvincularTareaUsuario.jsonStoreTercero == undefined){
		var url = "../tareas/listaTareasDelGrupoAsociadosUsuario.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero.almacen);
		
		timeLapse.grupos.desvincularTareaUsuario.jsonStoreTercero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/desvincularTareaUsuario.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.desvincularTareaUsuario.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSegundo"), "onclick",  timeLapse.grupos.desvincularTareaUsuario.seleccionSegundo);
	dojo.connect(dojo.byId("botonSeleccionTercero"), "onclick",  timeLapse.grupos.desvincularTareaUsuario.seleccionTercero);
	dojo.connect(dojo.byId("botonVolverPrimero"), "onclick",  timeLapse.grupos.desvincularTareaUsuario.volverSeleccionPrimero);
	dojo.connect(dojo.byId("botonVolverSegundo"), "onclick",  timeLapse.grupos.desvincularTareaUsuario.volverSeleccionSegundo);
	dojo.connect(dojo.byId("botonDesvincular"), "onclick",  timeLapse.grupos.desvincularTareaUsuario.efectuarDesvinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.desvincularTareaUsuario.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.grupos.desvincularTareaUsuario.filtraGridSegundo);
	dojo.connect(dojo.byId("filtroGridTercero"), "onkeyup", timeLapse.grupos.desvincularTareaUsuario.filtraGridTercero);
	
	dojo.connect(dojo.byId("anclaFiltroTareasTercero"), "onclick",timeLapse.grupos.desvincularTareaUsuario.filtraTareasTercero);


	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.desvincularTareaUsuario.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.desvincularTareaUsuario.filtraGridPrimero("*");
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
			timeLapse.grupos.desvincularTareaUsuario.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularTareaUsuario.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposPertenecientes.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.grupos.desvincularTareaUsuario.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../usuarios/listaUsuariosAsociadosGrupoIncluyendoCreador.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var grid = dijit.byId("gridPrimero");
	var rowPrimero = grid.selection.getSelected();	
	
	var parametros = {oidGrupo:rowPrimero[0].oid};
	var cadParametros = dojo.objectToQuery(parametros);

	timeLapse.grupos.desvincularTareaUsuario.jsonStoreSegundo._jsonFileUrl  = url + "?" + cadParametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularTareaUsuario.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.desvincularTareaUsuario.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.desvincularTareaUsuario.jsonStoreSegundo.close();
	timeLapse.grupos.desvincularTareaUsuario.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}

/**
 * Función que realiza la recarga del tercer grid
 */
timeLapse.grupos.desvincularTareaUsuario.recargaGridTercero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la tercera parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var cadUrl = "../tareas/listaTareasDelGrupoAsociadosUsuario.action";
	
	/*
	 * Al objeto de parámetros le añado el grupo seleccionado (oidUsuario) del grid segundo 
	 * y cambio el valor del grupo con el valor seleccionado en el grid primero 
	 */	
	var gridPrimero= dijit.byId("gridPrimero");
	var rowPrimero = gridPrimero.selection.getSelected();	
	
	var objParametros = timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero.almacen;
	objParametros.grupos = rowPrimero[0].oid;
	
	var gridSegundo = dijit.byId("gridSegundo");
	var rowSegundo = gridSegundo.selection.getSelected();	
	
	objParametros.oidUsuario = rowSegundo[0].oid;
	
	var cadParametros = dojo.objectToQuery(objParametros);	
	
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery();
	
	timeLapse.grupos.desvincularTareaUsuario.jsonStoreTercero._jsonFileUrl  = cadUrl + "?" + cadParametros;
	var grid = dijit.byId("gridTercero");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularTareaUsuario.muestraNumerosTercero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.desvincularTareaUsuario.filtraGridTercero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.desvincularTareaUsuario.jsonStoreTercero.close();
	timeLapse.grupos.desvincularTareaUsuario.jsonStoreTercero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la tercera parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.grupos.desvincularTareaUsuario.seleccionSegundo = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.desvincularTareaUsuario.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Grupo");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularTareaUsuario.textos.debesSeleccionarSoloUnaGrupo);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
}

timeLapse.grupos.desvincularTareaUsuario.seleccionTercero = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridSegundo");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.desvincularTareaUsuario.recargaGridTercero();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Usuario");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularTareaUsuario.textos.debesSeleccionarSoloUnUsuario);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorSegundo","contenedorTercero",vaBien);
	}
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.grupos.desvincularTareaUsuario.volverSeleccionPrimero = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función a la que se llama para volver a la pantalla del segundo grid
 */
timeLapse.grupos.desvincularTareaUsuario.volverSeleccionSegundo = function(){
	timeLapse.utils.efectos.intercambia("contenedorTercero", "contenedorSegundo");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.desvincularTareaUsuario.efectuarDesvinculacion = function(){
	
	var grid = dijit.byId("gridTercero");
	var rowsSegundo = grid.selection.getSelected();	
	
	var correcto = true;
		
	//Inicializo los parámetros que voy a enviarle a la vinculación de Rol/Acción
	var parametros = {listOidEntidades:new Array()};
	
	
	if (rowsSegundo.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularTareaUsuario.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.grupos.desvincularTareaUsuario.recargaGridTercero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/desvinculaUsuarioTareas.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.desvincularTareaUsuario.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularTareaUsuario.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.desvincularTareaUsuario.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularTareaUsuario.muestraNumerosSegundo();
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.desvincularTareaUsuario.filtraGridTercero = function(parametro){
	
	var idFiltro = "filtroGridTercero";
	var idGrid = "gridTercero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularTareaUsuario.muestraNumerosTercero();
}

/**
 * Unión del botón de filtro
 */
timeLapse.grupos.desvincularTareaUsuario.persisteCambiosFiltroTercero = function(){
	timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.grupos.desvincularTareaUsuario.filtraTareasTercero = function(){
    
	var filtroTareas = timeLapse.grupos.desvincularTareaUsuario.filtroTareasTercero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.grupos.desvincularTareaUsuario.persisteCambiosFiltroTercero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularTareaUsuario.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularTareaUsuario.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularTareaUsuario.muestraNumerosTercero = function(){
	var grid = dijit.byId("gridTercero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroTercero").attr("content","" + total);
	
}
