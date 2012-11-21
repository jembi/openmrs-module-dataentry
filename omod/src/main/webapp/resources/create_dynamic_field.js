function createDeleteButton(baseName){
		var deleteButton = $(document.createElement("span")).attr("id","delete_"+fieldGroupCount).attr("class","redbox").text("X");
		deleteButton.click(
			function()
			{
				var idString = $(this).attr("id");
				var selectorText = "#"+baseName+"tableid_"+idString.substring(idString.indexOf("_")+1);
				
				$(selectorText).hide(200,
						function()
						{
							$(selectorText).remove();
						}
				);
			}
		);
		return deleteButton;
	}

function addOptionsToSelect(selectElement, oIDispArray, oIValArray){
	selectElement.append($(document.createElement("option")).attr("value","").text(""));
	for( var j=0; j < oIDispArray.length; j++){
		selectElement.append($(document.createElement("option")).attr("value",oIValArray[j]).text(oIDispArray[j]));
	}
}

function createNakedOptionSelect(nameValue, oIDispArray,oIValArray, classAttr){
	var selectElement = $(document.createElement("select")).attr("id",fieldGroupCount).attr("class", classAttr).attr("name",nameValue);
	addOptionsToSelect(selectElement,oIDispArray,oIValArray);
	var tableRow = $(document.createElement("tr"))
			.append($(document.createElement("td")).append(selectElement));	

	return tableRow;
}


function createFamilyHIVSerologies(baseName, relDispArray, relValArray, resDispArray, resValArray, classAttr, name, surname, dateB, dateD, relation, result, resultDate){	
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	var relToPat = createNakedOptionSelect("rel_"+fieldGroupCount,relDispArray,relValArray,classAttr);
	
	var hivRes = createNakedOptionSelect("res_"+fieldGroupCount,resDispArray,resValArray,classAttr);
	
	
	//field for the results of treatment
	var nom = $(document.createElement("input")).attr("type","text").attr("name","nom_"+fieldGroupCount);
	var prenom = $(document.createElement("input")).attr("type","text").attr("name","pren_"+fieldGroupCount);
	var dob = $(document.createElement("input")).attr("type","text").attr("name","dob_"+fieldGroupCount).attr("size", "11").attr("class", "date").focus(function(e) { showCalendar(this); });
	var dod = $(document.createElement("input")).attr("type","text").attr("name","dod_"+fieldGroupCount).attr("size", "11").attr("class", "date").focus(function(e) { showCalendar(this); });
	var resDate = $(document.createElement("input")).attr("type","text").attr("name","resDate_"+fieldGroupCount).attr("size", "11").attr("class", "date").focus(function(e) { showCalendar(this); });
		
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding titles
	//if(counting == 0) {
	table.append($(document.createElement("thead"))
			.append($(document.createElement("tr"))
			.append($(document.createElement("th")).text(name))
			.append($(document.createElement("th")).text(surname))
			.append($(document.createElement("th")).text(relation))
			.append($(document.createElement("th")).text(dateB))
			.append($(document.createElement("th")).text(dateD))
			.append($(document.createElement("th")).text(result))
			.append($(document.createElement("th")).text(resultDate))
			.append($(document.createElement("th")).text())
			));
	//}
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","20").append(nom))
			.append($(document.createElement("td")).attr("width","20").append(prenom))
			.append($(document.createElement("td")).attr("width","13").append(relToPat))
			.append($(document.createElement("td")).attr("width","12").append(dob))
			.append($(document.createElement("td")).attr("width","12").append(dod))
			.append($(document.createElement("td")).attr("width","10").append(hivRes))			
			.append($(document.createElement("td")).attr("width","12").append(resDate))
			.append($(document.createElement("td")).append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
	//counting++;
	//alert(counting);
}


function createOpportunisticInfections(baseName, oIDispArray, oIValArray, classAttr, sysDispArray, sysValArray, resTtle, sDate, eDate, ois){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var optionSelectRow = createNakedOptionSelect("oi_"+fieldGroupCount,oIDispArray,oIValArray,classAttr);
	var optionSelectRowSys = createNakedOptionSelect("treatmentResult_"+fieldGroupCount,sysDispArray,sysValArray,classAttr); 
	//field for the results of treatment
	var OIStartDate = $(document.createElement("input")).attr("type","text").attr("name","OIStartDate_"+fieldGroupCount).attr("size", "11").attr("class", "date").focus(function(e) { showCalendar(this); });
	var OIEndDate = $(document.createElement("input")).attr("type","text").attr("name","OIEndDate_"+fieldGroupCount).attr("size", "11").attr("class", "date").focus(function(e) { showCalendar(this); });
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding titles
	table.append($(document.createElement("thead"))
			.append($(document.createElement("tr")).attr("align", "center")
			.append($(document.createElement("th")).text(ois).attr("width","30%"))
			.append($(document.createElement("th")).text(sDate).attr("width","15%"))
			.append($(document.createElement("th")).text(eDate).attr("width","15%"))
			.append($(document.createElement("th")).text(resTtle).attr("width","30%"))
			.append($(document.createElement("th")).attr("width","10%"))
			));
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "center")
			.append($(document.createElement("td")).attr("width","30%").append(optionSelectRow))
			.append($(document.createElement("td")).attr("width","15%").append(OIStartDate))
			.append($(document.createElement("td")).attr("width","15%").append(OIEndDate))
			.append($(document.createElement("td")).attr("width","30%").append(optionSelectRowSys))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}


