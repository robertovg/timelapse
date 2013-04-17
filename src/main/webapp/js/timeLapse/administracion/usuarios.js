/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.usuarios");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.llamadas");
dojo.requireLocalization("timeLapse","usuarios");

/**
 * Función que realiza la autentificación en la aplicación del usuario
 */
timeLapse.administracion.usuarios.autentificarse = function(){
	var nlsStrings = dojo.i18n.getLocalization("timeLapse","usuarios");
    
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var obj = dijit.byId('myForm').attr('value');
		var vaBien = {
		accion: function(datosRespuesta){
			
					if(datosRespuesta.usuarioLogueado){
						window.location.href = "../container/index.action";
					}	
			
			}
		};	
		
		timeLapse.utils.llamadas.llamadaAsinc("adminUsuarios/autentificacion.action",obj, vaBien);
		
		//dijit.byId("myForm").submit();
		//dojo.xhrPost({ form: "myForm" });

		
	}else{
		console.warn("El formulario de Login tiene errores");
		timeLapse.utils.dialogos.muestra(nlsStrings.errorFormularioLogin);

	}
	

}
/**
 * Función de registro en la aplicación
 */
timeLapse.administracion.usuarios.registarse = function(){
	console.log("Inicio de la carga del registro en la aplicación");    
	dojo.require("timeLapse.utils.llamadas");
	dojo.require("dijit.form.ValidationTextBox");	
	dojo.require("dijit.form.TextBox");	
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Form");
	
	
	var contenedor = dijit.byId("popUpContentPane");
	
	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/registroDeLaAplicacion.jsp"); 
	contenedor.attr('content',contenido);		
	
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonGuardar"), "onclick",  timeLapse.administracion.usuarios.guardaModifica);
	dijit.byId("formularioPopUp").show();
	console.log("Fin de la carga del registro en la aplicación");    

	
	
}
/**
 * Función a la que se llama para hacer efectivo una insercción de un nuevo usuario en la aplicación
 * @return
 */
timeLapse.administracion.usuarios.guardaModifica = function(){
	console.log("Inicio guardaModifica del registro en la aplicación");
	var nlsStrings = dojo.i18n.getLocalization("timeLapse","usuarios");
    
	var correcto = true;
	var obj = null;
	var vaBien = null;
	if(dijit.byId("formularioPopUp").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		obj = dijit.byId('formularioPopUp').attr('value');
		if(obj.formPasswd1 != obj.formPasswd2){
			console.warn("No ser repitió correctamente la contraseña");
			timeLapse.utils.dialogos.muestra(nlsStrings.errorPasswordNoIguales);
			correcto = false;
		}
		
		vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.administracion.usuarios.limpiaPopUp();				
				console.log("Fin guardaModifica del registro en la aplicación");							
			
			}
		};
		
	}else{
		console.warn("El formulario de Registro tiene errores");
		timeLapse.utils.dialogos.muestra(nlsStrings.errorFormularioRegistro);
		correcto = false;
	}
	
	if(correcto){
		timeLapse.utils.llamadas.llamadaAsinc("adminUsuarios/registroAplicacion.action",obj, vaBien);
	}
	
		
}
/**
 * Función que se encarga de limpiar y esconder el formulario
 */
timeLapse.administracion.usuarios.limpiaPopUp = function(){
    console.log("Limpieza del popUp de Registro");    

	if(dijit.byId("formularioPopUp").open){
		dijit.byId("formularioPopUp").hide();
	}
	dijit.byId("formNombreUsu").attr('value',"");
	dijit.byId("formNombrePropio").attr('value',"");	
	dijit.byId("formApellido1").attr('value',"");
	dijit.byId("formApellido2").attr('value',"");
	dijit.byId("formPasswd1").attr('value',"");
	dijit.byId("formPasswd2").attr('value',"");
	
	console.log("Fin de la limpieza del popUp de Registro");
	
}

/**
 * Función que se utiliza para salir de la aplicación
 */
timeLapse.administracion.usuarios.cerrarSesion = function(){
    console.log("Se sale de la aplicación: ");    
    window.location.href = "../container/cerrarSesion.action?nocache=" + Math.random() * 100;
    
}
;