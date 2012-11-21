package org.openmrs.module.dataentry.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.LaboratoryTest;
import org.openmrs.module.dataentry.LaboratoryTestManager;
import org.openmrs.module.dataentry.web.view.LabTestView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

public class SaveLaboratoryTestController extends AbstractController{
	protected final Log log = LogFactory.getLog(getClass());
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		log.info("received ajax request in controller");
		log.info("query string from ajax request : "  + request.getQueryString());
		LaboratoryTest labTest = new LaboratoryTest();
		labTest.setPatientId(Integer.parseInt(request.getParameter("patientId")));
		labTest.setEncounterType(request.getParameter("encounterType"));
		
		labTest.setTestConceptId(Integer.parseInt(request.getParameter("conceptId")));
		labTest.setTestResult(request.getParameter("result"));
		String testDate = (request.getParameter("date"));
		
		labTest.setTestDate(testDate);
		labTest.setConvSetConceptId(Integer.parseInt(request.getParameter("convsetId")));
		
		
		/********************** hard coded in the house ******/
		
		labTest.setProviderId((Integer.parseInt(request.getParameter("providerId"))));
		labTest.setLocationId((Integer.parseInt(request.getParameter("locationId"))));
		/******************************************************/
		
		LaboratoryTestManager.getInstance().saveLaboratoryTest(labTest);
		View view = new LabTestView();
		HashMap<String,Object> model = new HashMap<String, Object>();
		model.put("date", labTest.getTestDate());
		model.put("test",  Context.getConceptService().getConcept(labTest.getTestConceptId()).getBestName(Context.getLocale()));
		model.put("result", labTest.getTestResult());
		
		ModelAndView modelAndView = new ModelAndView(view,model);
			
			
		return modelAndView;
	}
	
	@SuppressWarnings("unused")
	private String manipulateStringDate(String testDate, char charToReplace, char charToInsert){
		StringBuffer formattedDate = new StringBuffer();
		for(int i = 0; i < testDate.length(); i++){
			if(testDate.charAt(i) == charToReplace){
				formattedDate.append(charToInsert);
			}else{
				formattedDate.append(testDate.charAt(i));
			}
		}
		return formattedDate.toString();
	}
}
