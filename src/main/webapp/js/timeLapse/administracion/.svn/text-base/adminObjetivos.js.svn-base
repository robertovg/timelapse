/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.adminObjetivos");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");


dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Form");



dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.filtrado");

dojo.requireLocalization("timeLapse","adminObjetivos");
//Inicializo los textos
timeLapse.administracion.adminObjetivos.textos = dojo.i18n.getLocalization("timeLapse","adminObjetivos");



timeLapse.administracion.adminObjetivos.init = function() {
	var contenedor = dijit.byId("principal");

	if(timeLapse.administracion.adminObjetivos.jsonStore == undefined){
		timeLapse.administracion.adminObjetivos.jsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true});	
	}
	
	//Me traigo el jsp y lo pongo en el main	
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/adminObjetivos.jsp");
	contenedor.attr('content',contenido);
	
	//Inserto los datos al ItemFileWriteStore del grid 
    timeLapse.administracion.adminObjetivos.recargaGrid();
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.administracion.adminObjetivos.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.administracion.adminObjetivos.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.administracion.adminObjetivos.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.administracion.adminObjetivos.guardaModifica);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.administracion.adminObjetivos.filtraGrid);
		
}
timeLapse.administracion.adminObjetivos.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio Recarga la parrilla de Administración de Objetivos")
	var grid = dijit.byId("grid");
	var vaBien = {
		accion: function(datosObjetivosBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.adminObjetivos.filtraGrid("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			for(var i = 0; i < datosObjetivosBD.listObjetivos.length; i++){
				var obj = new Object();
				obj.oid = datosObjetivosBD.listObjetivos[i].oid;
				obj.nombre = datosObjetivosBD.listObjetivos[i].nombre;		
				obj.descripcion = datosObjetivosBD.listObjetivos[i].descripcion;
				obj.orden = datosObjetivosBD.listObjetivos[i].orden; 
		   		grid.store.newItem(obj);
			}
			grid.store.save();
			timeLapse.administracion.adminObjetivos.filtraGrid();
			dojo.connect(grid, "onRowClick", timeLapse.administracion.adminObjetivos.muestraNumeros);
			console.log("Fin Recarga la parrilla de Administración de Objetivos")
			
		}
	};
	
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaObjetivos.action",null, vaBien);
	
	
}

/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminObjetivos.muestraNuevo = function(){
	console.log("Crear Uno Nuevo Obetivo:");
	timeLapse.administracion.adminObjetivos.limpiaPopUp();
	dijit.byId("formularioPopUp").show();
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminObjetivos.muestraModificar = function(){
	timeLapse.administracion.adminObjetivos.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo un Objetivo");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminObjetivos.textos.debesSeleccionarSoloUnObjetivo);
	
	}
	
	if(correcto){
		var selected = row[0];
		console.log("Modificar Objetivo:" + selected.oid);
		
		dijit.byId("formNombre").attr('value',selected.nombre);
		dijit.byId("formDesc").attr('value',selected.descripcion);
		dijit.byId("formOrden").attr('value',selected.orden);
		dijit.byId("formOID").attr('value',selected.oid);		
		dijit.byId("formularioPopUp").show();
		
	}    
		
}
/**
 * Función que se llama cuando se pulsa el Eliminar 
 */
timeLapse.administracion.adminObjetivos.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()}
	var nombresElementosAEliminar = "";
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos un Objetivo");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminObjetivos.textos.debeSeleccionarAlMenosUnObjetivo);

	}else{
		
		for(var i = 0; i < rows.length ; i ++){
			var row = rows[i];			
	      	console.log("Eliminar Objetivo: " + row.oid );
			resul.listID.push(row.oid);
			if(i == rows.length - 1){
				nombresElementosAEliminar += row.nombre;	
			}else if(i == rows.length - 2){
				nombresElementosAEliminar += row.nombre + " " + timeLapse.administracion.adminObjetivos.textos.conjuncionCopulativa + " ";
			}else{
				nombresElementosAEliminar += row.nombre + ", ";
			}
	    }
		
	}
	//Pregunto si se va a realizar la eliminación
	if (correcto) {
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.administracion.adminObjetivos.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.administracion.adminObjetivos.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if(correcto){
		
		
		var vaBien = {
		accion: function(datosRespuesta){
							
				timeLapse.administracion.adminObjetivos.recargaGrid();						
			
			}
		};	
		timeLapse.utils.llamadas.llamadaAsinc("administracion/eliminaObjetivos.action",resul, vaBien);

	}
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.administracion.adminObjetivos.guardaModifica = function(){
	
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');		
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.administracion.adminObjetivos.limpiaPopUp();
				timeLapse.administracion.adminObjetivos.recargaGrid();
						
			
			}
		};	
		timeLapse.utils.llamadas.llamadaAsinc("administracion/actualizaObjetivo.action",obj, vaBien);
			
		console.log("Se guarda el objetivo: " + dojo.toJson(obj, true));
		
		
	}else{
		console.warn("El formulario Objetivos tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminObjetivos.textos.formularioObjetivosConErrores);
	}
	
			
}
/**
 * Función que se encarga de limpiar y esconder el formulario
 */
timeLapse.administracion.adminObjetivos.limpiaPopUp = function(){
	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}
	dijit.byId("formNombre").attr('value',"");
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formOrden").attr('value',1);
	dijit.byId("formOID").attr('value',null);
	
}

/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.adminObjetivos.filtraGrid = function(parametro){
	
	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.adminObjetivos.muestraNumeros();
}
/**
 * Muestra el total de elementos
 */
timeLapse.administracion.adminObjetivos.muestraNumeros = function(){
	var grid = dijit.byId("grid");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}



