/**
 * @author robe
 */
dojo.provide("timeLapse.utils.efectos");
dojo.require("timeLapse.utils.constantes");
dojo.require("dojox.widget.Toaster");

/**
 * Recarga el contenido de un div a través de una función pasada como parámetro con un efecto.
 * @param {Object} idNode
 * @param {Object} ejecucion
 */
timeLapse.utils.efectos.recargaContenido = function(idNode, ejecucion){
	//Oculto y lo hago aparecer de nuevo
    console.log("Inicio Recarga Con Fx Nodo" +idNode);

	dojo.fadeOut({
				node: idNode,
				duration:timeLapse.utils.constantes.fadeDuration,
				delay: timeLapse.utils.constantes.fadeDelay,				
				onEnd: function(){
				  ejecucion.accion();	
				  dojo.fadeIn({node: idNode, duration:timeLapse.utils.constantes.fadeDuration, delay: timeLapse.utils.constantes.fadeDelay}).play();
				}
			}).play();
			
	console.log("Fin Recarga Con Fx Nodo" +idNode);
}
/**
 * Recarga el contenido de un div a través de una función pasada como parámetro sin utilizar ningún
 * efecto
 * @param {Object} idNode
 * @param {Object} ejecucion
 */
timeLapse.utils.efectos.recargaContenidoSinFx = function(idNode, ejecucion){
	//Oculto y lo hago aparecer de nuevo
    console.log("Inicio Recarga sin fx Nodo" +idNode);	
  	ejecucion.accion();
			
	console.log("Fin Recarga sin fx Nodo" +idNode);
}
/**
 * Función que oculta el div pasado como parámetro
 * @param {Object} identificadorDiv
 */
timeLapse.utils.efectos.oculta = function(identificadorDiv){
	dojo.byId(identificadorDiv).style.display = "none";
}
/**
 * Función que muestra el div pasado como parámetro
 * @param {Object} identificadorDiv
 */
timeLapse.utils.efectos.muestra = function(identificadorDiv){
	dojo.byId(identificadorDiv).style.display = "";	
}
/**
 * Función que intercambia utilizando un efecto de fadeout los contenidos ocultado el primer div pasado como parámetro, y mostrando
 * el segundo, en el medio ejecuta la acción que opcionalmente se le pasa como tercer parámetro
 * @param {Object} identificadorAOcultar
 * @param {Object} identificadorAMostrar
 * @param {Object} ejecucion
 */
timeLapse.utils.efectos.intercambia = function(identificadorAOcultar, identificadorAMostrar,ejecucion){
	 console.log("Inicio Intercambio div con Fx Nodo, oculto" +identificadorAOcultar);

	dojo.fadeOut({
				node: identificadorAOcultar,
				duration:timeLapse.utils.constantes.fadeDuration,
				delay: timeLapse.utils.constantes.fadeDelay,				
				onEnd: function(){
				  dojo.byId(identificadorAOcultar).style.display = "none";
				  dojo.byId(identificadorAMostrar).style.display = "";
				  if(ejecucion){
				  	ejecucion.accion();
				  }
				  
				  dojo.fadeIn({node: identificadorAMostrar, duration:timeLapse.utils.constantes.fadeDuration, delay: timeLapse.utils.constantes.fadeDelay}).play();
				}
			}).play();
			
	console.log("Inicio Intercambio div con Fx Nodo, se muestra" +identificadorAMostrar);
}
/**
 * Función que intercambia los contenidos ocultado el primer div pasado como parámetro, y mostrando
 * el segundo, en el medio ejecuta la acción que opcionalmente se le pasa como tercer parámetro 
 * @param {Object} identificadorAOcultar
 * @param {Object} identificadorAMostrar
 * @param {Object} ejecucion
 */
timeLapse.utils.efectos.intercambiaSinFx = function(identificadorAOcultar, identificadorAMostrar,ejecucion){
	 console.log("Inicio Intercambio div sin Fx Nodo, oculto" +identificadorAOcultar);

	timeLapse.utils.efectos.oculta(identificadorAOcultar);
	if (ejecucion) {
		ejecucion.accion();
	}	
	timeLapse.utils.efectos.muestra(identificadorAMostrar);
	console.log("Inicio Intercambio div sin Fx Nodo, se muestra" +identificadorAMostrar);
}

