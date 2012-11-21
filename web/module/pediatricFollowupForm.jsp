<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:require privilege="View Data Entry" otherwise="/login.htm" redirect="/module/dataentry/pediatricFollowup.form"/>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="/moduleResources/dataentry/dataentrystyle.css" />
<openmrs:htmlInclude file="/moduleResources/dataentry/validator.js" />
<openmrs:htmlInclude file="/moduleResources/dataentry/jquery.validate.js" />
<openmrs:htmlInclude file="/moduleResources/dataentry/create_dynamic_field.js" />

<script type="text/javascript">
var $ = jQuery.noConflict();

var patientIdParameter = <c:out value="${patient.patientId}"/>

var fieldGroupCount = 0;
function fgc() {
	return fieldGroupCount;
}



// Opportunistic Infection options
var oiArray = new Array();
var oiIdArray = new Array();
<c:forEach var="answer" items="${conceptAnswers}">
	oiArray.push("<c:out value="${answer.answerConcept.name.name}"/>");
	oiIdArray.push("<c:out value="${answer.answerConcept.conceptId}"/>");
</c:forEach> 

// Systems options
var sysArray = new Array();
var sysIdArray = new Array();
<c:forEach var="system" items="${systems}">
	sysArray.push("<c:out value="${system.answerConcept.name.name}"/>");
	sysIdArray.push("<c:out value="${system.answerConcept.conceptId}"/>");
</c:forEach>

var vSIdArray = new Array();
var vSArray = new Array();

<c:forEach var="vs" items="${vitalSigns}">
	vSIdArray.push("<c:out value="${vs.concept.conceptId}"/>");
	vSArray.push("<c:out value="${vs.concept.name}"/>");
</c:forEach>

//Family Planning
var fpArray = new Array();
var fpIdArray = new Array();
<c:forEach var="fp" items="${familyPlannings}">
	fpArray.push("<c:out value="${fp.answerConcept.name.name}"/>");
	fpIdArray.push("<c:out value="${fp.answerConcept.conceptId}"/>");
</c:forEach>

//Sexually Transmitted Infection
var stiArray = new Array();
var stiIdArray = new Array();
<c:forEach var="sti" items="${stiList}">
	stiArray.push("<c:out value="${sti.answerConcept.name.name}"/>");
	stiIdArray.push("<c:out value="${sti.answerConcept.conceptId}"/>");
</c:forEach>
    
//Result of Treatment
var rtArray = new Array();
var rtIdArray = new Array();
<c:forEach var="rt" items="${treatmentResults}">
	rtArray.push("<c:out value="${rt.answerConcept.name.name}"/>");
	rtIdArray.push("<c:out value="${rt.answerConcept.conceptId}"/>");
</c:forEach>

//symptome present
var symArray = new Array();
var symIdArray = new Array();
<c:forEach var="sym" items="${presentSymptom}">
	symArray.push("<c:out value="${sym.answerConcept.name.name}"/>");
	symIdArray.push("<c:out value="${sym.answerConcept.conceptId}"/>");
</c:forEach>    
	/************************************************************************** 
	*	function for adding a set of form object
	***************************************************************************/
	
       
	
	$(document).ready(
			function(){			
				$("input").focus(function () {
			         $(this).css('background-color','#abcdef');
			    });

				$("input").blur(function () {
			         $(this).css('background-color','white');
			    });
				
				$("#addOIid").click(
					function(){
						createOpportunisticInfections("oppInf",oiArray,oiIdArray,"dynamicOppInf", rtArray, rtIdArray, "<spring:message code="dataentry.treatRes" />", "<spring:message code="dataentry.oppInfSD" />", "<spring:message code="dataentry.oppInfED" />", "<spring:message code="dataentry.oppInf" />")
					}
				);

				$("#addSysId").click(
						function(){
							createSystems("systems", sysArray, sysIdArray, symArray, symIdArray, "dynamicSystems", "<spring:message code="dataentry.sysName" />", "<spring:message code="dataentry.sysFind" />", "<spring:message code="dataentry.presSymptom" />", "<spring:message code="dataentry.sysComment" />");
						}
					)

				$("#addVSId").click(
						function(){
							createVitalSign("vitalSigns", vSArray, vSIdArray, "dynamicVS", "<spring:message code="dataentry.vsName" />", "<spring:message code="dataentry.vsValue" />");
						}
					);

				$("#addFPId").click(
						function(){
							createFamilyPlanning("familyPlannings", fpArray, fpIdArray, "dynamicFP", "<spring:message code="dataentry.fpMethod" />");
						}
					);
				
				$("#addSurgeryId").click(
						function(){
							createSurgery("surgeries", "dynamicSurgery", "<spring:message code="dataentry.surgeName" />", "<spring:message code="dataentry.surgeDate" />");
						}
					);
				
				$("#addSTIId").click(
						function(){
							createSTI("stiList", stiArray, stiIdArray, "dynamicSTI", "<spring:message code="dataentry.sti" />", "<spring:message code="dataentry.doi" />");
						}
					);

				$('#pedRet').salidate({
					'encDate' : {
		            callback: 'required',
		            msg: 'Required!'
		        },
		        'encProviderId' : {
		            callback: 'required',
		            msg: 'Required!'
		        },
		        'encLocationId' : {
		            callback: 'required',
		            msg: 'Required!'
		        }
		    });
			
		}
);

