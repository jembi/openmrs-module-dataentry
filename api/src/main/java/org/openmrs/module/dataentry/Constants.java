package org.openmrs.module.dataentry;

import org.openmrs.api.context.Context;

public class Constants {
	
	public static final int CURED = 1746;
	public static final int CURRENTLY_IN_TREATMENT = 1432;
	public static final int UNKOWN = 1067;
	
	
	public static final int OPPORTUNISTIC_INFECTIONS_ID = 1607;
	public static final int STI = 174;
	
	public static final int SYSTEM_ID = 2139;
	public static final int VITAL_SIGNS = 1114;
	public static final int MODE_OF_ADMISSION = 1650;
	public static final int MAIN_ACTIVITY = 1304;
	public static final int CIVIL_STATUS = 1054;
	public static final int WHO_STAGE = 1480;
	public static final int FAMILY_PLANNING = 374;
	public static final int TB_TEST_RESULT = 3495;
	public static final int TB_SCREENING_RESULT = 2136;
	public static final int TB_TYPE = 1607;
	public static final int NIGHT_SWEATS_FOR_GREATER_THAN_3_WEEKS = 2163;
	public static final int NIGHT_SWEATS_FOR_LESS_THAN_3_WEEKS = 2164;
	public static final int REASON_FOR_VISIT = 6189;
	public static final int NEXT_REASON_FOR_VISIT = 8130;
	public static final int COHORT_ID = 4;
	public static final int PARTNER_HIV_STATUS = 3082;
	public static final int PERSON_PHONE_NUMBER = 1426;
	public static final int CONTACT_NAME = 1327;
	public static final int RELATIONSHIP_TO_CONTACT = 1328;
	public static final int RELATIONSHIP_OF_RELATIVE_TO_PATIENT = 2172;
	public static final int PHONE_NUMBER_OF_CONTACT = 6194;
	public static final int CHILDREN_OF_PATIENT = 2649;
	public static final String PROVIDER_ROLE = "Provider";
	public static final int NORMAL = 1115;
	public static final int UBNORMAL = 1116;
	public static final int ADULT_INITIAL = 1;
	public static final int ADULT_RETURN = 2;
	public static final int PEDS_INITIAL = 3;
	public static final int PEDS_RETURN = 4;

	public static final int REASON_ORDER_STOPPED = 1812;
	public static final int TB_PROGRAM = 1648;
	public static final int PMTCT_PROGRAM = 1647;
	public static final int HIV_PROGRAM = 1482;
	public static final int MALNUTRITION_PROGRAM = 2234;
	public static final int EXAMINATION_OF_SYSTEM_CONSTRUCT = 2138;
	public static final int PATIENT_TRANSFERRED = 2536;
	public static final int DATE_OF_TRANSFER = 1428;
	public static final int TRANSFER_FROM = 1427;
	public static final int FEVER = 1069;
	public static final int TB_CONTACT = 2133;
	public static final int WEIGHT_LOSS = 1293;
	public static final int SYM_PRES = 1293;
	public static final int COUGHT_DURATION = 5959;
	public static final int PREGNANCY_NUMBER = 5624;
	public static final int DELIVERED_CHILD_NUMBER = 1053;
	public static final int LIVING_CHILD_NUMBER = 2549;
	public static final int CLINICAL_IMPRESSION_COMMENTS = 1364;
	public static final int NEXT_VISIT_DATE = 5096;
	public static final int SYSTEM_FINDING = 1119;
	public static final int PREVIOUS_FAMILY_PLANNING_METHOD = 2722;
	public static final int HIV_TEST_DATE = 1837;
	public static final int REVIEW_OF_SYSTEM_GENERAL = 1609;
	public static final int RESULT_OF_HIV_TEST = 2169;
	public static final int DATE_OF_BIRTH = 6954;
	public static final int DATE_OF_DEATH = 1815;
	public static final int LABORATORY_EXAMINATIONS_CONSTRUCT = 1337;
	public static final String STR_PEDS_RETURN ="PEDSRETURN";
	public static final String STR_ADULT_RETURN ="ADULTRETURN";
	
	public static final int DRUG_ORDER_TYPE = 1;
	
	public static final int ADVERSE_EFFECTS = 1297;
	public static final int ADVERSE_ACTIONS = 1645;
	public static final int NEW_PROPHYLAXIS = 3117;
	
	public static final int CURRENT_WHO_HIV_STAGE = 5356;	
	public static final int PEDS_WHO_CATEGORY_QUERY = 1224;
	public static final int WHO_STAGE_1 = 1204;
	public static final int WHO_STAGE_2 = 1205;
	public static final int WHO_STAGE_3 = 1206;
	public static final int WHO_STAGE_4 = 1207;
	
	public static final int WHO_PEDS_1 = 1220;
	public static final int WHO_PEDS_2 = 1221;
	public static final int WHO_PEDS_3 = 1222;
	public static final int WHO_PEDS_4 = 1223;
	
	public static final int WHO_STAGES_CRITERIA_PRESENT = 2743;
	public static final int PEDS_WHO_SPECIFIC_CONDITION_QUERY = 1225;
	public static final int PHYSICAL_SYSTEM_COMMENT = 2188;
	public static final int DURATION_OF_SYMPTOM_IN_DAYS = 1294;
	public static final int DATE_OF_SURGERY = 3179;
	
	
	/**
	 * get Global property values
	 * 
	 * @param id
	 * @return
	 */
	public int getGP(String id) {
		return Integer.valueOf(Context.getAdministrationService().getGlobalProperty(id));
	}
}