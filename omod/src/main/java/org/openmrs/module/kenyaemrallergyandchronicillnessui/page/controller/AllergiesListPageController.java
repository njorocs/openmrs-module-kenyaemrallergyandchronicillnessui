package org.openmrs.module.kenyaemrallergyandchronicillnessui.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergy;
import org.openmrs.module.allergyapi.api.PatientService;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@AppPage("kenyaemrallergiesandchronicillnessesList.home")
public class AllergiesListPageController {

    protected static final Log log = LogFactory.getLog(AllergiesListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUiUtils,
                           @RequestParam(value = "patientId") Patient patient,
                           UiUtils ui, PageModel model) {

       /*PatientDAO patientDAO = Context.getService(PatientDAO.class);*/
        PatientService patientService = Context.getService(PatientService.class);
        List<Allergy> allergies = patientService.getAllergies(patient);
       /* List<Allergy> allergies = patientDAO.getAllergies(patientId);*/
        model.put("allergies", allergies);
        model.put("patient", patient);

    }

}
