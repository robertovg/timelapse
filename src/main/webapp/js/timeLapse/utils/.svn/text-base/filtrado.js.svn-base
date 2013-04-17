/**
 * @author robe
 */
dojo.provide("timeLapse.utils.filtrado");
var ultimaBusqueda = "";
/**
 * Función que se encarga de realizar el filtrado en los grids
 * @param {Object} idFiltro
 * @param {Object} idGrid
 * @param {Object} parametro
 */
timeLapse.utils.filtrado.filtradoGrids = function(idFiltro, idGrid, parametro){
	var cadFiltro = "";
	//Si no se le pasa la expresión regular por la que filtrar, cojo el valor del input filtro
	if(parametro != undefined && typeof parametro == "string"){
		cadFiltro = parametro;		
	}else{
		cadFiltro = dijit.byId(idFiltro).attr('value');
	}
	
	
	if (cadFiltro != ultimaBusqueda)  {
		var grid = dijit.byId(idGrid);
		grid.selection.clear();

		ultimaBusqueda = cadFiltro;
		if (cadFiltro == "") {
			cadFiltro = "*";
		}else{
			cadFiltro = "*" + cadFiltro + "*";
		}
		grid.filter({
			nombre: cadFiltro
		});
		
	}


}
/**
 * Función que se llama una vez que se le dá al botón de filtrado, se encarga de inicializar el filtro
 * de tareas y mostrarlo.
 * @param {Object} filtroTareas
 */
timeLapse.utils.filtrado.filtraTareas = function(filtroTareas, accionParaPersistirCambios){
	console.log("Inicio de la carga filtro");
    
    var contenedor = dijit.byId("popUpContentPane");
    
    //Me traigo el jsp y lo pongo en el main
    var contenido = timeLapse.utils.llamadas.recibir("jsp/tareas/filtroTareas.jsp");
    contenedor.attr('content', contenido);
	
	//Muestro/Oculto los atributos del filtro
	filtroTareas.muestraEscondeAtrubutos();
    
    //Lanzo asíncronamente la carga para los select, una vez vuelven añado *(todas) y -(no asociadas)	
    //Grupos
    filtroTareas.cargaFiltroGrupos();
    //Categorías
    filtroTareas.cargaFiltroCategorias();
    //Tareas Padre
    filtroTareas.cargaFiltroTareasPadre();
    
    //Conecto los botones a la funciones
    dojo.connect(dojo.byId("botonFiltrar"), "onclick", accionParaPersistirCambios);
    
    //Actualizo los valores del formulario si con los valores del objeto filtrado que aquí hay
    filtroTareas.actualizaValoresFiltro();
    
    dijit.byId("filtroPopUp").show();
    
    
    //También dependiendo de como esté inicializado, así desactivo o los valores, consiguiendo así reutilizar el mismo código 
    console.log("Fin de la carga filtro");
	
}
