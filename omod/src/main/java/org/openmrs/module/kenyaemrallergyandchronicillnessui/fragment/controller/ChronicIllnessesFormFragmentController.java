package org.openmrs.module.kenyaemrallergyandchronicillnessui.fragment.controller;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.activelist.Allergy;
import org.openmrs.activelist.AllergySeverity;
import org.openmrs.activelist.AllergyType;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergies;
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

public class ChronicIllnessesFormFragmentController {

    public void controller(@FragmentParam(value = "patientId") Patient patientId,
                           @FragmentParam(value = "allergyId", required = false) Allergy allergy,
                           PageModel pageModel) {

       /* Allergy exists = allergy != null ? allergy : null;*/
        Allergy exists = allergy != null ? allergy : null;
        pageModel.addAttribute("command", addUpdateAllergiesAndChronicIllnessesForm(patientId, exists));
        pageModel.addAttribute("allergenOptions", getAllergenOptions());
        pageModel.addAttribute("reactionOptions", getReactionOptions());
        pageModel.addAttribute("severityOptions", getSeverityOptions());
        pageModel.addAttribute("patient", patientId);
    }
    protected static PatientService patientService = null;

    protected List<SimpleObject> getAllergenOptions() {
        List<SimpleObject> allergenOptions = new ArrayList<SimpleObject>();

        for (Map.Entry<Integer, String> option : createAllergenOptions().entrySet())
            allergenOptions.add(SimpleObject.create("value", option.getKey(), "label", option.getValue()));

        return allergenOptions;
    }

    private Map<Integer, String> createAllergenOptions() {
        Map<Integer, String> allergenOptions = new HashMap<Integer, String>();
        allergenOptions.put(162543, "Beef");
        allergenOptions.put(72609, "Caffeine");
        allergenOptions.put(162544, "Chocolate");
        allergenOptions.put(162545, "Dairy Food");
        allergenOptions.put(162171, "Eggs");
        allergenOptions.put(162546, "Fish");
        allergenOptions.put(162547, "Milk Protein");
        allergenOptions.put(162172, "Peanuts");
        allergenOptions.put(162175, "Shellfish");
        allergenOptions.put(162176, "Soy");
        allergenOptions.put(162548, "Strawberries");
        allergenOptions.put(162177, "Wheat");
        allergenOptions.put(162542, "Adhesive Tape");
        allergenOptions.put(162536, "Bee Stings");
        allergenOptions.put(162537, "Dust");
        allergenOptions.put(162538, "Latex");
        allergenOptions.put(162539, "Mold");
        allergenOptions.put(162540, "Pollen");
        allergenOptions.put(162541, "Ragweed");
        allergenOptions.put(5622, "Other");
        return allergenOptions;
    }

    protected List<SimpleObject> getReactionOptions() {
        List<SimpleObject> reactionOptions = new ArrayList<SimpleObject>();
        for (Map.Entry<Integer, String> option : createReactionOptions().entrySet())
            reactionOptions.add(SimpleObject.create("value", option.getKey(), "label", option.getValue()));

        return reactionOptions;
    }

    private Map<Integer, String> createReactionOptions() {
        Map<Integer, String> reactionOptions = new HashMap<Integer, String>();
        reactionOptions.put(1067, "Unknown");
        reactionOptions.put(121629, "Anaemia");
        reactionOptions.put(148888, "Anaphylaxis");
        reactionOptions.put(148787, "Angioedema");
        reactionOptions.put(120148, "Arrhythmia");
        reactionOptions.put(108, "Bronchospasm");
        reactionOptions.put(143264, "Cough");
        reactionOptions.put(142412, "Diarrhea");
        reactionOptions.put(118773, "Dystonia");
        reactionOptions.put(140238, "Fever");
        reactionOptions.put(140039, "Flushing");
        reactionOptions.put(139581, "GI upset");
        reactionOptions.put(139084, "Headache");
        reactionOptions.put(159098, "Hepatotoxicity");
        reactionOptions.put(111061, "Hives");
        reactionOptions.put(117399, "Hypertension");
        reactionOptions.put(879, "Itching");
        reactionOptions.put(121677, "Mental status change");
        reactionOptions.put(159347, "Musculoskeletal pain");
        reactionOptions.put(121, "Myalgia");
        reactionOptions.put(512, "Rash");
        reactionOptions.put(5622, "Other");
        return reactionOptions;
    }

    protected List<SimpleObject> getSeverityOptions() {
        List<SimpleObject> severityOptions = new ArrayList<SimpleObject>();

        for (Map.Entry<Integer, String> option : createSeverityOptions().entrySet())
            severityOptions.add(SimpleObject.create("value", option.getKey(), "label", option.getValue()));

        return severityOptions;
    }

