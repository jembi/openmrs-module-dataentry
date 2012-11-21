<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<spring:message code="dataentry.dataEntryCompleteFor" /> : ${patientName} 

<br/>
<br/>

<a href="<openmrs:contextPath/>/patientDashboard.form?patientId=${patientId}">Back to Patient Dashboard</a>