function createSystems(baseName, displayArray, valueArray, sysDispArray, sysValArray, classAttr, name, comment, symLabel, sysCommentLabel){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var optionSelectRow = createNakedOptionSelect("sn_"+fieldGroupCount,displayArray,valueArray,classAttr);
	var symSelectRow = createNakedOptionSelect("sys_"+fieldGroupCount,sysDispArray,sysValArray,classAttr);
	
	//field for the results of treatment
	var systemFinding = createSystemFinding();
	//rows="5" cols="50"
	var sysComment = $(document.createElement("textArea")).attr("name","sysComment_"+fieldGroupCount).attr("rows","5").attr("cols","50");
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width", "8%").text(name))
			.append($(document.createElement("td")).attr("width", "20%").append(optionSelectRow)));
			
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width", "8%").text(comment))
			.append($(document.createElement("td")).attr("width", "20%").append(systemFinding)));
	
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width", "8%").text(symLabel))
			.append($(document.createElement("td")).attr("width", "20%").append(symSelectRow)));
	
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width", "8%").text(sysCommentLabel))
			.append($(document.createElement("td")).attr("width", "20%").append(sysComment))
			.append($(document.createElement("td")).attr("width", "8%").append(deleteButton))
			);
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}


function createVitalSign(baseName, displayArray, valueArray, classAttr, name, value){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var optionSelectRow = createNakedOptionSelect("vsn_"+fieldGroupCount,displayArray,valueArray,classAttr);
	
	//field for the results of treatment
	var vitalValue = $(document.createElement("input")).attr("type","text").attr("name","vsnValue_"+fieldGroupCount);
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	
	// adding titles
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","30%").text(name))
			.append($(document.createElement("td")).attr("width","30%").text(value))
			.append($(document.createElement("td")).attr("width","10%"))
			);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","30%").append(optionSelectRow))
			.append($(document.createElement("td")).attr("width","30%").append(vitalValue))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}


