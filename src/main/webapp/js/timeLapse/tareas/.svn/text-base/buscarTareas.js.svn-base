/**
 * @author robe
 */
dojo.provide("timeLapse.tareas.buscarTareas");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.formato");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.tareas.adminTareas");
dojo.require("timeLapse.tareas.tareasEspecificasDTO");



dojo.requireLocalization("timeLapse","buscarTareas");
//Inicializo los textos
timeLapse.tareas.buscarTareas.textos = dojo.i18n.getLocalization("timeLapse","buscarTareas");

timeLapse.tareas.buscarTareas.init = function() {	
	
	var contenedor = dijit.byId("principal");
	
	/*
	 * Contiene los datos del segundo grid
	 */
	if(timeLapse.tareas.buscarTareas.jsonStoreSegundo == undefined){		
		
		timeLapse.tareas.buscarTareas.jsonStoreSegundo = new dojo.data.ItemFileWriteStore(
		{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/buscarTareas.jsp"); 
	contenedor.attr('content',contenido);

	//Cargo los combos de filtros
	timeLapse.tareas.buscarTareas.cargaFiltroGrupos();
	timeLapse.tareas.buscarTareas.cargaFiltroCategorias();
	timeLapse.tareas.buscarTareas.cargaFiltroTareasPadre();
	
    dijit.byId("filtroCategorias").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
    dijit.byId("filtroGrupos").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
    dijit.byId("filtroTareasPadres").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
	
    //Conecto los botones a la funciones
					
	dojo.connect(dojo.byId("botonSeleccionReset"), "onclick",  timeLapse.tareas.buscarTareas.reseteaFormulario);
	dojo.connect(dojo.byId("botonSeleccionSiguiente"), "onclick",  timeLapse.tareas.buscarTareas.seleccionSiguiente);
	dojo.connect(dojo.byId("botonVolver"), "onclick",  timeLapse.tareas.buscarTareas.volverSeleccionBusqueda);
	dojo.connect(dojo.byId("botonAdministrar"), "onclick",  timeLapse.tareas.buscarTareas.administrarTareas);	
	
		
}

/**
* Función que carga el select de combos de los Grupos para el proceso de filtrado de las tareas
*/
timeLapse.tareas.buscarTareas.cargaFiltroGrupos = function(){
    console.log("Inicio carga Filtro Grupos");
    
	var cadUrl = "../grupos/listaGruposUtilizados.action";
	
    
    var selectStore = new dojo.data.ItemFileReadStore({
        url: cadUrl,
        clearOnClose: true,
        syncMode: true,
        urlPreventCache: true
    });
    var select = dijit.byId("filtroGrupos");
    
    select.store = selectStore;
    select.startup();
    
    console.log("Fin carga Filtro Grupos");
    
}
/**
 * Función que carga el select de combos de las Categorías para el proceso de filtrado de las tareas
 */
timeLapse.tareas.buscarTareas.cargaFiltroCategorias = function(){
    console.log("Inicio carga Filtro Categorias");
    
	var cadUrl = "../categorias/listaCategoriasUtilizadas.action";
		
	
    var selectStore = new dojo.data.ItemFileReadStore({
        url: cadUrl,
        clearOnClose: true,
        syncMode: true,
        urlPreventCache: true
    });
    var select = dijit.byId("filtroCategorias");
    
    select.store = selectStore;
    select.startup();
    
    console.log("Fin carga Filtro Categorias");
    
};
    
/**
 * Función que carga el select de combos de las Categorías para el proceso de filtrado de las tareas
 */
timeLapse.tareas.buscarTareas.cargaFiltroTareasPadre =  function(){
    console.log("Inicio carga Filtro Tareas Padre");
	
    var cadUrl = "../tareas/listaTareasQueSonPadre.action";
	
	
    var selectStore = new dojo.data.ItemFileReadStore({
        url : cadUrl,
        clearOnClose: true,
        syncMode: true,
        urlPreventCache: true
    });
    var select = dijit.byId("filtroTareasPadres");
    
    select.store = selectStore;
    select.startup();
    
    
    console.log("Fin carga Filtro Tareas Padre");
    
};



/**
 * Función que realiza la recarga del segundo grid
 */
timeLapse.tareas.buscarTareas.recargaGridSegundo = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la segunda parrilla de tiempo");
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/busquedaTareas.action";
	
	var formulario = dijit.byId('myForm').attr('value');
	var obj = new Object();
	
	//Textos
	obj.cadenaContiene = formulario.formContiene;
	obj.cadenaNoContiene = formulario.formNoContiene;
	obj.cadenaUbicacion = formulario.formUbicacion;
	
	//Asociaciones
	obj.categorias = formulario.filtroCategorias;
	obj.grupos = formulario.filtroGrupos;
	obj.tareasPadres = formulario.filtroTareasPadres;
	
	//Incluye Excluye
	obj.tareasRealizadas = formulario.filtroTareasRealizadas;
	obj.temporizadas = formulario.filtroTemporizadas;
	obj.tareasQueSonPadre = formulario.filtroTareasQueSonPadre;
	obj.autorrealizables = formulario.filtroAutorrealizables;	
	
	//Fechas
	obj.fechaPlanificacionInferior = formulario.filtroFechaPlanificacionInf ? dojo.date.locale.format(formulario.filtroFechaPlanificacionInf, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;	
	obj.fechaPlanificacionSuperior = formulario.filtroFechaPlanificacionSup ? dojo.date.locale.format(formulario.filtroFechaPlanificacionSup, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;	
	obj.fechaRealizacionInferior = formulario.filtroFechaRealizacionInf ? dojo.date.locale.format(formulario.filtroFechaRealizacionInf, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;	
	obj.fechaRealizacionSuperior = formulario.filtroFechaRealizacionSup ? dojo.date.locale.format(formulario.filtroFechaRealizacionSup, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;	
	obj.fechaCreacionInferior = formulario.filtroFechaCreacionInf ? dojo.date.locale.format(formulario.filtroFechaCreacionInf, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;	
	obj.fechaCreacionSuperior = formulario.filtroFechaCreacionSup ? dojo.date.locale.format(formulario.filtroFechaCreacionSup, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;
	
	
	
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(obj);
	
	timeLapse.tareas.buscarTareas.jsonStoreSegundo._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("gridSegundo");	
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.buscarTareas.muestraNumerosSegundo);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.buscarTareas.jsonStoreSegundo.close();
	timeLapse.tareas.buscarTareas.jsonStoreSegundo.fetch();
	grid._refresh();
	
	
	console.log("Fin creación de la segunda parrilla de tiempo");	
	
}
/**
 * Función que se encarga de mostrar el segundo grid
 */
timeLapse.tareas.buscarTareas.seleccionSiguiente = function(){
		
	var correcto = true;
	
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');
		
		var varFiltroTemporizadas = dijit.byId("filtroTemporizadas").attr('value');
		var varFiltroFechaPlanificacionInf = dijit.byId("filtroFechaPlanificacionInf").attr('value');
		var varFiltroFechaPlanificacionSup = dijit.byId("filtroFechaPlanificacionSup").attr('value');
		var varFiltroTareasRealizadas = dijit.byId("filtroTareasRealizadas").attr('value');
		var varFiltroFechaRealizacionInf = dijit.byId("filtroFechaRealizacionInf").attr('value');
		var varFiltroFechaRealizacionSup = dijit.byId("filtroFechaRealizacionSup").attr('value');
		var correcto = true;
		//Comprobar temporiazas
		if(correcto && (varFiltroTemporizadas == timeLapse.utils.constantes.filtroTareas_excluir)
				&& ((varFiltroFechaPlanificacionInf != "" && varFiltroFechaPlanificacionInf != null) 
					|| (varFiltroFechaPlanificacionSup != "" && varFiltroFechaPlanificacionSup != null) ) ){
			correcto = false;
			timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.temporiazadasIncoherentes);					
		}
		//Comprobar realizadas
		if(correcto && (varFiltroTareasRealizadas == timeLapse.utils.constantes.filtroTareas_excluir)
				&& ((varFiltroFechaRealizacionInf != "" && varFiltroFechaRealizacionInf != null) 
				|| (varFiltroFechaRealizacionSup != "" && varFiltroFechaRealizacionSup != null )) ){
			correcto = false;
			timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.realizadasIncoherentes);					
		}
			
			
		console.log("Se guardan las fechas ");
		
	}else{
		console.warn("Las búsqueda tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.buscarTareas.textos.formularioBusquedaConErrores);
		correcto = false;
	}
	
	var vaBien = {
		accion: function(datosRespuesta){		
			timeLapse.tareas.buscarTareas.recargaGridSegundo();	
			
		}
	};
	if (correcto) {
		
		timeLapse.utils.efectos.intercambia("contenedorPrimero","contenedorSegundo",vaBien);
	}
	
}
/**
 * Función que se encarga de resetear el formulario
 */
timeLapse.tareas.buscarTareas.reseteaFormulario = function(){

	//Textos
	dijit.byId("formContiene").attr('value', "");
    dijit.byId("formNoContiene").attr('value', "");
    dijit.byId("formUbicacion").attr('value', "");
	
	//Asociaciones
	dijit.byId("filtroCategorias").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
    dijit.byId("filtroGrupos").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
    dijit.byId("filtroTareasPadres").attr('value', timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS);
	
	//Icluye/Excluye
	dijit.byId("filtroTareasRealizadas").attr('value', timeLapse.utils.constantes.filtroTareas_incluir);
    dijit.byId("filtroTemporizadas").attr('value', timeLapse.utils.constantes.filtroTareas_incluir);
    dijit.byId("filtroTareasQueSonPadre").attr('value', timeLapse.utils.constantes.filtroTareas_incluir);
	dijit.byId("filtroAutorrealizables").attr('value', timeLapse.utils.constantes.filtroTareas_incluir);

	//Fechas
	dijit.byId("filtroFechaPlanificacionInf").attr('value', "");
    dijit.byId("filtroFechaPlanificacionSup").attr('value', "");
    dijit.byId("filtroFechaRealizacionInf").attr('value', "");
	dijit.byId("filtroFechaRealizacionSup").attr('value', "");
    dijit.byId("filtroFechaCreacionInf").attr('value', "");
    dijit.byId("filtroFechaCreacionSup").attr('value', "");
	
}




/**
 * Función a la que se llama para volver a la pantalla del primer contenedor
 */
timeLapse.tareas.buscarTareas.volverSeleccionBusqueda = function(){
	timeLapse.utils.efectos.intercambia("contenedorSegundo", "contenedorPrimero");

}
/**
 * Función que realiza fabrica el objeto que contendrá los resultados de esta búsqueda y muestra la administración
 * de tareas
 */
timeLapse.tareas.buscarTareas.administrarTareas = function(){

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
		
		
		timeLapse.container.menu.tareasEspecificas = new timeLapse.tareas.tareasEspecificas(listOidTareasEspecificas, timeLapse.utils.constantes.origen_tareas_busqueda);
			
		//Redirijo hacia adminTareas	
		timeLapse.container.menu.cargaFuncionalidad("tareas.adminTareas");	
	}else{
		timeLapse.container.menu.tareasEspecificas = null;
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.buscarTareas.textos.noAdministrarSinResultados);
	}
	
	
	
		
}

/**
 * Muestra el total de tiempo
 */
timeLapse.tareas.buscarTareas.muestraNumerosSegundo = function(){
	var grid = dijit.byId("gridSegundo");	
	var total = grid.rowCount;
	dijit.byId("contenedorNumeroSegundo").attr("content","" + total);
	
}