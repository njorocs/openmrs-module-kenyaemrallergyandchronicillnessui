package org.openmrs.module.kenyaemrallergyandchronicillnessui.page.controller;

import org.openmrs.Patient;
import org.openmrs.module.allergyapi.Allergy;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class AddUpdateChronicIllnesseFormPageController {

    public void controller(@RequestParam("patientId") Patient patient,
                           @RequestParam(value = "illnessId", required = false) ChronicIllness chronicIllness,
                           @RequestParam("returnUrl") String url,
                           PageModel model) {

        model.addAttribute("patient", patient);
        model.addAttribute("chronicIllness", chronicIllness);
        model.addAttribute("returnUrl", url);

    }
}