function createFamilyPlanning(baseName, displayArray, valueArray, classAttr, fpm){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var optionSelectRow = createNakedOptionSelect("fpm_"+fieldGroupCount,displayArray,valueArray,classAttr);
	
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(fpm))
			.append($(document.createElement("td")).attr("width","20%").append(optionSelectRow))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function createSurgery(baseName, classAttr, name, date){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	var surgeryDate = $(document.createElement("input")).attr("type","text").attr("name","surgeryDateValue_"+fieldGroupCount).attr("size", "11").focus(function(e) { showCalendar(this); });
	
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","7%").text(date))
			.append($(document.createElement("td")).attr("width","10%").append(surgeryDate))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function createSTI(baseName, displayArray, valueArray, classAttr, infection, date){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var sti = $(document.createElement("input")).attr("type","checkbox").attr("name","sti_"+fieldGroupCount).attr("value", "174");
	
	//field for the results of treatment
	var doi = $(document.createElement("input")).attr("type","text").attr("name","stiDate_"+fieldGroupCount).attr("class", "date").attr("size", "11").focus(function(e) { showCalendar(this); });
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","2%").text(infection))
			.append($(document.createElement("td")).attr("width","10%").append(sti))
			.append($(document.createElement("td")).attr("width","15%").text(date))
			.append($(document.createElement("td")).attr("width","10%").append(doi))
			.append($(document.createElement("td")).attr("width","71%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function createSystemFinding() {
	
	var selectOpt = $(document.createElement("select")).attr("name", "systemFinding_" + fieldGroupCount);
	var opt1 = $(document.createElement("option")).attr("value", "").text("");
	var opt2 = $(document.createElement("option")).attr("value", "1115").text("NORMAL");
	var opt3 = $(document.createElement("option")).attr("value", "1116").text("ABNORMAL");
	
	var selection = selectOpt.append(opt1).append(opt2).append(opt3);
	
	return selection;
}

function createAdverseEffect(baseName, displayArray, valueArray, classAttr, adverseEffect){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var effectList = createNakedOptionSelect("ae_"+fieldGroupCount,displayArray,valueArray,classAttr);
	
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(adverseEffect))
			.append($(document.createElement("td")).attr("width","20%").append(effectList))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function createOtherDrug(baseName, displayArray, valueArray, classAttr, otherDrug){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var drugList = createNakedOptionSelect("od_"+fieldGroupCount,displayArray,valueArray,classAttr);
	
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(otherDrug))
			.append($(document.createElement("td")).attr("width","20%").append(drugList))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function createAction(baseName, displayArray, valueArray, classAttr, action){
	// the containing table
	var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var actionList = createNakedOptionSelect("action_"+fieldGroupCount,displayArray,valueArray,classAttr);
	
	
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(action))
			.append($(document.createElement("td")).attr("width","20%").append(actionList))
			.append($(document.createElement("td")).attr("width","10%").append(deleteButton))
			);
	
	

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
}

function addWhoStage(baseName, stagesArray, classAttr, titleArr) {
var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");

	// designation selector
	var stages = createNakedOptionSelect("stages_"+fieldGroupCount,stagesArray[0],stagesArray[1],classAttr);
	
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(title))
			.append($(document.createElement("td")).attr("width","20%").append(stages))
			);

	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).html(table);
	table.show(200);
}

