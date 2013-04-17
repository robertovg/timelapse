/**
 * @author robe
 */
dojo.provide("timeLapse.tareas.verProximasTareas");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.RadioButton");

dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");
dojo.require("timeLapse.utils.formato");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.tareas.tareasEspecificasDTO");
dojo.requireLocalization("timeLapse","adminTareas");

//Inicializo los textos
timeLapse.tareas.verProximasTareas.textos = dojo.i18n.getLocalization("timeLapse","adminTareas");

timeLapse.tareas.verProximasTareas.init = function() {	
	
	var contenedor = dijit.byId("principal");
	
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.tareas.verProximasTareas.jsonStoreSegundo == undefined){		
		
		timeLapse.tareas.verProximasTareas.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/verProximasTareas.jsp"); 
	contenedor.attr('content',contenido);

	//Fabrico el grid
    timeLapse.tareas.verProximasTareas.recargaGridSegundo();
	//Pongo orden de importancia
	dijit.byId("ordenImportancia").attr('checked', true);
    dijit.byId("ordenFecha").attr('checked', false);
	
	
	//Conecto los botones a la funciones
	dojo.connect(dojo.byId("botonAdministrar"), "onclick",  timeLapse.tareas.verProximasTareas.administrarTareas);
	dojo.connect(dojo.byId("botonMarcarFinalizada"), "onclick",  timeLapse.tareas.verProximasTareas.marcarComoFinalizadas);
	dojo.connect(dojo.byId("ordenImportancia"), "onclick",  timeLapse.tareas.verProximasTareas.recargaGridSegundo);
	dojo.connect(dojo.byId("ordenFecha"), "onclick",  timeLapse.tareas.verProximasTareas.recargaGridSegundo);
	
			
}

/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.tareas.verProximasTareas.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la lista próximas tareas");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	

	var orden = null;
 	if (dijit.byId("ordenImportancia").attr('checked')) {
        orden = timeLapse.utils.constantes.filtroTareas_ORDEN_IMPORTANCIA;
    }
    else 
        if (dijit.byId("ordenFecha").attr('checked')) {
            orden = timeLapse.utils.constantes.filtroTareas_ORDEN_FECHA;
        }
    
	var obj = new Object();
	obj.orden = orden;
	
	var url = "../tareas/listaProximasTareas.action";	
	
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(obj);
	
	timeLapse.tareas.verProximasTareas.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");
	dojo.connect(grid, "onRowClick", timeLapse.tareas.verProximasTareas.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.verProximasTareas.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.verProximasTareas.jsonStoreSegundo.close();
	timeLapse.tareas.verProximasTareas.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la lista próximas tareas");	
	
}

/**
 * Función que hace una petición al servidor para marcar las tareas enviadas como
 * realizadas. La fecha de realización que se asignarán será la actual.
 */
timeLapse.tareas.verProximasTareas.marcarComoFinalizadas = function(){
	var grid = dijit.byId("gridSegundo");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.verProximasTareas.textos.debesSeleccionarAlmenosUnaTarea);

	}else{
		
		dojo.forEach(rows,
		    function(row) {
				
		        console.log("Finaliza Tareaa: " + row.oid );
				resul.listID.push(row.oid);
		    }
		);
			
	}
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.tareas.verProximasTareas.recargaGridSegundo();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/finalizarTareas.action",resul, vaBien);		
	}
}

/**
 * Función que realiza fabrica el objeto que contendrá los resultados de esta búsqueda y muestra la administración
 * de tareas
 */
timeLapse.tareas.verProximasTareas.administrarTareas = function(){

	var correcto = true;
	var grid = dijit.byId("gridSegundo");
	var rows = grid.store._arrayOfAllItems;
	
	if (rows.length > 0) {
		var listOidTareasEspecificas = new Array();
		//Añado todos los oid que han resultado
		dojo.forEach(rows,
		    function(row) {				
				listOidTareasEspecificas.push(row.oid);
		    }
		);
		
		
		timeLapse.container.menu.tareasEspecificas = new timeLapse.tareas.tareasEspecificas(listOidTareasEspecificas, timeLapse.utils.constantes.origen_tareas_proximas);
			
		//Redirijo hacia adminTareas	
		timeLapse.container.menu.cargaFuncionalidad("tareas.adminTareas");	
	}else{
		timeLapse.container.menu.tareasEspecificas = null;
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.verProximasTareas.textos.noAdministrarSinResultados);
	}
		
}

/**
 * Muestra el total de tiempo
 */
timeLapse.tareas.verProximasTareas.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);	
}


