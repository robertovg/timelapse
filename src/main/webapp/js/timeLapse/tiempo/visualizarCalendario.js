/**
 * @author robe
 */
dojo.provide("timeLapse.tiempo.visualizarCalendario");
dojo.require("dojo.date.stamp");
dojo.require("dojo.cookie");

dojo.require("timeLapse.widget.Calendar");
dojo.require("timeLapse.widget.Timezones");

timeLapse.tiempo.visualizarCalendario.calendario = null;

timeLapse.tiempo.visualizarCalendario.init = function() {
	
	var contenedor = dijit.byId("principal");

	//Me traigo el jsp y lo pongo en el main
	var contenido = timeLapse.utils.llamadas.recibir("jsp/tiempo/visualizarCalendario.jsp"); 
	contenedor.attr('content',contenido);
	
	//Inicialización del Calendario
	timeLapse.tiempo.visualizarCalendario.calendario = dijit.byId("dojoCalendar");
	timeLapse.tiempo.visualizarCalendario.calendario.setTimeZones(timeLapse.widget.timezones);
	timeLapse.tiempo.visualizarCalendario.calendario.selectedtimezone = "Central European"; //dojo.cookie("DCTZ");
	timeLapse.tiempo.visualizarCalendario.calendario.onSetTimeZone = timeLapse.tiempo.visualizarCalendario.widgetTimeZoneChanged;
	timeLapse.tiempo.visualizarCalendario.calendario.changeEventTimes = true;
	timeLapse.tiempo.visualizarCalendario.calendario.onEventChanged = timeLapse.tiempo.visualizarCalendario.widgetEventChanged;
	timeLapse.tiempo.visualizarCalendario.calendario.setAbleToCreateNew(true);
	timeLapse.tiempo.visualizarCalendario.calendario.onNewEntry = timeLapse.tiempo.visualizarCalendario.widgetNewEntry;
	timeLapse.tiempo.visualizarCalendario.calendario.onValueChanged = timeLapse.tiempo.visualizarCalendario.widgetValueChanged;
	timeLapse.tiempo.visualizarCalendario.widgetValueChanged(new Date());
}
timeLapse.tiempo.visualizarCalendario.widgetValueChanged = function (dateObj){
	var d1s = new Date(dateObj);
	d1s.setDate(1);
	d1s.setHours(14,0,0,0);
	var d1e = new Date(dateObj);
	d1e.setDate(1);
	d1e.setHours(14,30,0,0);
	var d15s = new Date(dateObj);
	d15s.setDate(15);
	var d15e = new Date(dateObj);
	d15e.setDate(15);
	var d28s = new Date(dateObj);
	d28s.setDate(28);
	d28s.setHours(16,40,0,0);
	var d28e = new Date(dateObj);
	d28e.setDate(28);
	d28e.setHours(18,30,0,0);
	var entries = {
		"id1": {
			starttime: dojo.date.stamp.toISOString(d1s),
			endtime: dojo.date.stamp.toISOString(d1e),
			allday: false,
			repeated: false,
			title: "Title 1",
			url: "",
			body: "This is the body of entry with id: id1 and title: Title 1",
			attributes: {
				Location: "My Galactic Headquarters",
				Chair: "John Doe"
			},
			type: ["meeting","appointment"]
		},
		"id2": {
			starttime: dojo.date.stamp.toISOString(d15s),
			endtime: dojo.date.stamp.toISOString(d15e),
			allday: true,
			repeated: false,
			title: "Title 2",
			url: "",
			body: "This is the body of entry with id: id2 and title: Title 2",
			attributes: {
				Location: "Somewhere"
			},
			type: ["appointment","super"]
		},
		"id3": {
			starttime: dojo.date.stamp.toISOString(d28s),
			endtime: dojo.date.stamp.toISOString(d28e),
			allday: false,
			repeated: false,
			title: "Title 3",
			url: "",
			body: "This is the body of entry with id: id3 and title: Title 3",
			attributes: "",
			type: ["reminder"]
		}
	}
	timeLapse.tiempo.visualizarCalendario.calendario.setCalendarEntries(entries);
}

timeLapse.tiempo.visualizarCalendario.widgetEventChanged = function(eventId,eventObject){
	var sReturn = "id " + eventId + "=\n";
	for(var i in eventObject){
		if(typeof(eventObject[i]) != "object"){
			sReturn += i + " = " + eventObject[i] + "\n";
		}else{
			oChildObject = eventObject[i];
			var sChildReturn = "";
			var iNum = 0;
			for(var j in oChildObject){
				if(iNum > 0){
					sChildReturn += ", ";
				}
				sChildReturn += j + ": " + oChildObject[j];
				iNum++;
			}
			sReturn += i + " = " + sChildReturn + "\n";
		}
	}
	alert(sReturn);
	//Call script to update back-end db
	timeLapse.tiempo.visualizarCalendario.calendario.refreshScreen();
}

timeLapse.tiempo.visualizarCalendario.widgetNewEntry = function(eventObject){
	var sReturn = "";
	for(var i in eventObject){
		if(typeof(eventObject[i]) != "object"){
			sReturn += i + " = " + eventObject[i] + "\n";
		}else{
			oChildObject = eventObject[i];
			var sChildReturn = "";
			var iNum = 0;
			for(var j in oChildObject){
				if(iNum > 0){
					sChildReturn += ", ";
				}
				sChildReturn += j + ": " + oChildObject[j];
				iNum++;
			}
			sReturn += i + " = " + sChildReturn + "\n";
		}
	}
	alert(sReturn);
	//Call script to add to back-end db
	timeLapse.tiempo.visualizarCalendario.calendario.refreshScreen();
}

timeLapse.tiempo.visualizarCalendario.widgetTimeZoneChanged = function(){
	//Setting cookie
	if(timeLapse.tiempo.visualizarCalendario.calendario.selectedtimezone == ""){
		dojo.cookie("DCTZ", null, {expires: -1});
	}else{
		dojo.cookie("DCTZ",timeLapse.tiempo.visualizarCalendario.calendario.selectedtimezone, { expires: 3650 });
	}
}

/*function setLocale(sLocale){
	timeLapse.tiempo.visualizarCalendario.calendario.lang = sLocale;
	timeLapse.tiempo.visualizarCalendario.calendario._preInitUI(new Date(timeLapse.tiempo.visualizarCalendario.calendario.value));
}*/
