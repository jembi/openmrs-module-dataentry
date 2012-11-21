/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.db;

import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Relationship;
import org.openmrs.Role;

/**
 *
 */
public interface DataEntryDAO {
	
	public Relationship getRelationshipByPatient(Patient patient);
	
//	public PatientProgram getPatientProgramByPatient(Patient patient);
	
//	public void saveAdultEnrollmentData(Encounter encounter);
	
//	public Role getRoleByRoleName(String role);
}
