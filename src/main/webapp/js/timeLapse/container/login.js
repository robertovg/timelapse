/**
 * @author robe
 */

//Importaciones
dojo.addOnLoad(init);
function init(){
	dojo.registerModulePath("timeLapse", "../js/timeLapse");
	console.log("Inicio carga la pantalla de Login y las dependencias de dojo");
	dojo.require("dojo.parser");
	
	
	dojo.require("dijit.layout.ContentPane");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.TextBox");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.ProgressBar");
	dojo.require("dojox.widget.Toaster");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.Menu");
	dojo.require("dijit.Tooltip");
	dojo.require("dijit.form.CheckBox");
	

	dojo.require("timeLapse.utils.efectos");
	dojo.require("timeLapse.utils.constantes");
	dojo.require("timeLapse.administracion.usuarios");
	dojo.parser.parse();
	
    //Conecto los botones a la funciones				
	dojo.connect(dojo.byId("botonEntrar"), "onclick",  timeLapse.administracion.usuarios.autentificarse);
	dojo.connect(dojo.byId("botonRegistrar"), "onclick",  timeLapse.administracion.usuarios.registarse);


	window.location.href = "#";    
	timeLapse.utils.efectos.intercambia("preloader", "cuerpoPrincipal");

	
	//hideLoader();
	console.log("Fin carga del Login");
}
