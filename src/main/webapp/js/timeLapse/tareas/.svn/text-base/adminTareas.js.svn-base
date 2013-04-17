/**
 * @author robe
 */
dojo.provide("timeLapse.tareas.adminTareas");

dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dojox.grid.DataGrid");


dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.DropDownButton");
dojo.require("dijit.TooltipDialog");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Form");



dojo.require("timeLapse.utils.constantes");
dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");
dojo.require("timeLapse.utils.formato");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.tareas.filtroTareasDTO");


dojo.requireLocalization("timeLapse","adminTareas");

//Inicializo los textos
timeLapse.tareas.adminTareas.textos = dojo.i18n.getLocalization("timeLapse","adminTareas");
//Filtro de las tareas
timeLapse.tareas.adminTareas.filtroTareas = null;

timeLapse.tareas.adminTareas.init = function() {
	timeLapse.tareas.adminTareas.filtroTareas = new timeLapse.tareas.filtroTareas(timeLapse.tareas.adminTareas.recargaGrid,timeLapse.utils.constantes.filtroTareas_TIPO_TODAS);
	
	var tareasEspecificas = timeLapse.container.menu.tareasEspecificas;
	//Si hay tareas que vienen de donde sea (busqueda o proximas) lo mando como parámetro
	if(tareasEspecificas != null){
		timeLapse.tareas.adminTareas.filtroTareas.almacen.listOidTareasEspecificas = tareasEspecificas.listOidTareasEspecificas;
	}
	
	var contenedor = dijit.byId("principal");
	
	if(timeLapse.tareas.adminTareas.jsonStore == undefined){
		var url = "../tareas/listaTareas.action";
		
	
		
		//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
		var parametros = dojo.objectToQuery(timeLapse.tareas.adminTareas.filtroTareas.almacen);
		
		timeLapse.tareas.adminTareas.jsonStore = new dojo.data.ItemFileReadStore(
		{url: url + "?" + parametros ,clearOnClose:true,syncMode:true,urlPreventCache:true, typeMap : timeLapse.utils.formato.typeMap});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/adminTareas.jsp"); 
	contenedor.attr('content',contenido);
	
	var origenTipo = timeLapse.utils.constantes.origen_tareas_normal;	
	if (tareasEspecificas != null) {
		origenTipo = tareasEspecificas.origenTareasEspecificas;
	}
	//Hago que se muestre la apariencia de grid según el origen
	timeLapse.tareas.adminTareas.colorea(origenTipo);
	
	
	timeLapse.tareas.adminTareas.cargaSelectGrupos();
	timeLapse.tareas.adminTareas.cargaSelectCategorias();
	//timeLapse.tareas.adminTareas.cargaSelectTareaPadre();
	
	
	//Fabrico el grid
    timeLapse.tareas.adminTareas.recargaGrid();
	
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.tareas.adminTareas.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.tareas.adminTareas.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.tareas.adminTareas.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.tareas.adminTareas.guardaModifica);
	dojo.connect(dojo.byId("botonMarcarFinalizada"), "onclick",  timeLapse.tareas.adminTareas.marcarComoFinalizadas);
	dojo.connect(dojo.byId("botonClonar"), "onclick",  timeLapse.tareas.adminTareas.clonarTarea);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.tareas.adminTareas.filtraGrid);
	dojo.connect(dojo.byId("anclaFiltroTareas"), "onclick",timeLapse.tareas.adminTareas.filtraTareas);
	dojo.connect(dojo.byId("anclaQuitarTareasEspecificas"), "onclick",timeLapse.tareas.adminTareas.restableceEspecificas);
	

}
/**
 * Función que se encarga de recargar/atualizar los datos del grid de tareas 
 */
