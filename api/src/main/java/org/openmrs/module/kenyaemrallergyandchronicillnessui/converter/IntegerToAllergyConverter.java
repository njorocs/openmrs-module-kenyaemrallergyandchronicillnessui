package org.openmrs.module.kenyaemrallergyandchronicillnessui.converter;


import org.openmrs.activelist.Allergy;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.Allergen;
import org.openmrs.ui.framework.converter.util.ConversionUtil;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
@Component
public class IntegerToAllergyConverter implements Converter<Integer, Allergy> {

    @Override
    public org.openmrs.activelist.Allergy convert(Integer id) {
        PatientService patientService = Context.getService(PatientService.class);
        if (id == null) {
            return null;
        } else {
            return patientService.getAllergy(id);
        }
    }

}
