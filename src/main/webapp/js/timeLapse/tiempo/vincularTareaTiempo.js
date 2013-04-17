/**
 * @author robe
 */
dojo.provide("timeLapse.tiempo.vincularTareaTiempo");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.tareas.filtroTareasDTO");

dojo.requireLocalization("timeLapse","vincularTareaTiempo");
//Inicializo los textos
timeLapse.tiempo.vincularTareaTiempo.textos = dojo.i18n.getLocalization("timeLapse","vincularTareaTiempo");
//Filtro de las tiempo
timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo = null;


timeLapse.tiempo.vincularTareaTiempo.init = function() {
	
	timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo = new timeLapse.tareas.filtroTareas(timeLapse.tiempo.vincularTareaTiempo.recargaGridSegundo,timeLapse.utils.constantes.filtroTareas_TIPO_NO_TEMPORIZADAS);
	
	var contenedor = dijit.byId("principal");
	
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo == undefined){
		var url = "../tareas/listaTareasNoAsociadasTiempo.action";
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo.almacen);
		
		timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{url: url + "?" + parametros,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tiempo/vincularTareaTiempo.jsp"); 
	contenedor.attr('content',contenido);

    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.tiempo.vincularTareaTiempo.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.tiempo.vincularTareaTiempo.volverSeleccionPermiso);
	dojo.connect(dojo.byId("botonVincular"), "onclick",  timeLapse.tiempo.vincularTareaTiempo.efectuarVinculacion);
	dojo.connect(dojo.byId("filtroGridSegundo"), "onkeyup", timeLapse.tiempo.vincularTareaTiempo.filtraGridSegundo);	
	dojo.connect(dojo.byId("anclaFiltroTareasSegundo"), "onclick",timeLapse.tiempo.vincularTareaTiempo.filtraTareasSegundo);
	
		
}

/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.tiempo.vincularTareaTiempo.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de tiempo");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareasNoAsociadasTiempo.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo.almacen);
	
	timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.tiempo.vincularTareaTiempo.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tiempo.vincularTareaTiempo.filtraGridSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo.close();
	timeLapse.tiempo.vincularTareaTiempo.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de tiempo");	
	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.tiempo.vincularTareaTiempo.seleccionSiguiente = function(){
		
	var correcto = true;
	var vaBien = {
		accion: function(){
			timeLapse.tiempo.vincularTareaTiempo.recargaGridSegundo();
		}
	};
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
			var obj = dijit.byId('myForm').attr('value');
			var valorFechaInicio = obj.formFechaInicio ? dojo.date.locale.format(obj.formFechaInicio, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;
			var valorHoraInicio= obj.formHoraInicio ? dojo.date.locale.format(obj.formHoraInicio, {fullYear:true, selector:'time', datePattern: "HH:mm"}) : null;
			var valorFechaFin  = obj.formFechaFin ? dojo.date.locale.format(obj.formFechaFin, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;
			var valorHoraFin = obj.formHoraFin ? dojo.date.locale.format(obj.formHoraFin, {fullYear:true, selector:'time', datePattern: "HH:mm"}): null;
			
			if(correcto && timeLapse.utils.constantes.validacionesCliente && valorFechaInicio == null && valorFechaFin == null){
				correcto = false;
				console.warn(timeLapse.tiempo.vincularTareaTiempo.textos.ningunaFechaSeleccionada);
				timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.ningunaFechaSeleccionada);
				
			}
			if(correcto && timeLapse.utils.constantes.validacionesCliente && valorFechaInicio == null && valorHoraInicio != null){
				correcto = false;
				console.warn(timeLapse.tiempo.vincularTareaTiempo.textos.horaInicioSinFechaInicio);
				timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.horaInicioSinFechaInicio);
				
			}
			if (correcto && timeLapse.utils.constantes.validacionesCliente && valorFechaFin == null && valorHoraFin != null) {
				correcto = false;
				console.warn(timeLapse.tiempo.vincularTareaTiempo.textos.horaFinSinFechaFin);
				timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.horaFinSinFechaFin);
			}
			//Ahora compruebo que las fechas de inicio y fecha de fin sean coherentes
			var dateFechaInicio = timeLapse.utils.formato.stringToDate(valorFechaInicio);
			var dateHoraInicio = timeLapse.utils.formato.stringToDate(valorHoraInicio);
			var dateFechaFin = timeLapse.utils.formato.stringToDate(valorFechaFin);
			var dateHoraFin = timeLapse.utils.formato.stringToDate(valorHoraFin);
			
			if(correcto && timeLapse.utils.constantes.validacionesCliente && dateFechaInicio != null && dateFechaFin !=null){
				if(dateFechaInicio > dateFechaFin){
					correcto = false;
					console.warn(timeLapse.tiempo.vincularTareaTiempo.textos.horaFinSinFechaFin);
					timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.fechaFinNoPuedeSerMenorFechaInicio);
				}else if(valorFechaInicio == valorFechaFin){
					if(dateHoraInicio != null && dateHoraFin != null
						&& dateHoraInicio > dateHoraFin){
							correcto = false;
							console.warn(timeLapse.tiempo.vincularTareaTiempo.textos.horaFinSinFechaFin);
							timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.fechaFinNoPuedeSerMenorFechaInicio);
						
					}
					
				}
			}
			
		console.log("Se guardan las fechas ");
		
	}else{
		console.warn("Las tareas tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.formularioFechasConErrores);
		
	}
	
	
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
	
}
/**
 * Función a la que se llama para volver a la pantalla del primer contenedor
 */
