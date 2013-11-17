/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Provider;
import org.openmrs.RelationshipType;
import org.openmrs.User;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.LocationService;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.Constants;
import org.openmrs.module.dataentry.utils.Utils;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 *
 */
public class AdultEnrollmentFormController extends
		ParameterizableViewController {

	private Log log = LogFactory.getLog(this.getClass());

	@SuppressWarnings("rawtypes")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String oiId = null;
		String treatmentResultId = null;
		String OIStartDateId = null;
		String OIEndDateId = null;

		String snId = null;
		String systemFindingId = null;
		String sysId = null;
		String sysCommentValue = null;

		String vsnId = null;
		String vsnValueId = null;

		String resId = null;
		String relId = null;
		String dobId = null;
		String dodId = null;
		String resDateId = null;

		String fpmId = null;

		String stiId = null;
		String stiDateId = null;

		List<Concept> whosList = new ArrayList<Concept>();
		ModelAndView mav = new ModelAndView();
		Constants constan = new Constants();
		LocationService locService = Context.getLocationService();
		Person encProvider = null;
		Location encLocation = locService.getDefaultLocation();
		Location hivLoc = locService.getDefaultLocation();
		Location dftLoc = locService.getDefaultLocation();
		// Location location = null;

		Date encDate = new Date();
		Date hivDate = new Date();

		String locationStr = Context.getAuthenticatedUser().getUserProperties()
				.get(OpenmrsConstants.USER_PROPERTY_DEFAULT_LOCATION);

		try {
			dftLoc = locService.getLocation(Integer.valueOf(locationStr));
		} catch (Exception e) {
			log.info("Error: >>>> " + e.getMessage());
			mav
					.addObject(
							"msg",
							"It will be good if you first set your default location in \"My Profile\" link  on the upper right corner of the page!");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		ConceptService conceptService = Context.getConceptService();
		PatientService patientService = Context.getPatientService();
		UserService userService = Context.getUserService();
		EncounterService encService = Context.getEncounterService();
		Patient patient = null;
		if (request.getParameter("patientId") != null
				&& !request.getParameter("patientId").equals("")) {
			patient = patientService.getPatient(Integer.valueOf(request
					.getParameter("patientId")));
		}
		
		// provider options
    	HashMap<Integer, String> providerMap = new HashMap<Integer,String>();
    	
    	for(User user : userService.getUsersByRole(userService.getRole(Constants.PROVIDER_ROLE))) {
    		providerMap.put(user.getPerson().getPersonId(), user.getPersonName().getGivenName());
    	}
    		
    	for(Provider provider: Context.getProviderService().getAllProviders()){
    		providerMap.put(provider.getPerson().getPersonId(), provider.getPerson().getPersonName().getGivenName() + "  " + provider.getPerson().getPersonName().getFamilyName());	
    	}

		List<EncounterType> encTypes = encService.getAllEncounterTypes();

		Collection<ConceptAnswer> opportunisticInfections = conceptService
				.getConcept(Constants.OPPORTUNISTIC_INFECTIONS_ID).getAnswers();

		Collection<ConceptAnswer> systems = conceptService.getConcept(
				Constants.SYSTEM_ID).getAnswers();
		Collection<ConceptSet> vitalSigns = conceptService.getConcept(
				Constants.VITAL_SIGNS).getConceptSets();
		Collection<ConceptAnswer> familyPlannings = conceptService.getConcept(
				Constants.FAMILY_PLANNING).getAnswers();
		Collection<ConceptAnswer> tbResults = conceptService.getConcept(
				Constants.TB_TEST_RESULT).getAnswers();
		Collection<ConceptAnswer> screenResults = conceptService.getConcept(
				Constants.TB_SCREENING_RESULT).getAnswers();
		List<Location> locations = Context.getLocationService()
				.getAllLocations();
		Collection<ConceptAnswer> professions = conceptService.getConcept(
				Constants.MAIN_ACTIVITY).getAnswers();
		Collection<ConceptAnswer> civalStatus = conceptService.getConcept(
				Constants.CIVIL_STATUS).getAnswers();
		Collection<ConceptAnswer> visitReasons = conceptService.getConcept(
				Constants.REASON_FOR_VISIT).getAnswers();
		Collection<ConceptAnswer> nextVisitReasons = conceptService.getConcept(
				Constants.NEXT_REASON_FOR_VISIT).getAnswers();
		Collection<ConceptAnswer> modeOfAdmission = conceptService.getConcept(
				Constants.MODE_OF_ADMISSION).getAnswers();
		Collection<ConceptAnswer> partnerHIVStatus = conceptService.getConcept(
				Constants.PARTNER_HIV_STATUS).getAnswers();
		Collection<ConceptAnswer> treatmentResults = new HashSet<ConceptAnswer>();
		try {
			treatmentResults = conceptService.getConcept(
					constan.getGP("dataentry.oppInfTreatRes")).getAnswers();
		} catch (Exception e) {
			log
					.error("Global property dataentry.oppInfTreatRes is not set correctly.");
		}
		Collection<ConceptAnswer> whoStages = conceptService.getConcept(
				Constants.WHO_STAGE).getAnswers();
		Collection<ConceptAnswer> relationships = conceptService.getConcept(
				Constants.RELATIONSHIP_OF_RELATIVE_TO_PATIENT).getAnswers();
		Collection<ConceptAnswer> hivTestResults = conceptService.getConcept(
				Constants.RESULT_OF_HIV_TEST).getAnswers();

		Collection<ConceptAnswer> currentWhoHivStage = conceptService
				.getConcept(Constants.CURRENT_WHO_HIV_STAGE).getAnswers();
		Collection<ConceptSet> whoStage1 = conceptService.getConcept(
				Constants.WHO_STAGE_1).getConceptSets();
		Collection<ConceptSet> whoStage2 = conceptService.getConcept(
				Constants.WHO_STAGE_2).getConceptSets();
		Collection<ConceptSet> whoStage3 = conceptService.getConcept(
				Constants.WHO_STAGE_3).getConceptSets();
		Collection<ConceptSet> whoStage4 = conceptService.getConcept(
				Constants.WHO_STAGE_4).getConceptSets();
		Collection<ConceptAnswer> presentSymptom = conceptService.getConcept(
				Constants.SYM_PRES).getAnswers();

		List<RelationshipType> relationshipTypes = Context.getPersonService()
				.getAllRelationshipTypes();
		// PatientProgram patientProgram = deService
		// .getPatientProgramByPatient(patient);
		// try {
		// location = patient.getPatientIdentifier().getLocation();
		// } catch (NullPointerException e) {
		// mav.addObject("msg",
		// "The patient don't seem to have an Identifier!");
		// }

		if (request.getParameter("adultInit") != null
				&& !request.getParameter("adultInit").equals("")) {
			List<Obs> obss = new ArrayList<Obs>();

			if (request.getParameter("encProvider") != null
					&& !request.getParameter("encProvider").equals(""))
				encProvider = Context.getPersonService().getPerson(Integer.valueOf(request.getParameter("encProvider")));
			if (request.getParameter("encLocation") != null
					&& !request.getParameter("encLocation").equals(""))
				encLocation = locService.getLocation(Integer.valueOf(request
						.getParameter("encLocation")));

			if (request.getParameter("encDate") != null
					&& !request.getParameter("encDate").equals(""))
				encDate = sdf.parse(request.getParameter("encDate").toString());

			if (request.getParameter("text_1334") != null
					&& !request.getParameter("text_1334").equals(""))
				hivLoc = Context.getLocationService().getLocation(
						Integer.valueOf(request.getParameter("text_1334")));

			if (request.getParameter("date_1837") != null
					&& !request.getParameter("date_1837").equals("")) {
				Concept c = conceptService.getConcept(1837);
				hivDate = sdf.parse(request.getParameter("date_1837"));
				Obs hivDateObs = Utils.createObservation(encDate, hivLoc,
						patient, c, hivDate, 2);
				obss.add(hivDateObs);
			}

			Concept persPhNrConc = conceptService
					.getConcept(Constants.PERSON_PHONE_NUMBER);
			if (request.getParameter("personPhoneNr_1426") != null
					&& !request.getParameter("personPhoneNr_1426").equals("")) {
				Obs persPhNrObs = null;
				try {
					persPhNrObs = Utils.createObservation(encDate, encLocation,
							patient, persPhNrConc, Double.parseDouble(request
									.getParameter("personPhoneNr_1426")), 1);
				} catch (Exception e) {
					mav.addObject("msg",
							"The phone number isn't wrote correctly!");
					log.info(e.getMessage());
				}

				obss.add(persPhNrObs);
			}

			Concept contactNrConc = conceptService
					.getConcept(Constants.PHONE_NUMBER_OF_CONTACT);
			if (request.getParameter("contactNr_6194") != null
					&& !request.getParameter("contactNr_6194").equals("")) {
				Obs contactNrObs = null;
				try {
					contactNrObs = Utils.createObservation(encDate,
							encLocation, patient, contactNrConc, request
									.getParameter("contactNr_6194"), 3);
				} catch (Exception e) {
					mav
							.addObject("msg",
									"The phone number of the contact is not entered correctly!");
				}
				obss.add(contactNrObs);
			}

			Concept contactNameConc = conceptService
					.getConcept(Constants.CONTACT_NAME);
			if (request.getParameter("contactName_1327") != null
					&& !request.getParameter("contactName_1327").equals("")) {
				Obs contactNameObs = Utils.createObservation(encDate,
						encLocation, patient, contactNameConc, request
								.getParameter("contactName_1327"), 3);
				obss.add(contactNameObs);
			}

			if (request.getParameter("contactRelation_1328") != null
					&& !request.getParameter("contactRelation_1328").equals("")) {
				String relationship = Context.getPersonService()
						.getRelationshipType(
								Integer.valueOf(request
										.getParameter("contactRelation_1328")))
						.getaIsToB()
						+ ""
						+ Context.getPersonService().getRelationshipType(
								Integer.valueOf(request
										.getParameter("contactRelation_1328")))
								.getbIsToA();

				Concept contactRelConc = conceptService
						.getConcept(Constants.RELATIONSHIP_TO_CONTACT);
				Obs contactRelObs = Utils.createObservation(encDate,
						encLocation, patient, contactRelConc, relationship, 3);
				obss.add(contactRelObs);
			}

			Concept profConc = conceptService
					.getConcept(Constants.MAIN_ACTIVITY);
			if (request.getParameter("profession_1304") != null
					&& !request.getParameter("profession_1304").equals("")) {
				Concept profValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("profession_1304")));
				Obs profObs = Utils.createObservation(encDate, encLocation,
						patient, profConc, profValue, 4);
				obss.add(profObs);
			}

			Concept statusConc = conceptService
					.getConcept(Constants.CIVIL_STATUS);
			if (request.getParameter("civilStatus_1054") != null
					&& !request.getParameter("civilStatus_1054").equals("")) {
				Concept statusValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("civilStatus_1054")));
				Obs statusObs = Utils.createObservation(encDate, encLocation,
						patient, statusConc, statusValue, 4);
				obss.add(statusObs);
			}

			Concept hivStatConc = conceptService
					.getConcept(Constants.PARTNER_HIV_STATUS);

			if (request.getParameter("hivStatPartner_3082") != null
					&& !request.getParameter("hivStatPartner_3082").equals("")) {
				Concept hivStatValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("hivStatPartner_3082")));
				Obs hivStatObs = Utils.createObservation(encDate, encLocation,
						patient, hivStatConc, hivStatValue, 4);
				obss.add(hivStatObs);
			}

			Concept admiModeConc = conceptService
					.getConcept(Constants.MODE_OF_ADMISSION);
			if (request.getParameter("admissionMode_1650") != null
					&& !request.getParameter("admissionMode_1650").equals("")) {
				Concept admiModeValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("admissionMode_1650")));
				Obs admiModeObs = Utils.createObservation(encDate, encLocation,
						patient, admiModeConc, admiModeValue, 4);
				obss.add(admiModeObs);
			}

			Concept transConc = conceptService
					.getConcept(Constants.PATIENT_TRANSFERRED);
			if (request.getParameter("patTranserred_2536") != null
					&& !request.getParameter("patTranserred_2536").equals("")) {
				Concept transValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("patTranserred_2536")));
				Obs transObs = Utils.createObservation(encDate, encLocation,
						patient, transConc, transValue, 4);
				obss.add(transObs);
			}

			Concept dotConc = conceptService
					.getConcept(Constants.DATE_OF_TRANSFER);
			if (request.getParameter("dot_1428") != null
					&& !request.getParameter("dot_1428").equals("")) {
				Date dot = sdf.parse(request.getParameter("dot_1428"));
				Obs dotObs = Utils.createObservation(encDate, encLocation,
						patient, dotConc, dot, 2);
				obss.add(dotObs);
			}

			Concept transFromConc = conceptService
					.getConcept(Constants.TRANSFER_FROM);
			if (request.getParameter("transferFrom_1427") != null
					&& !request.getParameter("transferFrom_1427").equals("")) {
				Obs transFromObs = Utils.createObservation(encDate,
						encLocation, patient, transFromConc, request
								.getParameter("transferFrom_1427"), 3);
				obss.add(transFromObs);
			}

			Concept visitReasonConc = conceptService
					.getConcept(Constants.REASON_FOR_VISIT);
			if (request.getParameter("currentVisitReason_6189") != null
					&& !request.getParameter("currentVisitReason_6189").equals(
							"")) {
				Concept currVRValue = conceptService.getConcept(Integer
						.valueOf(request
								.getParameter("currentVisitReason_6189")));
				Obs currVRObs = Utils.createObservation(encDate, encLocation,
						patient, visitReasonConc, currVRValue, 4);
				obss.add(currVRObs);
			}

			Concept tbResConc = conceptService
					.getConcept(Constants.TB_TEST_RESULT);
			if (request.getParameter("tbTestResult_3495") != null
					&& !request.getParameter("tbTestResult_3495").equals("")) {
				Concept tbResValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("tbTestResult_3495")));
				Obs tbResObs = Utils.createObservation(encDate, encLocation,
						patient, tbResConc, tbResValue, 4);
				obss.add(tbResObs);
			}

			// TB Screening
			Concept scrResConc = conceptService
					.getConcept(Constants.TB_SCREENING_RESULT);
			if (request.getParameter("tbScreenResult_2136") != null
					&& !request.getParameter("tbScreenResult_2136").equals("")) {
				Concept scrResValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("tbScreenResult_2136")));
				Obs scrResObs = Utils.createObservation(encDate, encLocation,
						patient, scrResConc, scrResValue, 4);
				obss.add(scrResObs);

			}

			Concept tbTypeConc = conceptService
					.getConcept(Constants.OPPORTUNISTIC_INFECTIONS_ID);
			if (request.getParameter("tbType_1607") != null
					&& !request.getParameter("tbType_1607").equals("")) {
				Concept tbTypeValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("tbType_1607")));
				Obs tbTypeObs = Utils.createObservation(encDate, encLocation,
						patient, tbTypeConc, tbTypeValue, 4);
				obss.add(tbTypeObs);
			}

			Concept feverConc = conceptService.getConcept(Constants.FEVER);
			if (request.getParameter("fever_1069") != null
					&& !request.getParameter("fever_1069").equals("")) {
				Concept feverValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("fever_1069")));
				Obs feverObs = Utils.createObservation(encDate, encLocation,
						patient, feverConc, feverValue, 4);
				obss.add(feverObs);
			}

			Concept tbContactConc = conceptService
					.getConcept(Constants.TB_CONTACT);
			if (request.getParameter("tbContact_2133") != null
					&& !request.getParameter("tbContact_2133").equals("")) {
				Obs tbContactObs = Utils.createObservation(encDate,
						encLocation, patient, tbContactConc, Double
								.parseDouble(request
										.getParameter("tbContact_2133")), 1);
				obss.add(tbContactObs);
			}

			Concept feverInDaysConc = conceptService
					.getConcept(Constants.DURATION_OF_SYMPTOM_IN_DAYS);
			if (request.getParameter("numeric_1294") != null
					&& !request.getParameter("numeric_1294").equals("")) {
				Obs feverInDaysObs = Utils.createObservation(encDate,
						encLocation, patient, feverInDaysConc, Double
								.parseDouble(request
										.getParameter("numeric_1294")), 1);
				obss.add(feverInDaysObs);
			}

			Concept weightLossConc = conceptService
					.getConcept(Constants.WEIGHT_LOSS);
			if (request.getParameter("weightLoss_1293") != null
					&& !request.getParameter("weightLoss_1293").equals("")) {
				Concept weightLossValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("weightLoss_1293")));
				Obs weightLossObs = Utils.createObservation(encDate,
						encLocation, patient, weightLossConc, weightLossValue,
						4);
				obss.add(weightLossObs);
			}

			Concept nightSweatDurConc = conceptService
					.getConcept(Constants.WEIGHT_LOSS);
			if (request.getParameter("nightSweatsDuration_1293") != null
					&& !request.getParameter("nightSweatsDuration_1293")
							.equals("")) {
				Concept nightSweatDurValue = conceptService.getConcept(Integer
						.valueOf(request
								.getParameter("nightSweatsDuration_1293")));
				Obs nightSweatDurObs = Utils.createObservation(encDate,
						encLocation, patient, nightSweatDurConc,
						nightSweatDurValue, 4);
				obss.add(nightSweatDurObs);
			}

			Concept nightSweatConc = conceptService
					.getConcept(Constants.WEIGHT_LOSS);
			if (request.getParameter("nightSweats_1293") != null
					&& !request.getParameter("nightSweats_1293").equals("")) {
				Concept nightSweatValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("nightSweats_1293")));
				Obs nightSweatObs = Utils.createObservation(encDate,
						encLocation, patient, nightSweatConc, nightSweatValue,
						4);
				obss.add(nightSweatObs);
			}

			Concept coughConc = conceptService
					.getConcept(Constants.WEIGHT_LOSS);
			if (request.getParameter("cough_1293") != null
					&& !request.getParameter("cough_1293").equals("")) {
				Concept coughValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("cough_1293")));
				Obs coughObs = Utils.createObservation(encDate, encLocation,
						patient, coughConc, coughValue, 4);
				obss.add(coughObs);
			}

			Concept coughTypeConc = conceptService
					.getConcept(Constants.WEIGHT_LOSS);
			if (request.getParameter("coughType_1293") != null
					&& !request.getParameter("coughType_1293").equals("")) {
				Concept coughTypeValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("coughType_1293")));
				Obs coughTypeObs = Utils.createObservation(encDate,
						encLocation, patient, coughTypeConc, coughTypeValue, 4);
				obss.add(coughTypeObs);
			}

			Concept coughDurConc = conceptService
					.getConcept(Constants.COUGHT_DURATION);
			if (request.getParameter("coughDuration_5959") != null
					&& !request.getParameter("coughDuration_5959").equals("")) {
				Concept coughDurValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("coughDuration_5959")));
				Obs coughDurObs = Utils.createObservation(encDate, encLocation,
						patient, coughDurConc, coughDurValue, 4);
				obss.add(coughDurObs);
			}

			Concept whoConc = conceptService.getConcept(Constants.WHO_STAGE);
			if (request.getParameter("whoStage_1480") != null
					&& !request.getParameter("whoStage_1480").equals("")) {
				Concept whoValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("whoStage_1480")));
				Obs whoObs = Utils.createObservation(encDate, encLocation,
						patient, whoConc, whoValue, 4);
				obss.add(whoObs);
			}

			Concept pregnNrConc = conceptService
					.getConcept(Constants.PREGNANCY_NUMBER);
			if (request.getParameter("PregnanciesNr_5624") != null
					&& !request.getParameter("PregnanciesNr_5624").equals("")) {
				int pregnNrValue = Integer.valueOf(request
						.getParameter("PregnanciesNr_5624"));
				Obs pregnNrObs = Utils.createObservation(encDate, encLocation,
						patient, pregnNrConc, pregnNrValue, 1);
				obss.add(pregnNrObs);
			}

			Concept childDelivNrConc = conceptService
					.getConcept(Constants.DELIVERED_CHILD_NUMBER);
			if (request.getParameter("DelivChildNr_1053") != null
					&& !request.getParameter("DelivChildNr_1053").equals("")) {
				int childDelivNrValue = Integer.valueOf(request
						.getParameter("DelivChildNr_1053"));
				Obs childDelivNrObs = Utils.createObservation(encDate,
						encLocation, patient, childDelivNrConc,
						childDelivNrValue, 1);
				obss.add(childDelivNrObs);
			}

			Concept livChilNrConc = conceptService
					.getConcept(Constants.LIVING_CHILD_NUMBER);
			if (request.getParameter("livingChildrenNr_2549") != null
					&& !request.getParameter("livingChildrenNr_2549")
							.equals("")) {
				int livChilNrValue = Integer.valueOf(request
						.getParameter("livingChildrenNr_2549"));
				Obs livChilNrObs = Utils.createObservation(encDate,
						encLocation, patient, livChilNrConc, livChilNrValue, 1);
				obss.add(livChilNrObs);
			}

			Concept noteConc = conceptService
					.getConcept(Constants.CLINICAL_IMPRESSION_COMMENTS);
			if (request.getParameter("notes_1364") != null
					&& !request.getParameter("notes_1364").equals("")) {
				String noteValue = request.getParameter("notes_1364");
				Obs noteObs = Utils.createObservation(encDate, encLocation,
						patient, noteConc, noteValue, 3);
				obss.add(noteObs);
			}

			Concept nextVisitConc = conceptService.getConcept(5096);
			if (request.getParameter("nextVisitDate_5096") != null
					&& !request.getParameter("nextVisitDate_5096").equals("")) {
				Date nextVisitDate = sdf.parse(request
						.getParameter("nextVisitDate_5096"));
				Obs nextVisitObs = Utils.createObservation(encDate,
						encLocation, patient, nextVisitConc, nextVisitDate, 2);
				obss.add(nextVisitObs);
			}

			Concept nvReasonConc = conceptService
					.getConcept(Constants.NEXT_REASON_FOR_VISIT);
			if (request.getParameter("nextVisitReason_8130") != null
					&& !request.getParameter("nextVisitReason_8130").equals("")) {
				Concept nvReasonValue = conceptService.getConcept(Integer
						.valueOf(request.getParameter("nextVisitReason_8130")));
				Obs nvReasonObs = Utils.createObservation(encDate, encLocation,
						patient, nvReasonConc, nvReasonValue, 4);
				obss.add(nvReasonObs);
			}
			

			EncounterType encType = new EncounterType();
			if (request.getParameter("encounterTypeId").equals("-1")) {
				encType = encService.getEncounterType(Constants.ADULT_INITIAL);
			} else {
				encType = encService.getEncounterType(Integer.valueOf(request
						.getParameter("encounterTypeId")));
			}

			// for the expandable fields
			Map requestMap = request.getParameterMap();
			ArrayList<String> fieldNames = new ArrayList<String>();
			for (Object va : requestMap.keySet()) {
				fieldNames.add((String) va);
			}

			if (fieldNames.size() != 0) {
				for (String str : fieldNames) {
					String suffixId = str.substring(str.indexOf("_") + 1);
					String oiSuffix = "oi_" + suffixId;
					String systemFindingSuffix = "systemFinding_" + suffixId;
					String vsnSuffix = "vsn_" + suffixId;
					String fpmSuffix = "fpm_" + suffixId;
					String surgSuffix = "surgeryDateValue_" + suffixId;
					String stiSuffix = "sti_" + suffixId;
					String stiDateSuffix = "stiDate_" + suffixId;
					String relSuffix = "rel_" + suffixId;
					String whoSuffix = "whos_" + suffixId;

					if (whoSuffix.equals(str)) {
						if (request.getParameter(whoSuffix) != null
								&& !request.getParameter(whoSuffix).equals("")) {
							String whosId = request.getParameter(whoSuffix);

							Concept whosConc = conceptService
									.getConcept(Constants.WHO_STAGES_CRITERIA_PRESENT);
							if (whosId != null) {
								Concept whosValue = conceptService
										.getConcept(Integer.valueOf(whosId));

								Obs whosObs = Utils.createObservation(encDate,
										encLocation, patient, whosConc,
										whosValue, 4);
								whosList.add(conceptService.getConcept(Integer
										.valueOf(whosId)));
								obss.add(whosObs);
							}
						}
					}

					if (oiSuffix.equals(str)) {
						Obs oppObsGr = Utils.createObsGr(encDate, encLocation,
								patient, new Date(),
								conceptService.getConcept(constan
										.getGP("dataentry.infectionSet")));

						if (request.getParameter("oi_" + suffixId) != null
								&& !request.getParameter("oi_" + suffixId)
										.equals("")) {

							oiId = request.getParameter("oi_" + suffixId);

							Concept oiConc = conceptService
									.getConcept(Constants.OPPORTUNISTIC_INFECTIONS_ID);
							if (oiId != null) {
								Concept oiValue = conceptService
										.getConcept(Integer.valueOf(oiId));

								Obs oiObs = Utils.createObservation(encDate,
										encLocation, patient, oiConc, oiValue,
										4);
								oppObsGr.addGroupMember(oiObs);
							}
						}
						if (request.getParameter("treatmentResult_" + suffixId) != null
								&& !request.getParameter(
										"treatmentResult_" + suffixId).equals(
										"")) {
							treatmentResultId = request
									.getParameter("treatmentResult_" + suffixId);
							Concept trConc = conceptService.getConcept(constan
									.getGP("dataentry.oppInfTreatRes"));
							if (treatmentResultId != null) {
								Concept trValue = conceptService
										.getConcept(Integer
												.valueOf(treatmentResultId));
								Obs trObs = Utils.createObservation(encDate,
										encLocation, patient, trConc, trValue,
										4);
								oppObsGr.addGroupMember(trObs);
							}
						}

						if (request.getParameter("OIStartDate_" + suffixId) != null
								&& !request.getParameter(
										"OIStartDate_" + suffixId).equals("")) {
							OIStartDateId = request.getParameter("OIStartDate_"
									+ suffixId);
							Concept oiSDConc = conceptService
									.getConcept(constan
											.getGP("dataentry.startDate"));
							if (OIStartDateId != null) {
								Date oiSD = sdf.parse(OIStartDateId);
								Obs oiSDObs = Utils
										.createObservation(encDate,
												encLocation, patient, oiSDConc,
												oiSD, 2);
								oppObsGr.addGroupMember(oiSDObs);
							}

						}

						if (request.getParameter("OIEndDate_" + suffixId) != null
								&& !request.getParameter(
										"OIEndDate_" + suffixId).equals("")) {
							OIEndDateId = request.getParameter("OIEndDate_"
									+ suffixId);
							Concept oiEDConc = conceptService
									.getConcept(constan
											.getGP("dataentry.endDate"));
							if (OIEndDateId != null) {
								Date oiED = sdf.parse(OIEndDateId);
								Obs oiEDObs = Utils
										.createObservation(encDate,
												encLocation, patient, oiEDConc,
												oiED, 2);
								oppObsGr.addGroupMember(oiEDObs);
							}
						}
						if (OIStartDateId != null || OIEndDateId != null
								|| treatmentResultId != null || oiId != null) {
							obss.add(oppObsGr);
						}
					}

					if (systemFindingSuffix.equals(str)) {
						Obs sysObsGr = Utils
								.createObsGr(
										encDate,
										encLocation,
										patient,
										new Date(),
										conceptService
												.getConcept(Constants.EXAMINATION_OF_SYSTEM_CONSTRUCT));

						if (request.getParameter("sn_" + suffixId) != null
								&& !request.getParameter("sn_" + suffixId)
										.equals("")) {
							snId = request.getParameter("sn_" + suffixId);
							Concept snConc = conceptService
									.getConcept(Constants.SYSTEM_ID);
							Concept snValue = conceptService.getConcept(Integer
									.valueOf(snId));
							Obs snObs = Utils.createObservation(encDate,
									encLocation, patient, snConc, snValue, 4);
							sysObsGr.addGroupMember(snObs);
						}
						if (request.getParameter("systemFinding_" + suffixId) != null
								&& !request.getParameter(
										"systemFinding_" + suffixId).equals("")) {
							systemFindingId = request
									.getParameter("systemFinding_" + suffixId);
							Concept sysFindConc = conceptService
									.getConcept(Constants.SYSTEM_FINDING);
							Concept sysFindValue = conceptService
									.getConcept(Integer
											.valueOf(systemFindingId));
							Obs sysFindObs = Utils.createObservation(encDate,
									encLocation, patient, sysFindConc,
									sysFindValue, 4);
							sysObsGr.addGroupMember(sysFindObs);
						}
						if (request.getParameter("sys_" + suffixId) != null
								&& !request.getParameter("sys_" + suffixId)
										.equals("")) {
							sysId = request.getParameter("sys_" + suffixId);
							Concept sysConc = conceptService
									.getConcept(Constants.SYM_PRES);
							Concept sysValue = conceptService
									.getConcept(Integer.valueOf(sysId));
							Obs symptomObs = Utils.createObservation(encDate,
									encLocation, patient, sysConc, sysValue, 4);
							sysObsGr.addGroupMember(symptomObs);
						}
						if (request.getParameter("sysComment_" + suffixId) != null
								&& !request.getParameter(
										"sysComment_" + suffixId).equals("")) {
							sysCommentValue = request
									.getParameter("sysComment_" + suffixId);
							Concept sysCommentConc = conceptService
									.getConcept(Constants.PHYSICAL_SYSTEM_COMMENT);

							Obs sysCommentObs = Utils.createObservation(
									encDate, encLocation, patient,
									sysCommentConc, sysCommentValue, 3);
							sysObsGr.addGroupMember(sysCommentObs);
						}
						if (snId != null || systemFindingId != null
								|| sysId != null || sysCommentValue != null) {
							obss.add(sysObsGr);
						}
					}

					if (vsnSuffix.equals(str)) {
						Obs vsObsGr = Utils.createObsGr(encDate, encLocation,
								patient, new Date(), conceptService
										.getConcept(Constants.VITAL_SIGNS));
						if (request.getParameter("vsn_" + suffixId) != null
								&& !request.getParameter("vsn_" + suffixId)
										.equals("")) {
							vsnId = request.getParameter("vsn_" + suffixId);
							vsnValueId = request.getParameter("vsnValue_"
									+ suffixId);
							if (vsnId != null && vsnValueId != null) {
								Concept vsnConc = conceptService
										.getConcept(Integer.valueOf(vsnId));
								Double vsnValue = Double
										.parseDouble(vsnValueId);
								Obs vsnObs = Utils.createObservation(encDate,
										encLocation, patient, vsnConc,
										vsnValue, 1);
								vsObsGr.addGroupMember(vsnObs);
							}
						}
						if (vsnId != null && vsnValueId != null) {
							obss.add(vsObsGr);
						}
					}

					if (relSuffix.equals(str)) {
						Obs fsObsGr = Utils.createObsGr(encDate, encLocation,
								patient, new Date(),
								conceptService.getConcept(constan
										.getGP("dataentry.familySerology")));
						if (request.getParameter("res_" + suffixId) != null
								&& !request.getParameter("res_" + suffixId)
										.equals("")) {
							resId = request.getParameter("res_" + suffixId);
							Concept resConc = conceptService
									.getConcept(Constants.RESULT_OF_HIV_TEST);
							if (resId != null) {
								Concept resValue = conceptService
										.getConcept(Integer.valueOf(resId));
								Obs resObs = Utils.createObservation(encDate,
										encLocation, patient, resConc,
										resValue, 4);
								fsObsGr.addGroupMember(resObs);
							}
						}

						if (request.getParameter("rel_" + suffixId) != null
								&& !request.getParameter("rel_" + suffixId)
										.equals("")) {
							relId = request.getParameter("rel_" + suffixId);
							Concept relConc = conceptService
									.getConcept(Constants.RELATIONSHIP_OF_RELATIVE_TO_PATIENT);
							if (relId != null) {
								Concept relValue = conceptService
										.getConcept(Integer.valueOf(relId));
								Obs relObs = Utils.createObservation(encDate,
										encLocation, patient, relConc,
										relValue, 4);
								fsObsGr.addGroupMember(relObs);
							}

						}
						if (request.getParameter("dob_" + suffixId) != null
								&& !request.getParameter("dob_" + suffixId)
										.equals("")) {
							dobId = request.getParameter("dob_" + suffixId);
							Concept dobConc = conceptService
									.getConcept(Constants.DATE_OF_BIRTH);
							if (dobId != null) {
								Date dobValue = sdf.parse(dobId);
								Obs dobObs = Utils.createObservation(encDate,
										encLocation, patient, dobConc,
										dobValue, 2);
								fsObsGr.addGroupMember(dobObs);
							}
						}

						if (request.getParameter("dod_" + suffixId) != null
								&& !request.getParameter("dod_" + suffixId)
										.equals("")) {
							dodId = request.getParameter("dod_" + suffixId);
							Concept dodConc = conceptService
									.getConcept(Constants.DATE_OF_DEATH);
							if (dodId != null) {
								Date dodValue = sdf.parse(dodId);
								Obs dodObs = Utils.createObservation(encDate,
										encLocation, patient, dodConc,
										dodValue, 2);
								fsObsGr.addGroupMember(dodObs);
							}
						}

						if (request.getParameter("resDate_" + suffixId) != null
								&& !request.getParameter("resDate_" + suffixId)
										.equals("")) {
							resDateId = request.getParameter("resDate_"
									+ suffixId);
							Concept resDateConc = conceptService
									.getConcept(Constants.HIV_TEST_DATE);
							if (resDateId != null) {
								Date resDateValue = sdf.parse(resDateId);
								Obs resDateObs = Utils.createObservation(
										encDate, encLocation, patient,
										resDateConc, resDateValue, 2);
								fsObsGr.addGroupMember(resDateObs);
							}
						}
						if (resDateId != null || dodId != null || dobId != null
								|| relId != null || resId != null) {
							obss.add(fsObsGr);
						}
					}

					if (fpmSuffix.equals(str)) {
						if (request.getParameter("fpm_" + suffixId) != null
								&& !request.getParameter("fpm_" + suffixId)
										.equals("")) {
							fpmId = request.getParameter("fpm_" + suffixId);

							Concept fpmConc = conceptService
									.getConcept(Constants.PREVIOUS_FAMILY_PLANNING_METHOD);
							if (fpmId != null) {
								Concept fpmValue = conceptService
										.getConcept(Integer.valueOf(fpmId));
								Obs fpmObs = Utils.createObservation(encDate,
										encLocation, patient, fpmConc,
										fpmValue, 4);
								obss.add(fpmObs);
							}
						}
					}

					if (surgSuffix.equals(str)) {
						if (request
								.getParameter("surgeryDateValue_" + suffixId) != null
								&& !request.getParameter(
										"surgeryDateValue_" + suffixId).equals(
										"")) {
							String surgeryDateValueId = request
									.getParameter("surgeryDateValue_"
											+ suffixId);

							Concept surgDateConc = conceptService
									.getConcept(Constants.DATE_OF_SURGERY);
							if (surgeryDateValueId != null) {
								Date surgDateValue = sdf
										.parse(surgeryDateValueId);
								Obs surgDateObs = Utils.createObservation(
										encDate, encLocation, patient,
										surgDateConc, surgDateValue, 2);
								obss.add(surgDateObs);
							}
						}
					}

					if (stiSuffix.equals(str) && stiDateSuffix.equals(str)) {
						Obs stiObsGr = Utils.createObsGr(encDate, encLocation,
								patient, new Date(), conceptService
										.getConcept(constan
												.getGP("dataentry.stiSet")));
						if (request.getParameter("sti_" + suffixId) != null
								&& !request.getParameter("sti_" + suffixId)
										.equals("")) {
							stiId = request.getParameter("sti_" + suffixId);
							Concept stiConc = conceptService
									.getConcept(Constants.TB_TYPE);
							if (stiId != null) {
								Concept stiValue = conceptService
										.getConcept(Integer.valueOf(stiId));
								Obs stiObs = Utils.createObservation(encDate,
										encLocation, patient, stiConc,
										stiValue, 4);
								stiObsGr.addGroupMember(stiObs);
							}
						}
						if (request.getParameter("stiDate_" + suffixId) != null
								&& !request.getParameter("stiDate_" + suffixId)
										.equals("")) {
							stiDateId = request.getParameter("stiDate_"
									+ suffixId);
							Concept stiDateConc = conceptService
									.getConcept(constan
											.getGP("dataentry.startDate"));
							if (stiDateId != null) {
								Date stiDateValue = sdf.parse(stiDateId);
								Obs stiDateObs = Utils.createObservation(
										encDate, encLocation, patient,
										stiDateConc, stiDateValue, 2);
								stiObsGr.addGroupMember(stiDateObs);
							}
						}
						if (stiId != null || stiDateId != null) {
							obss.add(stiObsGr);
						}
					}

				}
			}

			// current who hiv stage
			if (Utils.getConceptFromSet(whosList, "adult") != null) {
				Concept whoConcept = Utils.getConceptFromSet(whosList, "adult");
				Concept currentWhoConcept = conceptService
						.getConcept(Constants.WHO_STAGE);
				Obs currentWhoObs = Utils.createObservation(encDate,
						encLocation, patient, currentWhoConcept, whoConcept, 4);
				obss.add(currentWhoObs);
			}
			
			// saving encounter
			Encounter encounter = Utils.createEncounter(encDate, encProvider,
					encLocation, patient, encType, obss);

			if (Utils.isAdultInit(patient, "Adult") != true
					|| Utils.isAdultInit(patient, "Peds") != true) {
				encService.saveEncounter(encounter);
				mav.addObject("msg", "The " + encType.getName()
						+ " Form saved!");
			} else {
				mav.addObject("msg", "The " + encType.getName()
						+ " Form alread Exist, may be you should fill the "
						+ encType.getName() + " form");
			}

			if (encType.getEncounterTypeId() == Constants.ADULT_RETURN
					|| encType.getEncounterTypeId() == Constants.PEDS_RETURN) {
				encService.saveEncounter(encounter);
				mav.addObject("msg", "The " + encType.getName()
						+ " Form saved!");
			}

		}

		if (request.getParameter("conceptId") != null
				&& !request.getParameter("conceptId").equals("")) {
			String concepId = request.getParameter("conceptId");
			mav.addObject("concepId", concepId);
		}

		mav.addObject("conceptAnswers", opportunisticInfections);
		mav.addObject("systems", systems);
		mav.addObject("patient", patient);
		mav.addObject("vitalSigns", vitalSigns);
		mav.addObject("familyPlannings", familyPlannings);
		mav.addObject("tbResults", tbResults);
		mav.addObject("screenResults", screenResults);
		mav.addObject("constants", constan);
		mav.addObject("locations", locations);
		mav.addObject("relationshipTypes", relationshipTypes);
		mav.addObject("professions", professions);
		mav.addObject("civalStatus", civalStatus);
		mav.addObject("visitReasons", visitReasons);
		mav.addObject("nextVisitReasons", nextVisitReasons);
		mav.addObject("providerMap", providerMap);
		mav.addObject("modeOfAdmission", modeOfAdmission);
		mav.addObject("partnerHIVStatus", partnerHIVStatus);
		// mav.addObject("patientProgram", patientProgram);
		// mav.addObject("location", location);
		mav.addObject("treatmentResults", treatmentResults);
		mav.addObject("whoStages", whoStages);
		mav.addObject("relationships", relationships);
		mav.addObject("hivTestResults", hivTestResults);
		mav.addObject("encTypes", encTypes);
		mav.addObject("dftLoc", dftLoc);

		mav.addObject("currentWhoHivStage", currentWhoHivStage);
		mav.addObject("whoStage1", whoStage1);
		mav.addObject("whoStage2", whoStage2);
		mav.addObject("whoStage3", whoStage3);
		mav.addObject("whoStage4", whoStage4);
		mav.addObject("presentSymptom", presentSymptom);
		Map<String, Collection<ConceptSet>> whoMap = new HashMap<String, Collection<ConceptSet>>();

		whoMap.put("WHO Stage 1 Adult", whoStage1);
		whoMap.put("WHO Stage 2 Adult", whoStage2);
		whoMap.put("WHO Stage 3 Adult", whoStage3);
		whoMap.put("WHO Stage 4 Adult", whoStage4);

		mav.addObject("whoMap", whoMap);
		mav.addObject("now", new Date());

		mav.setViewName(getViewName());
		return mav;
	}
}
