package org.openmrs.module.kenyaemrallergyandchronicillnessui.page.controller;

import org.openmrs.Patient;
import org.openmrs.module.allergyapi.Allergy;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class AddUpdateAllergiesFormPageController {

    public void controller(@RequestParam("patientId") Patient patient,
                           @RequestParam(value = "allergyId", required = false) Allergy allergy,
                           @RequestParam("returnUrl") String url,
                           PageModel model) {

        model.addAttribute("patient", patient);
        model.addAttribute("allergyId", allergy);
        model.addAttribute("returnUrl", url);

    }
}
