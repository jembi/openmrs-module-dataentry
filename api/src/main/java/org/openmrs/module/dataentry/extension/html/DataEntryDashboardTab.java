/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.extension.html;

import org.openmrs.module.web.extension.PatientDashboardTabExt;

/**
 *
 */
public class DataEntryDashboardTab extends PatientDashboardTabExt {

	/**
     * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getPortletUrl()
     */
    @Override
    public String getPortletUrl() {
	    return "patientDataEntryPortlet";
    }

	/**
     * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getRequiredPrivilege()
     */
    @Override
    public String getRequiredPrivilege() {
	    return "View Data Entry";
    }

	/**
     * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabId()
     */
    @Override
    public String getTabId() {
	    return "DataEntryTabId";
    }

	/**
     * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabName()
     */
    @Override
    public String getTabName() {
	    return "Data Entry";
    }

}
