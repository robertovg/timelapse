/**
 * @author robe
 */
dojo.provide("timeLapse.container.menu");
dojo.require("timeLapse.utils.semaforo");
dojo.require("timeLapse.utils.constantes");

timeLapse.container.menu.tareasEspecificas = null;
/**
 * Función que se encarga de ocultar la imagen que se muestra cuando se están cargando las
 * dependencias de dojo
 */
timeLapse.container.menu.hideLoader = function(){
    dojo.fadeOut({
        node: "preloader",
        duration: 700,
        onEnd: function(){
            dojo.style("preloader", "display", "none");
            
        }
    }).play();
}




/**
 * Función para cargar los botones de objetivos de la parte superior
 */
timeLapse.container.menu.cargaObjetivos = function(){
    console.log("Inicio Carga Objetivos en el Menu Superior");
    var cabecera = dojo.byId("cabecera");
    var vaBien = {
        accion: function(){
        
            var vaBien = {
                accion: function(datosResul){
                    //Limpio					
                    timeLapse.utils.limpieza.limpiaMenuSup();
                    
                    //Pido los datos
                    var datos = datosResul.objetivos;
                    if (datos != null) {
                    
                        //Por cada uno de los objetivos creo un nuevo Botón en el menú 
                        for (var i = 0; i < datos.length; i++) {
                            //Creo el nuevo Botón	
                            var nuevoBoton = new dijit.form.Button({
                                label: datos[i].nombre,
                                id: datos[i].oid,
                                onClick: timeLapse.container.menu.cargaFuncionalidades
                            });
                            //Lo añado a la cabecera 
                            cabecera.appendChild(nuevoBoton.domNode);
                            //Le añado el toolTip con la descripción																
                            var toolTip = new dijit.Tooltip({
                                connectId: [nuevoBoton.domNode],
                                label: datos[i].descripcion
                            });
                        }
                        
                        
                    }
                    
                }
            };
            timeLapse.utils.llamadas.llamadaAsinc("menu/objetivos.action", null, vaBien);
            
            
        }
    };
    //Oculto y lo hago aparecer de nuevo
    timeLapse.utils.efectos.recargaContenido(cabecera.id, vaBien);
    console.log("Fin Carga Objetivos en el Menu Superior");
}
/**
 * Función que carga el menú izquierdo dependiendo del botón del menú superior pulsado
 */
timeLapse.container.menu.cargaFuncionalidades = function(){
    var oid = timeLapse.utils.constantes.oidFuncionalidadTareas;
    if(this.id != undefined){
		oid = this.id;	
	}
	console.log("Inicio Carga funcionalidades del Objetivo: " + oid);
    var menuLateral = dijit.byId("menuLateral");
    	if(menuLateral != undefined){
    		menuLateral.destroyDescendants(true);
			menuLateral.destroy(true);
			
			
    	}
		var refNode2 = dojo.doc.createElement("span");
		menuLateral = new dijit.layout.AccordionContainer(null, refNode2.domNode);
		
  
     
    var parametros = {
        "oid": oid
    };
    var vaBien = {
        accion: function(){
            var vaBien2 = {
                accion: function(datosRespuesta){
                
                    
                    var datos = datosRespuesta.listFuncionalidad;
                    
                    //Creo los ContentPane por cada funcionlidad y link por accion.
                    var n = datos.length;
                    for (var i = 0; i < n; i++) {
                        var cad = "";
                        cad += "<ul>";
                        if (datos[i].tlpAccions.length == 0) {
                            cad += "<li>";
                            cad += "No existe ninguna acción para esta funcionalidad";
                            cad += "</li>";
                            
                        }
                        
                        for (var j = 0; j < datos[i].tlpAccions.length; j++) {
                            var accion = datos[i].tlpAccions[j];
                            cad += "<li>";
                            cad += "<a href='javascript:timeLapse.container.menu.cargaFuncionalidad(\"" + accion.path + "\");'>";
                            cad += accion.nombre;
                            cad += "</a>";
                            cad += "</li>";
                            
                        }
                        cad += "</ul>";
                        
                        var refNode = dojo.doc.createElement("span");
                        refNode.innerHTML = cad;
                        var nuevoAcordPane = new dijit.layout.ContentPane({
                            title: datos[i].nombre,
                            selected: i == n - 1,
                            content: refNode
                        });
                        
                        menuLateral.addChild(nuevoAcordPane);
                        
                        
                    }
					dijit.byId("cabMenuLateral").attr('content',menuLateral);
                    menuLateral.startup();
                    
                    console.log("Fin Carga funcionalidades del Objetivo rec");
                    
                }
            };
            timeLapse.utils.llamadas.llamadaAsinc("menu/funcionalidad.action", parametros, vaBien2);
            
        }
    };
    
	timeLapse.utils.efectos.recargaContenido("cabMenuLateral", vaBien);
	 //Limpio el menu lateral				
    timeLapse.utils.limpieza.limpiaMenuIzq();	
	console.log("Fin Carga funcionalidades del Objetivo ");
    
    
}
/**
 * Función que carga la parte principal de la aplicación según la funcionalidad
 * seleccionada
 * @param {Object} path
 */
