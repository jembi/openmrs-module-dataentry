/**
 * Auto generated file comment
 */
package org.openmrs.module.dataentry.web.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Program;
import org.openmrs.api.PatientService;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataentry.Constants;
import org.openmrs.module.dataentry.utils.Utils;
import org.openmrs.web.controller.PortletController;

/**
 *
 */
public class PatientDataEntryController extends PortletController {



	/**
	 * @see org.openmrs.web.controller.PortletController#populateModel(javax.servlet.http.HttpServletRequest,
	 *      java.util.Map)
	 */
	@Override
	protected void populateModel(HttpServletRequest request,
			Map<String, Object> model) {
		ProgramWorkflowService pws = Context.getProgramWorkflowService();
		Program program = pws.getProgram(Constants.HIV_PROGRAM);
	    PatientService patientService = Context.getPatientService();

		Integer patientId = Integer.parseInt(request.getParameter("patientId"));

		Patient patient = patientService.getPatient(patientId);

		Collection<PatientProgram> patientPrograms = Context.getProgramWorkflowService()
				.getPatientPrograms(patient, program, null, null, null, null, false);
		
		model.put("patientId", patientId);
		model.put("patient", patient);
		if (patientPrograms.size() != 0) {
			model.put("notInProgram", "yes");
		}
		
		if (Utils.isAdultInit(patient, "Adult") == true) {
			model.put("alreadyAdultInit", "yes");
		}
		if (Utils.isAdultInit(patient, "Peds") == true) {
			model.put("alreadyPedInit", "yes");
		}

		super.populateModel(request, model);
	}
}
