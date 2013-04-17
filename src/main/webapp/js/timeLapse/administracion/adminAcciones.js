/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.adminAcciones");

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

dojo.requireLocalization("timeLapse","adminAcciones");

//Inicializo los textos
timeLapse.administracion.adminAcciones.textos = dojo.i18n.getLocalization("timeLapse","adminAcciones");;


timeLapse.administracion.adminAcciones.init = function() {
	var contenedor = dijit.byId("principal");
	
	if(timeLapse.administracion.adminAcciones.jsonStore == undefined){
		timeLapse.administracion.adminAcciones.jsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/adminAcciones.jsp"); 
	contenedor.attr('content',contenido);
	
	
	var vaBien = {
		accion: function(datosFuncionalidadBD){
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			var datosFuncionalidad = new Array();	
			for(var i = 0; i < datosFuncionalidadBD.listFuncionalidad.length; i++){
				var obj = new Object();
				obj.oid = datosFuncionalidadBD.listFuncionalidad[i].oid;
				obj.nombre = datosFuncionalidadBD.listFuncionalidad[i].nombre;
				datosFuncionalidad.push(obj);
						
			}
				
			var myData = {identifier:'oid',items: datosFuncionalidad};
			var selectStore = new dojo.data.ItemFileReadStore({data: myData});
			var select = dijit.byId("formFuncionalidad");
			
			select.store = selectStore;
			select.startup();
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaFuncionalidades.action",null, vaBien);
	
	//Fabrico el grid
    timeLapse.administracion.adminAcciones.recargaGrid();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.administracion.adminAcciones.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.administracion.adminAcciones.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.administracion.adminAcciones.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.administracion.adminAcciones.guardaModifica);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.administracion.adminAcciones.filtraGrid);
	
		
}
timeLapse.administracion.adminAcciones.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Incio creación parrilla de Administración de las Acciones")
	
	
	var grid = dijit.byId("grid");
	var vaBien = {
		accion: function(datosAccionesBD){
			//Hago una limpieza de sus datos
			timeLapse.administracion.adminAcciones.filtraGrid("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosAccionesBD.listAcciones.length; i++){
				var obj = new Object();
				obj.oid = datosAccionesBD.listAcciones[i].oid;
				obj.nombre = datosAccionesBD.listAcciones[i].nombre;
				obj.nombreFunc = datosAccionesBD.listAcciones[i].tlpFuncionalidad.nombre;
				obj.oidFunc = datosAccionesBD.listAcciones[i].tlpFuncionalidad.oid;
				obj.path = datosAccionesBD.listAcciones[i].path;
				obj.descripcion = datosAccionesBD.listAcciones[i].descripcion;
				obj.orden = datosAccionesBD.listAcciones[i].orden; 
				grid.store.newItem(obj);
		   		
			}
			grid.store.save();
			timeLapse.administracion.adminAcciones.filtraGrid();
			dojo.connect(grid, "onRowClick", timeLapse.administracion.adminAcciones.muestraNumeros);
			console.log("Fin creación parrilla de Administración de las Acciones")
			
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("administracion/listaAcciones.action",null, vaBien);
	
}
/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminAcciones.muestraNuevo = function(){
	console.log("Crear Una Nueva Acción:");
	timeLapse.administracion.adminAcciones.limpiaPopUp();
	dijit.byId("formularioPopUp").show();
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.administracion.adminAcciones.muestraModificar = function(){
	timeLapse.administracion.adminAcciones.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Acción");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminAcciones.textos.debesSelelecionarSoloUnaAccion);

	
	}
	
	if(correcto){
		var selected = row[0];
		console.log("Modificar Objetivo:" + selected.oid);
		
		dijit.byId("formNombre").attr('value',selected.nombre);
		dijit.byId("formFuncionalidad").attr('value',selected.oidFunc);
		dijit.byId("formPath").attr('value',selected.path);
		dijit.byId("formDesc").attr('value',selected.descripcion);
		dijit.byId("formOrden").attr('value',selected.orden);
		dijit.byId("formOID").attr('value',selected.oid);		
		dijit.byId("formularioPopUp").show();
		
	}
		
    
		
}
/**
 * Función que se llama cuando se pulsa el Eliminar 
 */
timeLapse.administracion.adminAcciones.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	var nombresElementosAEliminar = "";
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Acción");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminAcciones.textos.debesSeleccionarAlmenosUnaAccion);

	}else{
		
		for(var i = 0; i < rows.length ; i ++){
			var row = rows[i];			
	        console.log("Eliminar Acción: " + row.oid );
			resul.listID.push(row.oid);
			if(i == rows.length - 1){
				nombresElementosAEliminar += row.nombre;	
			}else if(i == rows.length - 2){
				nombresElementosAEliminar += row.nombre + " " + timeLapse.administracion.adminAcciones.textos.conjuncionCopulativa + " ";
			}else{
				nombresElementosAEliminar += row.nombre + ", ";
			}
	    }
		
			
	}
	//Pregunto si se va a realizar la eliminación
	if (correcto) {
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.administracion.adminAcciones.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.administracion.adminAcciones.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.administracion.adminAcciones.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("administracion/eliminaAcciones.action",resul, vaBien);
		
		
	}
	
    
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.administracion.adminAcciones.guardaModifica = function(){
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.administracion.adminAcciones.limpiaPopUp();
				timeLapse.administracion.adminAcciones.recargaGrid();			
			
			}
		};
		
		timeLapse.utils.llamadas.llamadaAsinc("administracion/actualizaAccion.action",obj, vaBien);		
		console.log("Se guarda la Acción: " + dojo.toJson(obj, true));
		
	}else{
		console.warn("El formulario Acciones tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.adminAcciones.textos.formularioConErrores);
		
	}
	
	
	
		
}
/**
 * Función que se encarga de limpiar y esconder el formularios
 */
timeLapse.administracion.adminAcciones.limpiaPopUp = function(){
	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}
	dijit.byId("formNombre").attr('value',"");
	dijit.byId("formFuncionalidad").attr('displayedValue',"");
	dijit.byId("formFuncionalidad").attr('value',"");	
	dijit.byId("formPath").attr('value',"");
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formOrden").attr('value',1);
	dijit.byId("formOID").attr('value',null);
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.administracion.adminAcciones.filtraGrid = function(parametro){
	
	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.administracion.adminAcciones.muestraNumeros();
}
/**
 * Muestra el total de categorías
 */
timeLapse.administracion.adminAcciones.muestraNumeros = function(){
	var grid = dijit.byId("grid");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}