function createWhoStage(baseName, whosArray, stagesArray, classAttr, titleArr) {

var table = $(document.createElement("table")).attr("id",baseName+"tableid_" + ++fieldGroupCount).attr("width","100%");
	
	// designation selector
	var actionList = createNakedOptionSelect("whos_"+fieldGroupCount,whosArray[0], whosArray[1],classAttr);
	var who1 = createNakedOptionSelect("who1_"+fieldGroupCount,stagesArray[0][0],stagesArray[0][1],classAttr);
	var who2 = createNakedOptionSelect("who2_"+fieldGroupCount,stagesArray[1][0],stagesArray[1][1],classAttr);
	var who3 = createNakedOptionSelect("who3_"+fieldGroupCount,stagesArray[2][0],stagesArray[2][1],classAttr);
	var who4 = createNakedOptionSelect("who4_"+fieldGroupCount,stagesArray[3][0],stagesArray[3][1],classAttr);
	var tdWho = $(document.createElement("td")).attr("id","tdWho_"+fieldGroupCount).attr("width","20%");
	// delete button
	var deleteButton = createDeleteButton(baseName);
	
	// adding row fields
	table.append($(document.createElement("tr")).attr("align", "left")
			.append($(document.createElement("td")).attr("width","10%").text(titleArr))
			.append($(document.createElement("td")).attr("width","20%").append(actionList))
			.append(tdWho)
			.append($(document.createElement("td")).attr("width", "10%").append(deleteButton))
			);

	// add the line separator between tables
	table.append($(document.createElement("tr"))
			.append($(document.createElement("td")).attr("colspan","5")
					.append($(document.createElement("hr")).attr("color","#C9C9C9"))
					));	
	
	// add the entire set of elements to the div
	table.hide();
	$("#"+baseName).append(table);
	table.show(200);
	
	$("."+classAttr).change(function() {
		var idVal = this.id;
		var val = $("#"+idVal).val();
		if(val == '1204') {
			$("#tdWho_"+idVal).html("Who Stage 3 Adult ").html(who1);
		}
		if(val == '1205') {
			$("#tdWho_"+idVal).html("Who Stage 3 Adult ").html(who2);
		}
		if(val == '1206') {
			$("#tdWho_"+idVal).html("Who Stage 3 Adult ").html(who3);
		}
		if(val == '1207') {
			$("#tdWho_"+idVal).html("Who Stage 4 Adult ").html(who4);
		}
	});
	
}

/** a function which helps in validation to avoid that user input a future date **/
function CompareDates(dateFormat)
{
	var dt1 = null;
    var mon1 = null;
    var yr1 = null;
    var str2 = null;
    
	var str1 = document.getElementById("encDateId").value;
	$("#encDateId").datepicker('getDate');
    var now  = new Date();
    var nowDay = now.getDate().toString().length == 1 ? '0'+now.getDate() : now.getDate();
    var month = now.getMonth()+1;
    var nowMonth = month.toString().length == 1 ? '0' + month : month;
    var nowYear = now.getYear()+1900;
    
    //var str2 = nowDay + "/" + nowMonth + "/" + nowYear;
    
    //alert("str1: -" + str1 + "- str2: -" + str2 + "-");
    
    
	if(dateFormat=='dd/mm/yyyy' || dateFormat=='jj/mm/aaaa') { 
		str2 = nowDay + "/" + nowMonth + "/" + nowYear;
	    dt1  = parseInt(str1.substring(0,2),10);
	    mon1 = parseInt(str1.substring(3,5),10);
	    yr1  = parseInt(str1.substring(6,10),10);
	    dt2  = parseInt(nowDay);
	    mon2 = parseInt(nowMonth);
	    yr2  = parseInt(nowYear);
	} else if(dateFormat=='mm/dd/yyyy' || dateFormat=='mm/jj/aaaa') {
		str2 = nowMonth + "/" + nowDay + "/" +  nowYear;
		mon1  = parseInt(str1.substring(0,2),10);
	    dt1 = parseInt(str1.substring(3,5),10);
	    yr1  = parseInt(str1.substring(6,10),10);
	    mon2  = parseInt(nowMonth);
	    dt2 = parseInt(nowDay);
	    yr2  = parseInt(nowYear);
	} else{
		alert("Invalid date : "+dateFormat+": not supported !");
		$("#encDateId").val("");
		return;
	}
	var month1 = mon1 - 1;
	var month2 = mon2 - 1;
    var date1 = new Date(yr1, mon1, dt1);
    var date2 = new Date(nowYear, nowMonth, nowDay);
    if(date2 < date1)
    {
    	 $("#msgId").html("The date can't be in future");
    	 $("#msgId").addClass("error");
    	 $("#encDateId").val("");    	 
    }
    else
    {
    	$("#msgId").html("");
   	 	$("#msgId").removeClass("error");
    }
}