</script>

<a
	href="${pageContext.request.contextPath}/patientDashboard.form?patientId=${patient.patientId}"
	style="text-decoration: none;"><openmrs:portlet
	url="patientHeader" id="patientDashboardHeader"
	patientId="${patient.patientId}" /></a>

<div id="adultdiv">
<form method="post" id="pedRet"
	action="pediatricFollowup.form?patientId=${patient.patientId}&pedRet=on"><input type="hidden"
	name="patientId" value="${patient.patientId}" />

<fieldset><legend><spring:message code="dataentry.location" /></legend>
<table cellspacing="20px">
	<tr>
		<td><spring:message code="dataentry.province" /></td>
		<td><input type="text" name="province"
			value="${patient.personAddress.stateProvince}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.district" /></td>
		<td><input type="text" name="district"
			value="${patient.personAddress.countyDistrict}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.sector" /></td>
		<td><input type="text" name="sector"
			value="${patient.personAddress.cityVillage}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.cell" /></td>
		<td><input type="text" name="cell"
			value="${patient.personAddress.neighborhoodCell}" readonly="readonly" /></td>
	</tr>
	<tr>
		<td><spring:message code="dataentry.umudugudu" /></td>
		<td><input type="text" name="umudugudu"
			value="${patient.personAddress.address1}" readonly="readonly" /></td>
	</tr>
</table>
</fieldset>


<fieldset><legend><spring:message code="dataentry.addInfo" /></legend>
<table class="adulttbl">
	<tr>
		<td><spring:message code="dataentry.visitReason" /></td>
		<td><select name="currentVisitReason_6189">
			<option value=""></option>
			<c:forEach items="${visitReasons}" var="vr">
				<option value="${vr.answerConcept.conceptId}">${vr.answerConcept.name.name}</option>
			</c:forEach>
		</select></td>
		<td><spring:message code="dataentry.persPhoneNr" /></td>
		<td><input type="text" name="personPhoneNr"
			value="${personPhoneNrObs}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.contactName" /></td>
		<td><input type="text" name="contactName"
			value="${contactNameObs}" readonly="readonly" /></td>
	</tr>
	<tr>
		<td><spring:message code="dataentry.contRel" /></td>
		<td><input type="text" name="contactRelation"
			value="${relationship.relationshipType.bIsToA}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.contPhoneNr" /></td>
		<td><input type="text" name="contactPhoneNr"
			value="${contactPhoneNrObs}" readonly="readonly" /></td>
		<td><spring:message code="dataentry.profession" /></td>
		<td><input type="text" name="profession" value="${professionObs}"
			readonly="readonly" /></td>
	</tr>
	<tr>
	</tr>
</table>
</fieldset>


<fieldset><legend><spring:message code="dataentry.encInfo" /></legend>
<table cellspacing="20px">
	<tr>
		<td><spring:message code="dataentry.provider" /></td>
		<td><select name="encProvider">
			<option value=""></option>
				<c:forEach items="${providerMap}" var="provider">
					<option value="${provider.key}">${provider.value}</option>
				</c:forEach>
		</select></td>
		<td><spring:message code="dataentry.location" /></td>
		<td><select name="encLocation">
			<option value=""></option>
			<c:forEach items="${locations}" var="location">
				<option value="${location.locationId}" ${location.locationId eq dftLoc.locationId ? 'selected=selected': ''} >${location.name}</option>
			</c:forEach>
		</select></td>
		<td><spring:message code="dataentry.date" /></td>
		<td>
		<input type="hidden" name="now" id="nowId" value="<openmrs:formatDate date="${now}" type='textbox' />" />
		<input type="text" name="encDate" id="encDateId" onchange="CompareDates('<openmrs:datePattern />');"
			onfocus="showCalendar(this)" class="date" size="11" /><span id="msgId"></span>
		</td>		
	</tr>
	<tr>
	<td><spring:message code="dataentry.encType" /></td>
		<td>
			<select name="encounterTypeId">
				<option value="-1">---</option>
				<c:forEach items="${encTypes}" var="encType" >
				<c:if test="${encType.encounterTypeId eq 3}">
					<option value="${encType.encounterTypeId}" >${encType.name}</option>
				</c:if>
				<c:if test="${encType.encounterTypeId eq 4}">
					<option value="${encType.encounterTypeId}" >${encType.name}</option>
				</c:if>
				</c:forEach>
			</select>
		</td>
	</tr>
