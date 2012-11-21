package org.openmrs.module.dataentry.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptSet;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.Constants;
import org.openmrs.module.dataentry.LaboratoryTest;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class LaboratoryTestFormController extends SimpleFormController{

    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());
        	    
    
    /***
     * Populate some reference data for the form element options
     */
    @SuppressWarnings("deprecation")
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request, Object obj, Errors err) throws Exception {
    	HashMap<String, Object> refMap = new HashMap<String,Object>();
    	
    	// get laboratory examinations into a list
    	HashMap<Integer,String> labTestMap = new HashMap<Integer,String>();
    	Concept labTestConstruct = Context.getConceptService().getConcept(Constants.LABORATORY_EXAMINATIONS_CONSTRUCT);
    	
    	for(ConceptSet conceptSetMember:labTestConstruct.getConceptSets()){
    		
    		Concept labTestConcept = conceptSetMember.getConcept();
    		if(!labTestConcept.getDatatype().isCoded() && labTestConcept.getConceptId()!= 3007){
    			labTestMap.put(labTestConcept.getConceptId(), labTestConcept.getBestName(Context.getLocale()).toString());
    		}
    	}
    	
    	// location objects
    	HashMap<Integer, String> locationOptions = new HashMap<Integer,String>();
    	for (Location location: Context.getLocationService().getAllLocations()){
    		locationOptions.put(location.getLocationId(), location.getName());
    		
    	}
    	refMap.put("locationOptions",locationOptions);
    	
    	
    	// provider options
    	HashMap<Integer, String> providerOptions = new HashMap<Integer,String>();
    	
    	for(User user : Context.getUserService().getUsersByRole(Context.getUserService().getRole(Constants.PROVIDER_ROLE))) {
    		providerOptions.put(user.getPerson().getPersonId(), user.getPersonName().getGivenName());
    	}
    		
    	for(Provider provider: Context.getProviderService().getAllProviders()){
    		providerOptions.put(provider.getPerson().getPersonId(), provider.getPerson().getPersonName().getGivenName() + "  " + provider.getPerson().getPersonName().getFamilyName());	
    	}
    	refMap.put("providerOptions", providerOptions);
    	Patient patient = Context.getPatientService().getPatient(Integer.valueOf(request.getParameter("patientId")));
    	
    	refMap.put("labTestOptions", labTestMap);
    	refMap.put("labTestConceptId",Constants.LABORATORY_EXAMINATIONS_CONSTRUCT);
    	refMap.put("patientId", request.getParameter("patientId"));
    	refMap.put("encounterType", request.getParameter("encounterType"));
    	refMap.put("patient", patient);
    	return refMap;
	}


	/**
	 * Collect the dynamic request parameters and populate the Intake.
	 * Save the Intake
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object object, BindException exceptions) throws Exception {		
		log.info("proccessing lab entry submit");
		@SuppressWarnings("unused")
		Map requestMap = request.getParameterMap();
		
		return new ModelAndView();
    }


    /**
     * Get a LaboratoryTest to back the web-form.  
     */
    @Override
	protected LaboratoryTest formBackingObject(HttpServletRequest request) throws Exception { 
    	
    	// get the patient using patientId from the get request
    	@SuppressWarnings("unused")
		Patient patient = null;
    	LaboratoryTest labTest = new LaboratoryTest();    	
    	return labTest;
    }
}
