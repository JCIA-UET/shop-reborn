function dr(cb) {
	console.log("dr checked");
	document.getElementById("dr").checked = true;
	document.getElementById("mr").checked = false;
	document.getElementById("tr").checked = false;
	
	document.getElementById("day-selector").style.display = "";
	document.getElementById("month-selector").style.display = "";
	document.getElementById("year-selector").style.display = "";
}

function mr(cb) {
	document.getElementById("dr").checked = false;
	document.getElementById("mr").checked = true;
	document.getElementById("tr").checked = false;
	console.log("mr checked");
	
	document.getElementById("day-selector").style.display = "";
	document.getElementById("month-selector").style.display = "none";
	document.getElementById("year-selector").style.display = "";
}

function tr(cb) {
	document.getElementById("dr").checked = false;
	document.getElementById("mr").checked = false;
	document.getElementById("tr").checked = true;
	console.log("tr checked");
	
	document.getElementById("day-selector").style.display = "none";
	document.getElementById("month-selector").style.display = "none";
	document.getElementById("year-selector").style.display = "none";
}

function daysInMonth(year, month) {
    return (year == " " ? new Date(0, month, 0).getDate() : new Date(year, month, 0).getDate());
}


function setDay() {
	var year = document.getElementById("year");
    var month = document.getElementById("month");
    var day = document.getElementById("day");
    setDayList(year, month, day);
}

function setType() {
	var objMonth = document.getElementById("month");
	var objDay = document.getElementById("day");
	var objYear = document.getElementById("year");
	
	var value = objType.options[objType.selectedIndex].value;
	
	if(document.getElementById("dr").checked == true) {
		objDay.style.display = "";
		objMonth.style.display = "";
		objYear.style.display = "";
	}
	else if(document.getElementById("mr").checked == true) {
		objDay.style.display = "none";
		objMonth.style.display = "";
		objYear.style.display = "";
	}
	else if(document.getElementById("tr").checked == true) {
		objDay.style.display = "none";
		objMonth.style.display = "none";
		objYear.style.display = "none";
	}
}

function setDayList(objYear, objMonth, objDay) {
	var month = objMonth.options[objMonth.selectedIndex].value;
	console.log(month);
    var day = objDay.options[objDay.selectedIndex].value;
    console.log(day);
    var year = objYear.options[objYear.selectedIndex].value;
    console.log(year);
    
    if (day == " ") {
    	var days = ((month == " ") ? 31 : daysInMonth(year, month));
        	
    	objDay.options.length = 0;
        objDay.options[0] = new Option("Choose a day", " ");
        	
        for (var i = 1; i <= days; i++) {
        	objDay.options[i] = new Option(i, i);
        }	
    }
}
 
function setDayList(objYear, objMonth, objDay) {
	var month = objMonth.options[objMonth.selectedIndex].value;
    var day = objDay.options[objDay.selectedIndex].value;
    var year = objYear.options[objYear.selectedIndex].value;
    
    if (day == " ") {
    	var days = ((month == " ") ? 31 : daysInMonth(year, month));
        	
    	objDay.options.length = 0;
        objDay.options[0] = new Option("Choose a day", " ");
        	
        for (var i = 1; i <= days; i++) {
        	objDay.options[i] = new Option(i, i);
        }	
    }
}
 
window.onload = function() {
	document.getElementById('cal').onsubmit = function() {
		var checkedValue = function(){
			var cdr = document.getElementById("dr");
			var cmr = document.getElementById("mr");
			var ctr = document.getElementById("tr");
			if(cdr.checked == true) return oCheckBoxCdr.value;
			else if(cmr.checked == true) return oCheckBoxCmr.value;
			else if(ctr.checked == true) return oCheckBoxCtr.value;
		};
		console.log(checkedValue);
		var input = document.getElementById('action');
		input.value = checkedValue;
	};
}