</table>
</fieldset>


<fieldset><legend><spring:message code="dataentry.medHist" /></legend>
<fieldset><legend><spring:message code="dataentry.oppInf" /></legend>
<div id="oppInf"></div>
<p id="addOIid" class="greenbox"></p>
</fieldset>

<fieldset><legend><spring:message
	code="dataentry.tbScreen" /></legend>
<table cellspacing="20px">
	<tr>		
		<td><spring:message code="dataentry.hadFever" /></td>
		<td><input type="checkbox" name="fever_1069" value="5945" /></td>
		<td><spring:message code="dataentry.durFeverInDays" /></td>
		<td><select name="numeric_1294">		
			<option value="" /></option>
			<c:forEach var="i" begin="1" end="9">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select></td>		
		<td><spring:message code="dataentry.contactTBPat" /></td>
		<td><input type="checkbox" name="tbContact_2133" value="1" /></td>
		<td><spring:message code="dataentry.weightLoss" /></td>
		<td><input type="checkbox" name="weightLoss_1293" value="832" /></td>
	</tr>
	<tr>
		<td><spring:message code="dataentry.nightSweats" /></td>
		<td><input type="checkbox" name="nightSweats_1293" value="6029" /></td>
		<td><spring:message code="dataentry.nightSweatsDur" /></td>
		<td><select name="nightSweatsDuration_1293">
			<option value=""></option>
			<option value="2164"><spring:message
				code="dataentry.lessThan3Weeks" /></option>
			<option value="2163"><spring:message
				code="dataentry.greaterThan3Weeks" /></option>
		</select></td>
		<td><spring:message code="dataentry.tbScreenResult" /></td>
		<td><select name="tbScreenResult_2136">
				<option value=""></option>
				<c:forEach items="${screenResults}" var="screenResult">
					<option value="${screenResult.answerConcept.conceptId}">${screenResult.answerConcept.name.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="5">		
			<hr />
		</td>
	</tr>
	<tr>
		<td><spring:message code="dataentry.tbTestRes" /></td>
			<td><select name="tbTestResult_3495">
				<option value=""></option>
				<c:forEach items="${tbResults}" var="tbResult">
					<option value="${tbResult.answerConcept.conceptId}">${tbResult.answerConcept.name.name}</option>
				</c:forEach>
			</select>
		</td>
		<td><spring:message code="dataentry.tbType" /></td>
		<td><select name="tbType_1607">
			<option value="" /></option>
			<option value="1547"><spring:message
				code="dataentry.xPulmoTB" /></option>
			<option value="1549"><spring:message
				code="dataentry.pulmoTB" /></option>
		</select></td>
	</tr>
	
</table>
</fieldset>

<fieldset><legend><spring:message code="dataentry.coughSymptoms" /></legend>
<table cellspacing="20px">
	<tr>
		<td><spring:message code="dataentry.hasCough" /></td>
		<td><input type="checkbox" name="cough_1293" value="107" /></td>
		<td><spring:message code="dataentry.coughTypes" /></td>
		<td><select name="coughType_1293">
			<option value=""></option>
			<option value="5957"><spring:message code="dataentry.productiveCough" /></option>
			<option value="2128"><spring:message code="dataentry.dryCough" /></option>
		</select></td>
		<td><spring:message code="dataentry.coughDuration" /></td>
		<td><select name="coughDuration_5959">
			<option value=""></option>
			<option value="1072"><spring:message code="dataentry.days" /></option>
			<option value="1073"><spring:message code="dataentry.weeks" /></option>
			<option value="1074"><spring:message code="dataentry.months" /></option>
		</select></td>
	</tr>
</table>

</fieldset>
</fieldset>

<fieldset><legend><spring:message code="dataentry.systems" /></legend>
<div id="systems"></div>
<p id="addSysId" class="greenbox"></p>
</fieldset>

<fieldset><legend><spring:message code="dataentry.vitalSigns" /></legend>
<div id="vitalSigns"></div>
<p id="addVSId" class="greenbox"></p>
</fieldset>

<fieldset><legend><spring:message code="dataentry.whoStage" /></legend>
<table>
	<tr>
		<!-- first column -->
		<td>
		<table>
			<c:forEach items="${whoMap}" var="whos" varStatus="status">

				<c:if test="${whos.key eq 'WHO Stage 1 Peds'}">
					<tr>
						<td colspan="2" style="background-color: #D4D0C8;">${whos.key
						}</td>
					</tr>
					<c:forEach items="${whos.value}" var="who1">
						<tr>
							<td>${who1.concept.name.name}<br /><hr /></td>
							<td style="padding-right: 20px"><input type="checkbox"
								name="whos_${who1.concept.conceptId}" value="${who1.concept.conceptId}" /><br /><hr /></td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${whos.key eq 'WHO Stage 2 Peds'}">
					<tr>
						<td colspan="2" style="background-color: #D4D0C8;">${whos.key}</td>
					</tr>
					<c:forEach items="${whos.value}" var="who2">
						<tr>
							<td>${who2.concept.name.name}<br /><hr /></td>
							<td style="padding-right: 20px"><input type="checkbox"
								name="whos_${who2.concept.conceptId}" value="${who2.concept.conceptId}" /><br /><hr /></td>
						</tr>
					</c:forEach>
				</c:if>

				<c:if test="${whos.key eq 'WHO Stage 3 Peds'}">
					<tr>
						<td colspan="2" style="background-color: #D4D0C8;">${whos.key}</td>
					</tr>
					<c:forEach items="${whos.value}" var="who3">
						<tr>
							<td>${who3.concept.name.name}<br /><hr /></td>
							<td style="padding-right: 20px"><input type="checkbox"
								name="whos_${who3.concept.conceptId}" value="${who3.concept.conceptId}" /><br /><hr /></td>
						</tr>
					</c:forEach>
				</c:if>

			</c:forEach>
		</table>
		</td>

		<!-- second column -->
		<td>
		<table>
			<c:forEach items="${whoMap}" var="whos" varStatus="status">

				<c:if test="${whos.key eq 'WHO Stage 4 Peds'}">
					<tr>
						<td colspan="2" style="background-color: #D4D0C8;">${whos.key
						}</td>
					</tr>
					<c:forEach items="${whos.value}" var="who4">
						<tr>
							<td>${who4.concept.name.name}<br /><hr /></td>
							<td style="padding-right: 20px"><input type="checkbox"
								name="whos_${who4.concept.conceptId}" value="${who4.concept.conceptId}" /><br /><hr /></td>
						</tr>
					</c:forEach>
				</c:if>

			</c:forEach>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
		</table>
		</td>
	</tr>
</table>
</fieldset>

<fieldset><legend><spring:message code="dataentry.surgery" /></legend>
<div id="surgeries"></div>
<p id="addSurgeryId" class="greenbox"></p>
</fieldset>


<fieldset><legend><spring:message code="dataentry.sti" /></legend>
<div id="stiList"></div>
<p id="addSTIId" class="greenbox"></p>
</fieldset>

<fieldset><legend><spring:message code="dataentry.conclusion" /></legend>
<table>
	<tr>
		<td><spring:message code="dataentry.sumNotes" /></td>
		<td colspan="3"><textarea name="notes_1364" rows="5" cols="50"></textarea></td>
	</tr>
	<tr>
		<td><spring:message code="dataentry.nextDOV" /></td>
		<td><input type="text" name="nextVisitDate_5096"
			onfocus="showCalendar(this)" class="date" size="11" /></td>
		<td style="padding-left: 20px"><spring:message code="dataentry.nextVisitReason" /></td>
			<td><select name="nextVisitReason_8130">
			<option value=""></option>
			<c:forEach items="${nextVisitReasons}" var="vr">
				<option value="${vr.answerConcept.conceptId}">${vr.answerConcept.name.name}</option>
			</c:forEach>
		</select></td>
	</tr>
</table>
</fieldset>
<input type="submit" value="Submit Data" class="send" /> &nbsp;&nbsp;&nbsp;<input type="reset" value="Cancel" class="send" />
</form>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>