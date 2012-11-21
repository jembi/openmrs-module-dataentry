/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.Constants;

/**
 *
 */
public class Utils {
	
	/**
	 * returns the current entered obs from a list of specific <code>Obs</code>
	 * 
	 * @param numbers
	 * @return
	 */
	public static Integer biggestObsIdNubmer(List<Obs> numbers) {

		int tmp = 0, big = 0;
		for (Obs obs : numbers) {
			if (tmp < obs.getObsId()) {
				big = obs.getObsId();
				tmp = big;
			} else {
				big = tmp;
				tmp = big;
			}
		}
		return big;
	}

	/**
	 * Auto generated method comment
	 * 
	 * @param obsDatetime
	 *            The obs datetime
	 * @param loc
	 *            The location
	 * @param p
	 *            The Patient
	 * @param c
	 *            The concept concerned
	 * @param obsValue
	 *            The value of the obs
	 * @param obsValueType
	 *            The type of obs value - 1 Numeric; 2 Datetime; 3 Text; 4 Coded
	 * @return
	 */
	public static Obs createObservation(Date obsDatetime, Location loc,
			Person p, Concept c, Object obsValue, int obsValueType) {
		Obs o = new Obs();

		try {
			o.setDateCreated(new Date());
			o.setObsDatetime(obsDatetime);
			o.setLocation(loc);
			o.setPerson(p);
			o.setConcept(c);
			
			if (obsValueType == 1) {
				o.setValueNumeric((Double) obsValue);
			} else if (obsValueType == 2) {
				o.setValueDatetime((Date) obsValue);
			} else if (obsValueType == 3) {
				o.setValueText("" + obsValue);
			} else if (obsValueType == 4) {
				o.setValueCoded((Concept) obsValue);
			}
		} catch (Exception e) {
			System.out
					.println("An Error occured when trying to create an observation :\n");
			e.printStackTrace();
			o = null;
		}
		return o;
	}

	/**
	 * Auto generated method comment
	 * 
	 * @param encounterDate
	 *            Date of the encounter
	 * @param provider
	 *            Provider
	 * @param location
	 *            Location
	 * @param patient
	 *            Patient
	 * @param encounterType
	 *            The type of that encounter
	 * @param obsList
	 *            The list of obs related to that encounter
	 * @return
	 */
	public static Encounter createEncounter(Date encounterDate, User provider,
			Location location, Patient patient, EncounterType encounterType,
			List<Obs> obsList) {
		Encounter enc = new Encounter();

		try {
			enc.setDateCreated(new Date());
			enc.setEncounterDatetime(encounterDate);
			enc.setProvider(provider);
			enc.setLocation(location);
			enc.setPatient(patient);
			enc.setEncounterType(encounterType);

			for (Obs o : obsList) {
				if (null != o)
					enc.addObs(o);
				else
					System.out
							.println("An observation has not been saved because it was null.");
			}
		} catch (Exception e) {
			System.out
					.println("An Error occured when trying to create an encounter :\n");
			e.printStackTrace();
			enc = null;
		}
		return enc;
	}

	public static Obs createObsGr(Date obsDatetime, Location location,
			Person p, Date dateCreated, Concept concept) {
		Obs obs = new Obs();

		obs.setObsDatetime(obsDatetime);
		obs.setPerson(p);
		obs.setLocation(location);
		obs.setDateCreated(new Date());
		obs.setCreator(Context.getAuthenticatedUser());
		obs.setConcept(concept);

		return obs;
	}

	@SuppressWarnings("deprecation")
	public static boolean isAdultInit(Patient patient, String type) {

		boolean isTrue = false;

		EncounterService encService = Context.getEncounterService();
		List<Encounter> encounters = encService.getEncounters(patient);

		for (Encounter enc : encounters) {
			if (enc.getEncounterType().getEncounterTypeId() == Constants.ADULT_INITIAL
					|| enc.getEncounterType().getEncounterTypeId() == Constants.PEDS_INITIAL) {
				isTrue = true;
				break;
			}
		}
		return isTrue;
	}

	/**
	 * Retrieve a hashmap of concept answers (concept id, bestname) given the id
	 * of the coded concept question
	 * 
	 * @param codedConceptQuestionId
	 * @return
	 */
	public static HashMap<Integer, String> createCodedOptions(
			int codedConceptQuestionId) {
		HashMap<Integer, String> answersMap = new HashMap<Integer, String>();
		Concept questionConcept = Context.getConceptService().getConcept(
				codedConceptQuestionId);
		if (questionConcept != null) {
			for (ConceptAnswer ca : questionConcept.getAnswers()) {
				answersMap.put(ca.getAnswerConcept().getConceptId(), ca
						.getAnswerConcept().getDisplayString().toLowerCase(
								Context.getLocale()));
			}
		}
		return answersMap;
	}

	public static Concept getConceptFromSet(List<Concept> whosList,
			String whoType) {
		ConceptService conceptService = Context.getConceptService();
		int temp = 0, big = 0;
		List<ConceptSet> concSetList = null;
		Concept concept = null;
		List<Concept> concList = new ArrayList<Concept>();
		for (Concept conc : whosList) {
			concSetList = Context.getConceptService().getSetsContainingConcept(
					conc);
			for (ConceptSet cs : concSetList) {
				if (whoType.equals("adult")) {
					if (cs.getConceptSet().getConceptId() == Constants.WHO_STAGE_1) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_STAGE_2) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_STAGE_3) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_STAGE_4) {
						concList.add(cs.getConceptSet());
					}
				}
				if (whoType.equals("peds")) {
					if (cs.getConceptSet().getConceptId() == Constants.WHO_PEDS_1) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_PEDS_2) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_PEDS_3) {
						concList.add(cs.getConceptSet());
					}
					if (cs.getConceptSet().getConceptId() == Constants.WHO_PEDS_4) {
						concList.add(cs.getConceptSet());
					}
				}
			}

			for (Concept concepts : concList) {
				temp = concepts.getConceptId();
				if (big < temp) {
					big = temp;
				}
			}
		}
		concept = conceptService.getConcept(big);
		return concept;
	}

	public static List<EncounterType> getEncTypeList() {
		List<EncounterType> encTypeList = new ArrayList<EncounterType>();

		return null;
	}

}
