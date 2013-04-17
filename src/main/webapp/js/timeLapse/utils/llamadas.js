/**
 * @author robe
 */
dojo.provide("timeLapse.utils.llamadas");

dojo.require("timeLapse.utils.semaforo");

timeLapse.utils.llamadas.llamadaSinc = function(direccion, parametros){
	dojo.deprecated("Esta función no se debería utilizar, utilizar mejor timeLapse.utils.llamadas.llamadaAsinc");
	
	var datos = null;
	dojo.xhrGet({
		url: "../" + direccion,
		handleAs: "json",
		content: parametros,
		sync:true,
		preventCache: false,
		load: function(response, ioArgs){
			//Asigno los datos de respuesta a la variable datos
			datos = response;
			
		},
		// función que se llama si se produce algún error
		error: function(error,ioArgs){
			console.warn("error!",error);
		}
	});
	return datos;
}
timeLapse.utils.llamadas.llamadaAsinc = function(direccion, parametros, funcionVuelta, idDivRespuestas){
	
	console.log("Inicio petición asincrona hacia: " + direccion.toString());
	timeLapse.utils.semaforo.aumenta();
	dojo.xhrGet({
		url: "../" + direccion,
		handleAs: "json",
		content: parametros,		
		sync:false,
		preventCache: true,
		load: function(response, ioArgs){
			//Ejecuto la funcion de vuelta
			if(funcionVuelta){
				//funcionVuelta.accion(response.targetSource.target);
				funcionVuelta.accion(response);
				
			}
			//Escondo la barra de progreso			
			timeLapse.utils.semaforo.disminuye();
			var cadRespuesta = null;
			if(response.mensajeRespuesta != null){
				cadRespuesta = new String(response.mensajeRespuesta);
			} 
			//Muestro el mensaje de vuelta, si lo hay.
			if(cadRespuesta != null){
				dojo.publish("mensajeRespuesta", [{
			      message: cadRespuesta, 
			      type: "message", 
			      duration: parseInt(timeLapse.utils.constantes.mensajeRespuestaDuration)
			    }]);					
	     		 
			
			}
			console.log("Fin petición asincrona hacia: " + direccion.toString());
			
		},
		// función que se llama si se produce algún error
		error: function(error,ioArgs){
			dojo.require("timeLapse.utils.dialogos");
			timeLapse.utils.dialogos.trataMensajes(error, ioArgs, idDivRespuestas);
			timeLapse.utils.semaforo.disminuye();

			
		}
	});
	
	
	
}

timeLapse.utils.llamadas.recibir = function(direccion){
	console.log("Inicio petición sincrona de datos: " + direccion.toString());
	timeLapse.utils.semaforo.aumenta();
	var datos = null;
	dojo.xhrGet({
		url: "../" + direccion,
		handleAs: "text",
		sync: true,
		preventCache: false,
		load: function(response, ioArgs){
		  	datos = response;
			timeLapse.utils.semaforo.disminuye();		
		},
		// función que se llama si se produce algún error
		error: function(error, ioArgs){
			console.warn("error!",error);
			timeLapse.utils.llamadas.disminuye();
		}
	});
	
	console.log("Fin petición sincrona de datos: " + direccion.toString());
	return datos;
}


