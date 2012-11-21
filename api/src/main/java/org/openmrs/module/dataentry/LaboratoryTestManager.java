package org.openmrs.module.dataentry;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptDatatype;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.LaboratoryTest;
import org.openmrs.module.dataentry.Constants;
import org.openmrs.util.OpenmrsUtil;

public class LaboratoryTestManager {
	private static LaboratoryTestManager laboratoryTestManager = null;
	protected final Log log = LogFactory.getLog(getClass());
	
	public static LaboratoryTestManager getInstance(){
		if(laboratoryTestManager == null){
			laboratoryTestManager = new LaboratoryTestManager();
		}
		
		return laboratoryTestManager;
	}
	
	
	/**
	 * Retrieve a lab test which according to the parameters specified. 
	 * Should definitely paramaterize this allow for bounds and ranges
	 * @param labTest
	 */
	public LaboratoryTest getLaboratoryTest(LaboratoryTest labTest){
			List<Person> personList = new ArrayList<Person>();
			personList.add(Context.getPatientService().getPatient(labTest.getPatientId()));
			
			@SuppressWarnings("unused")
			Integer conceptId = labTest.getTestConceptId();
			return null;
	}
	
	
	
	/**
	 * Save this lab test. Important to note that if an encounter already exists
	 * for the patient on the same day, location, and provider as the lab test
	 * then we just add this test to that encounter.  If not then we create a new 
	 * encounter
	 * 
	 * @param labTest
	 */
	public void saveLaboratoryTest(LaboratoryTest labTest){
		 
		 // create patient list from labTest
		 ArrayList<Person> patientList = new ArrayList<Person>();
		 Patient patient = Context.getPatientService().getPatient(labTest.getPatientId());
		 patientList.add(patient);
		 
		 // get the convset concept
		 Concept convsetConcept = Context.getConceptService().getConcept(labTest.getConvSetConceptId());
		 ArrayList<Concept> convsetList = new ArrayList<Concept>();
		 convsetList.add(convsetConcept);
		 
		 // get the actual labtest concept
		 Concept labtestConcept = Context.getConceptService().getConcept(labTest.getTestConceptId());
		 
		 // get the location
		 Location location = Context.getLocationService().getLocation(labTest.getLocationId());
		 ArrayList<Location> locationList = new ArrayList<Location>();
		 locationList.add(location);
		 
		 // get the provider
		 User provider = Context.getUserService().getUser(labTest.getProviderId());
		 
		 Date observationDate;
		 Obs groupingObservation = null;
		 
		 try {
			 observationDate = OpenmrsUtil.getDateFormat().parse(labTest.getTestDate());
			 
			 // initialize our observation
			 Obs observation = new Obs(patient,labtestConcept,observationDate,location);
			
			 // decide how to assign the observation value depending on the datatype
			 ConceptDatatype datatype = labtestConcept.getDatatype();
			 if(datatype.isNumeric()){
				 observation.setValueNumeric(Double.parseDouble(labTest.getTestResult()));
			 }
			 else if(datatype.isText()){
				 observation.setValueText(labTest.getTestResult());
			 }
			 else if(datatype.isCoded()){
				 observation.setValueCoded(Context.getConceptService().getConcept(labTest.getCodedTestResultConceptId()));
			 }
			 else if(datatype.isDate()){
				 observation.setValueDatetime(OpenmrsUtil.getDateFormat().parse(labTest.getTestResult()));;
			 }
			 else if(datatype.isBoolean()){
				 observation.setValueNumeric(Double.parseDouble(labTest.getTestResult()));
			 }
			 
			 
			 // see if there is already an encounter and an obs group for this observation
			 List<Encounter> encounters = Context.getEncounterService().getEncounters(patient, location, observationDate, observationDate, null, null, false);
			 Encounter encounter = null;
			 
			 // existing encounter?
			 if(encounters.size() > 0){
				 log.info("found a matching encounter.  will add this obs to the found encounter");
				 encounter = encounters.get(0);
				 List<Obs> groupingObservations = Context.getObsService().getObservations(patientList, encounters, convsetList, null, null, locationList, null, null, null, null, null, false);

				 // an obs group exists so lets update it
				 if(groupingObservations.size() > 0){
					 groupingObservation = groupingObservations.get(0);	 
				 }
				 else{
					 // no obs group for this convset so lets create it 
					 groupingObservation = new Obs(patient, convsetConcept,observationDate,location);
				 }
				 
			 }else{ 
				 
				 // no existing encounter so make a new encounter and grouping obs
				 
				 log.info("did not find an existing encounter so creating new one provider " + provider.getFamilyName() +  " " + provider.getUserId());
				 log.info("encounter type is " + labTest.getEncounterType());
				 encounter = new Encounter();
				 if(labTest.getEncounterType() != null && labTest.getEncounterType().equals("adult")){
					 encounter.setEncounterType(Context.getEncounterService().getEncounterType(Constants.STR_ADULT_RETURN));
				 }
				 else if(labTest.getEncounterType() != null && labTest.getEncounterType().equals("child")){
					 encounter.setEncounterType(Context.getEncounterService().getEncounterType(Constants.STR_PEDS_RETURN));
				 }
				 
				 encounter.setPatient(patient);
				 encounter.setEncounterDatetime(observationDate);
				 encounter.setLocation(location);
				 encounter.setProvider(provider);
				 encounter.setCreator(Context.getAuthenticatedUser());
				 groupingObservation = new Obs(patient, convsetConcept,observationDate,location);
			 }
			 if(encounter != null){
				 groupingObservation.addGroupMember(observation);
				 encounter.addObs(groupingObservation);
				 encounter.addObs(observation);
				 Context.getEncounterService().saveEncounter(encounter);
			 }
			 
		 } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	
}