timeLapse.container.menu.cargaFuncionalidad = function(path){
    console.log("Inicio Carga la funcionalidad: " + path);
    var principal = dijit.byId("principal");
	
	
    dijit.byId("popUpContentPane").attr('content', "");
	timeLapse.utils.semaforo.aumenta();

    
    var vaBien = {
        accion: function(){
        
            //Borro su contenido			
            var texto = "timeLapse." + path;
            dojo.require(texto);
            
            var array = path.split(".");
            var objetivo = timeLapse[array[0]];
            var accion = objetivo[array[1]];
            accion.init();
            timeLapse.utils.semaforo.disminuye();
        }
    };
    
    try {
    
        //Oculto y lo hago aparecer de nuevo
        timeLapse.utils.efectos.recargaContenido(principal.id, vaBien);
		//Limpio el Principal				
    	timeLapse.utils.limpieza.limpiaPrincipal();
        
    } 
    catch (err) {
        console.error("Ocurrio un error mientras se intentaba cargar la funcionalidad");
		timeLapse.utils.dialogos.muestra("Ocurrio un error mientras se intentaba cargar la funcionalidad");

        throw err;
    }
	console.log("Fin Carga la funcionalidad");
    
    
}
/**
 * 
 * @param {Object} path
 */
timeLapse.container.menu.cargaUsuarioRol = function(){
	console.log("Inicio Carga Usuario y Rol del Container.");
    
    
    var vaBien = {
        accion: function(){
            var vaBien2 = {
                accion: function(datosRespuesta){
					
                    var cadenaUsuario = datosRespuesta.usuario.nombre + " - " + datosRespuesta.usuario.rol.nombre;
                    dojo.byId("usuarioRol").innerHTML = cadenaUsuario;
					                   
                    
                }
            };
            timeLapse.utils.llamadas.llamadaAsinc("menu/usuarioRol.action", null, vaBien2);
            console.log("Fin Carga Usuario y Rol del Container.");
            
        }
		
    };
    
	timeLapse.utils.efectos.recargaContenido("pie", vaBien);

}
/**
 * Método encargado de cargar la página inicial de la aplicación.
 */
timeLapse.container.menu.cargaPaginaInicial = function(){
		
	var oid = timeLapse.utils.constantes.oidFuncionalidadTareas;   
	console.log("Inicio Carga funcionalidades del Objetivo: " + oid);
    var menuLateral = dijit.byId("menuLateral");
    	if(menuLateral != undefined){
    		menuLateral.destroyDescendants(true);
			menuLateral.destroy(true);
			
			
    	}
		var refNode2 = dojo.doc.createElement("span");
		menuLateral = new dijit.layout.AccordionContainer(null, refNode2.domNode);
		
  
     
    var parametros = {
        "oid": oid
    };
    var vaBien = {
        accion: function(){
            var vaBien2 = {
                accion: function(datosRespuesta){
                
                    
                    var datos = datosRespuesta.listFuncionalidad;
                    
                    //Creo los ContentPane por cada funcionlidad y link por accion.
                    var n = datos.length;
                    for (var i = 0; i < n; i++) {
                        var cad = "";
                        cad += "<ul>";
                        if (datos[i].tlpAccions.length == 0) {
                            cad += "<li>";
                            cad += "No existe ninguna acción para esta funcionalidad";
                            cad += "</li>";
                            
                        }
                        
                        for (var j = 0; j < datos[i].tlpAccions.length; j++) {
                            var accion = datos[i].tlpAccions[j];
                            cad += "<li>";
                            cad += "<a href='javascript:timeLapse.container.menu.cargaFuncionalidad(\"" + accion.path + "\");'>";
                            cad += accion.nombre;
                            cad += "</a>";
                            cad += "</li>";
                            
                        }
                        cad += "</ul>";
                        
                        var refNode = dojo.doc.createElement("span");
                        refNode.innerHTML = cad;
                        var nuevoAcordPane = new dijit.layout.ContentPane({
                            title: datos[i].nombre,
                            selected: i == n - 1,
                            content: refNode
                        });
                        
                        menuLateral.addChild(nuevoAcordPane);
                        
                        
                    }
					dijit.byId("cabMenuLateral").attr('content',menuLateral);
                    menuLateral.startup();
                    
                    console.log("Fin Carga funcionalidades del Objetivo rec");
					timeLapse.container.menu.cargaFuncionalidad("tareas.verProximasTareas");
                    
                }
				
            };
            timeLapse.utils.llamadas.llamadaAsinc("menu/funcionalidad.action", parametros, vaBien2);
            
        }
    };
    
	timeLapse.utils.efectos.recargaContenido("cabMenuLateral", vaBien);
	 //Limpio el menu lateral				
    timeLapse.utils.limpieza.limpiaMenuIzq();	
	console.log("Fin Carga funcionalidades del Objetivo ");
	
	
	
	
}



/**
 * Método encargado de mostrar la página de ayuda
 */
timeLapse.container.menu.muestraAyuda = function(){
	
	window.open("../images/ayudaTimeLapse.pdf","ayudaTimelapse",'resizable,scrollbars');
	
}