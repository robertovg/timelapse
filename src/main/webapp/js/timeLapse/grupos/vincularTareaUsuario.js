/**
 * @author robe		
 */
dojo.provide("timeLapse.grupos.vincularTareaUsuario");

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

dojo.requireLocalization("timeLapse","vincularTareaUsuario");
//Inicializo los textos
timeLapse.grupos.vincularTareaUsuario.textos = dojo.i18n.getLocalization("timeLapse","vincularTareaUsuario");

//Filtro de las tareas
timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero = null;


timeLapse.grupos.vincularTareaUsuario.init = function() {
	
	timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero = new timeLapse.tareas.filtroTareas(timeLapse.grupos.vincularTareaUsuario.recargaGridTercero,timeLapse.utils.constantes.filtroTareas_PARA_VINCULAR_USUARIO);

	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.vincularTareaUsuario.jsonStorePrimero == undefined){
		timeLapse.grupos.vincularTareaUsuario.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.grupos.vincularTareaUsuario.jsonStoreSegundo == undefined){
		var url = "../usuarios/listaUsuariosAsociadosGrupoIncluyendoCreador.action";
				
		timeLapse.grupos.vincularTareaUsuario.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url ,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
	/*
	 * Contiene los datos del tercer grid
	 */
	if(timeLapse.grupos.vincularTareaUsuario.jsonStoreTercero == undefined){
		var url = "../tareas/listaTareasDelGrupoSinAsociarUsuario.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero.almacen);
		
		timeLapse.grupos.vincularTareaUsuario.jsonStoreTercero = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/vincularTareaUsuario.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.vincularTareaUsuario.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSegundo"), "onclick",  timeLapse.grupos.vincularTareaUsuario.seleccionSegundo);
	dojo.connect(dojo.byId("botonSeleccionTercero"), "onclick",  timeLapse.grupos.vincularTareaUsuario.seleccionTercero);
	dojo.connect(dojo.byId("botonVolverPrimero"), "onclick",  timeLapse.grupos.vincularTareaUsuario.volverSeleccionPrimero);
	dojo.connect(dojo.byId("botonVolverSegundo"), "onclick",  timeLapse.grupos.vincularTareaUsuario.volverSeleccionSegundo);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.grupos.vincularTareaUsuario.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.vincularTareaUsuario.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.grupos.vincularTareaUsuario.filtraGridSegundo);
	dojo.connect(dojo.byId("filtroGridTercero"), "onkeyup", timeLapse.grupos.vincularTareaUsuario.filtraGridTercero);
	
	dojo.connect(dojo.byId("anclaFiltroTareasTercero"), "onclick",timeLapse.grupos.vincularTareaUsuario.filtraTareasTercero);


	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.vincularTareaUsuario.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.vincularTareaUsuario.filtraGridPrimero("*");
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
			timeLapse.grupos.vincularTareaUsuario.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularTareaUsuario.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposPertenecientes.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.grupos.vincularTareaUsuario.recargaGridSegundo = function(){
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

	timeLapse.grupos.vincularTareaUsuario.jsonStoreSegundo._jsonFileUrl  = url + "?" + cadParametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularTareaUsuario.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.vincularTareaUsuario.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.vincularTareaUsuario.jsonStoreSegundo.close();
	timeLapse.grupos.vincularTareaUsuario.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}

/**
 * Función que realiza la recarga del tercer grid
 */
timeLapse.grupos.vincularTareaUsuario.recargaGridTercero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la tercera parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var cadUrl = "../tareas/listaTareasDelGrupoSinAsociarUsuario.action";
	
	/*
	 * Al objeto de parámetros le añado el grupo seleccionado (oidUsuario) del grid segundo 
	 * y cambio el valor del grupo con el valor seleccionado en el grid primero 
	 */	
	var gridPrimero= dijit.byId("gridPrimero");
	var rowPrimero = gridPrimero.selection.getSelected();	
	
	var objParametros = timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero.almacen;
	objParametros.grupos = rowPrimero[0].oid;
	/*
	var gridSegundo = dijit.byId("gridSegundo");
	var rowSegundo = gridSegundo.selection.getSelected();	
	
	objParametros.oidUsuario = rowSegundo[0].oid;
	*/
	var cadParametros = dojo.objectToQuery(objParametros);	
	
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery();
	
	timeLapse.grupos.vincularTareaUsuario.jsonStoreTercero._jsonFileUrl  = cadUrl + "?" + cadParametros;
	var grid = dijit.byId("gridTercero");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularTareaUsuario.muestraNumerosTercero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.vincularTareaUsuario.filtraGridTercero);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.vincularTareaUsuario.jsonStoreTercero.close();
	timeLapse.grupos.vincularTareaUsuario.jsonStoreTercero.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la tercera parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.grupos.vincularTareaUsuario.seleccionSegundo = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.vincularTareaUsuario.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Grupo");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularTareaUsuario.textos.debesSeleccionarSoloUnaGrupo);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
}

timeLapse.grupos.vincularTareaUsuario.seleccionTercero = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridSegundo");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.vincularTareaUsuario.recargaGridTercero();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Usuario");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularTareaUsuario.textos.debesSeleccionarSoloUnUsuario);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorSegundo","contenedorTercero",vaBien);
	}
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.grupos.vincularTareaUsuario.volverSeleccionPrimero = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función a la que se llama para volver a la pantalla del segundo grid
 */
timeLapse.grupos.vincularTareaUsuario.volverSeleccionSegundo = function(){
	timeLapse.utils.efectos.intercambia("contenedorTercero", "contenedorSegundo");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.vincularTareaUsuario.efectuarVinculacion = function(){
	
	var grid = dijit.byId("gridTercero");
	var rowsSegundo = grid.selection.getSelected();	
	
	var correcto = true;
	var grid = dijit.byId("gridSegundo");
	var rowPrimero = grid.selection.getSelected();
	
	//Inicializo los parámetros que voy a enviarle a la vinculación de Rol/Acción
	var parametros = {listOidEntidades:new Array(), oidEntidad:rowPrimero[0].oid};
	
	
	if (rowsSegundo.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularTareaUsuario.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.grupos.vincularTareaUsuario.recargaGridTercero();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/vinculaUsuarioTareas.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.vincularTareaUsuario.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularTareaUsuario.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.vincularTareaUsuario.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularTareaUsuario.muestraNumerosSegundo();
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.vincularTareaUsuario.filtraGridTercero = function(parametro){
	
	var idFiltro = "filtroGridTercero";
	var idGrid = "gridTercero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularTareaUsuario.muestraNumerosTercero();
}

/**
 * Unión del botón de filtro
 */
timeLapse.grupos.vincularTareaUsuario.persisteCambiosFiltroTercero = function(){
	timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.grupos.vincularTareaUsuario.filtraTareasTercero = function(){
    
	var filtroTareas = timeLapse.grupos.vincularTareaUsuario.filtroTareasTercero;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.grupos.vincularTareaUsuario.persisteCambiosFiltroTercero);
	
};

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularTareaUsuario.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularTareaUsuario.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularTareaUsuario.muestraNumerosTercero = function(){
	var grid = dijit.byId("gridTercero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroTercero").attr("content","" + total);
	
}
