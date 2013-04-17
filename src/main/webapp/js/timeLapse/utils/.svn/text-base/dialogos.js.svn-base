/**
 * @author robe
 */
dojo.provide("timeLapse.utils.dialogos");
dojo.require("dijit.Dialog");
/**
 * Función que se encarga de mostrar mensajes al usuario.
 * Ahora mismo están como alerts, pero estaría bien en un futuro
 * hacer algo de insección en el propio jsp
 * @param {Object} mensaje
 */
timeLapse.utils.dialogos.muestra = function(mensaje, titulo){
	var tituloLocal = titulo ? titulo : "Pop Up";
	
	 // Creo el dialogo
    var dialogoPopUp = new dijit.Dialog({
        title: tituloLocal,
        style: "width: 300px"
    });
	dialogoPopUp.attr("content", mensaje);
   	dialogoPopUp.show();
    
	
	
}
/**
 * Función que se encarga de pedir confirmaciones al usuario
 * del navegador.
 * @param {Object} mensaje
 */
timeLapse.utils.dialogos.confirma = function(mensaje){
	
	return window.confirm(mensaje);
	
}
/**
 * Función que se encarga de realizar preguntas al usuario.
 * Igual que el de arriba de momento lo dejo por defecto con el típico
 * del navegador.
 * @param {Object} mensaje
 */
timeLapse.utils.dialogos.pregunta = function(mensaje){
	
	return window.prompt(mensaje);
	
}
/**
 * Función que se encarga de tratar los mensajes devueltos por el servidor 
 * @param {Object} error
 * @param {Object} ioArgs
 * @param {Object} idDivRespuestas
 */
timeLapse.utils.dialogos.trataMensajes = function(mensajes, ioArgs, idDivRespuestas){
	dojo.require("timeLapse.utils.constantes");
	var cadInfos = "";
  	var cadErrores = "";
	//Si es una excepción conocida
	if (mensajes.status == timeLapse.utils.constantes.excepcionTimeLapse) {
		//Inicializo la timeLapseException
		var tlMensajes = eval('(' + mensajes.responseText + ')');
		//Trato los errores
		if (tlMensajes.errores.length > 0) {
		
			cadErrores += "<span class='errores'>";
			if (tlMensajes.errores.length > 1) {
			
				cadErrores += "<ul>";
				
			}
			else 
				
			for (var i = 0; i < tlMensajes.errores.length; i++) {
				var error = tlMensajes.errores[i];
				cadErrores += "<li>";
				cadErrores += error.codigo + ": " + error.mensaje;
				cadErrores += "</li>";
			}
			if (tlMensajes.errores.length > 1) {
				cadErrores += "</ul>";
			}
			cadErrores += "</span>";
		}
		//Trato los mensajes
		
		if (tlMensajes.infos.length > 0) {
		
			cadInfos += "<span class='infos'>";
			if (tlMensajes.infos.length > 1) {
				cadInfos += "<ul>";
				
			}
			for (var i = 0; i < tlMensajes.infos.length; i++) {
				var info = tlMensajes.infos[i];
				cadInfos += "<li>";
				cadInfos += info.codigo + ": " + info.mensaje;
				cadInfos += "</li>";
			}
			if (tlMensajes.infos.length > 1) {
				cadInfos += "</ul>";
			}
			cadInfos += "</span>";
		}
		
	//Si es una excepción extraña	
	}
	else 
		if (mensajes.status == timeLapse.utils.constantes.excepcionDescontrolada) {
			var tlMensajes = eval('(' + mensajes.responseText + ')');
			
			cadErrores += "<span class='errores'>";
			
			//cadErrores += "Clase: " + tlMensajes.class + "<br>";
			cadErrores += "Mensaje: " + tlMensajes.message + "<br>";
			
			cadErrores += "</span>";
		//Muestro los errores de validación	
		}
		else 
			if (mensajes.status == timeLapse.utils.constantes.errorDeValidacionServidor) {
			
				var mensajes = eval('(' + mensajes.responseText + ')');
				
				cadInfos += "<span class='infos'>";
				
				cadInfos += "<ul>";
				
				
				for (atributo in mensajes) {
				
					cadInfos += "<li>";
					cadInfos += mensajes[atributo];
					cadInfos += "</li>";
				}
				
				cadInfos += "</ul>";
				
				cadInfos += "</span>";
				
			}
			else 
				if (mensajes.status == timeLapse.utils.constantes.errorUsuarioCaducado) {
					    window.location.href = "../container/index.action?nocache=" + Math.random() * 100;

				}
			else if(mensajes.status == timeLapse.utils.constantes.errorFaltaDePermisos){
				cadErrores = "El usuario no tiene permisos suficientes para ejecutar el action solicitado"; 
			}
			

	
	//Muestro los mensajes
	timeLapse.utils.dialogos.muestraMensajes(cadErrores, cadInfos, idDivRespuestas);
	
}
/**
 * Función que muestra los mensajes enviados desde el servidor en forma de excepciones.
 * @param {Object} errores
 * @param {Object} infos
 * @param {Object} idDivRespuestas
 */
timeLapse.utils.dialogos.muestraMensajes = function(errores, infos, idDivRespuestas){

	//Si se ha definido el idDivRespuestas
	if(idDivRespuestas){
		window.alert("TODO Hay que mostrar bien un monton de errores");
		/*
		var errorRefNode = dojo.doc.createElement("span");
    	errorRefNode.innerHTML = errores;
		
		var infoRefNode = dojo.doc.createElement("span");
    	infoRefNode.innerHTML = infos;
		*/
		
	//Si no se ha definido un div para informar de los mensajes
	}else{
		      

		if(errores != ""){
			
			console.error("error!",errores);
			timeLapse.utils.dialogos.muestra(errores, "Errores");
		
				
		}
		if(infos != ""){
			
			console.log("info!", infos);
			timeLapse.utils.dialogos.muestra(infos);			
			
			
		}
		
	}
}