    private Map<Integer, String> createSeverityOptions() {
        Map<Integer, String> severityOptions = new HashMap<Integer, String>();
        severityOptions.put(160754, "Mild");
        severityOptions.put(160755, "Moderate");
        severityOptions.put(160756, "Severe");
        severityOptions.put(160758, "Fatal");
        severityOptions.put(1067, "Unknown");
        return severityOptions;
    }

    public SimpleObject saveAllergy(@MethodParam("addUpdateAllergiesAndChronicIllnessesForm") @BindParams EditAllergyAndChronicIllnessForm form, UiUtils ui) {
        ui.validate(form, form, null);
        Allergy allergy = form.save();
        /*Patient p= patientService.getPatient(2);*/

        return SimpleObject.create("patientId", allergy.getPerson().getPersonId());//allergy.get.getPatient().getId());

    }

    public EditAllergyAndChronicIllnessForm addUpdateAllergiesAndChronicIllnessesForm( @RequestParam(value = "patientId") Patient patient,@RequestParam(value = "allergyId", required = false) Allergy allergy) {
        if (allergy != null) {

            return new EditAllergyAndChronicIllnessForm(patient,allergy);
        } else {
            return new EditAllergyAndChronicIllnessForm(patient);
        }
    }

    public class EditAllergyAndChronicIllnessForm extends AbstractWebForm {

        private Allergy original;
        private Integer allergyId;
        private Patient patient;
        private Integer allergenConceptId;
        private String comment;
        Date onSetDate;
        /*private Integer codedAllergen;*/
        private AllergyType allergenType;
        private Allergies allergyType;
        private Concept reaction;
        private AllergySeverity severity;

        public EditAllergyAndChronicIllnessForm() {
        }

        public EditAllergyAndChronicIllnessForm(Patient patient) {
            this.patient = patient;
        }

        public EditAllergyAndChronicIllnessForm(Patient patient, Allergy allergy) {
            this.allergyId = allergy.getId();
            this.original = allergy;
            this.patient = patient;
            /*this.comment = allergy.get();*/
            //this.allergenId = allergy.getAllergen().getConceptId();
            this.allergenConceptId = allergy.getAllergen().getConceptId();
            this.severity = allergy.getSeverity();
            this.reaction = allergy.getReaction();
           /* this.codedAllergen = allergy.getAllergen().getConceptId();*/
            this.allergenType = allergy.getAllergyType();
        }

        @Override
        public Allergy save() {

            Allergy toSave;
            if (original != null) {
                toSave = original;
            } else {
                toSave = new Allergy();
            }
            toSave.setPerson(patient);
            toSave.setAllergen(Context.getConceptService().getConcept(allergenConceptId));
            /*toSave.getAllergen().getNonCodedAllergen();*/
            toSave.setReaction(reaction);
           // toSave.getAllergen().setNonCodedAllergen(allergen);
           // toSave.getAllergen().setAllergenType(allergenType);
            /* toSave.setComments(comment);*/
            toSave.setSeverity(severity);
            //PatientService patientService = null;

            patientService.saveAllergy(toSave);

            Context.getService(PatientService.class).saveAllergy(toSave);

            return toSave;
        }

        @Override
        public void validate(Object o, Errors errors) {

        }

      /*  public Integer getCodedAllergen() {
            return codedAllergen;
        }

        public void setCodedAllergen(Integer codedAllergen) {
            this.codedAllergen = codedAllergen;
        }*/

        public AllergyType getAllergenType() {
            return allergenType;
        }

        public void setAllergenType(AllergyType allergenType) {
            this.allergenType = allergenType;
        }

        public Concept getReaction() {
            return reaction;
        }

        public void setReaction(Concept reaction) {
            this.reaction = reaction;
        }

        public void setSeverity(AllergySeverity severity) {
            this.severity = severity;
        }

        public Allergy getOriginal() {
            return original;
        }

        public void setOriginal(Allergy original) {
            this.original = original;
        }

        public Allergy getAllergy() {
            return original;
        }

        public void setAllergy(Allergy allergy) {
            this.original = allergy;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public Integer getAllergyId() {
            return allergyId;
        }

        public void setAllergyId(Integer allergyId) {
            this.allergyId = allergyId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }


        public Date getOnSetDate() {
            return onSetDate;
        }

        public void setOnSetDate(Date onSetDate) {
            this.onSetDate = onSetDate;
        }

        public Allergies getAllergyType() {
            return allergyType;
        }

        public void setAllergyType(Allergies allergyType) {
            this.allergyType = allergyType;
        }

        public AllergySeverity getSeverity() {
            return severity;
        }

        public Integer getAllergenConceptId() {
            return allergenConceptId;
        }

        public void setAllergenConceptId(Integer allergenConceptId) {
            this.allergenConceptId = allergenConceptId;
        }
    }
}
