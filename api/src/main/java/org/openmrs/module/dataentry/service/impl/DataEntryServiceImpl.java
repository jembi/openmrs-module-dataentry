/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.service.impl;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Relationship;
import org.openmrs.Role;
import org.openmrs.module.dataentry.db.DataEntryDAO;
import org.openmrs.module.dataentry.service.DataEntryService;

/**
 *
 */
public class DataEntryServiceImpl implements DataEntryService {
	private DataEntryDAO dataEntryDAO;

	/**
	 * @return the dataEntryDAO
	 */
	public DataEntryDAO getDataEntryDAO() {
		return dataEntryDAO;
	}

	/**
	 * @param dataEntryDAO the dataEntryDAO to set
	 */
	public void setDataEntryDAO(DataEntryDAO dataEntryDAO) {
		this.dataEntryDAO = dataEntryDAO;
	}

	public Relationship getRelationshipByPatient(Patient patient) {		
		return dataEntryDAO.getRelationshipByPatient(patient);
	}

//	public PatientProgram getPatientProgramByPatient(Patient patient) {
//		return dataEntryDAO.getPatientProgramByPatient(patient);
//	}

//	public void saveAdultEnrollmentData(Encounter encounter) {
//		dataEntryDAO.saveAdultEnrollmentData(encounter);		
//	}

//	public Role getRoleByRoleName(String role) {
//		return dataEntryDAO.getRoleByRoleName(role);
//	}
	
	
}
