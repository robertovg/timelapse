/**
 * @author robe
 */
dojo.provide("timeLapse.administracion.cambioPassword");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.grid.DataGrid");


dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Form");

dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");

dojo.requireLocalization("timeLapse","cambioPassword");
//Inicializo los textos
timeLapse.administracion.cambioPassword.textos = dojo.i18n.getLocalization("timeLapse","cambioPassword");



timeLapse.administracion.cambioPassword.init = function() {
	var contenedor = dijit.byId("principal");

	//Me traigo el jsp y lo pongo en el main	
	var contenido = timeLapse.utils.llamadas.recibir("jsp/administracion/cambioPassword.jsp");
	contenedor.attr('content',contenido);
		    
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("efectuarCambio"), "onclick",  timeLapse.administracion.cambioPassword.efectuarCambio);
	
		
}
/**
 * Función que llama al action que se encarga de realizar el cambio de contraseña
 * @return
 */
timeLapse.administracion.cambioPassword.efectuarCambio = function(){
	
	if(dijit.byId("myForm").isValid() || !timeLapse.utils.constantes.validacionesCliente){
		
		var correcto = true;
		
		var obj = dijit.byId('myForm').attr('value');
		
		if(obj.formPasswd1 != obj.formPasswd2){
			correcto = false;
			console.warn("No se repitió correctamente la contraseña");
			timeLapse.utils.dialogos.muestra(timeLapse.administracion.cambioPassword.textos.errorPasswordNoIguales);
		}
		
				
		var vaBien = {
		accion: function(datosRespuesta){
			
				timeLapse.administracion.cambioPassword.limpiaCampos();				
						
			
			}
		};	
		//Si las contraseñas son iguales intento realizar el cambio
		if(correcto){
			var objParametro = new Object();
			objParametro.formPasswd1 = obj.formPasswd1;
			objParametro.formAntiguo = obj.formAntiguo;
			
			timeLapse.utils.llamadas.llamadaAsinc("administracion/cambioPassword.action",objParametro, vaBien);			
			console.log("Se cambia la contraseña");	
		}
		
	}else{
		console.warn("El formulario de cambio de contraseña tiene errores");
		timeLapse.utils.dialogos.muestra(timeLapse.administracion.cambioPassword.textos.errorFormularioCambio);
	}
			
}
/**
 * Función que limpia los campos del formulario una vez realizado el cambio
 */
timeLapse.administracion.cambioPassword.limpiaCampos = function(){
	
	dijit.byId("formAntiguo").attr('value',"");
	dijit.byId("formPasswd1").attr('value',"");
	dijit.byId("formPasswd2").attr('value',"");
}
