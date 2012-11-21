<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:require privilege="Add Data entry Laboratory examinations" otherwise="/login.htm" redirect="/module/dataentry/labtest.form"/>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="/moduleResources/dataentry/dataentrystyle.css" />

<script type="text/javascript">
var $ = jQuery.noConflict();
// lab test arrays
var labTestOptions = new Array();
var labTestIds = new Array();
<c:forEach var="testOption" items="${labTestOptions}">
	labTestOptions.push("<c:out value="${testOption.value}"/>");
	labTestIds.push(<c:out value="${testOption.key}"/>);
</c:forEach>

// provider arrays
var providerOptions = new Array();
var providerIds = new Array();
<c:forEach var="provider" items="${providerOptions}">
	providerOptions.push("<c:out value="${provider.value}"/>");
	providerIds.push(<c:out value="${provider.key}"/>);
</c:forEach>

// location arrays
var locationOptions = new Array();
var locationIds = new Array();
<c:forEach var="location" items="${locationOptions}">
	locationOptions.push("<c:out value="${location.value}"/>");
	locationIds.push(<c:out value="${location.key}"/>);
</c:forEach>

// conceptid for the convset - will ajax this in a bit
var g_convsetId = <c:out value="${labTestConceptId}"/>;
var g_patientId = <c:out value="${patientId}"/>;

$(document).ready(
		function(){
			var height = $("#outerform").height();
			$("#outerform").height(height);
			//$("#outerform").css("z-index",2);
			// the labtest selection element
			var selectElement = $(document.createElement("select")).attr("name","labTestConceptId").attr("id","labtestconceptid");

			var providerSelect = $(document.createElement("select")).attr("name","provider").attr("id","providerid");
			var locationSelect = $(document.createElement("select")).attr("name","location").attr("id","locationid");
			
			// populate the selection list
			for( var j=0; j < labTestOptions.length; j++){
				selectElement.append($(document.createElement("option")).attr("value",labTestIds[j]).text(labTestOptions[j]));
			}

			// populate providers
			providerSelect.append($(document.createElement("option")).attr("value",-1).text("--"));
			for( var j=0; j < providerOptions.length; j++){
				providerSelect.append($(document.createElement("option")).attr("value",providerIds[j]).text(providerOptions[j]));
			}

			// populate locations
			locationSelect.append($(document.createElement("option")).attr("value",-1).text("--"));
			for( var j=0; j < locationOptions.length; j++){
				locationSelect.append($(document.createElement("option")).attr("value",locationIds[j]).text(locationOptions[j]));
			}

			
			// insert it into the typeofexamid td tag
			$("#typeofexamid").append(selectElement);

			$("#provider").append(providerSelect);

			$("#location").append(locationSelect);
			
			
			// set a handler to activate the date chooser
			$("#encDateId").focus(
				function(){
					showCalendar(this);
				}
			);
			$("#addtestid").click(
				function(){
					$("#labtestformid").show(200,
						function(){
							$("#addtestid").hide(200);
						});		
				}
			);

			$("#savelabtestbuttonid").click(
					function(){
						var date = $("#encDateId").val();
						var result = $("#resultid").val();
						var patientId = g_patientId;
						var convsetId = g_convsetId;
						var providerId =  $("#providerid").val();
						var locationId =  $("#locationid").val();
						var encounterType = "${encounterType}";
						var labTestConceptId = $("#labtestconceptid").val();
						saveLaboratoryTest(null,patientId,convsetId,null,date,locationId,labTestConceptId, result, providerId, encounterType);
						
						$('#resultid').val("");
						$('#encDateId').val("");
						$('#providerid').find('option:first').attr('selected', 'selected').parent('select');
						$('#locationid').find('option:first').attr('selected', 'selected').parent('select');
						$('#labtestconceptid').find('option:first').attr('selected', 'selected').parent('select');
			});
	});

function saveLaboratoryTest(url, patId, convId, provId, date, locId, conId, result,provId, encounterType){
	//********************whoah dude hard coded url wtf *********************
	$.get("${pageContext.request.contextPath}/module/dataentry/savelabtest.form", {
		patientId :patId,
		convsetId :convId,
		date :date,
		locationId :locId,
		conceptId :conId,
		result :result,
		providerId: provId,
		encounterType : encounterType
	}, function(data) {
		var date = $("date", data).text();
		var test = $("test", data).text();
		var result = $("result", data).text();

		var dateRow = $(document.createElement("tr")).append($(document.createElement("td")).text(date))
							.append($(document.createElement("td")).text(test))
								.append($(document.createElement("td")).text(result));
		$("#resulttableid").append(dateRow);
	}, 'xml');
}


// lets make the server do the work for us and produce test count count count count in xml
</script>
<a
	href="${pageContext.request.contextPath}/patientDashboard.form?patientId=${patient.patientId}"
	style="text-decoration: none;"><openmrs:portlet url="patientHeader"
	id="patientDashboardHeader" patientId="${patient.patientId}" /></a>
<div class="boxHeader"><h2><spring:message code="Laboratory Examination" /></h2></div>

<div class="box">
<div id = "outerform" >
	
	<div id = "labtestformid">
		<table>
			<tr>
			<td><spring:message code="dataentry.type" /></td>
				<td id="typeofexamid"></td>
			</tr>
			<tr>
				<td><spring:message code="dataentry.dateofExam" /></td>
				<td><input id="encDateId" name="dateOfExam" size="11" onchange="CompareDates('<openmrs:datePattern />');"/><span id="msgId"></span></td>
			</tr>
			<tr>
				<td><spring:message code="dataentry.provider" /></td>
				<td id="provider"></td>
			</tr>
			<tr>
				<td><spring:message code="dataentry.location" /></td>
				<td id = "location"></td>
			</tr>
			<tr>
				<td><spring:message code="dataentry.result" /></td>
				<td><input id="resultid" name="result" size="8"/></td>
			</tr>
			<tr>
				<td><button id="savelabtestbuttonid" class="send" >Save Lab Test</button></td>
			</tr>
		</table>
	</div>
</div>

</div>
<!-- 	<p id="addtestid" class="hiddengreenbox"><spring:message code="dataentry.addTest" /></p> --> 

<br/>

<form:form commandName="laboratoryTest">
	<fieldset><legend ><spring:message code="dataentry.labTests" /></legend>
	<table id="resulttableid">
		
	</table>
	<input type = "hidden" name="encountertype" value="${encounterType}"/>
	</fieldset>
</form:form>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>
