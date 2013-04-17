/**
 * @author robe
 */
dojo.provide("timeLapse.grupos.adminGrupos");

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

dojo.requireLocalization("timeLapse","adminGrupos");

//Inicializo los textos
timeLapse.grupos.adminGrupos.textos = dojo.i18n.getLocalization("timeLapse","adminGrupos");


timeLapse.grupos.adminGrupos.init = function() {
	var contenedor = dijit.byId("principal");
	
	if(timeLapse.grupos.adminGrupos.jsonStore == undefined){
		timeLapse.grupos.adminGrupos.jsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/grupos/adminGrupos.jsp"); 
	contenedor.attr('content',contenido);
	
	//Fabrico el grid
    timeLapse.grupos.adminGrupos.recargaGrid();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.grupos.adminGrupos.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.grupos.adminGrupos.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.grupos.adminGrupos.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.grupos.adminGrupos.guardaModifica);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.grupos.adminGrupos.filtraGrid);
	
		
}
timeLapse.grupos.adminGrupos.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Grupos")
	
	
	var grid = dijit.byId("grid");
	var vaBien = {
		accion: function(datosGruposBD){
			//Hago una limpieza de sus datos
			timeLapse.grupos.adminGrupos.filtraGrid("*");
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
			timeLapse.grupos.adminGrupos.filtraGrid();			
			dojo.connect(grid, "onRowClick", timeLapse.grupos.adminGrupos.muestraNumeros);
			console.log("Fin creación de la parrilla de Grupos")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("grupos/listaGruposCreados.action",null, vaBien);
	
}
/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.grupos.adminGrupos.muestraNuevo = function(){
	console.log("Inicio Formulario nueva Categoría:");
	timeLapse.grupos.adminGrupos.limpiaPopUp();
	dijit.byId("formularioPopUp").show();
	console.log("Fin Formulario nueva Categoría:");
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.grupos.adminGrupos.muestraModificar = function(){
	console.log("Inicio Formulario modifica Categoría:");

	timeLapse.grupos.adminGrupos.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Categoría");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.adminGrupos.textos.debesSelelecionarSoloUnGrupo);

	
	}
	
	if(correcto){
		var selected = row[0];
		console.log("Modificar Objetivo:" + selected.oid);		
		dijit.byId("formNombre").attr('value',selected.nombre);		
		dijit.byId("formDesc").attr('value',selected.descripcion);		
		dijit.byId("formOID").attr('value',selected.oid);		
		dijit.byId("formularioPopUp").show();
		
	}
	console.log("Fin Formulario modifica Categoría:");
		
    
		
}
/**
 * Función que se llama cuando se pulsa el Eliminar 
 */
timeLapse.grupos.adminGrupos.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	var nombresElementosAEliminar = "";
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Categoría");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.adminGrupos.textos.debesSeleccionarAlmenosUnGrupo);

	}else{
		
		
	    for(var i = 0; i < rows.length ; i ++){
			var row = rows[i];
	        console.log("Eliminar Acción: " + row.oid );
			resul.listID.push(row.oid);
			if(i == rows.length - 1){
				nombresElementosAEliminar += row.nombre;	
			}else if(i == rows.length - 2){
				nombresElementosAEliminar += row.nombre + " " + timeLapse.grupos.adminGrupos.textos.conjuncionCopulativa + " ";
			}else{
				nombresElementosAEliminar += row.nombre + ", ";
			}
	    }
		
			
	}
	//Pregunto si se va a realizar la eliminación
	if(correcto){
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.grupos.adminGrupos.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.grupos.adminGrupos.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.grupos.adminGrupos.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("grupos/eliminaGrupos.action",resul, vaBien);
		
		
	}
	
    
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.grupos.adminGrupos.guardaModifica = function(){
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.grupos.adminGrupos.limpiaPopUp();
				timeLapse.grupos.adminGrupos.recargaGrid();			
			
			}
		};
		
		timeLapse.utils.llamadas.llamadaAsinc("grupos/actualizaGrupo.action",obj, vaBien);		
		console.log("Se guarda la Acción: " + dojo.toJson(obj, true));
		
	}else{
		console.warn("El formulario Categoría tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.grupos.adminGrupos.textos.formularioConErrores);
		
	}
	
	
	
		
}
/**
 * Función que se encarga de limpiar y esconder el formularios
 */
timeLapse.grupos.adminGrupos.limpiaPopUp = function(){
	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}
	dijit.byId("formNombre").attr('value',"");	
	dijit.byId("formDesc").attr('value',"");
	dijit.byId("formOID").attr('value',null);
	
}
/**
 * Método que  se llama cada vez que cambia el contenido del grid de filtrando por el contenido existente en el grid
 * @param {Object} parametro
 */
timeLapse.grupos.adminGrupos.filtraGrid = function(parametro){

	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.grupos.adminGrupos.muestraNumeros();
}

/**
 * Muestra el total de elementos
 */
timeLapse.grupos.adminGrupos.muestraNumeros = function(){
	var grid = dijit.byId("grid");	
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}