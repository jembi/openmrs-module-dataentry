/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry;

import java.util.Date;
import java.util.List;

import org.openmrs.ConceptAnswer;

/**
 *
 */
public class Adherance {
	private Date dateDeVisite;
	private Boolean attendVisit;
	private double weight;
	private List<ConceptAnswer> adverseEffects;
	private int doseMissed;
	private int remainingTabs;
	private Date nextVisitDate;
	private List<ConceptAnswer> actions;
	
	/**
	 * @return the dateDeVisite
	 */
	public Date getDateDeVisite() {
		return dateDeVisite;
	}
	/**
	 * @param dateDeVisite the dateDeVisite to set
	 */
	public void setDateDeVisite(Date dateDeVisite) {
		this.dateDeVisite = dateDeVisite;
	}
	/**
	 * @return the attendVisit
	 */
	public Boolean getAttendVisit() {
		return attendVisit;
	}
	/**
	 * @param attendVisit the attendVisit to set
	 */
	public void setAttendVisit(Boolean attendVisit) {
		this.attendVisit = attendVisit;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the adverseEffects
	 */
	public List<ConceptAnswer> getAdverseEffects() {
		return adverseEffects;
	}
	/**
	 * @param adverseEffects the adverseEffects to set
	 */
	public void setAdverseEffects(List<ConceptAnswer> adverseEffects) {
		this.adverseEffects = adverseEffects;
	}
	/**
	 * @return the doseMissed
	 */
	public int getDoseMissed() {
		return doseMissed;
	}
	/**
	 * @param doseMissed the doseMissed to set
	 */
	public void setDoseMissed(int doseMissed) {
		this.doseMissed = doseMissed;
	}
	/**
	 * @return the remainingTabs
	 */
	public int getRemainingTabs() {
		return remainingTabs;
	}
	/**
	 * @param remainingTabs the remainingTabs to set
	 */
	public void setRemainingTabs(int remainingTabs) {
		this.remainingTabs = remainingTabs;
	}
	/**
	 * @return the nextVisitDate
	 */
	public Date getNextVisitDate() {
		return nextVisitDate;
	}
	/**
	 * @param nextVisitDate the nextVisitDate to set
	 */
	public void setNextVisitDate(Date nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
	/**
	 * @return the actions
	 */
	public List<ConceptAnswer> getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(List<ConceptAnswer> actions) {
		this.actions = actions;
	}
	
}
