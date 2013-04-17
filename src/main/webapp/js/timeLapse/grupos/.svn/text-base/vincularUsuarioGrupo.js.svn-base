/**
 * @author robe		
 */
dojo.provide("timeLapse.grupos.vincularUsuarioGrupo");

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

dojo.requireLocalization("timeLapse","vincularUsuarioGrupo");
//Inicializo los textos
timeLapse.grupos.vincularUsuarioGrupo.textos = dojo.i18n.getLocalization("timeLapse","vincularUsuarioGrupo");

//Filtro de las tareas
timeLapse.grupos.vincularUsuarioGrupo.filtroTareasSegundo = null;


timeLapse.grupos.vincularUsuarioGrupo.init = function() {
	
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.vincularUsuarioGrupo.jsonStorePrimero == undefined){
		timeLapse.grupos.vincularUsuarioGrupo.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.grupos.vincularUsuarioGrupo.jsonStoreSegundo == undefined){
		var url = "../usuarios/listaUsuariosNoAsociadosAUnGrupo.action";
				
		timeLapse.grupos.vincularUsuarioGrupo.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url ,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/vincularUsuarioGrupo.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.vincularUsuarioGrupo.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.grupos.vincularUsuarioGrupo.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.grupos.vincularUsuarioGrupo.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.grupos.vincularUsuarioGrupo.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.vincularUsuarioGrupo.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.grupos.vincularUsuarioGrupo.filtraGridSegundo);
	
	

	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.vincularUsuarioGrupo.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.vincularUsuarioGrupo.filtraGridPrimero("*");
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
			timeLapse.grupos.vincularUsuarioGrupo.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposCreados.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.grupos.vincularUsuarioGrupo.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../usuarios/listaUsuariosNoAsociadosAUnGrupo.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var grid = dijit.byId("gridPrimero");
	var rowPrimero = grid.selection.getSelected();	
	
	var parametros = {oidGrupo:rowPrimero[0].oid};
	var cadParametros = dojo.objectToQuery(parametros);

	timeLapse.grupos.vincularUsuarioGrupo.jsonStoreSegundo._jsonFileUrl  = url + "?" + cadParametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.vincularUsuarioGrupo.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.vincularUsuarioGrupo.jsonStoreSegundo.close();
	timeLapse.grupos.vincularUsuarioGrupo.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.grupos.vincularUsuarioGrupo.seleccionSiguiente = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.vincularUsuarioGrupo.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularUsuarioGrupo.textos.debesSeleccionarSoloUnaGrupo);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.grupos.vincularUsuarioGrupo.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.vincularUsuarioGrupo.efectuarVinculacion = function(){
	
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
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.vincularUsuarioGrupo.textos.debesSeleccionarAlMenosUnUsuario);

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
				
				timeLapse.grupos.vincularUsuarioGrupo.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("grupos/vinculaUsuariosGrupo.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.vincularUsuarioGrupo.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.vincularUsuarioGrupo.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosSegundo();
}


/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.vincularUsuarioGrupo.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}