/**
 * @author robe
 */
dojo.provide("timeLapse.utils.limpieza");
timeLapse.utils.limpieza.limpiaMenuSup = function(){
	dojo.byId("cabecera").innerHTML = "";
	timeLapse.utils.limpieza.limpiaMenuIzq();
	
	
	
}
timeLapse.utils.limpieza.limpiaMenuIzq = function(){
	dijit.byId("cabMenuLateral").attr('content',"");
	timeLapse.utils.limpieza.limpiaPrincipal();	
}
timeLapse.utils.limpieza.limpiaPrincipal = function(){
	dijit.byId("principal").attr('content',"");
}

timeLapse.utils.limpieza.destruye = function(elem){
	console.log("Destruyo el widget: "+ elem);

	if(elem){
		elem.destroyDescendants();
		elem.destroy();
	}	
}
/**
 * Función que se le pasa un grid a partir del cual borra y deselecciona el mismo
 * @param {Object} elemGrid
 */
timeLapse.utils.limpieza.limpiaGrid = function(grid){
	
	
	if(grid != undefined && grid instanceof dojox.grid.DataGrid){
		
		var storeDelGrid = grid.store;	
			
		//Esta mierda, me hizo perder 2 días!!	Nota mental, no imprimir objetos infinitos!!!
		//console.log("Destruyo el grid: "+ dojo.toJson(storeDelGrid));
		console.log("Limpo de datos el grid");
		
		//Recorro todos los elementos del grid y los voy borrando
		function borra(items, request) {
	        if (items ) {
	          var i;
	          for (i = 0; i < items.length; i++) {
	            var item = items[i];
	            storeDelGrid.deleteItem(item);
	           
	          }
	        }
	  	}
		//Actualizo el grid
		storeDelGrid.fetch({query:{oid: "*"}, onComplete: borra, queryOptions: {deep:true}});
		storeDelGrid.save();
		//Deselecciono las filas seleccionadas	
		grid.selection.clear();
	
	
	}else{
		console.error("Se está llamando al limpiaGrid de pasandole un parámetro incorrecto");
	}
	
}

