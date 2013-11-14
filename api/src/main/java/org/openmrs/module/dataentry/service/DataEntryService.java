/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.service;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Relationship;
import org.openmrs.Role;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional
public interface DataEntryService {
	
	public Relationship getRelationshipByPatient(Patient patient);
	
//	public PatientProgram getPatientProgramByPatient(Patient patient);
//	
//	public void saveAdultEnrollmentData(Encounter encounter);
//	
//	public Role getRoleByRoleName(String role);

}
