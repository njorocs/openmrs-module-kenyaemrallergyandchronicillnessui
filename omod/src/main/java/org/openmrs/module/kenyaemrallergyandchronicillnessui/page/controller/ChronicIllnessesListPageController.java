package org.openmrs.module.kenyaemrallergyandchronicillnessui.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergy;
import org.openmrs.module.allergyapi.api.PatientService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.CIDAO;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ChronicIllnessesListPageController {

    protected static final Log log = LogFactory.getLog(ChronicIllnessesListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUiUtils,
                           @RequestParam(value = "patientId") Patient patient,
                           UiUtils ui, PageModel model) {

        CIDAO cidao = Context.getService(CIDAO.class);
        List<ChronicIllness> chronicIllnesses = cidao.getChronicIllnessByPatient(patient);
        model.put("chronicIllnesses", chronicIllnesses);
        model.put("patient", patient);

    }
}