timeLapse.tareas.adminTareas.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Tareas")
	
	//Hago una limpieza de sus datos
	timeLapse.utils.semaforo.aumenta();
	
	var url = "../tareas/listaTareas.action";
	//Pongo en la dirección de la recarga del grid los parametros y valores del filtro
	var parametros = dojo.objectToQuery(timeLapse.tareas.adminTareas.filtroTareas.almacen);
	
	timeLapse.tareas.adminTareas.jsonStore._jsonFileUrl  = url + "?" + parametros;
	var grid = dijit.byId("grid");
	dojo.connect(grid, "onRowClick", timeLapse.tareas.adminTareas.muestraNumeros);
	dojo.connect(grid, "_onFetchComplete", timeLapse.tareas.adminTareas.filtraGrid);
	dojo.connect(grid, "_onFetchComplete", timeLapse.utils.semaforo.disminuye);
	grid.selection.clear();
	timeLapse.tareas.adminTareas.jsonStore.close();
	timeLapse.tareas.adminTareas.jsonStore.fetch();
	grid._refresh();
	

	//dojo.connect(grid, "onFetchError", timeLapse.tareas.adminTareas.trataErrores);
	
	
	console.log("Fin creación de la parrilla de Tareas");
	
}


/**
 * Muestra el total de tareas
 */
timeLapse.tareas.adminTareas.muestraNumeros = function(){
	var grid = dijit.byId("grid");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}

/**
 * Función que carga el select de combos del Grupos para la modificación/creacion de tareas
 */
timeLapse.tareas.adminTareas.cargaSelectGrupos = function(){
	console.log("Inicio carga Select Grupos");
	
	var vaBien = {
		accion: function(datosRespuesta){
			var datosGrupos = new Array();
			var obj = new Object();	
			obj.oid = "-";
			obj.nombre = "-";
			datosGrupos.push(obj);
			for(var i = 0; i < datosRespuesta.listGrupos.length; i++){
				obj = new Object();
				obj.oid = datosRespuesta.listGrupos[i].oid;
				obj.nombre = datosRespuesta.listGrupos[i].nombre;
				datosGrupos.push(obj);
						
			}
				
			var myData = {identifier:'oid',items: datosGrupos};
			var selectStore = new dojo.data.ItemFileReadStore({data: myData});
			var select = dijit.byId("formGrupo");
			
			select.store = selectStore;
			select.startup();
			
			dijit.byId("formGrupo").attr('value',selected.oidGrupo != "-" ? selected.oidGrupo : "-");
		
			
		}
	};
	//Me Traigo y fabrico los datos de los objetivos
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposPertenecientes.action",null, vaBien);	
	
	console.log("Fin carga Select Grupos");
}
/**
 * Función que carga el select de combos de las Categorías para la modificación/creacion de tareas
 */
timeLapse.tareas.adminTareas.cargaSelectCategorias = function(){
	console.log("Inicio carga Select Categorias");
	
	var vaBien = {
		accion: function(datosRespuesta){
			var datosCategorias = new Array();
			var obj = new Object();
			obj.oid = "-";
			obj.nombre = "-";
			datosCategorias.push(obj);
			for(var i = 0; i < datosRespuesta.listCategorias.length; i++){
				obj = new Object();
				obj.oid = datosRespuesta.listCategorias[i].oid;
				obj.nombre = datosRespuesta.listCategorias[i].nombre;
				datosCategorias.push(obj);
						
			}
				
			var myData = {identifier:'oid',items: datosCategorias};
			var selectStore = new dojo.data.ItemFileReadStore({data: myData});
			var select = dijit.byId("formCategoria");
			
			select.store = selectStore;
			select.startup();
			
		}
	};
	//Me Traigo y fabrico los datos de los objetivos
	timeLapse.utils.llamadas.llamadaAsinc("categorias/listaCategorias.action",null, vaBien);
	
	console.log("Fin carga Select Categorias");	
}

/**
 * Función que carga el select de combos de las tareas padres para la modificación/creacion de tareas
 * @param {Object} oidTareaPadre
 */
