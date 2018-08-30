package org.openmrs.module.kenyaemrallergyandchronicillnessui.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergy;
import org.openmrs.module.allergyapi.api.PatientService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.CIService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AppPage("kenyaemrchronicillnessesList.home")
public class AllergiesListPageController {

    protected static final Log log = LogFactory.getLog(AllergiesListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUiUtils,
                           @RequestParam(value = "patientId") Patient patient,
                           UiUtils ui, PageModel model) {

       /*PatientDAO patientDAO = Context.getService(PatientDAO.class);*/
        PatientService patientService = Context.getService(PatientService.class);
        CIService ciService = Context.getService(CIService.class);
        List<Allergy> allergies = patientService.getAllergies(patient);
        List<ChronicIllness> chronicIllnesses = ciService.getChronicIllnessByPatient(patient);
       /* List<Allergy> allergies = patientDAO.getAllergies(patientId);*/
        model.put("allergies", allergies);
        model.put("patient", patient);
        model.put("chronicIllnesses",chronicIllnessFormatter(kenyaUiUtils,chronicIllnesses));

    }

    private List<SimpleObject> chronicIllnessFormatter(KenyaUiUtils kenyaUi, List<ChronicIllness> chronicIllnesses) {
        List<SimpleObject> objects = new ArrayList<SimpleObject>();

        for(ChronicIllness chronicIllness : chronicIllnesses) {

            SimpleObject illnessObject = SimpleObject.create(


                    "codedIllness", formatChronicIllness(chronicIllness.getCodedIllness()),

                    "onsetDate",  kenyaUi.formatDate(chronicIllness.getOnsetDate())
                    );
            objects.add(illnessObject);

        }

        return objects;
    }

    private String formatChronicIllness(Integer typeId) {
        if (typeId == null) {
            return null;
        } else {
            return chronicIllnessesOptions().get(typeId);
        }
    }

    private Map<Integer, String>chronicIllnessesOptions() {
        Map<Integer, String> chronicIllnessesOptions = new HashMap<Integer, String>();
        chronicIllnessesOptions.put(149019, "Alzheimer's Disease and other Dementias");
        chronicIllnessesOptions.put(148432, "Arthritis");
        chronicIllnessesOptions.put(153754, "Asthma");
        chronicIllnessesOptions.put(159351, "Cancer");
        chronicIllnessesOptions.put(119270, "Cardiovascular diseases");
        chronicIllnessesOptions.put(120637, "Chronic Hepatitis");
        chronicIllnessesOptions.put(145438, "Chronic Kidney Disease");
        chronicIllnessesOptions.put(1295, "Chronic Obstructive Pulmonary Disease(COPD)");
        chronicIllnessesOptions.put(120576, "Chronic Renal Failure");
        chronicIllnessesOptions.put(119692, "Cystic Fibrosis");
        chronicIllnessesOptions.put(120291, "Deafness and Hearing impairment");
        chronicIllnessesOptions.put(119481, "Diabetes");
        chronicIllnessesOptions.put(118631, "Endometriosis");
        chronicIllnessesOptions.put(117855, "Epilepsy");
        chronicIllnessesOptions.put(117789, "Glaucoma");
        chronicIllnessesOptions.put(139071, "Heart Disease");
        chronicIllnessesOptions.put(115728, "Hyperlipidaemia");
        chronicIllnessesOptions.put(117399, "Hypertension");
        chronicIllnessesOptions.put(117321, "Hypothyroidism");
        chronicIllnessesOptions.put(151342, "Mental illness");
        chronicIllnessesOptions.put(133687, "Multiple Sclerosis");
        chronicIllnessesOptions.put(115115, "Obesity");
        chronicIllnessesOptions.put(114662, "Osteoporosis");
        chronicIllnessesOptions.put(117703, "Sickle Cell Anaemia");
        chronicIllnessesOptions.put(118976, "Thyroid disease");

        return chronicIllnessesOptions;
    }


}