timeLapse.tiempo.vincularTareaTiempo.volverSeleccionPermiso = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza la vinculación entre las entidades marcadas
 */
timeLapse.tiempo.vincularTareaTiempo.efectuarVinculacion = function(){

	var correcto = true;
	var grid = dijit.byId("gridSegundo");
	var rowsSegundo = grid.selection.getSelected();
	
	var obj = dijit.byId('myForm').attr('value');
	var valorFechaInicio = obj.formFechaInicio ? dojo.date.locale.format(obj.formFechaInicio, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;
	var valorHoraInicio= obj.formHoraInicio ? dojo.date.locale.format(obj.formHoraInicio, {fullYear:true, selector:'time', datePattern: "HH:mm"}) : null;
	var valorFechaFin  = obj.formFechaFin ? dojo.date.locale.format(obj.formFechaFin, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;
	var valorHoraFin = obj.formHoraFin ? dojo.date.locale.format(obj.formHoraFin, {fullYear:true, selector:'time', datePattern: "HH:mm"}): null;
	
	//Inicializo los parámetros que voy a enviarle a la vinculación de Rol/Acción
	var parametros = {listOidEntidades:new Array()
		, formFechaInicio : valorFechaInicio
		, formFechaFin : valorFechaFin
		, formHoraInicio: valorHoraInicio
		, formHoraFin: valorHoraFin};
	
	
	if (rowsSegundo.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tiempo.vincularTareaTiempo.textos.debesSeleccionarAlMenosUnaTarea);

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
				
				timeLapse.tiempo.vincularTareaTiempo.recargaGridSegundo();				
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tiempo/vinculaTareasTiempo.action",parametros, vaBien);
		
	}
	
}

/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.tiempo.vincularTareaTiempo.filtraGridSegundo = function(parametro){
	
	var idFiltro = "filtroGridSegundo";
	var idGrid = "gridSegundo";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tiempo.vincularTareaTiempo.muestraNumerosSegundo();
}


/**
 * Unión del botón de filtro
 */
timeLapse.tiempo.vincularTareaTiempo.persisteCambiosFiltroSegundo = function(){
	timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tiempo
 */
timeLapse.tiempo.vincularTareaTiempo.filtraTareasSegundo = function(){
    
	var filtroTareas = timeLapse.tiempo.vincularTareaTiempo.filtroTareasSegundo;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tiempo.vincularTareaTiempo.persisteCambiosFiltroSegundo);
	
};

/**
 * Muestra el total de tiempo
 */
timeLapse.tiempo.vincularTareaTiempo.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}