package org.openmrs.module.dataentry.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

public class AdminList extends AdministrationSectionExt {

	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}

	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return "dataentry.title";
	}

	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("module/dataentry/adultEnrollment.form", "dataentry.title");
		map.put("module/dataentry/adultFollowup.form", "Adult Follow Up");
		map.put("module/dataentry/pediatricEnrollment.form", "Pediatric Enrollment Form");
		map.put("module/dataentry/pediatricFollowup.form", "Pediatric Follow Up");

		return map;
	}
	/**
     * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getRequiredPrivilege()
     */
    @Override
    public String getRequiredPrivilege() {
	    return "View Data Entry";
    }
}