timeLapse.tareas.adminTareas.cargaSelectTareaPadre = function(oidTareaPadre){
	console.log("Inicio carga Select Tareas Padre");
    
	var urlTareasPadre = "../tareas/listaTareasAAsociarComoPadres.action";
	if(oidTareaPadre != undefined){
		urlTareasPadre+= "?oidTareasPadre=" + oidTareaPadre;	
	}
	
	
	var selectStore = new dojo.data.ItemFileReadStore({
        url: urlTareasPadre,
        clearOnClose: true,
        syncMode: true,
        urlPreventCache: true
    });
    var select = dijit.byId("formTareaPadre");
	
    select.store = selectStore;
    select.startup();	
	  
    console.log("Fin carga Select Tareas Padre");
}

/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.tareas.adminTareas.muestraNuevo = function(){
	console.log("Inicio Formulario nueva Tarea:");	
	timeLapse.tareas.adminTareas.cargaSelectTareaPadre();
	timeLapse.tareas.adminTareas.limpiaPopUp();
	dojo.byId("contenedorFormularioAsociaciones").style.display = "";

	dijit.byId("formularioPopUp").show();
	console.log("Fin Formulario nueva Tarea:");
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.tareas.adminTareas.muestraModificar = function(){
	console.log("Inicio Formulario modifica Tarea:");

	timeLapse.tareas.adminTareas.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.debesSelelecionarSoloUnaTarea);

	
	}
	
	if(correcto){
		var selected = row[0];
		if(selected.oidTareaPadre != "" && selected.esPropiaLaTarea == "true"){
			timeLapse.tareas.adminTareas.cargaSelectTareaPadre(selected.oidTareaPadre);				
		}else{
			timeLapse.tareas.adminTareas.cargaSelectTareaPadre();
		}
		
		console.log("Modificar Objetivo:" + selected.oid);		
		dijit.byId("formNombre").attr('value',selected.nombre);		
		dijit.byId("formDesc").attr('value',selected.descripcion);
		dijit.byId("formImportancia").attr('value', selected.importancia);		
		dijit.byId("formFechaInicio").attr('value', timeLapse.utils.formato.gridToForm(selected.fechaInicio));
		dijit.byId("formHoraInicio").attr('value',timeLapse.utils.formato.stringToDate(selected.horaInicio));
		dijit.byId("formFechaFin").attr('value',timeLapse.utils.formato.gridToForm(selected.fechaFin));
		dijit.byId("formHoraFin").attr('value',timeLapse.utils.formato.stringToDate(selected.horaFin));
		dijit.byId("formFechaRealizacion").attr('value',timeLapse.utils.formato.gridToForm(selected.fechaRealizacion));
		dijit.byId("formHoraRealizacion").attr('value',timeLapse.utils.formato.stringToDate(selected.horaRealizacion));		
		/*
		 * Si la tarea es propia, cargo la modificación de relaciones,  
		 */
		if(selected.esPropiaLaTarea == "true"){
			dijit.byId("formCategoria").attr('value',selected.oidCategoria != "" ? selected.oidCategoria : "-");
			dijit.byId("formGrupo").attr('value',selected.oidGrupo != "" ? selected.oidGrupo : "-");
			dijit.byId("formTareaPadre").attr('value',selected.oidTareaPadre != "" ? selected.oidTareaPadre : "-");
			
			dojo.byId("contenedorFormularioAsociaciones").style.display = "";				
		}
		//En caso contrario
		else{
			
			dojo.byId("contenedorFormularioAsociaciones").style.display = "none";
		}
		
		
		dijit.byId("formLocalizacion").attr('value',selected.localizacionAsociada);
		var valorAutorrealizable = true;
		if(selected.autorrealizable == false || selected.autorrealizable == "false"){
			valorAutorrealizable = false;
		}
		dijit.byId("formAutorrealizable").attr('value',valorAutorrealizable);

		dijit.byId("formOID").attr('value',selected.oid);		
		dijit.byId("formularioPopUp").show();
		
	}
	console.log("Fin Formulario modifica Tarea:");
		
    
		
}

/**
 * Función que se llama cuando se pulsa el Eliminar 
 */
