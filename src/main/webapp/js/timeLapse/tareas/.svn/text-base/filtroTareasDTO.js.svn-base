dojo.provide("timeLapse.tareas.filtroTareasDTO");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.NumberSpinner");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dijit.form.DateTextBox");
dojo.require("dijit.form.TimeTextBox");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.DropDownButton");
dojo.require("dijit.TooltipDialog");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.Form");
dojo.require("dijit.form.Slider");

dojo.require("timeLapse.utils.llamadas");
dojo.require("timeLapse.utils.prototipos");
dojo.require("timeLapse.utils.limpieza");
dojo.require("timeLapse.utils.dialogos");
dojo.require("timeLapse.utils.formato");
dojo.require("timeLapse.utils.constantes");
dojo.require("timeLapse.tareas.adminTareas");



dojo.declare("timeLapse.tareas.filtroTareas", null, {
    constructor: function(recargadorGrid, tipoFiltro){
    	this.tipoFiltro = tipoFiltro;
		
        this.recargadorGrid = recargadorGrid;
        
        this.almacen = new Object();
        this.almacen.tareasRealizadas = timeLapse.utils.constantes.filtroTareas_excluir;
        this.almacen.temporizadas = timeLapse.utils.constantes.filtroTareas_incluir;
        this.almacen.tareasQueSonPadre = timeLapse.utils.constantes.filtroTareas_incluir;
        this.almacen.autorrealizables = timeLapse.utils.constantes.filtroTareas_incluir;
        this.almacen.periodicas = timeLapse.utils.constantes.filtroTareas_incluir;
        this.almacen.categorias = timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS;
        this.almacen.grupos = timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS;
        this.almacen.tareasPadres = timeLapse.utils.constantes.filtroTareas_VALOR_TODAS_EN_LISTAS;
        this.almacen.fechaPlanificacionInferior = null;
        this.almacen.fechaPlanificacionSuperior = null;
        this.almacen.fechaRealizacionInferior = null;
        this.almacen.fechaRealizacionSuperior = null;
        this.almacen.cadenaBusqueda = null;
        this.almacen.orden = timeLapse.utils.constantes.filtroTareas_ORDEN_IMPORTANCIA;
        this.propiedadesAtributos = new Object();
		switch(tipoFiltro) {
			case timeLapse.utils.constantes.filtroTareas_TIPO_TODAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};
				break;	
			case timeLapse.utils.constantes.filtroTareas_TIPO_HUERFANAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:false};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};
				break;
			case timeLapse.utils.constantes.filtroTareas_TIPO_HIJAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

		        this.almacen.tareasPadres = timeLapse.utils.constantes.filtroTareas_VALOR_ASOCIADAS_EN_LISTAS;
				break;				
			case timeLapse.utils.constantes.filtroTareas_TIPO_NO_TEMPORIZADAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:false};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:false};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;				
			case timeLapse.utils.constantes.filtroTareas_TIPO_TEMPORIZADAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:false};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;
				
			case timeLapse.utils.constantes.filtroTareas_TIPO_NO_CATEGORIZADAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:false};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;
				
			case timeLapse.utils.constantes.filtroTareas_TIPO_CATEGORIZADAS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

		        this.almacen.categorias = timeLapse.utils.constantes.filtroTareas_VALOR_ASOCIADAS_EN_LISTAS;

				break;
				
			case timeLapse.utils.constantes.filtroTareas_TIPO_SIN_GRUPOS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:false};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;
				
			case timeLapse.utils.constantes.filtroTareas_CON_GRUPOS:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:true};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

		        this.almacen.grupos = timeLapse.utils.constantes.filtroTareas_VALOR_ASOCIADAS_EN_LISTAS;

				break;
				
			case timeLapse.utils.constantes.filtroTareas_PARA_VINCULAR_USUARIO:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:false};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;
				
			case timeLapse.utils.constantes.filtroTareas_PARA_DESVINCULAR_USUARIO:
				this.propiedadesAtributos.tareasRealizadas = {idContainer:"elemTareasRealizadas",visible:true};
		        this.propiedadesAtributos.temporizadas = {idContainer:"elemTemporizadas",visible:true};
		        this.propiedadesAtributos.tareasQueSonPadre = {idContainer:"elemTareasQueSonPadre",visible:true};
		        this.propiedadesAtributos.autorrealizables = {idContainer:"elemAutorrealizables",visible:true};
		        this.propiedadesAtributos.periodicas = {idContainer:"elemPeriodicas",visible:false};
		        this.propiedadesAtributos.categorias = {idContainer:"elemCategorias",visible:true};
		        this.propiedadesAtributos.grupos = {idContainer:"elemGrupos",visible:false};
		        this.propiedadesAtributos.tareasPadres = {idContainer:"elemTareasPadres",visible:true};
		        this.propiedadesAtributos.fechaPlanificacion = {idContainer:"elemFechaPlanificacion",visible:true};        
		        this.propiedadesAtributos.fechaRealizacion = {idContainer:"elemFechaRealizacion",visible:true};        
		        this.propiedadesAtributos.orden = {idContainer:"elemOrden",visible:true};

				break;
				
				
		}
        
    },
    
    
    /**
     * Función que actualiza los valores del filtro
     */
    actualizaValoresFiltro: function(){
        //Leer el objeto
        var filtro = this.almacen;
        //Activar y desactivar campos según la "naturaleza de la búsqueda"
        
        //Actualizar campos con los valores del objeto
        dijit.byId("filtroTareasRealizadas").attr('value', filtro.tareasRealizadas);
        dijit.byId("filtroTemporizadas").attr('value', filtro.temporizadas);
        dijit.byId("filtroTareasQueSonPadre").attr('value', filtro.tareasQueSonPadre);
        dijit.byId("filtroAutorrealizables").attr('value', filtro.autorrealizables);
        dijit.byId("filtroPeriodicas").attr('value', filtro.periodicas);
        dijit.byId("filtroCategorias").attr('value', filtro.categorias);
        dijit.byId("filtroGrupos").attr('value', filtro.grupos);
        dijit.byId("filtroTareasPadres").attr('value', filtro.tareasPadres);
        dijit.byId("filtroFechaPlanificacionInf").attr('value', timeLapse.utils.formato.stringToDate(filtro.fechaPlanificacionInferior));
        dijit.byId("filtroFechaPlanificacionSup").attr('value', timeLapse.utils.formato.stringToDate(filtro.fechaPlanificacionSuperior));
        dijit.byId("filtroFechaRealizacionInf").attr('value', timeLapse.utils.formato.stringToDate(filtro.fechaRealizacionInferior));
        dijit.byId("filtroFechaRealizacionSup").attr('value', timeLapse.utils.formato.stringToDate(filtro.fechaRealizacionSuperior));
        if (filtro.orden == timeLapse.utils.constantes.filtroTareas_ORDEN_IMPORTANCIA) {
            dijit.byId("ordenImportancia").attr('checked', true);
            dijit.byId("ordenFecha").attr('checked', false);
        }
        else 
            if (filtro.orden == timeLapse.utils.constantes.filtroTareas_ORDEN_FECHA) {
                dijit.byId("ordenFecha").attr('checked', true);
                dijit.byId("ordenImportancia").attr('checked', false);
            }
        if (dijit.byId("filtroCadenaBusqueda") != undefined) {
            dijit.byId("filtroCadenaBusqueda").attr('value', filtro.cadenaBusqueda)
        }
        
        
    },
    /**
     * Función que carga el select de combos de los Grupos para el proceso de filtrado de las tareas
     */
    cargaFiltroGrupos: function(){
        console.log("Inicio carga Filtro Grupos");
        
		var cadUrl = null;
		if(this.tipoFiltro == timeLapse.utils.constantes.filtroTareas_CON_GRUPOS){
			cadUrl = "../grupos/listaGruposUtilizadosParaDesvincularGrupos.action";
		}else{
			cadUrl = "../grupos/listaGruposUtilizados.action";
		}	
        
        var selectStore = new dojo.data.ItemFileReadStore({
            url: cadUrl,
            clearOnClose: true,
            syncMode: true,
            urlPreventCache: true
        });
        var select = dijit.byId("filtroGrupos");
        
        select.store = selectStore;
        select.startup();
        
        console.log("Fin carga Filtro Grupos");
        
    },
    /**
     * Función que carga el select de combos de las Categorías para el proceso de filtrado de las tareas
     */
    cargaFiltroCategorias: function(){
        console.log("Inicio carga Filtro Categorias");
        
		var cadUrl = null;
		if(this.tipoFiltro == timeLapse.utils.constantes.filtroTareas_TIPO_CATEGORIZADAS){
			cadUrl = "../categorias/listaCategoriasUtilizadasParaDesvincularCategorias.action";
		}else{
			cadUrl = "../categorias/listaCategoriasUtilizadas.action";
		}		
		
        var selectStore = new dojo.data.ItemFileReadStore({
            url: cadUrl,
            clearOnClose: true,
            syncMode: true,
            urlPreventCache: true
        });
        var select = dijit.byId("filtroCategorias");
        
        select.store = selectStore;
        select.startup();
        
        console.log("Fin carga Filtro Categorias");
        
    },
    
    /**
     * Función que carga el select de combos de las Categorías para el proceso de filtrado de las tareas
     */
    cargaFiltroTareasPadre: function(){
        console.log("Inicio carga Filtro Tareas Padre");
		
        var cadUrl = null;
		if(this.tipoFiltro == timeLapse.utils.constantes.filtroTareas_TIPO_HIJAS){
			cadUrl = "../tareas/listaTareasQueSonPadreParaDesvincularTareas.action";
		}else{
			cadUrl = "../tareas/listaTareasQueSonPadre.action";
		}
		
        var selectStore = new dojo.data.ItemFileReadStore({
            url : cadUrl,
            clearOnClose: true,
            syncMode: true,
            urlPreventCache: true
        });
        var select = dijit.byId("filtroTareasPadres");
        
        select.store = selectStore;
        select.startup();
        
        
        console.log("Fin carga Filtro Tareas Padre");
        
    },
    /**
     * Función que se llama para hacer persistentes los cambios de los filtros
     */
    persisteCambiosFiltro: function(){
        console.log("Inicio de persistencia filtro");
        
        //Actualizar campos del timeLapse.tareas.adminTareas.filtroTareas
        var filtro = this.almacen;
        
        filtro.tareasRealizadas = dijit.byId("filtroTareasRealizadas").attr('value');
        filtro.temporizadas = dijit.byId("filtroTemporizadas").attr('value');
        filtro.tareasQueSonPadre = dijit.byId("filtroTareasQueSonPadre").attr('value');
        filtro.autorrealizables = dijit.byId("filtroAutorrealizables").attr('value');
        filtro.periodicas = dijit.byId("filtroPeriodicas").attr('value');
        filtro.categorias = dijit.byId("filtroCategorias").attr('value');
        filtro.grupos = dijit.byId("filtroGrupos").attr('value');
        filtro.tareasPadres = dijit.byId("filtroTareasPadres").attr('value');
        
        var valorFechaPlanificacionInf = dijit.byId("filtroFechaPlanificacionInf").attr('value');
        var valorFechaPlanificacionSup = dijit.byId("filtroFechaPlanificacionSup").attr('value');
        var valorFechaRealizacionInf = dijit.byId("filtroFechaRealizacionInf").attr('value');
        var valorFechaRealizacionSup = dijit.byId("filtroFechaRealizacionSup").attr('value');
        filtro.fechaPlanificacionInferior = valorFechaPlanificacionInf != null ? dojo.date.locale.format(valorFechaPlanificacionInf, {
            fullYear: true,
            selector: 'date',
            datePattern: "dd/MM/yyyy"
        }) : null;
        filtro.fechaPlanificacionSuperior = valorFechaPlanificacionSup != null ? dojo.date.locale.format(valorFechaPlanificacionSup, {
            fullYear: true,
            selector: 'date',
            datePattern: "dd/MM/yyyy"
        }) : null;
        filtro.fechaRealizacionInferior = valorFechaRealizacionInf != null ? dojo.date.locale.format(valorFechaRealizacionInf, {
            fullYear: true,
            selector: 'date',
            datePattern: "dd/MM/yyyy"
        }) : null;
        filtro.fechaRealizacionSuperior = valorFechaRealizacionSup != null ? dojo.date.locale.format(valorFechaRealizacionSup, {
            fullYear: true,
            selector: 'date',
            datePattern: "dd/MM/yyyy"
        }) : null;
        filtro.cadenaBusqueda = dijit.byId("filtroCadenaBusqueda") != undefined ? dijit.byId("filtroCadenaBusqueda").attr('value') : null;
        
        if (dijit.byId("ordenImportancia").attr('checked')) {
            filtro.orden = timeLapse.utils.constantes.filtroTareas_ORDEN_IMPORTANCIA;
        }
        else 
            if (dijit.byId("ordenFecha").attr('checked')) {
                filtro.orden = timeLapse.utils.constantes.filtroTareas_ORDEN_FECHA;
            }
        if(this.esCorrecto()){
			//LLamar al recarga grid
	        this.recargadorGrid();
	        
	        console.log("Fin de persistencia filtro");
	        if (dijit.byId("filtroPopUp").open) {
	            dijit.byId("filtroPopUp").hide();
	        }
		}
		
        
    },
    /**
     * Esconde 
     */
    muestraEscondeAtrubutos: function(){
		//Recorro los atributos a personalizar
		for (atributo in this.propiedadesAtributos){
			//Me dedico a esconder/mostrar
			if(this.propiedadesAtributos[atributo].visible){
				dojo.byId(this.propiedadesAtributos[atributo].idContainer).style.display = "";				
			}else{
				dojo.byId(this.propiedadesAtributos[atributo].idContainer).style.display = "none";
			}
			
			
			
		} 
	},
	esCorrecto: function(){
		var varFiltroTemporizadas = dijit.byId("filtroTemporizadas").attr('value');
		var varFiltroFechaPlanificacionInf = dijit.byId("filtroFechaPlanificacionInf").attr('value');
		var varFiltroFechaPlanificacionSup = dijit.byId("filtroFechaPlanificacionSup").attr('value');
		var varFiltroTareasRealizadas = dijit.byId("filtroTareasRealizadas").attr('value');
		var varFiltroFechaRealizacionInf = dijit.byId("filtroFechaRealizacionInf").attr('value');
		var varFiltroFechaRealizacionSup = dijit.byId("filtroFechaRealizacionSup").attr('value');
		var correcto = true;
		//Comprobar temporiazas
		if(correcto && (varFiltroTemporizadas == timeLapse.utils.constantes.filtroTareas_excluir)
				&& ((varFiltroFechaPlanificacionInf != "" && varFiltroFechaPlanificacionInf != null) 
					|| (varFiltroFechaPlanificacionSup != "" && varFiltroFechaPlanificacionSup != null) ) ){
			correcto = false;
			timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.temporiazadasIncoherentes);					
		}
		//Comprobar realizadas
		if(correcto && (varFiltroTareasRealizadas == timeLapse.utils.constantes.filtroTareas_excluir)
				&& ((varFiltroFechaRealizacionInf != "" && varFiltroFechaRealizacionInf != null) 
				|| (varFiltroFechaRealizacionSup != "" && varFiltroFechaRealizacionSup != null )) ){
			correcto = false;
			timeLapse.utils.dialogos.muestra(timeLapse.tareas.adminTareas.textos.realizadasIncoherentes);					
		}
		return correcto;		
	}
	
    
    
});
