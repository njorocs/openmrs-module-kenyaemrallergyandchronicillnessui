package org.openmrs.module.kenyaemrallergyandchronicillnessui.converter;

import org.openmrs.api.context.Context;
import org.openmrs.module.allergyapi.AllergyReaction;
import org.openmrs.module.allergyapi.api.PatientService;
import org.openmrs.ui.framework.converter.util.ConversionUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAllergyReactionConverter implements Converter<String, AllergyReaction> {

    @Override
    public AllergyReaction convert(String s) {
        PatientService patientService = Context.getService(PatientService.class);
        if (org.apache.commons.lang.StringUtils.isBlank(s)) {
            return null;
        } else if (ConversionUtil.onlyDigits(s)) {
            return patientService.getAllergyReactionById(Integer.valueOf(s));
        }
        return null;
    }

}
