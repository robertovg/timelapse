dojo.provide("timeLapse.widget.Calendar");
dojo.require("dojo.cldr.supplemental");
dojo.require("dojo.date");
dojo.require("dojo.date.locale");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dojox.layout.FloatingPane");
dojo.require("timeLapse.widget.CalendarDialog");
dojo.require("dojo.date.stamp");
dojo.require("dojo.date");
dojo.require("dojo.dnd.Source");
dojo.require("dojo.dnd.Manager");
dojo.require("dijit.Menu");
//dojo.require("dojo.cookie");

dojo.declare(
	"timeLapse.widget.Calendar",
	[dijit._Widget, dijit._Templated],
	{	
		// value: String|Date
		// value property of displayed date
		value: "", 
		// calendarType: String
		// which type of calendar to render first. month, week or day
		calendarType: 'month',
		// m_WeekStartsOn: Integer
		// adjusts the first day of the week in month display 0==Sunday..6==Saturday
		m_WeekStartsOn: "",
		// w_WeekStartsOn: Integer
		// adjusts the first day of the week in week display 0==Sunday..6==Saturday
		w_WeekStartsOn: 1,
		// ShowWeekNo: Bolean
		// if we should use week numbers on week display
		ShowWeekNo: false,
		// selectedtimezone: Object
		// Timezone used. See timeLapse.widget.Timezones
		selectedtimezone: "",
		// timezones: Array
		// Array of all timezones. See timeLapse.widget.Timezones
		timezones: "",
		// eventtypes: Object
		// The different types of events with title and src url.
		eventtypes: {
			meeting: {title: "Meeting", src: dojo.moduleUrl("timeLapse.widget", "templates/calimages/meeting.gif")},
			reminder: {title: "Reminder", src: dojo.moduleUrl("timeLapse.widget", "templates/calimages/reminder.gif")},
			appointment: {title: "Appointment", src: dojo.moduleUrl("timeLapse.widget", "templates/calimages/appointment.gif")}
		},
		
		calendarEvents: {},
		
		changeEventTimes: false,
		createNewEntries: false,
		
		DragObject: "",
		DropObject: "",
		templatePath: dojo.moduleUrl("timeLapse.widget", "templates/Calendar.html"),
		templateCssPath: dojo.moduleUrl("timeLapse.widget", "templates/Calendar.css"),
		postMixInProperties: function(){		
		    this.inherited(arguments);
			//timeLapse.widget.Calendar.superclass.postMixInProperties.apply(this, arguments);
			
			if(!this.m_WeekStartsOn){
				this.m_WeekStartsOn = dojo.cldr.supplemental.getFirstDayOfWeek(this.lang);
			}
			
			// Localized month names in the template
			this.monthLabels = dojo.date.locale.getNames('months', 'wide', 'standAlone', this.lang);
			// Localized day names in the template
			var m_DayLabels = dojo.date.locale.getNames('days', 'wide', 'standAlone', this.lang);
			if(this.m_WeekStartsOn > 0){
				//adjust dayLabels for different first day of week. ie: Monday or Thursday instead of Sunday
				for(var i=0;i<this.m_WeekStartsOn;i++){
					m_DayLabels.push(m_DayLabels.shift());
				}
			}
			this.m_DayLabels = m_DayLabels;
			
			this.today = new Date();
			
		},
		
		postCreate: function(){
    		this.inherited(arguments);
    		this.createMonthMenu();
    		this._preInitUI(this.today);
    	},
		
		fillInTemplate: function(args, frag) {
		    this.inherited(arguments);
			//timeLapse.widget.Calendar.superclass.fillInTemplate.apply(this, arguments);
			// Copy style info from input node to output node
			var source = this.getFragNodeRef(frag);
			dojo.copyStyle(this.domNode, source);
			this._preInitUI(this.today);
		},
		
		_preInitUI: function(dateObj) {
            while (this.calendarHeadNode.hasChildNodes()) this.calendarHeadNode.removeChild(this.calendarHeadNode.firstChild);
            while (this.calendarBodyNode.hasChildNodes()) this.calendarBodyNode.removeChild(this.calendarBodyNode.firstChild);
            while (this.calendarFootNode.hasChildNodes()) this.calendarFootNode.removeChild(this.calendarFootNode.firstChild);
			this.value = new Date(dateObj);
			this.firstDay = this._initFirstDay(dateObj);
			this._setLabels();
			
			if(this.calendarType=='month'){
				this._initMonthUI();
			}else if(this.calendarType=='week'){
				this._initWeekUI();
			}else if(this.calendarType=='day'){
				this._initDayUI();
			}
			
			this.onValueChanged(new Date(dateObj));
		},
		
		refreshScreen: function() {
			this._preInitUI(new Date(this.value));
		},
		
		removeElement: function(id) {
                var elem = dijit.byId(id);
                
                if (!elem)
                        return;
                //if (!elem.parentNode)
                //        return;
                //elem.parentNode.removeChild(elem);
                elem.destroyRecursive();
        },
		
		onSetCalendarEntries: function() {
			var hasTimeZone = false;
			if(typeof this.selectedtimezone !== "string" && this.selectedtimezone !== null){
				hasTimeZone = true;
			}
			
			var allDay = false;
			var startDate, endDate, rStartTime, oDateObject, oLI, oSpan, sHTML, src, oAttributes, iAttr;
			var oDiv, toolTip, oToolTip, tooltipArgs, oImgDiv;
			var currentDatestr,prevDatestr;
			prevDatestr='';
			for(var i in this.calendarEvents){
				allDay = this.calendarEvents[i].allday;
				
				startDate = dojo.date.stamp.fromISOString(this.calendarEvents[i].starttime);
				if(!allDay && hasTimeZone){
					startDate = this.setTZDate(startDate);
				}
				currentDatestr=dojo.date.stamp.toISOString(startDate,{selector:'date'});
				if (currentDatestr!=prevDatestr){
				    oDateObject = dojo.byId(currentDatestr);
				    prevDatestr = currentDatestr;
				}
				if(oDateObject){

					endDate = dojo.date.stamp.fromISOString(this.calendarEvents[i].endtime);
					if(!allDay && hasTimeZone){
						endDate = this.setTZDate(endDate);
					}
					oLI = document.createElement('li');
					dojo.addClass(oLI, "listItem");
					if(allDay){
						dojo.addClass(oLI, "listItem allDayEvent");
					}
					oLI.setAttribute("starttime", Number(startDate));
					oLI.setAttribute("endtime", Number(endDate));
					if(oDateObject.childNodes.length > 0){
						if(allDay){
						    dojo.place(oLI,oDateObject,'first');
						}else{
							insertedLI = false;
							for(var r=0; r<oDateObject.childNodes.length; r++) {
								rStartTime = oDateObject.childNodes[r].getAttribute("starttime");
								if(Number(endDate) <= rStartTime || Number(startDate) <= rStartTime){
								    dojo.place(oLI,oDateObject.childNodes[r],'before');
									insertedLI = true;
									break;
								}
							}
							if(!insertedLI){
							    dojo.place(oLI,oDateObject,'first');
							}
						}
					}else{
					    dojo.place(oLI,oDateObject,'first');
					}
					oToolTip = document.createElement('div');
                    var id = "toolTip" + i;
                    this.removeElement(id); // ensure the id is free, in case there were errors earlier
                    oToolTip.id = id;

					oImgDiv = document.createElement('div');
					oToolTip.appendChild(oImgDiv);
					
					if(this.calendarType!='month'){
						oSpan = document.createElement('span');
					}
					for(var t=0; t<this.calendarEvents[i].type.length; t++) {
						if(this.eventtypes[this.calendarEvents[i].type[t]]){
							oImage = document.createElement("img");
							oImage.setAttribute("title", this.eventtypes[this.calendarEvents[i].type[t]].title);
							oImage.setAttribute("src", this.eventtypes[this.calendarEvents[i].type[t]].src);
							if(this.calendarType!='month'){
								oSpan.appendChild(oImage);
								oLI.appendChild(oSpan);
							}
							oImgDiv.appendChild(oImage.cloneNode(true));
						}
					}
					
					oDiv = document.createElement('div');
					dojo.addClass(oDiv, "toolkittime");
					sDate = dojo.date.locale.format(startDate, {formatLength:"medium", selector:"date", locale:this.lang}) + "<br />";
					sStart = sHTML = sEnd = '';
					if(!allDay){
						oSpan = document.createElement('span');
						if(!this.calendarEvents[i].repeated && this.changeEventTimes){
							dojo.addClass(oSpan, "timetext");
						}
						sStart = dojo.date.locale.format(startDate, {formatLength:"short", selector:"time", locale:this.lang});
						sHTML = '';
						sHTML += ' - ';
						sHTML += dojo.date.locale.format(endDate, {formatLength:"short", selector:"time", locale:this.lang});
						sEnd = (hasTimeZone?" (" + unescape(this.selectedtimezone.sn) + ")":"");
						
						oSpan.innerHTML = this.calendarType!='month'&&Number(startDate)!=Number(endDate)?sStart+sHTML:sStart;
						oLI.appendChild(oSpan);
					}
					oDiv.innerHTML = sDate + sStart + (Number(startDate)!=Number(endDate)?sHTML:"") + sEnd;
					oToolTip.appendChild(oDiv);
						
					oDiv = document.createElement('div');
					dojo.addClass(oDiv, "toolkittitle");
					oDiv.innerHTML = this.calendarEvents[i].title;
					oToolTip.appendChild(oDiv);
					if(this.calendarEvents[i].body !== ""){
						oDiv = document.createElement('div');
						dojo.addClass(oDiv, "toolkitbody");
						oDiv.innerHTML = this.calendarEvents[i].body;
						oToolTip.appendChild(oDiv);
					}
					
					oLI.setAttribute("itemid", i);
					oSpan = document.createElement('span');
					dojo.addClass(oSpan, "titletext");
					
					sHTML = this.calendarEvents[i].title;
					if(this.calendarEvents[i].url !== ''){
						sHTML = '<a href="' + this.calendarEvents[i].url + '" target="_blank">' + this.calendarEvents[i].title + '</a>';
					}
					oSpan.innerHTML = sHTML;
					
					sHTMLA = '';
					oAttributes = this.calendarEvents[i].attributes;
					iAttr = 0;
					for(var a in oAttributes) {
						if(iAttr > 0){
							sHTMLA += '<br />';
						}
						sHTMLA += a + ': ' + oAttributes[a];
						iAttr++;
					}
					if(sHTMLA != ""){
						oDiv = document.createElement('div');
						dojo.addClass(oDiv, "toolkitattributes");
						oDiv.innerHTML = sHTMLA;
						oToolTip.appendChild(oDiv);
					}
					
					oLI.appendChild(oSpan);
					oSpan.id = "toolTip" + i;
					if(!this.calendarEvents[i].repeated && this.changeEventTimes){
						new dojo.dnd.Source(oLI, {accept: ["dragListDates"],copyOnly: false});
					}
					var tooltipD = new dijit.TooltipDialog({
                        title: "tooltip title"
                    }, oToolTip);
                    tooltipD.startup();
                    oLI.tooltip=tooltipD;
                    oLI.cal=this;
                    dojo.connect(dijit.byId(id), '_onBlur', function(){dijit.popup.close(this);});
                    dojo.connect(oLI, 'onclick',function () {dijit.popup.open({parent: this.cal,
                        popup: this.tooltip,
                        around: this,
                        orient: {'BL':'TL', 'BR':'TR', 'TL':'BL', 'TR':'BR'}
                        });
                    });
				}
			}
		},
		
		setCalendarEntries: function(entriesObj) {
			
		//	Example:
		//	entriesObj: {
		//		"id1": (String - Unique identifier of event) {
		//			starttime: "2006-12-30T08:05:00-06:00", (String - Formatted according to RFC 3339. See dojo.date.serialize)
		//			endtime: "2006-12-30T10:05:00-06:00", (String - Formatted according to RFC 3339. See dojo.date.serialize)
		//			allday: false, (Boolean - Is event an all day event)
		//			title: "Title 1", (String - Event title)
		//			url: "http://yourdomain.com/events/thisevent", (String - Event URL (if any))
		//			body: "This is the body", (String - Event body text (if any))
		//			attributes: {Location:"Location 1",Chair:"John Doe"}, (Object - All attributes you want in name value pairs)
		//			type: ["meeting","reminder"] (Array - Event/Icon types you want for this event. See "eventtypes")
		//		}
		//	}
			
			if(entriesObj != "" && typeof entriesObj=="string"){
				entriesObj = dojo.json.evalJson(entriesObj);
			}
			if(entriesObj){
				this.calendarEvents = entriesObj;
				this.onSetCalendarEntries();
			}
		},
		
		setTimeZones: function(timezoneObj) {
			if(timezoneObj != "" && typeof timezoneObj=="string"){
				timezoneObj = dojo.json.evalJson(timezoneObj);
			}
			if(timezoneObj){
				dojo.addClass(this.timezoneLabelNode, "selecticon timezoneicon");
				this.timezones = timezoneObj;
			}
		},
		
		_initMonthUI: function() {
			var nextDate = new Date(this.firstDay);
			this.curMonth = new Date(nextDate);
			this.curMonth.setDate(nextDate.getDate()+6); //first saturday gives us the current Month
			this.curMonth.setDate(1);
			var displayWeeks = Math.ceil((dojo.date.getDaysInMonth(this.curMonth) + this._getAdjustedDay(this.curMonth,this.m_WeekStartsOn))/7);
 			var oLabelsTR = this.calendarHeadNode.insertRow(-1);
			var oLabelsTD;
			for(var i=0; i<7; i++) {
				oLabelsTD = oLabelsTR.insertCell(-1);
				oLabelsTD.innerHTML = this.m_DayLabels[i];
			}
			
			var oTR, oTD, oDateDiv, oItemDiv;
			for(var week = 0; week < displayWeeks; week++){
				oTR = this.calendarBodyNode.insertRow(-1);
				oTR.valign = 'top';
				for (var day = 0; day < 7; ++day) {
					oTD = oTR.insertCell(-1);
					var currentClassName = (nextDate.getMonth()<this.value.getMonth())?'otherMonth':(nextDate.getMonth()==this.value.getMonth())?'currentMonth':'otherMonth';
					if(dojo.date.stamp.toISOString(nextDate,{selector:'date'}) == dojo.date.stamp.toISOString(this.today,{selector:'date'})){
						currentClassName = currentClassName + " " + "currentDate";
					}
					dojo.addClass(oTD, currentClassName);
					
					oDateDiv = document.createElement("div");
					dojo.addClass(oDateDiv, "clickDate");
					oDateDiv.setAttribute("date", dojo.date.stamp.toISOString(nextDate,{selector:'date'}));
					dojo.connect(oDateDiv, "onclick", this, "onDateClicked");
					oDateDiv.innerHTML = nextDate.getDate();
					
					oTD.appendChild(oDateDiv);
					oItemDiv = document.createElement("div");
					dojo.addClass(oItemDiv, "calendarItems");
					var oUL = document.createElement("ul");
					oUL.id = dojo.date.stamp.toISOString(nextDate,{selector:'date'});
					dojo.addClass(oUL, "listItems");
					oItemDiv.appendChild(oUL);
					var dt = new dojo.dnd.Source(oUL,{accept: ["dragListDates"],horizontal: false,copyOnly: false});
					dojo.connect(dt, "onDrop", this, this._dropFunction);
					oTD.appendChild(oItemDiv);
					
					nextDate = dojo.date.add(nextDate, "day", 1);
				}
			}
		},
		
		_initWeekUI: function() {
			function createDateContent(tdObject,dateObj,that){
				var oDateDiv = document.createElement("div");
				dojo.addClass(oDateDiv, "clickDate weekDate");
				oDateDiv.setAttribute("date", dojo.date.stamp.toISOString(dateObj,{selector:'date'}));
				dojo.connect(oDateDiv, "onclick", that, "onDateClicked");
				oDateDiv.innerHTML = dateObj.getDate();
				tdObject.appendChild(oDateDiv);
				var oMonthDiv = document.createElement("div");
				dojo.addClass(oMonthDiv, "weekMonth");
				sHTML = dojo.date.locale.format(dateObj, {datePattern:"EEEE", selector:"date", locale:that.lang}) + '<br />';
				sHTML += dojo.date.locale.format(dateObj, {datePattern:"MMMM yyyy", selector:"date", locale:that.lang});
				oMonthDiv.innerHTML = sHTML;
				tdObject.appendChild(oMonthDiv);
				var oItemDiv = document.createElement("div");
				dojo.addClass(oItemDiv, "calendarItems");
				var oUL = document.createElement("ul");
				oUL.id = dojo.date.stamp.toISOString(dateObj,{selector:'date'});
				dojo.addClass(oUL, "listItems");
				oItemDiv.appendChild(oUL);
				var dt = new dojo.dnd.Source(oUL,{accept: ["dragListDates"],horizontal: false,copyOnly: false});
				dojo.connect(dt, "onDrop", that, this._dropFunction);
				
				tdObject.appendChild(oItemDiv);
			}
			
			var nextDate = new Date(this.firstDay);
			var oTR, oTD;
			for (var r = 0; r < 4; ++r) {
				oTR = this.calendarBodyNode.insertRow(-1);
				if(r < 3){
					oTD = oTR.insertCell(-1);
					var currentClassName = "weekDay currentMonth";
					if(dojo.date.stamp.toISOString(nextDate,{selector:'date'}) == dojo.date.stamp.toISOString(this.today,{selector:'date'})){
						currentClassName += " " + "currentDate";
					}
					dojo.addClass(oTD, currentClassName);
					if(r == 2){
						oTD.rowSpan = 2;
					}
					createDateContent(oTD,nextDate,this);
					nextDate = dojo.date.add(nextDate, "day", 3);
				}
				oTD = oTR.insertCell(-1);
				currentClassName = "weekDay currentMonth";
				if(dojo.date.stamp.toISOString(nextDate,{selector:'date'}) == dojo.date.stamp.toISOString(this.today,{selector:'date'})){
					currentClassName += " " + "currentDate";
				}
				dojo.addClass(oTD, currentClassName);
				createDateContent(oTD,nextDate,this);
				if(r == 2){
					nextDate = dojo.date.add(nextDate, "day", 1);
				}else{
					nextDate = dojo.date.add(nextDate, "day", -2);
				}
			}
		},
		
		_initDayUI: function() {
			function createDateContent(tdObject,dateObj,that){
				var oDateDiv = document.createElement("div");
				dojo.addClass(oDateDiv, "weekDate");
				oDateDiv.innerHTML = dateObj.getDate();
				tdObject.appendChild(oDateDiv);
				var oMonthDiv = document.createElement("div");
				dojo.addClass(oMonthDiv, "weekMonth");
				sHTML = dojo.date.locale.format(dateObj, {datePattern:"EEEE", selector:"date", locale:that.lang}) + '<br />';
				sHTML += dojo.date.locale.format(dateObj, {datePattern:"MMMM yyyy", selector:"date", locale:that.lang});
				oMonthDiv.innerHTML = sHTML;
				tdObject.appendChild(oMonthDiv);
				var oItemDiv = document.createElement("div");
				dojo.addClass(oItemDiv, "calendarItems");
				
				var oUL = document.createElement("ul");
				oUL.id = dojo.date.stamp.toISOString(dateObj,{selector:'date'});
				dojo.addClass(oUL, "listItems");
				oItemDiv.appendChild(oUL);
				var dt = new dojo.dnd.Source(oUL);
				dojo.connect(dt, "onDrop", that, this._dropFunction);
				
				tdObject.appendChild(oItemDiv);
			}
			
			var nextDate = new Date(this.firstDay);
			var oTR, oTD;
			oTR = this.calendarBodyNode.insertRow(-1);
			oTD = oTR.insertCell(-1);
			var currentClassName = "currentMonth";
			if(dojo.date.stamp.toISOString(nextDate,{selector:'date'}) == dojo.date.stamp.toISOString(this.today,{selector:'date'})){
				currentClassName += " " + "currentDate";
			}
			dojo.addClass(oTD, currentClassName);
			createDateContent(oTD,nextDate,this);
		},
		
		getValue: function() {
			// summary: return current date in RFC 3339 format
			return dojo.date.stamp.toISOString(new Date(this.value),{selector:'date'}); //String
		},

		getDate: function() {
			// summary: return current date as a Date object
			return this.value; //Date
		},
		
		onValueChanged: function(dateObj) {
			//summary: function to overide event by end user
		},
		
		onEventChanged: function(eventId, eventObject) {
			//summary: function to overide event by end user
		},
		
		_eventChanged: function(changed,eventId,startTime,endTime) {
			if(changed && this.calendarEvents[eventId]){
				//Change the event time and date
				//var oObject = this.calendarEvents[eventId];
				//oObject.starttime = this.updateToISOString(startTime);
				//oObject.endtime = this.updateToISOString(endTime);
				this.calendarEvents[eventId].starttime = this.updateToISOString(startTime);
				this.calendarEvents[eventId].endtime = this.updateToISOString(endTime);
				
				
				this.onEventChanged(eventId, this.calendarEvents[eventId]);
				//this.calendarEvents[eventId] = null;
				//this.onEventChanged(eventId, oObject);
			}
			if(!changed){
				this.refreshScreen();
			}
		},
		
		onMoveToDate: function(evt) {
			evt.stopPropagation();
			var d = new Date();
			this.moveToDate(d);
		},
		
		moveToDate: function(dateObj) {
			//summary: move to date dateObj and update the UI
			if(typeof dateObj=="string"){
				this.value = dojo.date.stamp.fromISOString(dateObj);
			}else{
				this.value = new Date(dateObj);
			}
			this._preInitUI(this.value);
		},
		
		onSetCalendarType: function(evt) {
			evt.stopPropagation();
			switch(evt.currentTarget) {
				case this.dayLabelNode:
					this.setCalendarType('day');
					break;
					
				case this.weekLabelNode:
					this.setCalendarType('week');
					break;
					
				case this.monthLabelNode:
					this.setCalendarType('month');
					break;
			}
		},
		
		onDateClicked: function(evt) {
			var eventTarget = evt.target;
			evt.stopPropagation();
            evt.preventDefault();
            dojo.stopEvent(evt);
			this.value = dojo.date.stamp.fromISOString(eventTarget.getAttribute("date"));
			this.setCalendarType('day');
		},
		
		setCalendarType: function(sType) {
			this.calendarType = sType;
			var d = new Date(this.value);
			this._preInitUI(d);
		},
		
		toProperCase: function(sString) {
			var stringArray = sString.split(" ");
			var retString = "";
			for(var i=0;i<stringArray.length;i++){
				if(i > 0){
					retString += " ";
				}
				retString += stringArray[i].charAt(0).toUpperCase() + stringArray[i].substring(1,stringArray[i].length).toLowerCase();
			}
			return retString;
		},
		
		_setLabels: function() {
			var d = new Date(this.value);
			var currentMonthLabel = this.monthLabels[d.getMonth()];
			var currentYearLabel = d.getFullYear();
			
			var prevDate,nextDate,prevLabel,nextLabel;
			var lookup = dojo.date.locale._getGregorianBundle(this.lang);
			if(this.calendarType=='month'){
				prevDate = dojo.date.add(d, "month", -1);
				nextDate = dojo.date.add(d, "month", 1);
				prevLabel = dojo.date.locale.format(prevDate, {datePattern:"MMM yyyy", selector:"date", locale:this.lang});
				nextLabel = dojo.date.locale.format(nextDate, {datePattern:"MMM yyyy", selector:"date", locale:this.lang});
			}else if(this.calendarType=='week'){
				d = new Date(this.firstDay);
				var end = dojo.date.add(d, "day", 6);
				if(d.getMonth() != end.getMonth()){
					currentMonthLabel = this.monthLabels[d.getMonth()] + " - " + this.monthLabels[end.getMonth()];
				}
				if(d.getFullYear() != end.getFullYear()){
					currentYearLabel = d.getFullYear() + " - " + end.getFullYear();
				}
				prevDate = dojo.date.add(d, "week", -1);
				nextDate = dojo.date.add(d, "week", 1);
				if(this.ShowWeekNo){
					var prevWeekNo = dojo.date.getWeekOfYear(prevDate, this.w_WeekStartsOn) + 1;
					var currentWeekNo = dojo.date.getWeekOfYear(d, this.w_WeekStartsOn) + 1;
					var nextWeekNo = dojo.date.getWeekOfYear(nextDate, this.w_WeekStartsOn) + 1;
					var fieldWeek = lookup["field-week"];
					prevLabel = fieldWeek + " " + prevWeekNo;
					nextLabel = fieldWeek + " " + nextWeekNo;
					currentLabel = fieldWeek + " " + currentWeekNo + " - " + currentLabel;
				}else{
					prevLabel = dojo.date.locale.format(prevDate, {formatLength:"medium", selector:"date", locale:this.lang});
					nextLabel = dojo.date.locale.format(nextDate, {formatLength:"medium", selector:"date", locale:this.lang});
				}
			}else if(this.calendarType=='day'){
				d = new Date(this.firstDay);
				prevDate = dojo.date.add(d, "day", -1);
				nextDate = dojo.date.add(d, "day", 1);
				prevLabel = dojo.date.locale.format(prevDate, {formatLength:"medium", selector:"date", locale:this.lang});
				nextLabel = dojo.date.locale.format(nextDate, {formatLength:"medium", selector:"date", locale:this.lang});
			}
			
			this.prevLabelNode.innerHTML = prevLabel;
			this.currentMonthLabelNode.innerHTML = currentMonthLabel;
			this.currentYearLabelNode.innerHTML = currentYearLabel;
			this.nextLabelNode.innerHTML = nextLabel;
			
			//Top icons
			this.dayLabelNode.title = this.toProperCase(lookup["field-day"]);
			this.weekLabelNode.title = this.toProperCase(lookup["field-week"]);
			this.monthLabelNode.title = this.toProperCase(lookup["field-month"]);
			this.todayLabelNode.title = this.toProperCase(dojo.date.locale.format(this.today, {formatLength:"long", selector:"date", locale:this.lang}));
			
			if(this.createNewEntries){
				dojo.addClass(this.newEntryLabelNode, "selecticon newentryicon");
			}else{
				dojo.addClass(this.newEntryLabelNode, "");
			}
			
			if(this.timezones != ""){
				dojo.addClass(this.timezoneLabelNode, "selecticon timezoneicon");
				if(typeof this.selectedtimezone !== "string" && this.selectedtimezone !== null){
					this.timezoneLabelNode.title = this.toProperCase(lookup["field-zone"]) + ": " + unescape(this.selectedtimezone.sn);
					var oTR = document.createElement('tr');
					var oTD = document.createElement('td');
					oTD.colSpan = "6";
					oTD.style.paddingLeft = "3px";
					oTD.innerHTML = this.toProperCase(lookup["field-zone"]) + ": " + this.selectedtimezone.name;
					oTR.appendChild(oTD);
					this.calendarFootNode.appendChild(oTR);
				}else{
					this.timezoneLabelNode.title = this.toProperCase(lookup["field-zone"]);
				}
			}else{
				dojo.addClass(this.timezoneLabelNode, "");
			}
		},
		
		menuItemSelected: function(type, value){
			var d = new Date(this.value);
			if(type == 'month'){
				d = d.setMonth(value);
				var newDate = new Date(d);
				if(newDate.getMonth() != value){
					var days = dojo.date.getDaysInMonth(new Date(newDate.getFullYear(), newDate.getMonth()-1));
					d = new Date(newDate.getFullYear(), newDate.getMonth()-1, days);
				}
			}else if(type == 'year'){
				d = d.setFullYear(value);
			}
			this.moveToDate(d);
		},
		createMonthMenu: function() {
		    var sWidgetId = this.id;
            pMenu = new dijit.Menu({targetNodeIds:[this.currentMonthLabelNode], id:"monthMenu",leftClickToOpen:true});
            for (var i in this.monthLabels){
                pMenu.addChild( new dijit.MenuItem({label:this.monthLabels[i],month:i,onClick:function(){ dijit.byId(sWidgetId).menuItemSelected('month',this.month);}}));
            }
            pMenu.startup();
            
        },
		
		dayOfWeek: function(day,month,year) {
			var a = Math.floor((14 - month)/12);
			var y = year - a;
			var m = month + 12*a - 2;
			var d = (day + y + Math.floor(y/4) - Math.floor(y/100) + Math.floor(y/400) + Math.floor((31*m)/12)) % 7;
			return d + 1;
		},
		
		NthDay: function(nth,weekday,month,year) {
			if (nth > 0){
				return (nth-1)*7 + 1 + (7 + weekday - this.dayOfWeek((nth-1)*7 + 1,month,year))%7;
			}
			var days = dojo.date.getDaysInMonth(new Date(year,month));
			return days - (this.dayOfWeek(days,month,year) - weekday + 7)%7;
		},
		
		isDST: function(dateObject) {
			if(this.selectedtimezone.dst === 0){
				return false;
			}else{
				var year = dateObject.getFullYear();
				var aDST = this.selectedtimezone.dst.split(',');
				var aStandard = this.selectedtimezone.standard.split(',');
				var startMonth = aDST[0];
				var startNumber = aDST[1];
				var startDayOfWeek = aDST[2];
				var endMonth = aStandard[0];
				var endNumber = aStandard[1];
				var endDayOfWeek = aStandard[2];
				var startDST = new Date(year,startMonth-1,this.NthDay(startNumber,startDayOfWeek,startMonth,year),2,dateObject.getTimezoneOffset()+this.selectedtimezone.offset);
				var endDST = new Date(year,endMonth-1,this.NthDay(endNumber,endDayOfWeek,endMonth,year),2,dateObject.getTimezoneOffset()+this.selectedtimezone.offset);
				if(Number(startDST) < Number(endDST)){
					if(Number(dateObject) > Number(startDST) && Number(dateObject) < Number(endDST)){
						return true;
					}else{
						return false;
					}
				}else{
					endDST = new Date(year+1,endMonth-1,this.NthDay(endNumber,endDayOfWeek,endMonth,year+1),2,dateObject.getTimezoneOffset()+this.selectedtimezone.offset);
					if(Number(dateObject) > Number(startDST) && Number(dateObject) < Number(endDST)){
						return true;
					}else{
						return false;
					}
				}
			}
		},
		
		setTZDate: function(dateObject) {
			var DSTOffset = this.isDST(dateObject)?3600000:0;
			var utc = dateObject.getTime() + (dateObject.getTimezoneOffset() * 60000);
			return new Date(utc + (this.selectedtimezone.offset*60000) + DSTOffset);
		},
		
		setAbleToCreateNew: function (bAble) {
			this.createNewEntries = bAble;
			if(bAble){
				dojo.addClass(this.newEntryLabelNode, "selecticon newentryicon");
			}
		},
		
		createNewEntry: function (evt) {
			evt.stopPropagation();
			if(dijit.byId('newentrydialog')){
				dijit.byId('newentrydialog').show();
			}else{
				var width = "460px";
				var height = "350px";
				var div = document.createElement("div");
				div.style.position="absolute";
				div.style.width = width;
				div.style.height = height;
				//dojo.body().appendChild(div);
				var pars = {
					openerId: this.id,
					title: "New Entry",
					iconSrc: dojo.moduleUrl("timeLapse.widget","templates/calimages/calendar_add.gif"),
					id: "newentrydialog",
					width: width,
					height: height,
					resizable: false
				};
				var widget = new timeLapse.widget.CalendarDialogNewEntry(pars, div);
				dijit.byId('newentrydialog').show();
			}
		},
		
		onNewEntry: function(oEntry) {
			//summary: function to overide event by end user
		},
		
		_createNewEntry: function(oEntry) {
			this.onNewEntry(oEntry);
			var d = new Date(this.value);
			this._preInitUI(d);
		},
		
		showTimeZone: function (evt) {
			evt.stopPropagation();
			if(dijit.byId('timezonedialog')){
				dijit.byId('timezonedialog').show();
			}else{
				if(this.timezones != ""){
					var lookup = dojo.date.locale._getGregorianBundle(this.lang);
					var width = "445px";
					var height = "130px";
					var div = document.createElement("div");
					div.style.position="absolute";
					div.style.width = width;
					div.style.height = height;
					//dojo.body().appendChild(div);
					var pars = {
						openerId: this.id,
						title: this.toProperCase(lookup["field-zone"]),
						iconSrc: dojo.moduleUrl("timeLapse.widget","templates/calimages/timezone_icon.png"),
						id: "timezonedialog",
						width: width,
						height: height,
						resizable: false
					};
					var widget = new timeLapse.widget.CalendarDialogTimezone(pars, div);
					dijit.byId('timezonedialog').show();
				}
			}
		},
		
		onSetTimeZone: function() {
			//summary: function to overide event by end user
		},
		
		_setTimeZone: function(shortname) {
			if(shortname == ''){
				this.selectedtimezone = {name:"Central European", sn:"Western/Central%20Europe", offset:60, dst:"3,-1,1", standard:"10,-1,1"};
			}else{
				for(var i=0;i<this.timezones.length;i++){
					if(this.timezones[i].sn == shortname){
						this.selectedtimezone = this.timezones[i];
						break;
					}
				}
			}
			this.onSetTimeZone();
			var d = new Date(this.value);
			this._preInitUI(d);
		},
		
		updateToISOString: function(dateObject){
			var _ = dojo.string.pad;
			var formattedDate = [];
			var date = [_(dateObject.getFullYear(),4), _(dateObject.getMonth()+1,2), _(dateObject.getDate(),2)].join('-');
			formattedDate.push(date);
				
			var time = [_(dateObject.getHours(),2), _(dateObject.getMinutes(),2), _(dateObject.getSeconds(),2)].join(':');
			var timezoneOffset = dateObject.getTimezoneOffset();
			if(typeof this.selectedtimezone !== "string" && this.selectedtimezone !== null){
				timezoneOffset = -this.selectedtimezone.offset;
			}
			time += (timezoneOffset > 0 ? "-" : "+") + _(Math.floor(Math.abs(timezoneOffset)/60),2) + ":" + _(Math.abs(timezoneOffset)%60,2);
			formattedDate.push(time);
			
			return formattedDate.join('T'); // String
		},
		
		_dropFunction: function(evt){
			evt.stopPropagation();
			try{
				this.DragObject = evt.dragObject.domNode;
				this.DropObject = evt.dropTarget;
				if(this.DropObject.tagName == 'LI'){
					this.DropObject = this.DropObject.parentNode;
				}
				
				var eventId = this.DragObject.getAttribute("itemid");
				if(this.calendarEvents[eventId].allday){
					var starttime = new Date(parseInt(this.DragObject.getAttribute("starttime")));
					var endtime = new Date(parseInt(this.DragObject.getAttribute("endtime")));
					var dropDate = dojo.date.stamp.fromISOString(this.DropObject.getAttribute("id"));
					starttime.setFullYear(dropDate.getFullYear());
					starttime.setMonth(dropDate.getMonth());
					starttime.setDate(dropDate.getDate());
					endtime.setFullYear(dropDate.getFullYear());
					endtime.setMonth(dropDate.getMonth());
					endtime.setDate(dropDate.getDate());
					this._eventChanged(true, eventId, starttime, endtime);
				}else{
					var width = "300px";
					var height = "290px";
					var div = document.createElement("div");
					div.style.position="absolute";
					div.style.width = width;
					div.style.height = height;
					dojo.body().appendChild(div);
					var pars = {
						//contentClass: "timeLapse:CalendarDialogChangeTime",
						openerId: this.id,
						title: "Change",
						iconSrc: dojo.moduleUrl("timeLapse.widget","templates/calimages/timezone_icon.png"),
						width: width,
						height: height,
						resizable: false
					};
					var widget = new timeLapse.CalendarDialog(pars, div);
				}
			}catch(e){}
		},
		
		onIncrementCalendar: function(evt) {
			evt.stopPropagation();
			var d = new Date(this.value);
			switch(evt.currentTarget) {
				case this.nextLabelNode:
					d = dojo.date.add(d, this.calendarType, 1);
					break;
				case this.prevLabelNode:
					d = dojo.date.add(d, this.calendarType, -1);
					break;
			}
			this._preInitUI(d);
		},
		
		_initFirstDay: function(dateObj){
			//adj: false for first day of month, true for first day of week adjusted by startOfWeek
			var d = new Date(dateObj);
			if(this.calendarType=='month'){
				d.setDate(1);
				d.setDate(d.getDate()-this._getAdjustedDay(d,this.m_WeekStartsOn));
			}else if(this.calendarType=='week'){
				d.setDate(d.getDate()-this._getAdjustedDay(d,this.w_WeekStartsOn));
			}
			d.setHours(0,0,0,0);
			return d; // Date
		},
		
		_getAdjustedDay: function(dateObj,startsOn){
			//summary: used to adjust date.getDay() values to the new values based on the current first day of the week value
			var days = [0,1,2,3,4,5,6];
			if(startsOn>0){
				for(var i=0;i<startsOn;i++){
					days.unshift(days.pop());
				}
			}
			return days[dateObj.getDay()]; // Number: 0..6 where 0=Sunday
		},
		
		destroy: function(){
		    this.inherited(arguments);
			dojo.destroyNode(this.m_WeekTemplate);
		}
	}
);