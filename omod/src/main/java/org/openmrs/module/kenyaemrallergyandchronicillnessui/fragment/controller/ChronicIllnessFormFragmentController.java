package org.openmrs.module.kenyaemrallergyandchronicillnessui.fragment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.CIService;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.ChronicIllness;
import org.openmrs.module.kenyaemrallergyandchronicillnessui.api.db.CIDAO;
import org.openmrs.module.kenyaui.form.AbstractWebForm;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.MethodParam;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

public class ChronicIllnessFormFragmentController {
    protected static final Log log = LogFactory.getLog(ChronicIllnessFormFragmentController.class);
    public void controller(@RequestParam(value = "patientId") Patient patient,
                           @FragmentParam(value = "illnessId", required = false) ChronicIllness chronicIllness,
                           PageModel pageModel) {

        log.info("Patient == > "+patient);
        ChronicIllness exists = chronicIllness != null ? chronicIllness : null;
        pageModel.addAttribute("command", addUpdateChronicIllnessesForm(patient, exists));
        pageModel.addAttribute("chronicIllnessesOptions", getChronicIllnessesOptions());
        pageModel.addAttribute("patient", patient);
    }
    protected static CIDAO cidao = null;

    protected List<SimpleObject> getChronicIllnessesOptions() {
        List<SimpleObject> chronicIllnessesOptions = new ArrayList<SimpleObject>();

        for (Map.Entry<Integer, String> option : createchronicIllnessesOptions().entrySet())
            chronicIllnessesOptions.add(SimpleObject.create("value", option.getKey(), "label", option.getValue()));

        return chronicIllnessesOptions;
    }

    private Map<Integer, String> createchronicIllnessesOptions() {
        Map<Integer, String> chronicIllnessesOptions = new HashMap<Integer, String>();
        chronicIllnessesOptions.put(162543, "Beef");
        chronicIllnessesOptions.put(72609, "Caffeine");
        chronicIllnessesOptions.put(162544, "Chocolate");
        chronicIllnessesOptions.put(162545, "Dairy Food");
        chronicIllnessesOptions.put(162171, "Eggs");
        chronicIllnessesOptions.put(162546, "Fish");
        chronicIllnessesOptions.put(162547, "Milk Protein");
        chronicIllnessesOptions.put(162172, "Peanuts");
        chronicIllnessesOptions.put(162175, "Shellfish");
        chronicIllnessesOptions.put(162176, "Soy");
        chronicIllnessesOptions.put(162548, "Strawberries");
        return chronicIllnessesOptions;
    }

    public SimpleObject saveChronicIllness(@MethodParam("addUpdateChronicIllnessesForm") @BindParams EditChronicIllnessForm form, UiUtils ui) {
        ui.validate(form, form, null);
        ChronicIllness chronicIllness = form.save();

        return SimpleObject.create("id", chronicIllness.getPatient().getId());

    }

    public EditChronicIllnessForm addUpdateChronicIllnessesForm( @RequestParam(value = "patientId") Patient patient,@RequestParam(value = "id", required = false) ChronicIllness chronicIllness) {
        if (chronicIllness != null) {

            return new EditChronicIllnessForm(patient,chronicIllness);
        } else {
            return new EditChronicIllnessForm(patient);
        }
    }

    public class EditChronicIllnessForm extends AbstractWebForm {

        private ChronicIllness original;
        private Integer illnessId;
        private Patient patient;
        private Integer illnessConceptId;
        Date onSetDate;

        public EditChronicIllnessForm() {
        }

        public EditChronicIllnessForm(Patient patient) {
            this.patient = patient;
        }

        public EditChronicIllnessForm(Patient patient, ChronicIllness chronicIllness) {
            this.illnessId = chronicIllness.getId();
            this.original = chronicIllness;
            this.patient = patient;
            this.illnessConceptId = chronicIllness.getCodedIllness();
            this.onSetDate = chronicIllness.getOnsetDate();
        }

        @Override
        public ChronicIllness save() {

            ChronicIllness toSave;
            if (original != null) {
                toSave = original;
            } else {
                toSave = new ChronicIllness();
            }
            toSave.setPatient(patient);
            toSave.setCodedIllness(illnessConceptId);
            toSave.setOnsetDate(onSetDate);
            /*cidao.saveChronicIllness(toSave);*/
            Context.getService(CIService.class).saveChronicIllness(toSave);
            return toSave;
        }

        @Override
        public void validate(Object o, Errors errors) {

        }

             public ChronicIllness getOriginal() {
            return original;
        }

        public void setOriginal(ChronicIllness original) {
            this.original = original;
        }

        public Integer getIllnessId() {
            return illnessId;
        }

        public void setIllnessId(Integer illnessId) {
            this.illnessId = illnessId;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public Integer getIllnessConceptId() {
            return illnessConceptId;
        }

        public void setIllnessConceptId(Integer illnessConceptId) {
            this.illnessConceptId = illnessConceptId;
        }

        public Date getOnSetDate() {
            return onSetDate;
        }

        public void setOnSetDate(Date onSetDate) {
            this.onSetDate = onSetDate;
        }
    }
}
