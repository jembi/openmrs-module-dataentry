/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dataentry;


/**
 *
 */
public class GPConcepts {
	
	private String infectionSet;
	private String oppStartDate;
	private String oppEndDate;
	private String familySerology;
	private String stiSet;
	private String oppInfTreatRes;
	
    /**
	 * @return the oppInfTreatRes
	 */
	public String getOppInfTreatRes() {
		return oppInfTreatRes;
	}

	/**
	 * @param oppInfTreatRes the oppInfTreatRes to set
	 */
	public void setOppInfTreatRes(String oppInfTreatRes) {
		this.oppInfTreatRes = oppInfTreatRes;
	}

	/**
     * @return the infectionSet
     */
    public String getInfectionSet() {
    	return infectionSet;
    }
	
    /**
     * @param infectionSet the infectionSet to set
     */
    public void setInfectionSet(String infectionSet) {
    	this.infectionSet = infectionSet;
    }
	
    /**
     * @return the oppStartDate
     */
    public String getOppStartDate() {
    	return oppStartDate;
    }
	
    /**
     * @param oppStartDate the oppStartDate to set
     */
    public void setOppStartDate(String oppStartDate) {
    	this.oppStartDate = oppStartDate;
    }
	
    /**
     * @return the oppEndDate
     */
    public String getOppEndDate() {
    	return oppEndDate;
    }
	
    /**
     * @param oppEndDate the oppEndDate to set
     */
    public void setOppEndDate(String oppEndDate) {
    	this.oppEndDate = oppEndDate;
    }
	
    /**
     * @return the familySerology
     */
    public String getFamilySerology() {
    	return familySerology;
    }
	
    /**
     * @param familySerology the familySerology to set
     */
    public void setFamilySerology(String familySerology) {
    	this.familySerology = familySerology;
    }
	
    /**
     * @return the stiSet
     */
    public String getStiSet() {
    	return stiSet;
    }
	
    /**
     * @param stiSet the stiSet to set
     */
    public void setStiSet(String stiSet) {
    	this.stiSet = stiSet;
    }

}
