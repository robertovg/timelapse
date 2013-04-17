/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.adminFuncionalidades");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");


dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Form");



dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");

dojo.requireLocalization("timeLapse","adminFuncionalidades");
//Inicializo los textos
timeLapse.administracion.adminFuncionalidades.textos = dojo.i18n.getLocalization("timeLapse","adminFuncionalidades");


timeLapse.administracion.adminFuncionalidades.init = function() {
	var contenedor = dijit.byId("principal");
	
	if(timeLapse.administracion.adminFuncionalidades.jsonStore == undefined){
		timeLapse.administracion.adminFuncionalidades.jsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
	
	
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/adminFuncionalidades.jsp");	 
	contenedor.attr('content',contenido);
	
	var vaBien = {
		accion: function(datosObjetivosBD){
			var datosObjetivos = new Array();	
			for(var i = 0; i < datosObjetivosBD.listObjetivos.length; i++){
				var obj = new Object();
				obj.oid = datosObjetivosBD.listObjetivos[i].oid;
				obj.nombre = datosObjetivosBD.listObjetivos[i].nombre;
				datosObjetivos.push(obj);
						
			}
				
			var myData = {identifier:'oid',items: datosObjetivos};
			var selectStore = new dojo.data.ItemFileReadStore({data: myData});
			var select = dijit.byId("formObjetivo");
			
			select.store = selectStore;
			select.startup();	
			
		}
	};
	//Me Traigo y fabrico los datos de los objetivos
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaObjetivos.action",null, vaBien);

	
	
	//Fabrico el grid
    timeLapse.administracion.adminFuncionalidades.recargaGrid();

    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.administracion.adminFuncionalidades.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.administracion.adminFuncionalidades.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.administracion.adminFuncionalidades.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.administracion.adminFuncionalidades.guardaModifica);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.administracion.adminFuncionalidades.filtraGrid);
}
timeLapse.administracion.adminFuncionalidades.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio cración la parrilla de Administración de Funcionalidad")
	var grid = dijit.byId("grid");

	var vaBien = {
		accion: function(datosFuncionalidadesBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.adminFuncionalidades.filtraGrid("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			
			for(var i = 0; i < datosFuncionalidadesBD.listFuncionalidad.length; i++){
				var obj = new Object();
				obj.oid = datosFuncionalidadesBD.listFuncionalidad[i].oid;
				obj.nombre = datosFuncionalidadesBD.listFuncionalidad[i].nombre;
				obj.nombreObj = datosFuncionalidadesBD.listFuncionalidad[i].tlpObjetivo.nombre;
				obj.oidObj = datosFuncionalidadesBD.listFuncionalidad[i].tlpObjetivo.oid;
				obj.descripcion = datosFuncionalidadesBD.listFuncionalidad[i].descripcion;
				obj.orden = datosFuncionalidadesBD.listFuncionalidad[i].orden; 
				grid.store.newItem(obj);
		   		
			}
			grid.store.save();
			timeLapse.administracion.adminFuncionalidades.filtraGrid();
			dojo.connect(grid, "onRowClick", timeLapse.administracion.adminFuncionalidades.muestraNumeros);
			console.log("Fin cración la parrilla de Administración de Funcionalidad")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaFuncionalidades.action",null, vaBien);
	
	
	
}
/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminFuncionalidades.muestraNuevo = function(){
	console.log("Crear una nueva Funcionalidad:");
	timeLapse.administracion.adminFuncionalidades.limpiaPopUp();
	dijit.byId("formularioPopUp").show();
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminFuncionalidades.muestraModificar = function(){
	timeLapse.administracion.adminFuncionalidades.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Funcionalidad");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminFuncionalidades.textos.debeSeleccionarSoloUnaFuncionalidad);
	}
	
	if(correcto){
		var selected = row[0];
		console.log("Modificar Funcionalidad:" + selected.oid);
		
		dijit.byId("formNombre").attr('value',selected.nombre);
		dijit.byId("formObjetivo").attr('value',selected.oidObj);
		dijit.byId("formDesc").attr('value',selected.descripcion);
		dijit.byId("formOrden").attr('value',selected.orden);
		dijit.byId("formOID").attr('value',selected.oid);		
		dijit.byId("formularioPopUp").show();
		
	}
		
    
		
}
/**
 * Función que se llama cuando se pulsa el Eliminar 
 */
timeLapse.administracion.adminFuncionalidades.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()}
	var nombresElementosAEliminar = "";
	var correcto = true;
	if (rows.length == 0 || !timeLapse.utils.constantes.validacionesCliente) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Funcionalidad");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminFuncionalidades.textos.debeSeleccionarAlMenosUnaFuncionalidad);


	}else{
		
		for(var i = 0; i < rows.length ; i ++){
			var row = rows[i];			
	      	console.log("Eliminar Funcionalidad: " + row.oid );
			resul.listID.push(row.oid);
			if(i == rows.length - 1){
				nombresElementosAEliminar += row.nombre;	
			}else if(i == rows.length - 2){
				nombresElementosAEliminar += row.nombre + " " + timeLapse.administracion.adminFuncionalidades.textos.conjuncionCopulativa + " ";
			}else{
				nombresElementosAEliminar += row.nombre + ", ";
			}
	    }
		
	}
	//Pregunto si se va a realizar la eliminación
	if (correcto) {
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.administracion.adminFuncionalidades.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.administracion.adminFuncionalidades.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if(correcto){
		
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.administracion.adminFuncionalidades.recargaGrid();
			
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("administracion/eliminaFuncionalidades.action",resul, vaBien);
		
		
	}
	
    
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.administracion.adminFuncionalidades.guardaModifica = function(){
	if(dijit.byId("myForm").isValid()){
		
		var obj = dijit.byId('myForm').attr('value');		
		var vaBien = {
		accion: function(datosRespuesta){
				timeLapse.administracion.adminFuncionalidades.limpiaPopUp();
				timeLapse.administracion.adminFuncionalidades.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("administracion/actualizaFuncionalidad.action",obj, vaBien);
		console.log("Se guarda la funcionalidad: " + dojo.toJson(obj, true));
		
		
	}else{
		console.warn("El formulario Objetivos tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminFuncionalidades.textos.formularioFuncionalidadesTieneErrores);

		
	}
		
}
/**
 * Función que se encarga de limpiar y esconder el formulario
 */
timeLapse.administracion.adminFuncionalidades.limpiaPopUp = function(){
	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}	
	dijit.byId("formNombre").attr('value',"");
	dijit.byId("formObjetivo").attr('displayedValue',"");
	dijit.byId("formObjetivo").attr('value',"");	
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formOrden").attr('value',1);
	dijit.byId("formOID").attr('value',null);
	
}


/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.adminFuncionalidades.filtraGrid = function(parametro){
	
	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.adminFuncionalidades.muestraNumeros();
}
/**
 * Muestra el total de elementos
 */
timeLapse.administracion.adminFuncionalidades.muestraNumeros = function(){
	var grid = dijit.byId("grid");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}



