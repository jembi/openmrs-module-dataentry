package org.openmrs.module.dataentry;

public class LaboratoryTest {
	private int patientId;
	
	private int convSetConceptId;
	private int testConceptId;
	private int locationId;
	private String testResult;
	private String codedTestResultConceptId;
	private String testDate;
	private int providerId;
	private String encounterType;
	
	public String getEncounterType() {
		return encounterType;
	}
	public void setEncounterType(String encounterType) {
		this.encounterType = encounterType;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getCodedTestResultConceptId() {
		return codedTestResultConceptId;
	}
	public void setCodedTestResultConceptId(String codedTestResultConceptId) {
		this.codedTestResultConceptId = codedTestResultConceptId;
	}
	public int getConvSetConceptId() {
		return convSetConceptId;
	}
	public void setConvSetConceptId(int convSetConceptId) {
		this.convSetConceptId = convSetConceptId;
	}
	public int getTestConceptId() {
		return testConceptId;
	}
	public void setTestConceptId(int testConceptId) {
		this.testConceptId = testConceptId;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String dateOfTest) {
		this.testDate = dateOfTest;
	}
}
