/**
 * @author robe
 */
dojo.provide("timeLapse.utils.semaforo");
/**
 * Función que sirve para aumentar el semaforo y muestra la barra de progreso si es necesario
 */
timeLapse.utils.semaforo.aumenta = function(){	
	var contador = parseInt(dijit.byId('contadorSemaforo').attr('value'));
	if(contador == 0){
		if(dojo.byId('progress')){
			dojo.byId('progress').style.visibility='visible';		
		}	
	}
	contador++;
	dijit.byId('contadorSemaforo').attr('value',contador)
}
/**
 * Función que sirve para disminuir el semaforo y esconder la barra de progreso
 */
timeLapse.utils.semaforo.disminuye = function(){
	var contador = parseInt(dijit.byId('contadorSemaforo').attr('value'));
	if(contador == 1){
		if(dojo.byId('progress')){
			dojo.byId('progress').style.visibility='hidden';		
		}	
	}
	contador --;
	dijit.byId('contadorSemaforo').attr('value',contador)

	
}
