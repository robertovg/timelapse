/**
 * @author robe		
 */
dojo.provide("timeLapse.grupos.desvincularUsuarioGrupo");

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

dojo.requireLocalization("timeLapse","desvincularUsuarioGrupo");
//Inicializo los textos
timeLapse.grupos.desvincularUsuarioGrupo.textos = dojo.i18n.getLocalization("timeLapse","desvincularUsuarioGrupo");

//Filtro de las tareas
timeLapse.grupos.desvincularUsuarioGrupo.filtroTareasSegundo = null;


timeLapse.grupos.desvincularUsuarioGrupo.init = function() {
	
	
	var contenedor = dijit.byId("principal");
	/*
	 * Contiene los datos del primer grid 
	 */
	if(timeLapse.grupos.desvincularUsuarioGrupo.jsonStorePrimero == undefined){
		timeLapse.grupos.desvincularUsuarioGrupo.jsonStorePrimero = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo == undefined){
		var url = "../usuarios/listaUsuariosAsociadosGrupo.action";
				
		timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url ,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/desvincularUsuarioGrupo.jsp"); 
	contenedor.attr('content',contenido);
		
	//Fabrico el primer grid
    timeLapse.grupos.desvincularUsuarioGrupo.recargaGridPrimero();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.grupos.desvincularUsuarioGrupo.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.grupos.desvincularUsuarioGrupo.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.grupos.desvincularUsuarioGrupo.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridPrimero"), "onkeyup", timeLapse.grupos.desvincularUsuarioGrupo.filtraGridPrimero);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.grupos.desvincularUsuarioGrupo.filtraGridSegundo);
	
	

	
		
}
/**
 * Función que realiza la recarga del primer grid
 */
timeLapse.grupos.desvincularUsuarioGrupo.recargaGridPrimero = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("gridPrimero");
	
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.desvincularUsuarioGrupo.filtraGridPrimero("*");
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
			timeLapse.grupos.desvincularUsuarioGrupo.filtraGridPrimero();
			dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosPrimero);			
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposPertenecientes.action",null, vaBien);
	
}
/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.grupos.desvincularUsuarioGrupo.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de Tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../usuarios/listaUsuariosAsociadosGrupo.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var grid = dijit.byId("gridPrimero");
	var rowPrimero = grid.selection.getSelected();	
	
	var parametros = {oidGrupo:rowPrimero[0].oid};
	var cadParametros = dojo.objectToQuery(parametros);

	timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo._jsonFileUrl  = url + "?" + cadParametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.grupos.desvincularUsuarioGrupo.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo.close();
	timeLapse.grupos.desvincularUsuarioGrupo.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de Tareas");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.grupos.desvincularUsuarioGrupo.seleccionSiguiente = function(){
		
	var correcto = true;
	var grid = dijit.byId("gridPrimero");
	var row = grid.selection.getSelected();
	var vaBien = {
		accion: function(){
			timeLapse.grupos.desvincularUsuarioGrupo.recargaGridSegundo();
		}
	};
	
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularUsuarioGrupo.textos.debesSeleccionarSoloUnaGrupo);	
	}
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer grid
 */
timeLapse.grupos.desvincularUsuarioGrupo.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.grupos.desvincularUsuarioGrupo.efectuarVinculacion = function(){
	
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
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.desvincularUsuarioGrupo.textos.debesSeleccionarAlMenosUnUsuario);

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
				
				timeLapse.grupos.desvincularUsuarioGrupo.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("grupos/desvinculaUsuariosGrupos.action",parametros, vaBien);
		
	}
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 */
timeLapse.grupos.desvincularUsuarioGrupo.filtraGridPrimero = function(parametro){
	
	var idFiltro = "filtroGridPrimero";
	var idGrid = "gridPrimero";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosPrimero();
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.desvincularUsuarioGrupo.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosSegundo();
}


/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosPrimero = function(){
	var grid = dijit.byId("gridPrimero");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroPrimero").attr("content","" + total);
	
}

/**
 * Muestra el total de tareas
 */
timeLapse.grupos.desvincularUsuarioGrupo.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}