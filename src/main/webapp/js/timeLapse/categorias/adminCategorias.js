/**
 * @author robe
 */
dojo.provide("timeLapse.categorias.adminCategorias");

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

dojo.requireLocalization("timeLapse","adminCategorias");

//Inicializo los textos
timeLapse.categorias.adminCategorias.textos = dojo.i18n.getLocalization("timeLapse","adminCategorias");


timeLapse.categorias.adminCategorias.init = function() {
	var contenedor = dijit.byId("principal");
	
	if(timeLapse.categorias.adminCategorias.jsonStore == undefined){
		timeLapse.categorias.adminCategorias.jsonStore = new dojo.data.ItemFileWriteStore(
			{data: {identifier:'oid',items: new Array()},clearOnClose:true,syncMode:true});	
	}
		
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/categorias/adminCategorias.jsp"); 
	contenedor.attr('content',contenido);
	
	//Fabrico el grid
    timeLapse.categorias.adminCategorias.recargaGrid();
	
    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonNuevo"), "onclick",  timeLapse.categorias.adminCategorias.muestraNuevo);
	dojo.connect(dojo.byId("botonModificar"), "onclick",  timeLapse.categorias.adminCategorias.muestraModificar);
	dojo.connect(dojo.byId("botonEliminar"), "onclick",  timeLapse.categorias.adminCategorias.confirmaEliminar);
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.categorias.adminCategorias.guardaModifica);
	dojo.connect(dojo.byId("filtroGrid"), "onkeyup", timeLapse.categorias.adminCategorias.filtraGrid);
	
		
}
timeLapse.categorias.adminCategorias.recargaGrid = function(){
	//Pido los datos Json de los objetivos al action
	console.log("Inicio creación de la parrilla de Categorías")
	
	
	var grid = dijit.byId("grid");
	
	var vaBien = {
		accion: function(datosCategoriasBD){
			//Hago una limpieza de sus datos
			timeLapse.categorias.adminCategorias.filtraGrid("*");
			timeLapse.utils.limpieza.limpiaGrid(grid);
			
			//Transformo los objetos que me viene del servidor, a objetos planos, para dojo	
			for(var i = 0; i < datosCategoriasBD.listCategorias.length; i++){
				var obj = new Object();
				obj.oid = datosCategoriasBD.listCategorias[i].oid;
				obj.nombre = datosCategoriasBD.listCategorias[i].nombre;				
				obj.descripcion = datosCategoriasBD.listCategorias[i].descripcion;				 
				grid.store.newItem(obj);
		   		
			}
			grid.store.save();
			timeLapse.categorias.adminCategorias.filtraGrid();
			dojo.connect(grid, "onRowClick", timeLapse.categorias.adminCategorias.muestraNumeros);			
			console.log("Fin creación de la parrilla de Categorías")
			
		}
	};
	timeLapse.utils.llamadas.llamadaAsinc("categorias/listaCategorias.action",null, vaBien);
	
}
/**
 * Función que se llama cuando se pulsa el botón Nuevo y 
 * que activa el pop Up con el formulario
 */
timeLapse.categorias.adminCategorias.muestraNuevo = function(){
	console.log("Inicio Formulario nueva Categoría:");
	timeLapse.categorias.adminCategorias.limpiaPopUp();
	dijit.byId("formularioPopUp").show();
	console.log("Fin Formulario nueva Categoría:");
	
}
/**
 * Función que se llama cuando se pulsa el botón Modificar y 
 * que activa el pop Up con el formulario
 */
timeLapse.categorias.adminCategorias.muestraModificar = function(){
	console.log("Inicio Formulario modifica Categoría:");

	timeLapse.categorias.adminCategorias.limpiaPopUp();
	var grid = dijit.byId("grid");
	var row = grid.selection.getSelected();
	var correcto = true;
	if (row.length > 1  || row.length == 0){
		correcto = false;
		console.warn("Debe seleccionar solo y solo una Categoría");
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.adminCategorias.textos.debesSeleccionarAlmenosUnaCategoria);

	
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
timeLapse.categorias.adminCategorias.confirmaEliminar = function(){
	var grid = dijit.byId("grid");
	var rows = grid.selection.getSelected();
	var resul = {listID:new Array()};
	var nombresElementosAEliminar = "";
	var correcto = true;
	if (rows.length == 0) {
		correcto = false;
		console.warn("Debe seleccionar al menos una Categoría");
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.adminCategorias.textos.debesSeleccionarAlmenosUnaCategoria);

	}else{
		
		for(var i = 0; i < rows.length ; i ++){
			var row = rows[i];
	        console.log("Eliminar Acción: " + row.oid );
			resul.listID.push(row.oid);
			if(i == rows.length - 1){
				nombresElementosAEliminar += row.nombre;	
			}else if(i == rows.length - 2){
				nombresElementosAEliminar += row.nombre + " " + timeLapse.categorias.adminCategorias.textos.conjuncionCopulativa + " ";
			}else{
				nombresElementosAEliminar += row.nombre + ", ";
			}
	    }
		
			
	}
	//Pregunto si se va a realizar la eliminación	
	if(correcto){
		var texto = null;
		if(rows.length == 1){
			texto = timeLapse.categorias.adminCategorias.textos.estaSeguroDeEliminarUno;	
		}else{
			texto = timeLapse.categorias.adminCategorias.textos.estaSeguroDeEliminarMuchos;
		}
			
		
		texto = texto.replace(timeLapse.utils.constantes.constante_a_remplazar_constantes, nombresElementosAEliminar)
		correcto = timeLapse.utils.dialogos.confirma(texto);
	}
	//Realizo la llamada al servidor para borrar la entidad
	if(correcto){
		
		var vaBien = {
			accion: function(datosRespuesta){
				
				timeLapse.categorias.adminCategorias.recargaGrid();
				
			}
		};
		timeLapse.utils.llamadas.llamadaAsinc("categorias/eliminaCategorias.action",resul, vaBien);
		
		
	}
	
    
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción/modificación
 * de un Objetivo
 * @return
 */
timeLapse.categorias.adminCategorias.guardaModifica = function(){
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.categorias.adminCategorias.limpiaPopUp();
				timeLapse.categorias.adminCategorias.recargaGrid();			
			
			}
		};
		
		timeLapse.utils.llamadas.llamadaAsinc("categorias/actualizaCategoria.action",obj, vaBien);		
		console.log("Se guarda la Acción: " + dojo.toJson(obj, true));
		
	}else{
		console.warn("El formulario Categoría tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.categorias.adminCategorias.textos.formularioConErrores);
		
	}
	
	
	
		
}
/**
 * Función que se encarga de limpiar y esconder el formularios
 */
timeLapse.categorias.adminCategorias.limpiaPopUp = function(){
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
timeLapse.categorias.adminCategorias.filtraGrid = function(parametro){

	var idFiltro = "filtroGrid";
	var idGrid = "grid";
	
	timeLapse.utils.filtrado.filtradoGrids(idFiltro, idGrid, parametro);
	timeLapse.categorias.adminCategorias.muestraNumeros();

}
/**
 * Muestra el total de categorías
 */
timeLapse.categorias.adminCategorias.muestraNumeros = function(){
	var grid = dijit.byId("grid");
	var total = grid.selection.getSelected().length + " / " + grid.rowCount;
	dijit.byId("contenedorNumero").attr("content","" + total);
	
}