timeLapse.tareas.adminTareas.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	
	var correcto = true;
	var nombresElementosAEliminar = "";
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.debesSeleccionarAlmenosUnaTarea);

	}else{
		for(var i = 0; i < rows.length ; i ++){
				var row = rows[i];
			    console.log("Eliminar Tarea: " + row.oid );
				resul.listID.push(row.oid);
				if(i == rows.length - 1){
					nombresElementosAEliminar += row.nombre;	
				}else if(i == rows.length - 2){
					nombresElementosAEliminar += row.nombre + " " + timeLapse.tareas.adminTareas.textos.conjuncionCopulativa + " ";
				}else{
					nombresElementosAEliminar += row.nombre + ", ";
				}				 
		}
		
			
	}
	//Pregunto si se va a realizar la eliminación
	if(correcto){
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.tareas.adminTareas.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.tareas.adminTareas.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if (correcto) {
	
		var vaBien = {
			accion: function(datosRespuesta){
			
				timeLapse.tareas.adminTareas.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/eliminaTareas.action", resul, vaBien);
	}
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.tareas.adminTareas.guardaModifica = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var formulario1 = dijit.byId("myForm");
	var formulario2 = dijit.byId("myForm2");
	var valoresFormulario2 = formulario2.attr('value');
	var modificandoNoPropia = false;
	if(rows.length == 1){
		modificandoNoPropia = rows[0].esPropiaLaTarea == "false";	
	}
	
	var modificandoPropia = true;
	
	if(rows.length == 1){
		modificandoPropia = rows[0].esPropiaLaTarea == "true"
	}
	
	if((formulario1.isValid() && (modificandoNoPropia || formulario2.isValid()) )|| !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = formulario1.attr('value');
		obj.formFechaInicio = obj.formFechaInicio ? dojo.date.locale.format(obj.formFechaInicio, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}) : null;
		obj.formHoraInicio = obj.formHoraInicio ? dojo.date.locale.format(obj.formHoraInicio, {fullYear:true, selector:'time', datePattern: "HH:mm"}) : null;
		obj.formFechaFin = obj.formFechaFin ? dojo.date.locale.format(obj.formFechaFin, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"} ): null;
		obj.formHoraFin = obj.formHoraFin ? dojo.date.locale.format(obj.formHoraFin, {fullYear:true, selector:'time', datePattern: "HH:mm"}): null;
		obj.formFechaRealizacion = obj.formFechaRealizacion ? dojo.date.locale.format(obj.formFechaRealizacion, {fullYear:true, selector:'date', datePattern :"dd/MM/yyyy"}): null;
		obj.formHoraRealizacion =  obj.formHoraRealizacion ? dojo.date.locale.format(obj.formHoraRealizacion, {fullYear:true, selector:'time', datePattern: "HH:mm"}): null;
		
		if(modificandoPropia){
			obj.formCategoria = valoresFormulario2.formCategoria;
			obj.formGrupo = valoresFormulario2.formGrupo;
			obj.formTareaPadre = valoresFormulario2.formTareaPadre;
		}
			
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.tareas.adminTareas.limpiaPopUp();
				timeLapse.tareas.adminTareas.recargaGrid();			
			
			}
		};
		
		timeLapse.utils.llamadas.llamadaAsinc("tareas/actualizaTarea.action",obj, vaBien);		
		console.log("Se guarda la Tarea: " + dojo.toJson(obj, true));
		
	}else{
		console.warn("El formulario Tarea tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.formularioConErrores);
		
	}
		
}
/**
 * Función que se encarga de limpiar y esconder el formularios
 */
timeLapse.tareas.adminTareas.limpiaPopUp = function(){
	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}
	dijit.byId("formNombre").attr('value',"");	
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formOID").attr('value',null);	
	dijit.byId("formNombre").attr('value',"");		
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formImportancia").attr('value',1);		
	dijit.byId("formFechaInicio").attr('value',"");
	dijit.byId("formHoraInicio").attr('value',"");
	dijit.byId("formFechaFin").attr('value',"");
	dijit.byId("formHoraFin").attr('value',"");
	dijit.byId("formFechaRealizacion").attr('value',"");
	dijit.byId("formHoraRealizacion").attr('value',"");	
	dijit.byId("formCategoria").attr('value',"-");	
	dijit.byId("formGrupo").attr('value',"-");	
	dijit.byId("formTareaPadre").attr('value',"-");
	dijit.byId("formLocalizacion").attr('value',"");
	dijit.byId("formAutorrealizable").attr('value',false);
	
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.tareas.adminTareas.filtraGrid = function(parametro){

	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.tareas.adminTareas.muestraNumeros();
}

/**
 * Función que hace una petición al servidor para marcar las tareas enviadas como
 * realizadas. La fecha de realización que se asignarán será la actual.
 */
timeLapse.tareas.adminTareas.marcarComoFinalizadas = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.debesSeleccionarAlmenosUnaTarea);

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
				
				timeLapse.tareas.adminTareas.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/finalizarTareas.action",resul, vaBien);		
	}
}
/**
 * Función que clona la tarea marcada en el grid. El nombre será "Copia " + <nombreTarea> 
 */
