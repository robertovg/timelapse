/**
 * @author robe
 */
dojo.provide("timeLapse.utils.formato");
dojo.require("dojo.date.locale");
dojo.require("timeLapse.utils.constantes");


/**
 * Función que se encarga de realizar formateo de datos personalizdos de fechas
 * @param {Object} idFiltro
 * @param {Object} idGrid
 * @param {Object} parametro
 */
timeLapse.utils.formato.stringToDate = function(value) {

     
	 var resul = null;
	 if(value != null){
	 	var cadValue = value.toString();
		var reDiaHora = new RegExp("^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9]:[0-9][0-9]");
		var reDia = new RegExp("^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
		var reHora = new RegExp("^[0-9][0-9]:[0-9][0-9]");

		if(cadValue.match(reDiaHora)){
			resul = dojo.date.locale.parse(cadValue, {formatLength: "short", locale: "es"});	
		}else if(cadValue.match(reDia)){
			resul = dojo.date.locale.parse(cadValue, {selector: "date" ,formatLength: "short", locale: "es"});
		}else if(cadValue.match(reHora)){
			resul = dojo.date.locale.parse(cadValue, {timePattern: "HH:mm", selector: "time"});
		}
	 }
	 
	 
	 return resul;
 }
/**
 * Función utilizada por el grid para formatear los valores de las fechas
 * @param {Object} value
 */ 
timeLapse.utils.formato.dateToGrid = function(value){
	if(value != null && value != ""){
		return dojo.date.locale.format(value);
	}else{
		return null;
	}
}
/**
 * Función utilizada por el grid para formatear los valores de los textos acortando
 * @param {Object} value
 */ 
timeLapse.utils.formato.stringToGrid = function(value){
	if(value != null && value != ""){
		if(value.length > timeLapse.utils.constantes.maximosCaracteresPorCadena){			
			value = value.substring(0,timeLapse.utils.constantes.maximosCaracteresPorCadena) + "...";
		}
		
		return value
	}else{
		return "-";
	}
}
/**
 * Función utilizada por el grid para formatear los valores de los textos, sin acortar
 * @param {Object} value
 */ 
timeLapse.utils.formato.stringToGridSinCortar = function(value){
	if(value != null && value != ""){
		return value
	}else{
		return "-";
	}
}
/**
 * Función utilizada por el grid para formatear los valores booleanos
 * @param {Object} value
 */ 
timeLapse.utils.formato.booleanToGrid = function(value){
	var resul = "-";
	if(value != null && value != ""){
		if(value == "true"){
			resul = "Si";
		}else if(value == "false"){
			resul = "No";
		}
	}
	return resul;
}
/**
 * Transforma las fechas del grid a fechas que se admiten por los formularios
 * @param {Object} value
 */
timeLapse.utils.formato.gridToForm = function(value){
	var resul = null;
	if(value != null && value[0] != null){
		var cadFecha = value.toString();
		
		var mesDiaAnnio = cadFecha.substr(4,11);
		var cadMes = mesDiaAnnio.substr(0,3);
		var mes = null;
		switch(cadMes) {
			case 'Jan':
				mes = 1;
				break;
			case 'Feb':
				mes = 2;
				break;
			case 'Mar':
				mes = 3;
				break;
			case 'Apr':
				mes = 4;
				break;
			case 'May':
				mes = 5;
				break;
			case 'Jun':
				mes = 6;
				break;
			case 'Jul':
				mes = 7;
				break;
			case 'Aug':
				mes = 8;
				break;
			case 'Sep':
				mes = 9;
				break;
			case 'Oct':
				mes = 10;
				break;
			case 'Nov':
				mes = 11;
				break;
			case 'Dec':
				mes = 12;
			
				
			
		}

		var dia = parseInt(mesDiaAnnio.substr(4,2));
		var anio = parseInt(mesDiaAnnio.substr(7,4));
		/*		
		resul = dojo.date.locale.parse(mesDiaAnnio, {selector: "date" ,datePattern: "MMM dd yyyy"});*/
		resul = new Date(anio,mes -1,dia);
		
	}
	return resul;
	
}

timeLapse.utils.formato.typeMap = {
                  "Date": {
                             type: Date,
                             deserialize: function(value){
                                 return timeLapse.utils.formato.stringToDate(value);
                             }
                          }
              };



