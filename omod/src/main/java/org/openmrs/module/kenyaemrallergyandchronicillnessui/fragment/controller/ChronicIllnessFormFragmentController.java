package org.openmrs.module.kenyaemrallergyandchronicillnessui.fragment.controller;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.activelist.Allergy;
import org.openmrs.activelist.AllergySeverity;
import org.openmrs.activelist.AllergyType;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergies;
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

import static org.openmrs.module.kenyaemrallergyandchronicillnessui.fragment.controller.AllergyFormFragmentController.patientService;

public class ChronicIllnessFormFragmentController {
    public void controller(@FragmentParam (value = "patientId") Patient patientId,
                           @FragmentParam(value = "illnessId", required = false) ChronicIllness chronicIllness,
                           PageModel pageModel) {

       /* Allergy exists = allergy != null ? allergy : null;*/
        ChronicIllness exists = chronicIllness != null ? chronicIllness : null;
        pageModel.addAttribute("command", addUpdateChronicIllnessesForm(patientId, exists));
        pageModel.addAttribute("chronicIllnessesOptions", getChronicIllnessesOptions());
        pageModel.addAttribute("patient", patientId);
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

    public SimpleObject saveChronicIllness(@MethodParam("addUpdateChronicIllnessesForm") @BindParams EditChronicIllnessForm form, UiUtils ui) {
        ui.validate(form, form, null);
        ChronicIllness chronicIllness = form.save();
        return SimpleObject.create("patientId", chronicIllness.getPatient().getPatientId());

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
            ChronicIllness illness= Context.getService(CIService.class).saveChronicIllness(toSave);

            return illness;
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