timeLapse.tareas.adminTareas.clonarTarea = function(){
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var resul = {oidTarea:""};

	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Tarea");
		timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.debesSelelecionarSoloUnaTarea);

	
	}else{
		
		var selected = row[0];	
        console.log("Clona Tareaa: " + selected.oid );
		
		resul.oidTarea = selected.oid;	
	}
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
			
				timeLapse.tareas.adminTareas.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("tareas/clonarTarea.action",resul, vaBien);		
	}
}
/**
 * Unión del botón de filtro
 */
timeLapse.tareas.adminTareas.persisteCambiosFiltro = function(){
	timeLapse.tareas.adminTareas.filtroTareas.persisteCambiosFiltro();
}

/**
 * Método que muestra el filtro de tareas
 */
timeLapse.tareas.adminTareas.filtraTareas = function(){
    
	var filtroTareas = timeLapse.tareas.adminTareas.filtroTareas;
	timeLapse.utils.filtrado.filtraTareas(filtroTareas, timeLapse.tareas.adminTareas.persisteCambiosFiltro);
	
};
/**
 * Función que se encarga de colorear el grid de amarillo o azul según de donde provengan las tareas especificas
 * @param {Object} origenTareasEspecificas
 */
timeLapse.tareas.adminTareas.colorea = function(origenTareasEspecificas){
	var color = "#36393D";

	
	
	switch(origenTareasEspecificas){		
		
		case timeLapse.utils.constantes.origen_tareas_normal:
			//Normal
			color = "#36393D";			
			timeLapse.utils.efectos.oculta("anclaQuitarTareasEspecificas");
		break;
				
		case timeLapse.utils.constantes.origen_tareas_busqueda:
			//Azul
			color = "#869Ce2";
			timeLapse.utils.efectos.muestra("anclaQuitarTareasEspecificas");
		break;
		
		case timeLapse.utils.constantes.origen_tareas_proximas:
			//Amarillo
			color = "#FCB854";
			timeLapse.utils.efectos.muestra("anclaQuitarTareasEspecificas");
		break;
		
		default:
			color = "#36393D";			
			timeLapse.utils.efectos.oculta("anclaQuitarTareasEspecificas");
			
	}
	dojo.query(".partsContainer").style("backgroundColor",color);
	
	
}
/**
 * Función que elimina las tareas especificas
 */
timeLapse.tareas.adminTareas.restableceEspecificas = function(){
	
	timeLapse.tareas.adminTareas.colorea(timeLapse.utils.constantes.origen_tareas_normal);
	timeLapse.container.menu.tareasEspecificas = null;
	timeLapse.tareas.adminTareas.filtroTareas.almacen.listOidTareasEspecificas = null;
	timeLapse.tareas.adminTareas.recargaGrid();
}
