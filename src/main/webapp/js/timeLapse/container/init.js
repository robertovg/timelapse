/**
 * @author robe
 */

//Importaciones
dojo.addOnLoad(init);
/**
 * Función para inicializar los eventos y constantes
 */
function init(){
    dojo.registerModulePath("timeLapse", "../js/timeLapse");
    console.log("Se inicia la aplicación y se cargan las dependencias de dojo");
    dojo.require("dojo.parser");
    
	dojo.require("dijit.form.TextBox");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dijit.layout.BorderContainer");
    dojo.require("dijit.layout.AccordionContainer");
    dojo.require("dijit.layout.ContentPane");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.Menu");
    dojo.require("dijit.Tooltip");
    dojo.require("dijit.Dialog");
    dojo.require("dijit.ProgressBar");
	
	dojo.require("dojox.widget.Toaster");
    
    dojo.require("timeLapse.utils.llamadas");
    dojo.require("timeLapse.utils.constantes");
    dojo.require("timeLapse.utils.limpieza");
    dojo.require("timeLapse.utils.efectos");

    dojo.require("timeLapse.container.menu");
	dojo.require("timeLapse.administracion.usuarios");
	
    console.log("Se carga la estructura de la aplicación");
    dojo.parser.parse();
    dojo.connect(dojo.byId("salir"), "onclick", timeLapse.administracion.usuarios.cerrarSesion);
    dojo.connect(dojo.byId("inicio"), "onclick", timeLapse.container.menu.cargaPaginaInicial);
	dojo.connect(dojo.byId("ayuda"), "onclick", timeLapse.container.menu.muestraAyuda);
	
    timeLapse.container.menu.cargaObjetivos();	
	timeLapse.container.menu.cargaUsuarioRol();
	timeLapse.container.menu.cargaPaginaInicial();	
    timeLapse.container.menu.hideLoader();
	window.location.href = "#";
    
}


